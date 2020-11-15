import React from "react";


import "./ArticlesItem.css";

const SearchBar  = (props) => {
  const { findArticles, filterParams,
     titleHandler, minQuantityHandler, maxQuantityHandler,
     minPriceHandler, maxPriceHandler} = props;


  return (
    <div className="container">
        <div className="row">
          <div className="card col-md-9 offset-md-1">
            <h3 className="text-center">Filter params:</h3>
            <div className="card-body">
                <div className="form-row">
                <div className="col">
                  <label>
                    Title
                  </label>
                  <input
                    placeholder="Title"
                    name="title"
                    className="form-control"
                    value={filterParams.title}
                    onChange={titleHandler}
                  />
                  </div>
                  <div className="col">
                  <label>
                    Min Price
                  </label>
                  <input
                    placeholder="1"
                    name="minPrice"
                    type="number"
                    className="form-control"
                    value={filterParams.minPrice}
                    min={1}
                    max={1000000}
                    onChange={minPriceHandler}
                  />
                  </div>
                  <div className="col">
                  <label>
                    Max Price
                  </label>
                  <input
                    placeholder="1"
                    name="maxPrice"
                    type="number"
                    className="form-control"
                    value={filterParams.maxPrice}
                    min={1}
                    max={1000000}
                    onChange={maxPriceHandler}
                  />
                  </div>
                  <div className="col">
                  <label>
                    Min Quantity
                  </label>
                  <input
                    placeholder="1"
                    name="minQuantity"
                    type="number"
                    className="form-control"
                    value={filterParams.minQuantity}
                    min={1}
                    max={1000000}
                    onChange={minQuantityHandler}
                  />
                  </div>
                  <div className="col">
                   <label>
                    Max Quantity
                  </label>
                  <input
                    placeholder="1"
                    name="minQuantity"
                    type="number"
                    className="form-control"
                    value={filterParams.maxQuantity}
                    min={1}
                    max={1000000}
                    onChange={maxQuantityHandler}
                  />
                  </div>
                </div>
                <button className="btn btn-success" onClick={findArticles}>
                  Filter articles
                </button>
            </div>
          </div>
          </div>
          </div>
  );
};

export default SearchBar ;