package lceye.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lceye.service.JwtService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    // 1. 기존 스프링 시큐리티 방식의 필터 커스텀
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        // 플러터 토큰 추출
        String authorizationHeader = request.getHeader("Authorization");
        // 헤더가 'Bearer '로 시작하는지 확인하고 토큰만 추출
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // "Bearer " (7글자) 이후의 문자열이 순수한 토큰임
        } else {
            if (request.getCookies() != null){                      // 쿠키가 존재한다면
                for (Cookie cookie : request.getCookies()){         // 모든 쿠키를 순회하면서
                    if (cookie.getName().equals("loginMember")){    // "loginMember" 쿠키가 존재하면
                        token = cookie.getValue();                  // 토큰에 해당 쿠키 저장
                    } // if end
                } // for end
            } // if end
        } // if end

        // 1-2. UsernamePasswordAuthenticationToken 재정의
        if (token != null && jwtService.validateToken(token)){      // 토큰이 존재하면서, 토큰이 유효하면
            int loginMno = jwtService.getMnoFromClaims(token);      // 회원번호 추출하기
            String loginRole = jwtService.getRoleFromClaims(token); // 회원권한 추출하기
            // 1-3. 스프링 시큐리티 전용 서명 만들기
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            loginMno,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + loginRole))
                    );
            // 1-4. 스프링 시큐리티가 사용할 수 있게 토큰 저장
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } // if end
        // 1-5. 다른 필터에서 해당 토큰필터를 호출할 수 있도록 허용
        filterChain.doFilter(request, response);
    } // func end
} // class end