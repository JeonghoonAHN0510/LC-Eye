package lceye.service;

import com.google.cloud.translate.v3.LocationName;
import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.TranslateTextResponse;
import com.google.cloud.translate.v3.TranslationServiceClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class TranslationService {

    /**
     * Google Translation API PROJECT ID
     *
     * @author 민성호
     */
    private static final String Project_id = "tidy-landing-478007-q3";

    /**
     * Google API 호출하여 클라이언트가 입력한 문자열을
     * 영어로 번역 요청 로직
     *
     * @param text 번역요청 문자열
     * @return String 번역된 문자열
     * @author 민성호
     */
    public String Translate(String text){
        try(TranslationServiceClient client = TranslationServiceClient.create()){
            // API 호출 경로 설정을 위해 프로젝트 ID와 리전(엔드포인트) 지정
            LocationName parent = LocationName.of(Project_id,"global");

            // 번역 요청 객체 생성
            TranslateTextRequest request = TranslateTextRequest.newBuilder()
                    .setParent(parent.toString())
                    .setMimeType("text/plain") // 순수 텍스트 유형 지정
                    .setSourceLanguageCode("ko") // 요청 언어
                    .setTargetLanguageCode("en")
                    .addContents(text)
                    .build();

            // API 호출
            TranslateTextResponse response = client.translateText(request);

            // 결과 추출
            if (response.getTranslationsCount() > 0){
                // 응답 객체에서 번역된 텍스트를 추출해서 반환
                return response.getTranslations(0).getTranslatedText();
            }// if end

            // 번역 결과가 없거나 실패
            return "번역 결과를 받아오지 못했습니다.";

        }catch (Exception e){
            e.printStackTrace();
            return "API 호출 중 오류 발생: " + e.getMessage();
        }// try end
    }// func end

} // class end

