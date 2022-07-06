import React from 'react';
//
import { boardHistory } from "react-router-dom"
import CreateBoardForm from '../components/CreateBoardForm';

function CreateBoard() {

    /* ToDo: Create history object with useHistory hook for navigation */
    const history = boardHistory();

    function createBoardHandler(board) {
        //call the API
        //show the login page 
        /* ToDo: Implement createBoardHandler function and navigate to /boards  */
        fetch('', {
            method: 'POST',
            body:JASON.stringify(user),
            headers: {'Content-Type': 'application/json'}
    }).then(() => history.replace('/login'))
    
    }

    return (
        <div>
            //??
            <h1>Boards page</h1>
            //Pass the createBoardHandler function as props
            <CreateBoardForm CreateBoardForm = {createBoardHandler}/>
        </div>
    );
};

export default CreateBoard;
