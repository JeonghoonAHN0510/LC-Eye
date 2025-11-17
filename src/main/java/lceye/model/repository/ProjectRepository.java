package lceye.model.repository;

import lceye.model.entity.MemberEntity;
import lceye.model.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {

    /**
     * [PJ-02-01] 프로젝트 전체 조회중 mno 기반 전체 조회
     */
    List<ProjectEntity> findByMemberEntity(MemberEntity memberEntity);


} // interface end
