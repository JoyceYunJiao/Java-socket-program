import NewWorkspaceForm from "../components/workspaces/NewWorkspaceForm";
import axios from "axios";
import { useNavigate } from 'react-router-dom';

function NewWorkspace() {
    // navigate object for modifying browser history/redirect
    const navigate = useNavigate();
    
    function newWorkspaceHandler(workspace:any) {
        axios.post("http://localhost:8080/addWorkspace", workspace)
            .then(() => navigate("/workspaces", { replace: true }));
    }

    return (
        <NewWorkspaceForm onChange={newWorkspaceHandler} />
    );
}

export default NewWorkspace;