package lceye.service;

import lceye.model.dto.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service @Transactional @RequiredArgsConstructor
public class ExchangeService {

    private final FileService fileService;
    private final JwtService jwtService;
    private final ProjectService projectService;
    private final TranslationService translationService;

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
        String projectNumber = String.valueOf(exchangeList.get("pjno"));
        String name = cno + "_" + projectNumber + "_exchange_";
        String fileName;
        int pjNumber = (int) exchangeList.get("pjno");
        String createdate = String.valueOf(exchangeList.get("createdate"));
        if (createdate != null && !createdate.equalsIgnoreCase("null") ){ // createdate 키의 값이 null이 아니면
            // createdate 키의 값을 파일명 형식에 맞게 형식 변환
            DateTimeFormatter change = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(createdate,change);
            exchangeList.put("updatedate",now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            fileName = name + dateTime.format(formatter);
            System.out.println("dateTime = " + dateTime);
        }else { // 비어있으면 파일명 형식에 맞게 현재 날짜시간을 변환
            exchangeList.put("createdate",now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            fileName = name + now.format(formatter);
            System.out.println("fileName = " + fileName);
        }// if end
        boolean result = fileService.writeFile("exchange",fileName,exchangeList);
        if (result){
            boolean results = projectService.updatePjfilename(fileName,pjNumber);
            if (results) return true;
        }// if end
        return false;
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
        System.out.println("clientInput = " + clientInput + ", token = " + token);
        // 1. 성능 개선: 클라이언트 입력을 Set으로 변환 (O(1) 검색)
        Set<String> inputSet = new HashSet<>(clientInput);
        // 2. 매칭 결과를 저장할 Map. Value를 List로 정의하여 중복 매칭 결과 저장
        Map<String, List<String>> requestMap = new HashMap<>();
        String companyNumber = String.valueOf(cno);
        // 파일 서비스 호출
        List<Map<String, Object>> list = fileService.filterFile(companyNumber);
        for (Map<String, Object> map : list) {
            Object obj = map.get("exchanges");
            if (obj instanceof List<?> rawList) {
                for (Object exchangeObj : rawList) {
                    if (exchangeObj instanceof Map exchange) {
                        Object pjeNameObj = exchange.get("pjename");
                        Object pNameObj = exchange.get("pname");

                        // pjeName이 String 타입이고, Set에 포함되는지 확인 (O(1) 검색)
                        if (pjeNameObj instanceof String pjeName && inputSet.contains(pjeName)) {
                            String pName = pNameObj != null ? pNameObj.toString() : "N/A";
                            // 매칭된 결과를 해당 Key의 리스트에 추가 (덮어쓰기 방지)
                            requestMap.computeIfAbsent(pjeName, k -> new ArrayList<>()).add(pName);
                        }// if end
                    }// if end
                }// for end
            }// if end
        }// for end

        // 최종 반환 타입인 Map<String, Object>에 맞게 리턴
        System.out.println("requestMap: " + requestMap);
        return new HashMap<>(requestMap);
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
        System.out.println("clientInput = " + clientInput + ", token = " + token);
        List<ProjectDto> projectDtos = projectService.findByMno(mno);
        // 1. 성능 개선: 클라이언트 입력을 Set으로 변환 (O(1) 검색)
        Set<String> inputSet = new HashSet<>(clientInput);
        // 2. 매칭 결과를 저장할 Map. Value를 List로 하여 중복 매칭 시 모든 결과를 저장
        Map<String, List<String>> requestMap = new HashMap<>();
        List<Integer> pjnoList = projectDtos.stream().map(ProjectDto::getPjno).toList();

        for (int pjno : pjnoList) {
            // 파일 서비스는 예외 처리 고려
            List<Map<String, Object>> list = fileService.filterFile(String.valueOf(pjno));
            for (Map<String, Object> map : list) {
                Object obj = map.get("exchanges");
                // 3. 타입 안전성 확보: 캐스팅 전에 검증
                if (obj instanceof List<?> rawList) {
                    for (Object exchangeObj : rawList) {
                        // 내부 요소가 Map인지 확인
                        if (exchangeObj instanceof Map exchange) {
                            Object pjeNameObj = exchange.get("pjename");
                            Object pNameObj = exchange.get("pname");

                            // pjeName이 문자열이고, clientInput Set에 포함되는지 확인
                            if (pjeNameObj instanceof String pjeName && inputSet.contains(pjeName)) {
                                String pName = pNameObj != null ? pNameObj.toString() : "N/A";

                                // 매칭된 결과를 리스트에 추가 (덮어쓰기 방지)
                                requestMap.computeIfAbsent(pjeName, k -> new ArrayList<>()).add(pName);
                            }// if end
                        }// if end
                    }// for end
                }// if end
            }// for end
        }// for end

        // 최종 반환 타입에 맞게 Map<String, Object>로 반환
        System.out.println("requestMap: " + requestMap);
        return new HashMap<>(requestMap);
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
        ProjectDto dto = projectService.findByPjno(pjno);
        System.out.println("dto = " + dto);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
        if (dto != null){
            String projectNumber = String.valueOf(pjno);
            String name = cno + "_" + projectNumber + "_exchange_";
            DateTimeFormatter change = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dto.getCreatedate(),change);
            String fileName = name + dateTime.format(formatter);
            boolean result = fileService.deleteFile(fileName,"exchange");
            System.out.println("result = " + result);
            if (result){
                boolean results = projectService.deletePjfilename(pjno);
                if (results) return true;
            }// if end
        }// if end
        return false;
    }// func end

}// class end
