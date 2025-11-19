import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    selectedProject: null,
    projectListVersion: 0,
};

const projectSlice = createSlice({
    name: "project",
    initialState,
    reducers: {
        setSelectedProject: (state, action) => {
            state.selectedProject = action.payload;
        },
        clearSelectedProject: (state) => {
            state.selectedProject = null;
        },
        incrementProjectListVersion: (state) => {
            state.projectListVersion += 1;
        },
    },
});

export default projectSlice.reducer;
export const {
    setSelectedProject,
    clearSelectedProject,
    incrementProjectListVersion,
} = projectSlice.actions;
