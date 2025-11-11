package lceye.model.dto;

import lceye.model.entity.ProjectResultEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResultDto {
    // 1. 기본적인 정보
    private int pjrno;
    private String pjrdirection;
    private String pjramount;
    private int pjno;
    private int fno;
    private int uno;
    private String createdate;
    private String updatedate;

    // 2. 부가적인 정보

    // 3. toEntity 생성
    public ProjectResultEntity toEntity(){
        return ProjectResultEntity.builder()
                .pjrno(this.pjrno)
                .pjrdirection(this.pjrdirection)
                .pjramount(this.pjramount)
                .build();
    } // func end
} // class end