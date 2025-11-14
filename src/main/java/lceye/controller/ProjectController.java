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
     * [PJ-02]
     */
    public ResponseEntity<?> readAllProjectForManager(){
        // todo OngTK 기능 구현
        return ResponseEntity.ok(true);
    } // func end

    /**
     * [PJ-03]
     */
    public ResponseEntity<?> readAllProjectForWorker(){
        // todo OngTK 기능 구현
        return ResponseEntity.ok(true);
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
