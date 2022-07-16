
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Sign-in</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="text-center">
    <form class="form-signin" action="/login"  method="post">
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
<#--        <label for="inputEmail" class="sr-only">Email address</label>-->
<#--        <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" required autofocus> <br/>-->
        <label for="inputLogin" class="sr-only">login</label>
        <input type="login" id="inputEmail" name="login" class="form-control" placeholder="Login" required autofocus> <br/>
<#--        <label for="inputPassword" class="sr-only">Password</label>-->
<#--         <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required> <br/>-->
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <p>not registered?  <a href="/registration">get your registration</a></p>

        <p class="mt-5 mb-3 text-muted">&copy; Tinder Vlad Corporation 2022</p>
    </form>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>