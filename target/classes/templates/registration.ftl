<html lang="en">
<head>
    <!-- Bootstrap core CSS -->
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
<#--    <link rel="stylesheet" href="/assets/css/style.css">-->
</head>

<body>
        <form action=${submitMapping} method="post"> <#--"/registration"-->
            login: <input type="text" name="login" placeholder="enter login" class="form-control" value="${user.login}"/> <br/>
            password: <input type="password" name="password" placeholder="enter password" class="form-control" value="${user.password}"/> <br/>
            age: <input type="text" name="age" placeholder="enter your age" class="form-control" value="${user.age}"/> <br/>
            name: <input type="text" name="name" placeholder="enter your name" class="form-control" value="${user.name}"/> <br/>
            group: <input type="text" name="group" placeholder="enter your group" class="form-control" value="${user.groupId}"/> <br/>
            <input type="submit" value="Ok"/>
        </form>
    </body>
</html>