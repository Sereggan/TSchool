import React, { Component } from "react";
import QuizService from "../services/QuizService";

class ListQuizzesComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      quizzes: [],
    };
    this.addQuiz = this.addQuiz.bind(this);
    this.solveQuiz = this.solveQuiz.bind(this);
    this.deleteQuiz = this.deleteQuiz.bind(this);
  }

  componentDidMount() {
    QuizService.getQuizzes().then((res) => {
      this.setState({ quizzes: res.data });
    });
  }

  addQuiz() {
    this.props.history.push("/add-quiz");
  }

  solveQuiz(id) {
    this.props.history.push(`/solve-quiz/${id}`);
  }

  deleteQuiz(id) {
    QuizService.deleteQuiz(id - 1);
    window.location.reload(false);
  }

  render() {
    return (
      <div>
        <h2 className="text-center">Quizzes List</h2>
        <div className="row">
          <button className="btn btn-primary" onClick={this.addQuiz}>
            Add Quiz
          </button>
        </div>
        <div className="row">
          <table className="table table-striped table-bordered">
            <thead>
              <tr>
                <th> title </th>
                <th> Descriptions </th>
                <th> actions... </th>
              </tr>
            </thead>
            <tbody>
              {this.state.quizzes.map((quiz) => (
                <tr key={quiz.id}>
                  <td>{quiz.title}</td>
                  <td>{quiz.text}</td>
                  <td>
                    <button
                      className="btn btn-primary"
                      onClick={() => this.solveQuiz(quiz.id)}
                    >
                      Solve Quiz!
                    </button>
                    <button
                      className="btn btn-danger"
                      style={{ marginRight: -8 + "em" }}
                      onClick={() => this.deleteQuiz(++quiz.id)}
                    >
                      Delete Quiz!
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default ListQuizzesComponent;
