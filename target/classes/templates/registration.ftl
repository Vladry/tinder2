<html lang="en">
<head>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <!-- Custom styles for this template -->
    <link type="text/css" rel="stylesheet" href="../assets/css/style.css"/>
</head>

<body>
        <form action="/registration" method="post"> <#--"/registration"-->
            <#--            password: <input type="password" name="password" placeholder="enter password" class="form-control"/> <br/>-->
            login: <input type="text" name="login" placeholder="enter login" class="form-control"/> <br/>
            name: <input type="text" name="name" placeholder="enter your name" class="form-control"/> <br/>

            <label for="email">email:</label>
                <input type="text" name="email" value="@ukr.net" class="form-control"/><br/>

            <label for = "avatar">avatar URL:</label>
                <input type="text" name="avatar" placeholder="avatar URL"  class="form-control"/><br/>
            <input type="submit" value="Ok"/>
        </form>
    </body>
</html>