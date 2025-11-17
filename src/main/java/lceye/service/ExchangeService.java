package lceye.service;

import lceye.model.dto.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service @Transactional @RequiredArgsConstructor
public class ExchangeService {

    private final FileService fileService;
    private final JwtService jwtService;
    private final ProjectService projectService;

    /**
     * 투입물·산출물 저장/수정
     *
     * @param exchangeList 투입물·산출물
     * @param token 작성자 토큰
     * @return boolean
     * @author 민성호
     */
    public boolean saveInfo(Map<String, Object> exchangeList , String token){
        if (!jwtService.validateToken(token)) return false;
        int cno = jwtService.getCnoFromClaims(token);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
        // 파일 이름 형식 , cno_pjno_type_datetime(20251113_1600)
        String name = cno + "_" + exchangeList.get("pjno") + "_exchange_";
        String fileName;
        if (exchangeList.get("createdate") != null ){ // createdate 키의 값이 null이 아니면
            // createdate 키의 값을 파일명 형식에 맞게 형식 변환
            String createdate = (String) exchangeList.get("createdate");
            DateTimeFormatter change = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(createdate,change);
            fileName = name + dateTime.format(formatter);
        }else { // 비어있으면 파일명 형식에 맞게 현재 날짜시간을 변환
            fileName = name + now.format(formatter);
        }// if end
        return fileService.writeFile("exchange",fileName,exchangeList);
    }// func end

    /**
     * 로그인한 회원의 회사파일에서 일치하는 데이터 찾기
     *
     * @param clientInput 클라이언트가 입력한 투입물·산출물
     * @param token 로그인한 회원의 토큰
     * @return Map<String,Object>
     * @author 민성호
     */
    public Map<String,Object> autoMatchCno(List<String> clientInput , String token){
        if (!jwtService.validateToken(token)) return null;
        int cno = jwtService.getCnoFromClaims(token);
        Map<String,Object> requestMap = new HashMap<>();
        String companyNumber = ""+cno;
        List<Map<String,Object>> list = fileService.filterFile(companyNumber);
        for (Map<String , Object> map : list){
            Object obj = map.get("exchanges");
            if (obj instanceof List){
                List<Map<String,Object>> mapList = (List<Map<String,Object>>) obj;
                for (Map<String,Object> exchange : mapList){
                    for (String input : clientInput){
                        if (exchange.get("pjename").equals(input)){
                            requestMap.put(input,exchange.get("pname"));
                        }// if end
                    }// for end
                }// for end
            }// if end
        }// for end
        return requestMap;
    }// func end

    /**
     * 로그인한 회원의 작성파일에서 일치하는 데이터 찾기
     *
     * @param clientInput 클라이언트가 입력한 투입물·산출물
     * @param token 로그인한 회원의 토큰
     * @return Map<String,Object>
     * @author 민성호
     */
    public Map<String,Object> autoMatchPjno(List<String> clientInput , String token){
        if (!jwtService.validateToken(token)) return null;
        int mno = jwtService.getMnoFromClaims(token);
        List<ProjectDto> projectDtos = projectService.testGet(mno);
        Map<String,Object> requestMap = new HashMap<>();
        List<Integer> pjnoList = projectDtos.stream().map(ProjectDto::getPjno).toList();
        for (int pjno : pjnoList){
            List<Map<String,Object>> list = fileService.filterFile(pjno+"");
            for (Map<String,Object> map : list){
                Object obj = map.get("exchanges");
                if (obj instanceof List){
                    List<Map<String,Object>> mapList = (List<Map<String, Object>>) obj;
                    for (Map<String,Object> exchange : mapList){
                        for (String input : clientInput){
                            if (exchange.get("pjename").equals(input)){
                                requestMap.put(input,exchange.get("pname"));
                            }// if end
                        }// for end
                    }// for end
                }// if end
            }// for end
        }// for end
        return requestMap;
    }// func end

    /**
     * json 파일 삭제
     *
     * @param token 로그인한 회원의 토큰
     * @param pjno 삭제하는 프로젝트 번호
     * @return boolean
     * @author 민성호
     */
    public boolean clearIOInfo(String token , int pjno){
        if (!jwtService.validateToken(token)) return false;
        int cno = jwtService.getCnoFromClaims(token);
        ProjectDto dto = projectService.testPjnoGet(pjno);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
        if (dto != null){
            String projectNumber = String.valueOf(pjno);
            String name = cno + "_" + projectNumber + "_exchange_";
            DateTimeFormatter change = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dto.getCreatedate(),change);
            String fileName = name + dateTime.format(formatter);
            return fileService.deleteFile(fileName,"exchange");
        }// if end
        return false;
    }// func end

}// class end
