import { Card } from "react-bootstrap";

function Workspace(props:any) {
    return (
        <Card>
            <Card.Body>
                <h2>{props.workspace.title}</h2>
                <p>{props.workspace.description}</p>
            </Card.Body>
        </Card>
    );
}
export default Workspace;