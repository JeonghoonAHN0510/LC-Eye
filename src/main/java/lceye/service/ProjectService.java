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
     * [PJ-02] 프로젝트 전체조회
     */
    public List<ProjectDto> readAllProject(String token){
        // [1.1] Token이 없으면 null로 반환
        if(!jwtService.validateToken(token)) return null;
        // [1.2] Token이 있으면, 토큰에서 로그인한 사용자 정보 추출
        int mno = jwtService.getMnoFromClaims(token);
        MemberEntity memberEntity = memberRepository.getReferenceById(mno);
        String mrole = jwtService.getRoleFromClaims(token);
        // [1.3] mrole(역할)에 따른 서로 다른 조회 구현
        // [1.3.1] mrole = admin or manager : cno 기반 프로젝트 전체 조회
        if(mrole.equals("ADMIN") || mrole.equals("MANAGER")){
            return projectRepository.searchCno(memberEntity.getCompanyEntity().getCno()).stream().map(ProjectEntity :: toDto).toList();
        }
        // [1.3.2] mrole = worker : mno 기반 본인이 작성한 프로젝트만 조회
        if(mrole.equals("WORKER")){
            return projectRepository.findByMemberEntity(memberEntity).stream().map(ProjectEntity :: toDto).toList();
        }
        return null;
    } // func end


} // class end
