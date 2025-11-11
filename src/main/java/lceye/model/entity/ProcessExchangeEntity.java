package lceye.model.entity;

import jakarta.persistence.*;
import lceye.model.dto.ProcessExchangeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "process_exchange")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessExchangeEntity extends BaseTime{
    // 1. 테이블 설계
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private int pceno;
    @Column(columnDefinition = "enum('input', 'output') not null")
    private String pcedirection;
    @Column(columnDefinition = "double not null")
    private double pceamount;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "pcno", columnDefinition = "int unsigned")
    private ProcessInfoEntity processInfoEntity;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "fno", columnDefinition = "int unsigned")
    private FlowEntity flowEntity;

    // 2. toDto 생성
    public ProcessExchangeDto toDto(){
        return ProcessExchangeDto.builder()
                .pceno(this.pceno)
                .pcedirection(this.pcedirection)
                .pceamount(this.pceamount)
                .createdate(this.getCreatedate().toString())
                .updatedate(this.getUpdatedate().toString())
                .build();
    } // func end
} // class end