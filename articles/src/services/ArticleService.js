import axios from "axios";

const API_BASE_URL = "http://localhost:8081/api/articles";

class ArticleService {
  getArticles() {
    return axios.get(API_BASE_URL);
  }

  getArticlesWithParams(params){
    return axios.get(API_BASE_URL, {params : {
      maxPrice: params.maxPrice,
      minPrice: params.minPrice,
      maxQuantity: params.maxQuantity,
      minQuantity: params.minQuantity,
      title: params.title,
    }})
  }
}

export default new ArticleService();
