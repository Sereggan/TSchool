import React, { Component } from "react";

import ArticleService from "../../services/ArticleService";
import ArticlesList from "../components/ArticlesList";

class Users extends Component {
  constructor(props) {
    super(props);
    this.state = {
      articles: [],
    };
  }

  componentDidMount() {
    ArticleService.getArticles().then((res) => {
      this.setState({ articles: res.data });
    });
  }
  
  render() {
    return (
      <div>
        <h2 className="text-center">Articles List</h2>
        <div className="row">
          <table className="table table-striped table-bordered">
            <ArticlesList articles = {this.state.articles}/>
          </table>
        </div>
      </div>
    );
  }
}

export default Users;