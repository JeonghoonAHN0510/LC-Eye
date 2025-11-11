package lceye.model.entity;

import jakarta.persistence.*;
import lceye.model.dto.ProjectExchangeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project_exchange")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectExchangeEntity extends BaseTime{
    // 1. 테이블 설계
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private int pjeno;
    @Column(columnDefinition = "enum('input', 'output') not null")
    private String pjedirection;
    @Column(columnDefinition = "varchar(100) not null")
    private String pjename;
    @Column(columnDefinition = "double not null")
    private double pjeamount;
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
    @JoinColumn(name = "uno", columnDefinition = "int unsigned")
    private UnitsEntity unitsEntity;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "pcno", columnDefinition = "int unsigned")
    private ProcessInfoEntity processInfoEntity;

    // 2. toDto 생성
    public ProjectExchangeDto toDto(){
        return ProjectExchangeDto.builder()
                .pjeno(this.pjeno)
                .pjedirection(this.pjedirection)
                .pjename(this.pjename)
                .pjeamount(this.pjeamount)
                .createdate(this.getCreatedate().toString())
                .updatedate(this.getUpdatedate().toString())
                .build();
    } // func end
} // class end