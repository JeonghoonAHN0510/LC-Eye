import LoingLeftSection from "./pages/login/LoginLeftSection";
import LoingRightSection from "./pages/login/LoginRightSection";

export default function LoginRouter(props){
    return(
        <>
        <div style={{display:"flex", justifyContent:"space-evenly"}}>
            <LoingLeftSection/>
            <LoingRightSection/>
        </div>
        </>
    ) // return end
} // func end