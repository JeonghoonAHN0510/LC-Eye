import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./admin/components/Header";
import LoginRouter from './admin/LoginRouter.jsx';
import ProjectRouter from "./admin/ProjectRouter.jsx";

export default function App(props){
    return(
        <>
        <BrowserRouter>
            <Header/>
            <Routes>
                <Route path="/*" element={<LoginRouter/>}/>
                <Route path="/project/*" element={<ProjectRouter/>}/>
            </Routes>
        </BrowserRouter>
        </>
    ) // return end
} // func end