import * as React from 'react';
import PropTypes from 'prop-types';
import Box from '@mui/joy/Box';
import Table from '@mui/joy/Table';
import Typography from '@mui/joy/Typography';
import Sheet from '@mui/joy/Sheet';
import Checkbox from '@mui/joy/Checkbox';
import FormControl from '@mui/joy/FormControl';
import FormLabel from '@mui/joy/FormLabel';
import IconButton from '@mui/joy/IconButton';
import Link from '@mui/joy/Link';
import Tooltip from '@mui/joy/Tooltip';
import Select from '@mui/joy/Select';
import Option from '@mui/joy/Option';
import { visuallyHidden } from '@mui/utils';
import { useState } from "react";

export default function ProjectExchange(props){
    // 행 데이터
    const [inputRows, setInputRows] = useState([{ id : 1 , pjename : "" , pjeamount : "" , uname : "", pname : "" , isInput : true}])
    const [outputRows, setOutputRows] = useState([{ id : 1 , pjename : "" , pjeamount : "" , uname : "", pname : "" , isInput : false }])

    // 투입물 체크박스 선택 상태
    const [inputCheckedList, setInputCheckedList] = useState([]);
    const inputChecked = inputRows.length > 0 && inputCheckedList.length === inputRows.length;

    // 산출물 체크박스 선택 상태
    const [outputCheckedList, setOutputCheckedList] = useState([]);
    const outputChecked = outputRows.length > 0 && outputCheckedList.length === outputRows.length;

    // 투입물 전체 선택
    const handleCheckAllInput = (checked) => {
        if (checked) {
            setInputCheckedList(inputRows.map(input => input.id)); // 모든 id 선택
        } else {
            setInputCheckedList([]);
        }
    };

    // 산출물 전체 선택
    const handleCheckAllOutput = (checked) => {
        if (checked) {
            setOutputCheckedList(outputRows.map(output => output.id));
        }else {
            setOutputCheckedList([]);
        }// if end
    };

    // 투입물 개별 선택
    const handleCheckInput = (id) => {
        if (inputCheckedList.includes(id)) {
            setInputCheckedList(inputCheckedList.filter(item => item !== id));
        } else {
            setInputCheckedList([...inputCheckedList, id]);
        }
    };

    // 산출물 개별 선택
    const handleCheckOutput = (id) => {
        if(outputCheckedList.includes(id)){
            setOutputCheckedList(outputCheckedList.filter(item => item !== id));
        }else{
            setOutputCheckedList([...outputCheckedList, id]);
        }//if end
    };

    // 투입물 행 추가
    const addInputRow = () => {
        const newId = inputRows.length > 0 ? Math.max(...inputRows.map((r) => r.id)) + 1 : 1;
        setInputRows([...inputRows, { id: newId, pjename: "", pjeamount : "" , uname : "", pname : "" , isInput : true }]);
    };

    // 산출물 행 추가
    const addOutputRow = () => {
        const newId = outputRows.length > 0 ? Math.max(...outputRows.map((r) => r.id)) + 1 : 1;
        setOutputRows([...outputRows, { id: newId, pjename: "", pjeamount : "" , uname : "", pname : "" , isInput : false }])
    };

    // 투입물 td 입력값 변경
    const inputHandleChange = (id, field, value) => {
        setInputRows(inputRows.map((r) => (r.id === id ? { ...r, [field]: value } : r)));
    };

    // 산출물 td 입력값 변경
    const outputHandleChange = (id, field, value) => {
        setOutputRows(outputRows.map((r) => (r.id === id ? { ...r, [field]: value } : r)));
    };



    return(
        <>
        <div style={{alignItems: "center" , justifyContent: "end" , display: "flex"}}>
            <button style={{ color: "white", backgroundColor: "rgb(17 51 125)" , marginLeft: "10px"}}>
                선택 매칭
            </button>
            <button style={{ color: "white", backgroundColor: "rgb(17 51 125)" , marginLeft: "10px"}}>
                자동 매칭
            </button>
            <button style={{ color: "white", backgroundColor: "rgb(17 51 125)" , marginLeft: "10px"}}>
                저장
            </button>
            <button style={{ color: "white", backgroundColor: "rgb(17 51 125)" , marginLeft: "10px"}}>
                계산
            </button>
            <button style={{ color: "white", backgroundColor: "rgb(17 51 125)" , marginLeft: "10px"}}>
                삭제
            </button>
            <button style={{ color: "white", backgroundColor: "rgb(17 51 125)" , marginLeft: "10px"}}>
                초기화
            </button>
        </div>
        <div style={{display: "flex" , justifyContent: "space-between" , alignItems: "center"}}>
            <h3>투입물</h3>
            <button style={{ color: "white", backgroundColor: "rgb(17 51 125)" , width: "70px" , height: "30px"}}
            onClick={addInputRow}>
                행 추가
            </button>
        </div>
        <Table
         aria-label="basic table"
         sx={{
                borderCollapse: "collapse", // 테두리 겹치지 않게
                "& th, & td": {
                border: "1px solid #ccc", // 연한 회색으로 구분선
                padding: "8px",
                textAlign: "center",       
                verticalAlign: "middle",
                },
                "& thead th": {
                backgroundColor: "#666",
                color: "white",
                fontWeight: "bold",
                },
                "& tbody tr:hover": {
                backgroundColor: "#f5f5f5", // 마우스 오버 시 하이라이트
                },
            }}
        >
            <thead>
                <tr>
                <th>
                    <Checkbox
                    checked={inputChecked}
                    indeterminate={!inputChecked && inputCheckedList.length > 0}
                    onChange={(e) => handleCheckAllInput(e.target.checked)}
                    />
                </th>
                <th style={{ width: "20%" }}>No</th>
                <th>투입물 명</th>
                <th>투입량</th>
                <th>단위</th>
                <th>연결 데이터</th>
                </tr>
            </thead>

            <tbody>
                {inputRows.map((input,index) => (
                <tr key={input.id}>
                    <td>
                    <Checkbox
                        checked={inputCheckedList.includes(input.id)}
                        onChange={() => handleCheckInput(input.id)}
                    />
                    </td>
                    <td>{index + 1}</td>
                    <td>
                        <input
                        type="text"
                        value={input.pjename}
                        onChange={(e) => inputHandleChange(input.id, "pjename", e.target.value)}
                        style={{
                            border: "none",        
                            outline: "none",       
                            width: "100%",         
                            padding: "4px 0",   
                            textAlign: "center", 
                            backgroundColor: "transparent",
                        }}
                        />
                    </td>
                    <td>
                        <input
                        type="number"
                        value={input.pjeamount}
                        onChange={(e) => inputHandleChange(input.id, "pjeamount", e.target.value)}
                        style={{
                            border: "none",        
                            outline: "none",       
                            width: "100%",         
                            padding: "4px 0",   
                            textAlign: "center", 
                            backgroundColor: "transparent",
                        }}
                        />
                    </td>
                    <td>
                        <input
                        type="text"
                        value={input.uname}
                        onChange={(e) => inputHandleChange(input.id, "uname", e.target.value)}
                        style={{
                            border: "none",        
                            outline: "none",       
                            width: "100%",         
                            padding: "4px 0",   
                            textAlign: "center", 
                            backgroundColor: "transparent",
                        }}
                        />
                    </td>
                    <td>
                        <input
                        type="text"
                        value={input.pname}
                        onChange={(e) => inputHandleChange(input.id, "pname", e.target.value)}
                        style={{
                            border: "none",        
                            outline: "none",       
                            width: "100%",         
                            padding: "4px 0",   
                            textAlign: "center", 
                            backgroundColor: "transparent",
                        }}
                        />
                    </td>
                </tr>
                ))}
            </tbody>
        </Table>
        <div
            style={{
            width: "100%",
            textAlign: "center",
            borderBottom: "1px solid #aaa",
            lineHeight: "0.1em",
            margin: "10px 0 20px",
        }}>            
        </div>
        <hr/>
        <div style={{alignItems: "center" , justifyContent: "end" , display: "flex"}}>
            <button style={{ color: "white", backgroundColor: "rgb(17 51 125)" , marginLeft: "10px"}}>
                선택 매칭
            </button>
            <button style={{ color: "white", backgroundColor: "rgb(17 51 125)" , marginLeft: "10px"}}>
                자동 매칭
            </button>
            <button style={{ color: "white", backgroundColor: "rgb(17 51 125)" , marginLeft: "10px"}}>
                저장
            </button>
            <button style={{ color: "white", backgroundColor: "rgb(17 51 125)" , marginLeft: "10px"}}>
                계산
            </button>
            <button style={{ color: "white", backgroundColor: "rgb(17 51 125)" , marginLeft: "10px"}}>
                삭제
            </button>
            <button style={{ color: "white", backgroundColor: "rgb(17 51 125)" , marginLeft: "10px"}}>
                초기화
            </button>
        </div>
        <div style={{display: "flex" , justifyContent: "space-between" , alignItems: "center"}}>
            <h3>산출물</h3>
            <button style={{ color: "white", backgroundColor: "rgb(17 51 125)" , width: "70px" , height: "30px"}}
            onClick={addOutputRow}>
                행 추가
            </button>
        </div>
        <Table
         aria-label="basic table"
         sx={{
                borderCollapse: "collapse", // 테두리 겹치지 않게
                "& th, & td": {
                border: "1px solid #ccc", // 연한 회색으로 구분선
                padding: "8px",
                textAlign: "center",       
                verticalAlign: "middle",
                },
                "& thead th": {
                backgroundColor: "#666",
                color: "white",
                fontWeight: "bold",
                },
                "& tbody tr:hover": {
                backgroundColor: "#f5f5f5", // 마우스 오버 시 하이라이트
                },
            }}
        >
            <thead>
                <tr>
                <th>
                    <Checkbox
                    checked={outputChecked}
                    indeterminate={!outputChecked && outputCheckedList.length > 0}
                    onChange={(e) => handleCheckAllOutput(e.target.checked)}
                    />
                </th>
                <th style={{ width: "20%" }}>No</th>
                <th>산출물 명</th>
                <th>산출량</th>
                <th>단위</th>
                <th>연결 데이터</th>
                </tr>
            </thead>

            <tbody>
                {outputRows.map((output,index) => (
                <tr key={output.id}>
                    <td>
                    <Checkbox
                        checked={outputCheckedList.includes(output.id)}
                        onChange={() => handleCheckOutput(output.id)}
                    />
                    </td>
                    <td>{index + 1}</td>
                    <td>
                        <input
                        type="text"
                        value={output.pjename}
                        onChange={(e) => outputHandleChange(output.id, "pjename", e.target.value)}
                        style={{
                            border: "none",        
                            outline: "none",       
                            width: "100%",         
                            padding: "4px 0",   
                            textAlign: "center", 
                            backgroundColor: "transparent",
                        }}
                        />
                    </td>
                    <td>
                        <input
                        type="number"
                        value={output.pjeamount}
                        onChange={(e) => outputHandleChange(output.id, "pjeamount", e.target.value)}
                        style={{
                            border: "none",        
                            outline: "none",       
                            width: "100%",         
                            padding: "4px 0",   
                            textAlign: "center", 
                            backgroundColor: "transparent",
                        }}
                        />
                    </td>
                    <td>
                        <input
                        type="text"
                        value={output.uname}
                        onChange={(e) => outputHandleChange(output.id, "uname", e.target.value)}
                        style={{
                            border: "none",        
                            outline: "none",       
                            width: "100%",         
                            padding: "4px 0",   
                            textAlign: "center", 
                            backgroundColor: "transparent",
                        }}
                        />
                    </td>
                    <td>
                        <input
                        type="text"
                        value={output.pname}
                        onChange={(e) => outputHandleChange(output.id, "pname", e.target.value)}
                        style={{
                            border: "none",        
                            outline: "none",       
                            width: "100%",         
                            padding: "4px 0",   
                            textAlign: "center", 
                            backgroundColor: "transparent",
                        }}
                        />
                    </td>
                </tr>
                ))}
            </tbody>
        </Table>
        </>
    ) // return end
} // func end