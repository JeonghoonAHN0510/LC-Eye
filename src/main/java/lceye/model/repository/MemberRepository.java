package lceye.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lceye.model.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
    // 1. 요청된 로그인의 유효성 체크
    MemberEntity findByMidAndMpwd(String mid, String mpwd);
} // interface end