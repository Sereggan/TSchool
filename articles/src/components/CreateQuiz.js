import React, { useState } from "react";
import QuizService from "../services/QuizService";

const CreateQuiz = (props) => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [options, setOptions] = useState("");
  const [answer, setAnswer] = useState("");

  const addQuiz = (e) => {
    e.preventDefault();
    let newQuiz = {
      title,
      text: description,
      options: options.replace(/\s/g, "").split(","),
      answer: answer
        .replace(/\s/g, "")
        .split(",")
        .map((x) => +x),
    };
    QuizService.createQuiz(newQuiz).then((res) => {
      props.history.push("/quizzes");
    });
  };

  const cancel = () => {
    props.history.push("/quizzes");
  };

  return (
    <div className="container">
      <div className="row">
        <div className="card col-md-6 offset-md-3">
          <h3 className="text-center">Add quiz</h3>
          <div className="card-body">
            <form>
              <div className="form-group">
                <label>Title:</label>
                <input
                  placeholder="Title of Quiz"
                  name="title"
                  className="form-control"
                  value={title}
                  onChange={(e) => setTitle(e.target.value)}
                />
              </div>
              <div className="form-group">
                <label>Description of Quiz:</label>
                <input
                  placeholder="Description of Quiz"
                  name="description"
                  className="form-control"
                  value={description}
                  onChange={(e) => setDescription(e.target.value)}
                />
              </div>
              <div className="form-group">
                <label>Options(input using column, min = 2, max = 4):</label>
                <input
                  placeholder="Options of quiz, input using column"
                  name="options"
                  className="form-control"
                  value={options}
                  onChange={(e) => setOptions(e.target.value)}
                />
              </div>
              <div className="form-group">
                <label>
                  Correct answers(input as numbers using column, starts from 0,
                  max = 2):
                </label>
                <input
                  placeholder="0,1 for example"
                  name="answer"
                  className="form-control"
                  value={answer}
                  onChange={(e) => setAnswer(e.target.value)}
                />
              </div>
              <button
                className="btn btn-success"
                onClick={addQuiz}
                disabled={
                  title.length < 1 ||
                  description.length < 1 ||
                  options.replace(/\s/g, "").split(",").length < 2 ||
                  options.replace(/\s/g, "").split(",").length > 4 ||
                  answer.replace(/\s/g, "").split(",").length > 2 ||
                  answer.replace(/\s/g, "").split(",").length < 1
                }
              >
                Add Quiz
              </button>
              <button
                className="btn btn-danger"
                style={{ marginLeft: 10 + "rem" }}
                onClick={cancel}
              >
                Cancel
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CreateQuiz;
