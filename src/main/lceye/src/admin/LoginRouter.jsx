import LoingLeftSection from "./pages/login/LoginLeftSection";
import LoingRightSection from "./pages/login/LoginRightSection";
import { useSelector } from "react-redux"

export default function LoginRouter(props){
    const { isLogin } = useSelector((state) => state.admin);
    if (isLogin.role != null) location.href="/project"


    return(
        <>
        <div className="loginRouter">
            <LoingLeftSection/>
            <LoingRightSection/>
        </div>
        </>
    ) // return end
} // func end