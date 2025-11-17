import Header from './components/layout/Header.jsx';
import ProjectLeftSection from "./pages/project/ProjectLeftSection";
import ProjectRightSection from "./pages/project/ProjectRightSection";
import '../assets/css/project.css';

export default function ProjectRouter(props) {
    return (
        <>
            <div className='header'>
                <Header />
            </div>
            <div className='projectSection'>
                <div className='projectLeftSection'>
                    <ProjectLeftSection />
                </div>
                <div className='projectRightSection'>
                    <ProjectRightSection />
                </div>
            </div>
        </>
    ) // return end
} // func end