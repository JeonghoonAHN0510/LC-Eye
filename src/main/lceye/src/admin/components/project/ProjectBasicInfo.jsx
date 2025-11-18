import "../../../assets/css/projectBasicInfo.css";
import Button from '@mui/joy/Button';
import FormControl from '@mui/joy/FormControl';
import FormLabel from '@mui/joy/FormLabel';
import Textarea from '@mui/joy/Textarea';
import Input from '@mui/joy/Input';

export default function ProjectBasicInfo(props) {
    return (
        <>
            <div>
                <div className='headButton'>
                    <Button variant="outlined">저장</Button>
                </div>
                <FormControl>
                    <FormLabel>프로젝트 명</FormLabel>
                    <Input />
                </FormControl>
                <FormControl>
                    <FormLabel>프로젝트 설명</FormLabel>
                    <Textarea
                        minRows={3}
                        maxRows={5}
                    />
                </FormControl>
                <FormControl>
                    <FormLabel>작성자</FormLabel>
                    <Input />
                </FormControl>
                <FormControl>
                    <FormLabel type="number">제품 생산량</FormLabel>
                    <Input />
                </FormControl>
                <FormControl>
                    <FormLabel>단위</FormLabel>
                    <Input />
                </FormControl>
                <FormControl>
                    <FormLabel>등록일</FormLabel>
                    <Input type="date" />
                    <FormLabel>수정일</FormLabel>
                    <Input type="date" />
                </FormControl>


            </div>
        </>
    ) // return end
} // func end