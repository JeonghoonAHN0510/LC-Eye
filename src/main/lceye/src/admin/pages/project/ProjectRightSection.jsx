import ProjectBasicInfo from "../../components/project/ProjectBasicInfo";
import ProjectInput from "../../components/project/ProjectInput";
import ProjectResult from "../../components/project/ProjectResult";

export default function ProjectRightSection(props){
    return(
        <>
        <div>
            저장 BUTTON <br />
            <ProjectBasicInfo/>
            <ProjectInput/>
            <ProjectResult/>
        </div>
        </>
    ) // return end
} // func end