import React, { Component } from "react";
import QuizService from "../services/QuizService";

class SolveQuizComponents extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.match.params.id,
      title: "",
      description: "",
      options: [],
      answer: "",
      status: "",
    };
    this.solveQuiz = this.solveQuiz.bind(this);
    this.cancel = this.cancel.bind(this);
    this.answerHandler = this.answerHandler.bind(this);
  }

  componentDidMount() {
    console.log("HEHE");
    QuizService.getQuizById(this.state.id).then((res) => {
      console.log(res.data);
      this.setState({
        title: res.data.title,
        description: res.data.text,
        options: res.data.options,
      });
    });
  }
  solveQuiz = (e) => {
    e.preventDefault();
    let solution = {
      answer: this.state.answer
        .replace(/\s/g, "")
        .split(",")
        .map((x) => +x),
    };

    QuizService.solveQuiz(this.state.id, solution).then((res) => {
      let result = res.data;
      if (result.success) {
        this.setState({
          status: "You are right! Congratulations.",
        });
      } else {
        this.setState({
          status: "You are wrong! Try again.",
        });
      }
    });
  };

  answerHandler(event) {
    this.setState({
      answer: event.target.value,
    });
  }
  cancel() {
    this.props.history.push("/quizzes");
  }

  render() {
    return (
      <div className="container">
        <div className="row">
          <div className="card col-md-6 offset-md-3">
            <h3 className="text-center">Solve quiz</h3>
            <div className="card-body">
              <form>
                <div className="form-group">
                  <label>Title:</label>
                  <p>{this.state.title}</p>
                </div>
                <div className="form-group">
                  <label>Description of Quiz:</label>
                  <p>{this.state.description}</p>
                </div>
                <div className="form-group">
                  <label>Options:</label>
                  <ul className="list-group">
                    {this.state.options.map((option, key) => {
                      return (
                        <li className="list-group-item" key={key}>
                          {key} - {option}
                        </li>
                      );
                    })}
                  </ul>
                </div>
                <div className="form-group">
                  <label>
                    Correct answers(input as numbers using column, starts from
                    0, max = 2):
                  </label>
                  <input
                    placeholder="0,1 for example"
                    name="answer"
                    className="form-control"
                    value={this.state.answer}
                    onChange={this.answerHandler}
                  />
                </div>
                <button className="btn btn-success" onClick={this.solveQuiz}>
                  Solve Quiz
                </button>
                <button
                  className="btn btn-danger"
                  style={{ marginLeft: 10 + "rem" }}
                  onClick={this.cancel}
                >
                  Back to List of quizzes
                </button>
              </form>
              <h2 className="text-center">{this.state.status}</h2>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default SolveQuizComponents;
