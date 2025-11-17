package lceye.service;

import lceye.model.dto.CalculateResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LCICalculateService {

    /**
     * [LCI-01] LCI 계산하기
     */
    public boolean calcLCI(int pjno){
        // todo OngTK calcLCI 구현 필요




        return false;
    } // func end

    /**
     * [LCI-02] LCI 결과 개별 조회
     */
    public List<CalculateResultDto> readLCI(){
      // todo OngTK readLCI 구현 필요
        return null;
    } // func end

} // class end
