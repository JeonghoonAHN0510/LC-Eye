package lceye.service;

import lceye.model.dto.ProjectDto;
import lceye.model.entity.MemberEntity;
import lceye.model.entity.ProjectEntity;
import lceye.model.entity.UnitsEntity;
import lceye.model.repository.MemberRepository;
import lceye.model.repository.ProjectRepository;
import lceye.model.repository.UnitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ProjectService {
    private final JwtService jwtService;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final UnitsRepository unitsRepository ;
    /**
     * [PJ-01] 프로젝트 등록
     */
    public ProjectDto saveProject(String token, ProjectDto projectDto){
        System.out.println("ProjectService.saveProject");
        System.out.println("token = " + token + ", projectDto = " + projectDto);

        // [1.1] Token이 없으면
        if(!jwtService.validateToken(token)) return projectDto; // PK 발급 안되고 종료
        // [1.2] 토큰이 존재한다면, 토큰에서 mno(작성자) 정보를 추출
        int mno = jwtService.getMnoFromClaims(token);
        // [1.3] 부가 entity _ MemberEntity, UnitsEntity 가져오기
        MemberEntity memberEntity = memberRepository.getReferenceById(mno);
        UnitsEntity unitsEntity = unitsRepository.getReferenceById(projectDto.getUno());
        // [1.4] dto > entity
        ProjectEntity projectEntity = projectDto.toEntity();
        projectEntity.setMemberEntity(memberEntity);
        projectEntity.setUnitsEntity(unitsEntity);
        // [1.4] entity 저장
        projectRepository.save(projectEntity);
        // [1.5] 결과 반환
        return projectEntity.toDto();
    } // func end

    /**
     * 회원번호로 프로젝트 조회
     *
     * @param mno 회원번호
     * @return List<ProjectDto>
     * @author 민성호
     */
    public List<ProjectDto> findByMno(int mno ){
        List<ProjectEntity> entities = projectRepository.findByMno(mno);
        List<ProjectDto> dtoList = entities.stream().map(ProjectEntity::toDto).toList();
        return dtoList;
    }// func end

    /**
     * 프로젝트 번호로 조회
     *
     * @param pjno 프로젝트 번호
     * @return ProjectDto
     * @author 민성호
     */
    public ProjectDto findByPjno(int pjno){
        Optional<ProjectEntity> optional = projectRepository.findById(pjno);
        if (optional.isPresent()){
            ProjectEntity entity = optional.get();
            return entity.toDto();
        }// if end
        return null;
    }// func end

    /**
     * 프로젝트파일명 추가
     *
     * @param fileName 프로젝트파일명
     * @param pjno 프로젝트 번호
     * @return boolean
     * @author 민성호
     */
    public boolean updatePjfilename(String fileName , int pjno){
        Optional<ProjectEntity> optional = projectRepository.findById(pjno);
        if (optional.isPresent()){
            ProjectEntity entity = optional.get();
            entity.setPjfilename(fileName);
            return true;
        }// if end
        return false;
    }// func end



} // class end
