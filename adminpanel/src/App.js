import React from "react";

import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect,
} from "react-router-dom";
import "./App.css";
import Users from "./Users/pages/Users";


function App() {
  return (
    <Router>
      <div className="container">
        <Switch>
          <Route path="/" exact component={Users}/>
          <Redirect to="/" />
        </Switch>
      </div>
    </Router>
  );
}

export default App;
