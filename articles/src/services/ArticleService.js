import axios from "axios";

const API_BASE_URL = "http://localhost:8081/api/articles";

class ArticleService {
  getArticles() {
    return axios.get(API_BASE_URL);
  }

  getArticlesWithParams(params){
    return axios.get(API_BASE_URL, {params : {
      
    }})
  }
}

export default new ArticleService();
