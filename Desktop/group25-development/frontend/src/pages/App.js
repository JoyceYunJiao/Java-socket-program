import React from "react";
import {Route, Switch} from "react-router-dom";


function App(){
    return (
        <div>
        <Navigation />
        <Switch>
            <Route path = "/board" exact>
                <board />
            </Route>
        </Switch>
        </div>
    );    
}

export default App;