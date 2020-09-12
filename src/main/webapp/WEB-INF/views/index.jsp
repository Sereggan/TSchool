<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="col-md-offset-1 col-md-10">
        <hr />
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Products List</div>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-bordered">
                    <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Price</th>
                    </tr>

                    <!-- loop over and print our customers -->
                    <c:forEach var="article" items="${articles}">
                        <tr>
                            <td>${article.title }</td>
                            <td>${article.price }</td>
                            <td>${article.category }</td>
                        </tr>
                    </c:forEach>

                </table>

            </div>
        </div>
    </div>

</div>

</body>
</html>
