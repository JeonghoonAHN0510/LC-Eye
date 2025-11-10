import { configureStore } from "@reduxjs/toolkit";
import adminReducer from './adminSlice.jsx';


const store = configureStore({
    reducer : {
        admin : adminReducer
    }
});

export default store;