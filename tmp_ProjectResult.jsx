import { useEffect, useState } from "react";
import axios from "axios";
import "../../../assets/css/ProjectResult.css";
import { Table } from "@mui/material";

export default function ProjectResult(props) {
    const { pjno, isOpen } = props;

    const [lciInputData, setLciInputData] = useState(null);
    const [lciOutputData, setLciOutputData] = useState(null);

    // pjno ë¥?ë§¤ê°œë³€?˜ë¡œ LCI ì¡°íšŒ =========================================
    const readLCI = async (pjnoParam) => {
        if (!pjnoParam) return;
        try {
            const res = await axios.get("http://localhost:8080/api/lci", {
                params: { pjno: pjnoParam },
                withCredentials: true,
            });
            
            console.log(res.data.results);

        } catch (error) {
            console.error("[readLCI ?¤íŒ¨]", error);
        }
    }; // func end

    // ?„ì½”?”ì–¸???´ë¦´ ?Œë§ˆ??pjno ê¸°ì??¼ë¡œ ?¸ì¶œ ===========================
    useEffect(() => {
        if (isOpen && pjno) {
            readLCI(pjno);
        }
    }, [isOpen, pjno]);

    


    // return =======================================================
    return (
        <>
            <div className="projectResultBox">
                <div className="inputResultBox">
                    <div className="resultTitle">Input</div>
                    <Table>
                        <thead>
                            <tr>
                                <th>?´ë¦„</th>
                                <th>ê°?/th>
                                <th>?¨ìœ„</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>2</td>
                                <td>3</td>
                            </tr>
                        </tbody>
                    </Table>
                </div>
                <div className="outputResultBox">
                    <div className="resultTitle">Output</div>
                    <Table>
                        <thead>
                            <tr>
                                <th>?´ë¦„</th>
                                <th>ê°?/th>
                                <th>?¨ìœ„</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>2</td>
                                <td>3</td>
                            </tr>
                        </tbody>
                    </Table>
                </div>
            </div>
        </>
    ); // return end
} // func end


