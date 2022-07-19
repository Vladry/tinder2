<#--https://freemarker.apache.org/docs/ref_directive_list.html-->
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Liked people list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <!-- Custom styles for this template -->
    <link type="text/css" rel="stylesheet" href="../assets/css/style.css"/>



</head>
<body>
<h2>Your liked people list</h2>
<br>
<p><a href="/create_likes">go like new people</a></p>
<p><a href="/logout">logout</a></p>
<br>
<div class="container">
    <div class="row">

        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>

                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-likedUsers table" border="0">
                            <tbody>

                            <#if likedUsers?? >
                                <#list likedUsers as user>
                                    <tr>
                                        <td>${user.getId()}</td>
                                        <td width="10">
                                            <div class="avatar-img">
                                                <img class="img-circle" src= ${user.avatar} alt="user_avatar"/>  
                                            </div>

                                        </td>
                                        <td class="align-middle">
                                            name: ${user.getName()}
                                        </td>
                                        <td class="align-middle">
                                            ${user.getEmail()}
                                        </td>
<#--                                        <td class="align-middle">-->
<#--                                            ${user.getLogin()}-->
<#--                                        </td>-->
<#--                                        <td class="align-middle">-->
<#--                                            Last Login: 6/10/2017<br><small class="text-muted">5 days ago</small>-->
<#--                                        </td>-->
                                    </tr>
                                </#list>
                            </#if>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>-->
</body>
</html>