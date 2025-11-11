package lceye.model.dto;

import lceye.model.entity.ProcessExchangeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessExchangeDto {
    // 1. 기본적인 정보
    private int pceno;
    private int pcno;
    private int fno;
    private String pcedirection;
    private double pceamount;
    private String createdate;
    private String updatedate;

    // 2. 부가적인 정보

    // 3. toEntity 생성
    public ProcessExchangeEntity toEntity(){
        return ProcessExchangeEntity.builder()
                .pceno(this.pceno)
                .pcedirection(this.pcedirection)
                .pceamount(this.pceamount)
                .build();
    } // func end
} // class end