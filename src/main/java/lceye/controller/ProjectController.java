package lceye.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lceye.model.dto.ProjectDto;
import lceye.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * [PJ-01] 프로젝트 등록
     * <p>
     * "프로젝트명, 기준양, 기준단위(uno), 프로젝트 설명"을 받아 저장한다.
     * <p>
     * - 참고: 작성자(mno)를 Redis에서 확인하여 함께 저장
     * @author OngTK
     */
    @PostMapping
    public ResponseEntity<?> saveProject(@CookieValue(value = "loginMember", required = false) String token,
                                         @RequestBody ProjectDto projectDto){
        System.out.println("ProjectController.saveProject");
        System.out.println("token = " + token + ", projectDto = " + projectDto);
        ProjectDto result = null;
        // todo OngTK 기능 구현
        // [1.1] 쿠키 내 토큰이 존재
        if(token!=null){
            result = projectService.saveProject(token,projectDto);
        } else {
            return ResponseEntity.status(403).body("로그인 토큰이 존재하지 않습니다.");
        }

        if( result.getPjno() >= 1){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(402).body("프로젝트 저장을 실패했습니다.");
        }
    } // func end

    /**
     * [PJ-02] 프로젝트 전체조회
     * <p>
     * 프로젝트를 전체 조회한다. 단 사이드바 출력용으로 최소한의 자료만 출력한다.
     * <p>
     * - 참고 : 권한을 확인하여 Manager, Admin인 경우, 작성자와 상관없이 해당 회사의 모든 프로젝트를 조회한다.
     * <p>
     * 권한이 Worker일 경우, 본인이 작성한 프로젝트만 조회한다.
     * @author OngTK
     */
    @GetMapping("/all")
    public ResponseEntity<?> readAllProject(@CookieValue(value = "loginMember", required = false) String token){
        System.out.println("ProjectController.readAllProject");
        System.out.println("token = " + token);

        return ResponseEntity.ok(projectService.readAllProject(token));
    } // func end

    /**
     * [PJ-04]
     */
    public ResponseEntity<?> readProject(){
        // todo OngTK 기능 구현
        return ResponseEntity.ok(true);
    } // func end

    /**
     * [PJ-05]
     */
    public ResponseEntity<?> updateProject(){
        // todo OngTK 기능 구현
        return ResponseEntity.ok(true);
    }

} // class end
