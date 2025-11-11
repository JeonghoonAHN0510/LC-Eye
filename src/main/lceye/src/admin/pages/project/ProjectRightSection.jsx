import ProjectBasicInfo from "../../components/project/ProjectBasicInfo";
import ProjectExchange from "../../components/project/ProjectExchange.jsx";
import ProjectResult from "../../components/project/ProjectResult";

export default function ProjectRightSection(props){
    return(
        <>
        <div>
            저장 BUTTON <br />
            <ProjectBasicInfo/>
            <ProjectExchange/>
            <ProjectResult/>
        </div>
        </>
    ) // return end
} // func end