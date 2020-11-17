import React from "react";

import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect,
} from "react-router-dom";
import "./App.css";
import Articles from "./Articles/pages/Articles";

function App() {
  return (
    <Router>
      <div className="container">
        <Switch>
          <Switch>
            <Route path="/" exact component={Articles} />
            <Route path="/articles" exact component={Articles} />
            <Redirect to="/" />
          </Switch>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
