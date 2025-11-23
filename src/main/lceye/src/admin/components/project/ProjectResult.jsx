import { useEffect, useState } from "react";
import axios from "axios";
import "../../../assets/css/ProjectResult.css";
import { Pagination } from "@mui/material";
import { Select, Option } from "@mui/joy";
import ProjectListTable from "./ProjectListTable.jsx";
import { useLoading } from "../../contexts/LoadingContext.jsx";
import Button from '@mui/joy/Button';

export default function ProjectResult(props) {
    const { pjno, isOpen } = props;
    const { showLoading, hideLoading } = useLoading();

    const [lciInputData, setLciInputData] = useState([]);
    const [lciOutputData, setLciOutputData] = useState([]);
    const [rowsPerPage, setRowsPerPage] = useState(100);
    const [isDownloading, setIsDownloading] = useState(false);

    const [inputPage, setInputPage] = useState(1);
    const [outputPage, setOutputPage] = useState(1);
    const [inputPageInput, setInputPageInput] = useState("1");
    const [outputPageInput, setOutputPageInput] = useState("1");

    // LCI 데이터 조회 ================================================
    const readLCI = async (pjnoParam) => {
        if (!pjnoParam) return;
        const loadingId = showLoading("로딩중입니다.");
        try {
            const res = await axios.get("http://localhost:8080/api/lci", {
                params: { pjno: pjnoParam },
                withCredentials: true,
            });

            const inputListRaw = Array.isArray(res?.data?.inputList)
                ? res.data.inputList
                : [];
            const outputListRaw = Array.isArray(res?.data?.outputList)
                ? res.data.outputList
                : [];


            const sortByFnameAsc = (arr) =>
                [...arr].sort((a, b) =>
                    String(a?.fname ?? "").localeCompare(String(b?.fname ?? ""))
                );

            const inputList = sortByFnameAsc(inputListRaw);
            const outputList = sortByFnameAsc(outputListRaw);

            setLciInputData(inputList);
            setLciOutputData(outputList);

            setInputPage(1);
            setOutputPage(1);
            setInputPageInput("1");
            setOutputPageInput("1");
        } catch (error) {
            console.error("[readLCI error]", error);
            setLciInputData([]);
            setLciOutputData([]);
        } finally {
            hideLoading(loadingId);
        }
    }; // func end

    // pjno 변경 시 LCI 데이터 재조회 ==================================
    useEffect(() => {
        if (isOpen && pjno) {
            readLCI(pjno);
        }
    }, [isOpen, pjno]);

    // 페이징 처리 ===================================================
    const totalInputPages =
        rowsPerPage > 0 && lciInputData.length > 0
            ? Math.ceil(lciInputData.length / rowsPerPage)
            : 1;
    const totalOutputPages =
        rowsPerPage > 0 && lciOutputData.length > 0
            ? Math.ceil(lciOutputData.length / rowsPerPage)
            : 1;

    // 페이지 번호가 총 페이지 수를 넘지 않도록 조정
    useEffect(() => {
        if (inputPage > totalInputPages) {
            const next = totalInputPages || 1;
            setInputPage(next);
            setInputPageInput(String(next));
        }
    }, [totalInputPages, inputPage]);

    useEffect(() => {
        if (outputPage > totalOutputPages) {
            const next = totalOutputPages || 1;
            setOutputPage(next);
            setOutputPageInput(String(next));
        }
    }, [totalOutputPages, outputPage]);

    const inputStartIndex = (inputPage - 1) * rowsPerPage;
    const inputEndIndex = inputStartIndex + rowsPerPage;
    const outputStartIndex = (outputPage - 1) * rowsPerPage;
    const outputEndIndex = outputStartIndex + rowsPerPage;

    const paginatedInputData = lciInputData.slice(
        inputStartIndex,
        inputEndIndex
    );
    const paginatedOutputData = lciOutputData.slice(
        outputStartIndex,
        outputEndIndex
    );

    // amount 값 포맷 함수 ===================================================
    const formatAmount = (value) => {
        const num = Number(value);
        if (!Number.isFinite(num)) return value ?? "";
        if (num === 0) return "0.000e+00";
        return num.toExponential(3); // 지수 표기법으로 변환
    };

    // 페이지 전체 데이터 index 번호 부여 + amount 포맷 ===================================================
    const inputRowsWithNo = paginatedInputData.map((item, idx) => ({
        ...item,
        no: inputStartIndex + idx + 1,
        amount: formatAmount(item.amount),
    }));
    const outputRowsWithNo = paginatedOutputData.map((item, idx) => ({
        ...item,
        no: outputStartIndex + idx + 1,
        amount: formatAmount(item.amount),
    }));

    // 페이지 번호 클램프 함수 ===================================================
    const clampPage = (page, total) => {
        if (!total || total < 1) return 1;
        if (!page || Number.isNaN(page)) return 1;
        return Math.min(Math.max(page, 1), total);
    };

    // 페이지 점프 함수 ===================================================
    const handleJumpPage = (target) => {
        if (target === "input") {
            const value = inputPageInput.trim();
            if (!value) return;
            const num = parseInt(value, 10);
            if (Number.isNaN(num)) return;
            const page = clampPage(num, totalInputPages);
            setInputPage(page);
            setInputPageInput(String(page));
        } else if (target === "output") {
            const value = outputPageInput.trim();
            if (!value) return;
            const num = parseInt(value, 10);
            if (Number.isNaN(num)) return;
            const page = clampPage(num, totalOutputPages);
            setOutputPage(page);
            setOutputPageInput(String(page));
        }
    };

    // 엑셀 다운로드 =============================================
    const downloadExcel = async () => {
        // 1) pjno(프로젝트 번호)가 없거나, 이미 다운로드 중이면 함수 종료
        //    - 중복 클릭/요청 방지
        if (!pjno || isDownloading) return;
        // 2) 다운로드 진행 상태로 변경 (UI에서 버튼 비활성화 등에 사용)
        setIsDownloading(true);
        try {
            // 3) 서버로 엑셀 다운로드 요청
            //    - withCredentials: 쿠키/세션 인증 포함
            //    - responseType: "blob" → 바이너리(파일) 응답을 Blob으로 받기
            const res = await axios.get(
                "http://localhost:8080/api/excel/download",
                {
                    params: { pjno },
                    withCredentials: true,
                    responseType: "blob",
                }
            );
            // 4) 서버가 내려준 Content-Type 헤더를 확인
            //    - 정상 파일이면 보통 xlsx MIME 타입
            //    - 에러/상태값이면 application/json 혹은 text/plain 등일 수 있음
            const contentType =
                (res.headers && res.headers["content-type"]) || "";
            const lowerContentType = contentType.toLowerCase();
            // 5) 응답이 "파일(blob)"처럼 보이지만,
            //    Content-Type이 JSON 또는 text라면 "텍스트 기반 응답(에러/상태값)"일 가능성 판단
            //    - Blob 인스턴스이면서
            //    - content-type이 application/json or text/*
            const isTextResponse =
                res.data instanceof Blob &&
                (lowerContentType.includes("application/json") ||
                    lowerContentType.includes("text/"));
            // 6) 텍스트 기반 응답일 때 처리
            //    - 서버가 true/false 또는 JSON 형태로 "다운로드 가능/불가"를 보낸 케이스
            if (isTextResponse) {
                try {
                    // 6-1) Blob → text로 변환 후 공백 제거
                    const text = (await res.data.text()).trim();
                    // 6-2) text가 "true"면 다운로드 가능 → 함수 종료
                    if (text === "true") {
                        return;
                    }
                    // 6-3) text가 "false"면 다운로드 불가 → 알림 후 함수 종료
                    if (text === "false") {
                        alert("다운로드 실패");
                        return;
                    }
                    // 6-4) text가 JSON 형태라면 파싱 시도
                    const parsed = text ? JSON.parse(text) : null;
                    // 6-5) 파싱 결과가 boolean true면 성공으로 간주하고 종료
                    if (parsed === true) {
                        return;
                    }
                } catch (err) {
                    // 6-6) JSON 파싱 실패 등 예외는 로그만 남김
                    console.error("[downloadExcel json parse error]", err);
                }
                // 6-7) 여기까지 왔다는 건 → 파일이 아니라 에러/이상 응답
                alert("다운로드 실패");
                return;
            }
            // 7) 정상 파일 응답 처리 (Blob → 실제 다운로드)
            // 7-1) 서버에서 내려온 blob 데이터를 기반으로 새 Blob 생성
            //      - type이 없으면 예전 엑셀 타입으로 fallback
            const blob = new Blob([res.data], {
                type: contentType || "application/vnd.ms-excel",
            });
            // 7-2) Blob → 다운로드 링크 생성 및 클릭 → 다운로드 실행
            const downloadUrl = window.URL.createObjectURL(blob);
            // 7-3) a 태그를 만들어 다운로드 트리거
            const link = document.createElement("a");
            link.href = downloadUrl;
            // 7-4) Content-Disposition 헤더에서 파일명 추출
            //      - filename*=UTF-8''encodedName  또는 filename="name.xlsx" 형태 모두 대응
            const disposition = res.headers["content-disposition"] || "";
            const match = disposition.match(/filename\\*=UTF-8''(.+)|filename="?([^\";]+)"?/i);
            // 7-5) 매칭 결과에서 인코딩된 파일명 또는 일반 파일명을 선택
            const encoded = match?.[1] || match?.[2];
            // 7-6) 파일명이 있으면 decodeURIComponent로 디코딩,
            //      없으면 기본 파일명 사용
            const fileName = encoded ? decodeURIComponent(encoded) : `project-${pjno}.xlsx`;
            // 7-7) a 태그 download 속성에 파일명 지정
            link.setAttribute("download", fileName);
            // 7-8) DOM에 붙인 뒤 강제 클릭으로 다운로드 실행
            document.body.appendChild(link);
            link.click();
            // 7-9) 사용한 a 태그 제거
            link.remove();
            // 7-10) 생성해둔 blob URL 해제 (메모리 누수 방지)
            window.URL.revokeObjectURL(downloadUrl);
        } catch (error) {
            // 8) 요청 자체가 실패했을 때 처리
            // 8-1) 인증/권한 문제로 403 내려온 경우
            if (error?.response?.status === 403) {
                alert("다운로드 실패");
            } else {
                // 8-2) 그 외 네트워크/서버 오류 로그 출력
                console.error("[downloadExcel error]", error);
                alert("다운로드 실패");
            }
        } finally {
            // 9) 성공/실패와 무관하게 다운로드 상태 해제
            setIsDownloading(false);
        }
    };

    // return =======================================================
    return (
        <>
            <div className="projectResultTopBar">
                <Button
                    variant="outlined"
                    color="success"
                    loading={isDownloading}
                    onClick={downloadExcel}
                >
                    엑셀 다운로드
                </Button>
                <div>조회 행 수</div>
                <div style={{ width: "10rem" }}>
                    <Select
                        value={String(rowsPerPage)}
                        onChange={(_, newValue) => {
                            if (!newValue) return;
                            const next = parseInt(newValue, 10);
                            if (Number.isNaN(next) || next <= 0) return;
                            setRowsPerPage(next);

                            setInputPage(1);
                            setOutputPage(1);
                            setInputPageInput("1");
                            setOutputPageInput("1");
                        }}
                    >
                        <Option value="100">100개</Option>
                        <Option value="150">150개</Option>
                        <Option value="200">200개</Option>
                    </Select>

                </div>
            </div>
            <div className="projectResultBox">
                <div className="inputResultBox">
                    <div className="resultTitle">Input</div>
                    <ProjectListTable
                        columns={[
                            { id: "no", title: "No", width: 60 },
                            { id: "fname", title: "항목명", width: 100 },
                            { id: "amount", title: "양", width: 100 },
                            { id: "uname", title: "단위", width: 60 },
                        ]}
                        data={
                            inputRowsWithNo && inputRowsWithNo.length > 0
                                ? inputRowsWithNo
                                : [{ __empty: true }]
                        }
                        rememberKey="ProjectResultInputTable"
                        sortable={false}
                        stickyFirst={false}
                    />
                    {totalInputPages > 1 && (
                        <div className="projectResultPagination">
                            <Pagination
                                count={totalInputPages}
                                page={inputPage}
                                onChange={(_, page) => {
                                    setInputPage(page);
                                    setInputPageInput(String(page));
                                }}
                                siblingCount={1}
                                boundaryCount={1}
                                showFirstButton
                                showLastButton
                                size="small"
                            />
                            <div className="projectResultPaginationControl">
                                <input
                                    type="number"
                                    min={1}
                                    max={totalInputPages}
                                    value={inputPageInput}
                                    onChange={(e) => {
                                        const onlyNumber = e.target.value.replace(
                                            /[^0-9]/g,
                                            ""
                                        );
                                        setInputPageInput(onlyNumber);
                                    }}
                                    onKeyDown={(e) => {
                                        if (e.key === "Enter") {
                                            handleJumpPage("input");
                                        }
                                    }}
                                />
                                <button
                                    type="button"
                                    className="page-jump-button"
                                    onClick={() => handleJumpPage("input")}
                                >
                                    이동
                                </button>
                            </div>
                        </div>
                    )}
                </div>
                <div className="outputResultBox">
                    <div className="resultTitle">Output</div>
                    <ProjectListTable
                        columns={[
                            { id: "no", title: "No", width: 60 },
                            { id: "fname", title: "항목명", width: 100 },
                            { id: "amount", title: "양", width: 100 },
                            { id: "uname", title: "단위", width: 60 },
                        ]}
                        data={
                            outputRowsWithNo && outputRowsWithNo.length > 0
                                ? outputRowsWithNo
                                : [{ __empty: true }]
                        }
                        rememberKey="ProjectResultOutputTable"
                        sortable={false}
                        stickyFirst={false}
                    />
                    {totalOutputPages > 1 && (
                        <div className="projectResultPagination">
                            <Pagination
                                count={totalOutputPages}
                                page={outputPage}
                                onChange={(_, page) => {
                                    setOutputPage(page);
                                    setOutputPageInput(String(page));
                                }}
                                siblingCount={1}
                                boundaryCount={1}
                                showFirstButton
                                showLastButton
                                size="small"
                            />
                            <div className="projectResultPaginationControl">
                                <input
                                    type="number"
                                    min={1}
                                    max={totalOutputPages}
                                    value={outputPageInput}
                                    onChange={(e) => {
                                        const onlyNumber = e.target.value.replace(
                                            /[^0-9]/g,
                                            ""
                                        );
                                        setOutputPageInput(onlyNumber);
                                    }}
                                    onKeyDown={(e) => {
                                        if (e.key === "Enter") {
                                            handleJumpPage("output");
                                        }
                                    }}
                                />
                                <button
                                    type="button"
                                    className="page-jump-button"
                                    onClick={() => handleJumpPage("output")}
                                >
                                    이동
                                </button>
                            </div>
                        </div>
                    )}
                </div>
            </div>
        </>
    ); // return end
} // func end
