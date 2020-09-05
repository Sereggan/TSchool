<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
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
                <div class="panel-title">Boxes List</div>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>Title</th>
                        <th>Price</th>
                    </tr>

                    <!-- loop over and print our customers -->
                    <c:forEach var="box" items="${boxes}">
                        <tr>
                            <td>${box.title}</td>
                            <td>${box.price}</td>

                            <td>
                                <!-- display the update link --> <a href="${addBox}">add</a>
                            </td>

                        </tr>

                    </c:forEach>

                </table>

            </div>
        </div>
    </div>

</div>

</body>
</html>
