import { useEffect, useState } from "react";
import axios from "axios";
import ProjectListTable from "../../components/project/ProjectListTable.jsx";
import "../../../assets/css/projectLeftSessionBox.css";

export default function ProjectLeftSection(props) {
    // [0] 상태관리
    const [projects, setProjects] = useState([]);

    useEffect(() => {
        // 컴포넌트가 마운트될 때 프로젝트 정보를 불러옵니다.
        readAllProject();
    }, []);

    // [1] 프로젝트 정보 불러오기
    const readAllProject = async () => {
        try {
            const r = await axios.get("http://localhost:8080/api/project/all", { withCredentials: true });
            const d = r.data;
            console.log(d)
            setProjects(d);
        } catch (e) {
            console.error("[에러발생 readAllproject]" + e);
        }
    } // func end

    // ProjectListTable 테이블 컬럼 정의
    // id는 데이터 키, title은 헤더 라벨, width는 최소 폭
    const columns = [
        { id: "no", title: "No", width: 30 },
        { id: "pjname", title: "프로젝트명", width: 500 },
        { id: "pjdesc", title: "프로젝트 설명", width: 150 },
        { id: "mname", title: "작성자", width: 260 },
        { id: "createdate", title: "생성일", width: 100 },
    ];

    return (
        <>
            <div className="projectLeftSessionBox">
                <h2>
                    프로젝트 목록
                </h2>
                <div>
                    <ProjectListTable
                        columns={columns}
                        data={projects}
                        rememberKey="ProjectListTable"     // 컬럼 너비 로컬 스토리지 키(옵션)
                        onRowClick={(row) => console.log("row 클릭:", row)}  // 필요 없으면 제거
                    />
                </div>
            </div>
        </>
    ) // return end
} // func end