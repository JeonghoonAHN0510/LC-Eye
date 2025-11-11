package lceye.model.entity;

import jakarta.persistence.*;
import lceye.model.dto.ProjectResultDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project_result")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResultEntity extends BaseTime{
    // 1. 테이블 설계
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private int pjrno;
    @Column(columnDefinition = "enum('input', 'output') not null")
    private String pjrdirection;
    @Column(columnDefinition = "double not null")
    private String pjramount;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "pjno", columnDefinition = "int unsigned")
    private ProjectEntity projectEntity;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "fno", columnDefinition = "int unsigned")
    private FlowEntity flowEntity;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "uno", columnDefinition = "int unsigned")
    private UnitsEntity unitsEntity;

    // 2. toDto 생성
    public ProjectResultDto toDto(){
        return ProjectResultDto.builder()
                .pjrno(this.pjrno)
                .pjrdirection(this.pjrdirection)
                .pjramount(this.pjramount)
                .createdate(this.getCreatedate().toString())
                .updatedate(this.getUpdatedate().toString())
                .build();
    } // func end
} // class end