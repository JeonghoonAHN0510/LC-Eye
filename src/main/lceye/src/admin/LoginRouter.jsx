import LoingLeftSection from "./pages/login/LoginLeftSection";
import LoingRightSection from "./pages/login/LoginRightSection";

export default function LoginRouter(props){
    return(
        <>
        <div className="loginRouter">
            <LoingLeftSection/>
            <LoingRightSection/>
        </div>
        </>
    ) // return end
} // func end