import axios from "axios";

const QUIZZES_API_BASE_URL = "http://localhost:8080/api/quizzes";

class QuizService {
  getQuizzes() {
    return axios.get(QUIZZES_API_BASE_URL);
  }

  createQuiz(quiz) {
    return axios.post(QUIZZES_API_BASE_URL, quiz);
  }
  getQuizById(id) {
    return axios.get(QUIZZES_API_BASE_URL + `/${id}`);
  }
  solveQuiz(id, solution) {
    return axios.post(QUIZZES_API_BASE_URL + `/solve/${id}`, solution);
  }
  deleteQuiz(id) {
    axios.delete(QUIZZES_API_BASE_URL + "/" + id);
  }
}

export default new QuizService();
