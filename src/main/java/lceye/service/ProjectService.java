package lceye.service;

import lceye.model.dto.ProjectDto;
import lceye.model.entity.MemberEntity;
import lceye.model.entity.ProjectEntity;
import lceye.model.repository.MemberRepository;
import lceye.model.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ProjectService {
    private final JwtService jwtService;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    /**
     * [PJ-01] 프로젝트 등록
     */
    public ProjectDto saveProject(String token, ProjectDto projectDto){
        System.out.println("ProjectService.saveProject");
        System.out.println("token = " + token + ", projectDto = " + projectDto);

        // [1.1] Token이 없으면
        if(!jwtService.validateToken(token)) return projectDto;

        // [1.2] 토큰이 존재한다면, 토큰에서 mno(작성자) 정보를 추출
        int mno = jwtService.getMnoFromClaims(token);

        // [1.3] 부가 entity _ MemberEntity, UnitsEntity 생성
        MemberEntity memberEntity = memberRepository.getReferenceById(mno);
        // todo OngTK unitsEntity 관련 처리 필요

        // [1.3] dto > entity
        ProjectEntity projectEntity = projectDto.toEntity();
        projectEntity.setMemberEntity(memberEntity);

        projectRepository.save(projectEntity);

        return projectEntity.toDto();
    } // func end


} // class end
