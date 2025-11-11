package lceye.model.dto;

import lceye.model.entity.ProjectExchangeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectExchangeDto {
    // 1. 기본적인 정보
    private int pjeno;
    private String pjedirection;
    private String pjename;
    private double pjeamount;
    private int pjno;
    private int uno;
    private int pcno;
    private String createdate;
    private String updatedate;

    // 2. 부가적인 정보

    // 3. toEntity 생성
    public ProjectExchangeEntity toEntity(){
        return ProjectExchangeEntity.builder()
                .pjeno(this.pjeno)
                .pjedirection(this.pjedirection)
                .pjename(this.pjename)
                .pjeamount(this.pjeamount)
                .build();
    } // func end
} // class end