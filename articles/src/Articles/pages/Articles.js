import React, { Component } from "react";

import ArticleService from "../../services/ArticleService";
import ArticlesList from "../components/ArticlesList";
import SearchBar  from "../components/SearchBar";

class Users extends Component {
  constructor(props) {
    super(props);
    this.state = {
      articles: [],
      filterParams:{
        minPrice:1,
        maxPrice:1000000,
        minQuantity:1,
        maxQuantity:1000000,
        title: ""
      }
    };
    this.findArticles = this.findArticles.bind(this);
    this.minQuantityHandler = this.minQuantityHandler.bind(this);
    this.maxQuantityHandler = this.maxQuantityHandler.bind(this);
    this.minPriceHandler = this.minPriceHandler.bind(this);
    this.maxPriceHandler = this.maxPriceHandler.bind(this);
    this.titleHandler = this.titleHandler.bind(this);
  }

  componentDidMount() {
    ArticleService.getArticles().then((res) => {
      this.setState({ articles: res.data });
    });
  }

  findArticles() {
    ArticleService.getArticlesWithParams(this.state.filterParams).then((res)=>{
      this.setState({articles:res.data})
    });
  } 

  minPriceHandler(event){
    let value = event.target.value;
    if(value<1||value>1000000) value=1;
    this.setState({
      filterParams: {
            ...this.state.filterParams,
            minPrice: value
      }
  })
  }

  maxPriceHandler(event){
    let value = event.target.value;
    if(value<1||value>1000000) value=10000001;
    this.setState({
      filterParams: {
            ...this.state.filterParams,
            maxPrice: value
      }
  })
  }
  minQuantityHandler(event){
    let value = event.target.value;
    if(value<1||value>1000000) value=1;
    this.setState({
      filterParams: {
            ...this.state.filterParams,
            minQuantity: value
      }
  })
  }
  maxQuantityHandler(event){
    let value = event.target.value;
    if(value<1||value>1000000) value=1000000;
    this.setState({
      filterParams: {
            ...this.state.filterParams,
            maxQuantity: value
      }
  })
  }
  titleHandler(event){
    console.log(this.state.title)
    this.setState({
      filterParams: {
            ...this.state.filterParams,
            title: event.target.value
      }
  })
  }
  
  render() {
    return (
      <div>
        <h2 className="text-center">Articles List</h2>
        <div className="row">
          {this.state.title}
          <SearchBar  filterParams={this.state.filterParams}
          findArticles={this.findArticles}
          minPriceHandler = {this.minPriceHandler}
          maxPriceHandler = {this.maxPriceHandler}
          minQuantityHandler = {this.minQuantityHandler}
          maxQuantityHandler = {this.maxQuantityHandler}
          titleHandler = {this.titleHandler}/>
          <table className="table table-striped table-bordered">
            <ArticlesList articles = {this.state.articles}/>
          </table>
        </div>
      </div>
    );
  }
}

export default Users;