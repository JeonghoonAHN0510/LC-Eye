package lceye.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lceye.model.dto.MemberDto;
import lceye.service.JwtService;
import lceye.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final JwtService jwtService;

    /**
     * [MB-01] 로그인(login)
     * <p>
     * [아이디, 비밀번호]를 받아서 DB에 일치하는 회원이 존재한다면, Redis와 Cookie에 로그인 정보가 담긴 JWT 토큰을 저장한다.
     * <p>
     * 테스트 : {"mid":"admin", "mpwd":"1234"}
     * @param memberDto 아이디, 비밀번호가 담긴 Dto
     * @param response 요청한 회원의 HTTP 정보
     * @return 로그인을 성공한 회원의 Dto
     * @author AhnJH
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto memberDto, HttpServletResponse response){
        // 1. 입력받은 값을 Service에 전달하여 로그인 진행
        MemberDto result = memberService.login(memberDto);
        // 2. 로그인을 성공했다면
        if (result != null){
            // 3. 성공한 회원의 토큰을 쿠키에 저장 : loginMember
            Cookie cookie = new Cookie("loginMember", result.getToken());
            // 4. 쿠키 노출 및 탈취 방지
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(3600);     // 토큰 및 Redis의 유효시간이 1시간
            // 5. 생성한 쿠키를 클라이언트에게 반환
            response.addCookie(cookie);
        } // if end
        // 6. 최종적으로 결과 반환
        result.setToken(null);
        return ResponseEntity.ok(result);
    } // func end

    /**
     * [MB-02] 로그아웃(logout)
     * <p>
     * 요청한 회원의 로그인 정보를 Redis와 Cookie에서 제거한다.
     * @param token 요청한 회원의 token 정보
     * @param response 요청한 회원의 HTTP 정보
     * @return 로그아웃 성공 여부 - boolean
     * @author AhnJH
     */
    @GetMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(value = "loginMember", required = false) String token,
                                    HttpServletResponse response){
        // 1. 삭제 기능을 할 임시 쿠키 생성
        Cookie cookie = new Cookie("loginMember", null);
        // 2. 즉시 삭제 쿠키 설정
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        // 3. 임시 쿠키를 클라이언트에게 반환
        response.addCookie(cookie);
        // 4. Redis에 저장된 쿠키 삭제 진행 후 반환
        return ResponseEntity.ok(memberService.logout(token));
    } // func end

//    public ResponseEntity<?> getInfo(@CookieValue(value = "loginMember", required = false) String token){
//        // 1.
//    } // func end

    // 임시 로그인 확인용
    @Deprecated
    @GetMapping
    public ResponseEntity<?> tempGetMember(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("loginMember")){
                    String token = cookie.getValue();
                    boolean validateToken = jwtService.validateToken(token);
                    if (validateToken){
                        return ResponseEntity.ok("로그인 중입니다." + token);
                    } else {
                        return ResponseEntity.ok("유효하지 않은 토큰입니다.");
                    } // if end
                } // if end
            } // for end
        } // if end
        return ResponseEntity.ok("비로그인 중입니다.");
    } // func end
} // class end