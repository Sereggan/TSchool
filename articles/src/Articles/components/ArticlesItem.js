import React from "react";


import "./ArticlesItem.css";

const ArticlesItem = (props) => {
  const { article } = props;


  return (
    <React.Fragment>
              {
                <tr key={article.id}>
                  <td>{article.title}</td>
                  <td>{article.price}</td>
                  <td>{article.quantity}</td>
                </tr>
              }
    
    </React.Fragment>
  );
};

export default ArticlesItem;