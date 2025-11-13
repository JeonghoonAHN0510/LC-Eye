import { useSelector } from 'react-redux';
import { Navigate } from "react-router-dom";
import headerLogo from '../../../assets/img/LC-Eye_HeaderLogo.svg';
import Button from '@mui/joy/Button';

export default function Header(props) {
    const { isLogin } = useSelector((state) => state.admin);
    console.log(isLogin);

    // 혹시 비로그인 상태인데 들어와졌으면, 메인페이지로 이동
    if (isLogin.isAuth != true) return <Navigate to="/" />
    return (
        <>
            <div className='imgBox'>
                <img src={headerLogo} alt="headerLogo" />
            </div>
            <div className='infoBox'>
                <span>{isLogin.mname}</span>
                ({
                    isLogin.role == "ADMIN" ? "시스템 관리자"
                        : isLogin.role == "MANAGER" ? "회사 관리자"
                            : "실무자"
                })님 <br />
                소속 : {isLogin.cname}

                로그아웃

            </div>
        </>
    ) // return end
} // func end