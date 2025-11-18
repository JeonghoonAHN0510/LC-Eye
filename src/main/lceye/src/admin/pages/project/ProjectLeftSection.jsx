import axios from "axios";

export default function ProjectLeftSection(props) {
    
    // [1] 프로젝트 정보 불러오기
    const readAllProject = async () => {
        try {
            const r = await axios.get("http://localhost:8080/api/project/all",  { withCredentials: true });
            const d = r.data;
            console.log(d);
        } catch (e) {
            console.error("[에러발생 readAllproject]"+ e);
        }
    } // func end
    
    // 테이블 컬럼 정의: id는 데이터 키, title은 헤더 라벨, width는 최소 폭
    const columns = [
        { id: "no", title: "No", width: 30 },
        { id: "pjname", title: "프로젝트명", width: 90 },
        { id: "pjedsc", title: "프로젝트 설명", width: 150 },
        { id: "mno", title: "작성자", width: 260 },
        { id: "createdate", title: "생성일", width: 100 },
        { id: "updatedate", title: "수정일", width: 120 },
    ];

    return (
        <>
            <div>
                프로젝트 목록
            </div>
            <div>
                테이블 자리                    
                <button onClick={readAllProject}>불러오기</button>
            </div>
        </>
    ) // return end
} // func end