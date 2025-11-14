package lceye.controller;

import lceye.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * [PJ-01]
     */
    public ResponseEntity<?> saveProject(){
        // todo OngTK 기능 구현
        return ResponseEntity.ok(true);
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
