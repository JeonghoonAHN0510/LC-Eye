import { BrowserRouter, Route, Routes } from "react-router-dom";
import LoginRouter from './admin/LoginRouter.jsx';
import ProjectRouter from './admin/ProjectRouter.jsx';
import RoleRoute from "./admin/components/RoleRoute.jsx";

export default function App(props){
    return(
        <>
        <BrowserRouter>
            <Routes>
                {/* 모든 권한 접근 가능 */}
                
                {/* 로그인한 사람만 접근 가능 */}
                <Route element={<RoleRoute roles={["ADMIN", "MANAGER", "WORKER"]}/>}>
                    <Route path="/project/*" element={<ProjectRouter />}/>
                    <Route path="/*" element={<LoginRouter/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
        </>
    ) // return end
} // func end