package lceye.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

import jakarta.transaction.Transactional;
import lceye.model.entity.ProjectResultFileEntity;
import lceye.model.repository.ProjectResultFileRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectResultFileService {
    private final ProjectResultFileRepository projectResultFileRepository;

    /**
     * 프로젝트 번호를 받아, 해당하는 프로젝트의 결과파일명을 반환
     * @param pjno 프로젝트 번호
     * @return 프로젝트 결과파일명
     * @author AhnJH
     */
    public String getFileName(int pjno){
        return projectResultFileRepository.returnFilename(pjno);
    } // func end

    public void deleteFileName(int pjno){
        Optional<ProjectResultFileEntity> optional = projectResultFileRepository.findById(pjno);
        if (optional.isPresent()){
            ProjectResultFileEntity projectResultFileEntity = optional.get();
            projectResultFileEntity.setPrfname(null);
        } // if end
    } // func end
} // class end