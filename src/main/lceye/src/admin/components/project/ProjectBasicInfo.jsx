import "../../../assets/css/projectBasicInfo.css";
import Button from '@mui/joy/Button';
import FormControl from '@mui/joy/FormControl';
import FormLabel from '@mui/joy/FormLabel';
import Textarea from '@mui/joy/Textarea';
import Input from '@mui/joy/Input';
import Select from "@mui/joy/Select";
import Option from "@mui/joy/Option";
import { useEffect, useMemo, useState } from "react";
import axios from "axios";


export default function ProjectBasicInfo(props) {

    const [units, setUnits] = useState([]);          // 전체 unit 목록
    const [selectedGroup, setSelectedGroup] = useState(null); // 선택된 ugno
    const [selectedUnitUno, setSelectedUnitUno] = useState(null); // 선택된 uno

    // 단위 목록 조회
    useEffect(() => {
        const fetchUnits = async () => {
            try {
                const res = await axios.get("http://localhost:8080/api/units", {
                    withCredentials: true,
                });
                const data = Array.isArray(res.data) ? res.data : [];
                setUnits(data);
            } catch (e) {
                console.error("[에러발생 /api/units]", e);
            }
        };
        fetchUnits();
    }, []);

    // 단위 그룹 목록 (중복 ugno 제거)
    const unitGroups = useMemo(() => {
        const map = new Map(); // key: ugno, value: ugname
        units.forEach((u) => {
            if (!map.has(u.ugno)) map.set(u.ugno, u.ugname);
        });
        return Array.from(map, ([ugno, ugname]) => ({ ugno, ugname }));
    }, [units]);

    // 선택된 그룹에 해당하는 단위들
    const filteredUnits = useMemo(
        () => units.filter((u) => u.ugno === selectedGroup),
        [units, selectedGroup]
    );

    return (
        <>
            <div>
                <div className='headButton'>
                    <Button variant="outlined">저장</Button>
                </div>
                <FormControl className="bottomMargin">
                    <FormLabel>프로젝트 명</FormLabel>
                    <Input />
                </FormControl>
                <FormControl className="bottomMargin">
                    <FormLabel>프로젝트 설명</FormLabel>
                    <Textarea
                        minRows={3}
                        maxRows={5}
                    />
                </FormControl>
                <FormControl className="bottomMargin">
                    <FormLabel>작성자</FormLabel>
                    <Input />
                </FormControl>
                <FormControl className="bottomMargin">
                    <FormLabel type="number">제품 생산량</FormLabel>
                    <Input />
                </FormControl>
                <div className="unitSelectArea bottomMargin">
                    <FormControl>
                        <FormLabel>단위 그룹</FormLabel>
                        <Select
                            placeholder="단위 그룹 선택"
                            value={selectedGroup}
                            onChange={(event, newValue) => {
                                setSelectedGroup(newValue);    // ugno 저장
                                setSelectedUnitUno(null);      // 그룹 바뀌면 단위 초기화
                            }}
                        >
                            {unitGroups.map((g) => (
                                <Option key={g.ugno} value={g.ugno}>
                                    {g.ugname}
                                </Option>
                            ))}
                        </Select>
                    </FormControl>

                    <FormControl>
                        <FormLabel>단위</FormLabel>
                        <Select
                            placeholder="단위 선택"
                            value={selectedUnitUno}
                            onChange={(event, newValue) => {
                                setSelectedUnitUno(newValue);  // 여기 newValue가 선택된 uno
                            }}
                            disabled={!selectedGroup}        // 그룹 선택 전에는 비활성화
                        >
                            {filteredUnits.map((u) => (
                                <Option key={u.uno} value={u.uno}>
                                    {u.unit}
                                </Option>
                            ))}
                        </Select>

                    </FormControl>
                </div>
                <div className="unitSelectArea bottomMargin">
                    <FormControl>
                        <FormLabel>등록일</FormLabel>
                        <Input type="date" readOnly/>
                    </FormControl>
                    <FormControl>
                        <FormLabel>수정일</FormLabel>
                        <Input type="date" readOnly/>
                    </FormControl>
                </div>

            </div>
        </>
    ) // return end
} // func end