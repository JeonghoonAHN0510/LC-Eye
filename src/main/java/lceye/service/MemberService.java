package lceye.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

import jakarta.transaction.Transactional;
import lceye.model.dto.MemberDto;
import lceye.model.entity.MemberEntity;
import lceye.model.mapper.MemberMapper;
import lceye.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final JwtService jwtService;
    private final RedisTemplate<String, Object> memberTemplate;

    /**
     * [MB-01] 로그인(login)
     * <p>
     * [아이디, 비밀번호]를 받아서 DB에 일치하는 회원이 존재한다면, Redis와 Cookie에 로그인 정보가 담긴 JWT 토큰을 저장한다.
     * </p>
     * @param memberDto 아이디, 비밀번호가 담긴 Dto
     * @return 로그인을 성공한 회원의 Dto
     * @author AhnJH
     */
    public MemberDto login(MemberDto memberDto){
        // 1. 요청된 아이디와 비밀번호가 유효한지 확인
        MemberDto result = memberMapper.login(memberDto);
        // 2. 유효하지 않으면, null 반환
        if (result == null) return null;
        // 3. 유효하면, 로그인정보로 토큰 발급 : loginMno, loginCname, loginRole
        String token = jwtService.generateToken(result);
        result.setToken(token);
        // 4. 발급받은 토큰을 Redis에 저장 : 토큰의 유효시간이 1시간이기에 Redis에도 1시간 적용
        String key = "member:" + result.getMno();     // member:10001
        memberTemplate.opsForValue().set(key, token, Duration.ofHours(1));
        // 5. 최종적으로 토큰이 담긴 Dto 반환
        return result;
    } // func end

    /**
     * [MB-02] 로그아웃(logout)
     * <p>
     * 요청한 회원의 로그인 정보를 Redis와 Cookie에서 제거한다.
     * @param token 요청한 회원의 token 정보
     * @return Redis 제거 성공 여부 - boolean
     * @author AhnJH
     */
    public boolean logout(String token){
        // 1. 토큰트로부터 요청한 회원번호 추출하기
        int loginMno = jwtService.getMnoFromClaims(token);
        // 2. 회원번호를 토대로 토큰 key 생성
        String key = "member:" + loginMno;
        // 3. 요청한 로그인정보를 Redis에서 제거
        return memberTemplate.delete(key);
    } // func end
} // class end