package lceye.controller;

import lceye.service.ExchangeService;
import lceye.service.FileService;
import lceye.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/api/inout")
@RequiredArgsConstructor
public class ExchangeController {

    /**
     * 서비스 불러오기
     */
    private final TranslationService translationService;
    private final ExchangeService exchangeService;

    @PostMapping("/auto") // localhost:8080/api/inout/auto
    public ResponseEntity<?> test(){
        String text = "경유";
        return ResponseEntity.ok(translationService.Translate(text));
    }// func end

    /**
     * 투입물·산출물 저장/수정
     *
     * @param token 로그인 토큰
     * @param map 투입물·산출물 정보
     * @return boolean
     */
    @PostMapping
    public ResponseEntity<?> saveIOInfo(@CookieValue(value = "loginMember", required = false) String token,
                                        @RequestBody Map<String,Object> map){
        return ResponseEntity.ok(exchangeService.saveInfo(map,token));
    }// func end

    @PostMapping("/auto")
    public ResponseEntity<?> matchIO(@CookieValue(value = "loginMember", required = false) String token,
                                     @RequestBody List<String> inputList){
        Map<String,Object> pjnoMap = exchangeService.autoMatchPjno(inputList,token);
        Map<String,Object> cnoMap = exchangeService.autoMatchCno(inputList,token);
        if (pjnoMap != null && !pjnoMap.isEmpty()){
            return ResponseEntity.ok(pjnoMap);
        } else if (cnoMap != null && !cnoMap.isEmpty()) {
            return ResponseEntity.ok(cnoMap);
        }// if end
        return ResponseEntity.status(404).body(null);
    }// func end

}// class end
