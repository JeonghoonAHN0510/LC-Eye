package lceye.controller;

import lceye.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/api/inout")
@RequiredArgsConstructor
public class ExchangeController {

    /**
     * 서비스 불러오기
     */
    private final TranslationService translationService;

    @PostMapping("/auto") // localhost:8080/api/inout/auto
    public ResponseEntity<?> test(){
        String text = "경유";
        return ResponseEntity.ok(translationService.Translate(text));
    }// func end

}// class end
