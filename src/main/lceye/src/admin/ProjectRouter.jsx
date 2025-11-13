import Header from './components/layout/Header.jsx';
import ProjectLeftSection from "./pages/project/ProjectLeftSection";
import ProjectRightSection from "./pages/project/ProjectRightSection";
import '../assets/css/header.css';

export default function ProjectRouter(props) {
    return (
        <>
            <div className='header'>
                <Header />
            </div>
            <div style={{ display: "flex", justifyContent: "space-evenly" }}>
                <ProjectLeftSection />
                <ProjectRightSection />
            </div>
        </>
    ) // return end
} // func end