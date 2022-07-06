import React from 'react';
import logo from './logo.svg';
import {Route, Routes, BrowserRouter as Router} from 'react-router-dom';
import Workspaces from './pages/Workspaces';
import Navigation from './components/Navigation';
import './App.css';
import NewWorkspace from './pages/NewWorkspace';
import Login from './pages/Login';

function App() {
  return (
    <div className="App">
    <Router>
        <Navigation />
        
        {/* This "Routes" component allows different pages to be rendered depending on the URL */}
        <Routes>
          {/* Show workspaces page */}
          <Route path="/workspaces" element={<Workspaces/>} />
          {/* Show new workspace page */}
          <Route path="/newWorkspace" element={<NewWorkspace/>} />
          {/* Show user login page */}
          <Route path="/login" element={<Login/>} />

        </Routes>
      </Router>
    </div>
  );
}

export default App;
