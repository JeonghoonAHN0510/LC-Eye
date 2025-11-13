package lceye.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service @Transactional @RequiredArgsConstructor
public class ExchangeService {

    private final FileService fileService;

    public boolean saveInfo(Map<String, Object> exchangeList , int cno){
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

}// class end
