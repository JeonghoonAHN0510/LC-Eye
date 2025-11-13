import Header from './components/layout/Header.jsx';
import ProjectLeftSection from "./pages/project/ProjectLeftSection";
import ProjectRightSection from "./pages/project/ProjectRightSection";

export default function ProjectRouter(props){
    return(
        <>
        <Header />
        <div style={{display:"flex", justifyContent:"space-evenly"}}>
            <ProjectLeftSection/>
            <ProjectRightSection/>
        </div>
        </>
    ) // return end
} // func end