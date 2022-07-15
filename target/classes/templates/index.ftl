<html lang="en">
<head>
    <!-- Bootstrap core CSS -->
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">


    <script src="/assets/js/bootstrap.min.js"></script>
    <!-- Custom styles for this template -->
<#--    <link rel="stylesheet" href="/assets/css/style.css">-->
</head>

<body>
    <form action="/users" method="post">
        <div class="form-group">
            Name: <input type="text" name="login" placeholder="enter login" class="form-control"/> <br/>
            Name: <input type="password" name="password" placeholder="enter password" class="form-control"/> <br/>
            <input type="submit" value="Ok"/>
        </div>
    </form>

    <form>
        <p>for new registration:
        <a href="/registration">click here</a>
    </form>

</body>
</html>