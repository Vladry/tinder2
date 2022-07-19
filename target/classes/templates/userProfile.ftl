<html>
<head>
    <title>userProfile</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <!-- Custom styles for this template -->
<#--    <link type="text/css" rel="stylesheet" href="../assets/css/style.css"/>-->
</head>
<body>
<p>
    logged in user:
    <#if user??>
        ${user.name}
    <#else >
        ${message}
    </#if>

</p>
<br/>
<p><a href="/users">go like users</a></p>
</body>
</html>