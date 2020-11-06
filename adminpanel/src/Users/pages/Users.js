import React, { useState, useEffect, useContext, useCallback } from "react";

import { AuthContext } from "../../shared/context/auth-context";

import LoadingSpinner from "../../shared/UI/LoadingSpinner";
import ErrorModal from "../../shared/UI/ErrorModal";

const Users = (props) => {
  const auth = useContext(AuthContext);

  const [isLoading, setIsLoading] = useState(false);
  const [isFetch, setIsFetch] = useState(0);
  const [tasks, setTasks] = useState("");
  const [error, setError] = useState("");

  const changeStatus = useCallback(
    async (tid, currentStatus) => {
      setIsLoading(true);
      let newStatus;
      if (currentStatus === "active") {
        newStatus = "delayed";
      } else if (currentStatus === "delayed") {
        newStatus = "cancelled";
      } else {
        newStatus = "active";
      }
      let params = JSON.stringify({
        status: newStatus,
        userId: auth.userId,
      });
      try {
        await fetch(`${process.env.API_BACKEND_URL}/tasks/${tid}`, {
          method: "PATCH",
          headers: {
            Authorization: "Bearer " + auth.token,
            "Content-Type": "application/json",
            Accept: "application/json",
          },
          body: params,
        })
          .then((response) => {
            if (!response.ok) {
              throw new Error(response.message);
            }
            return response.json();
          })
          .catch((err) => {
            console.log(err);
          });
        setIsFetch((count) => count - 1);
        setIsLoading(false);
      } catch (err) {
        setIsLoading(false);
        setError(err.message || "Something went wrong, please try again");
      }
    },
    [auth.token, auth.userId]
  );



  const errorhandler = () => {
    setError(null);
  };

  return (
    <React.Fragment>
      <ErrorModal error={error} onClear={errorhandler} />
      {/* <NewTask updateTasks={setIsFetch} setIsLoading={setIsLoading} /> */}
      <p>hello</p>
      {isLoading && (
        <div className="center">
          <LoadingSpinner />
        </div>
      )}
      {!isLoading && tasks}
    </React.Fragment>
  );
};

export default Users;