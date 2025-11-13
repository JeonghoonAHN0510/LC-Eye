import { createSlice } from '@reduxjs/toolkit';

const initialState = {
    isLogin:{
        isAuth: null,
        role: null,
        cname: null,
        mname: null
    }
};

const adminSlice = createSlice({
    name : "admin",
    initialState,
    reducers : {
        checkingLogin: (state, action) => {
            state.isLogin = action.payload;
        },
    }
})

export default adminSlice.reducer;
export const { checkingLogin } = adminSlice.actions;