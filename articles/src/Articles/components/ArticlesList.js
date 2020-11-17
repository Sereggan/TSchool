import React from "react";

import "./ArticlesList.css";
import ArticlesItem from "./ArticlesItem";

const ArticlesList = (props) => {
  const { articles } = props;

  return (
    <React.Fragment>
      <thead>
        <tr>
          <th> Title </th>
          <th> Price </th>
          <th> Quantity </th>
        </tr>
      </thead>
      {articles.map((article) => {
        return <ArticlesItem article={article} key={article.id} />;
      })}
    </React.Fragment>
  );
};

export default ArticlesList;
