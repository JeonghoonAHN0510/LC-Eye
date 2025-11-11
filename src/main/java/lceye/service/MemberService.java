package lceye.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

import jakarta.transaction.Transactional;
import lceye.model.dto.MemberDto;
import lceye.model.entity.MemberEntity;
import lceye.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final RedisTemplate<String, Object> memberTemplate;

    /**
     * [MB-01] 로그인(login)
     * <p>
     * [아이디, 비밀번호]를 받아서 DB에 일치하는 회원이 존재한다면, Redis에 로그인 정보가 담긴 JWT 토큰을 저장한다.
     * </p>
     * @param memberDto 아이디, 비밀번호가 담긴 Dto
     * @return 로그인을 성공한 회원의 Dto
     * @author AhnJH
     */
    public MemberDto login(MemberDto memberDto){
        // 1. 요청된 아이디와 비밀번호가 유효한지 확인
        MemberEntity memberEntity = memberRepository.findByMidAndMpwd(memberDto.getMid(), memberDto.getMpwd());
        // 2. 유효하지 않으면, null 반환
        if (memberEntity == null) return null;
        // 3. 유효하면, 로그인정보로 토큰 발급 : loginMno, loginCno, loginRole
        String token = jwtService.generateToken(memberEntity.toDto());
        MemberDto result = memberEntity.toDto();
        result.setToken(token);
        // 4. 발급받은 토큰을 Redis에 저장 : 토큰의 유효시간이 1시간이기에 Redis에도 1시간 적용
        String key = "member:" + memberEntity.getMno();     // member:10001
        memberTemplate.opsForValue().set(key, token, Duration.ofHours(1));
        // 5. 최종적으로 토큰이 담긴 Dto 반환
        return result;
    } // func end
} // class end