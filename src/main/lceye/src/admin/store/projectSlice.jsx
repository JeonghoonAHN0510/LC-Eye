import { createSlice } from "@reduxjs/toolkit"

const initialState = {
    projects: [],
};  

const projectSlice = createSlice({
    name: "project",
    initialState,   
    reducers: {
        // 여기에 프로젝트 관련 리듀서 함수들을 추가할 수 있습니다.
    },
});


export default projectSlice.reducer;
export const {  } = projectSlice.actions;