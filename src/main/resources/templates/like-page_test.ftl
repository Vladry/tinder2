<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <!-- Custom styles for this template -->
        <link type="text/css" rel="stylesheet" href="../assets/css/style.css"/>
</head>
<body style="background-color: #f5f5f5;">

<#--<div class="col-4 offset-4">-->
<#--    <div class="card">-->
<#--        <div class="card-body">-->
<#--            <div class="row">-->
<#--                <div class="col-12 col-lg-12 col-md-12 text-center">-->
<#--                    <img src="https://robohash.org/68.186.255.198.png" alt="" class="mx-auto rounded-circle img-fluid">-->
<#--                    <#if unLikedUsers??>-->
<#--                    <h3 class="mb-0 text-truncated">пока не известно</h3>-->
<#--                    </#if>-->
<#--                    <br/>-->
<#--                    <p><a href="/liked">see your contacts</a></p>-->
<#--                    <p><a href="/logout">logout</a></p>-->
<#--                    <br>-->
<#--                </div>-->
<#--                <div class="col-12 col-lg-6">-->
<#--                    <button type="button" class="btn btn-outline-danger btn-block"><span class="fa fa-times"></span> Dislike</button>-->
<#--                </div>-->
<#--                <div class="col-12 col-lg-6">-->
<#--                    <button class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span> Like</button>-->
<#--                </div>-->
<#--                <!--/col&ndash;&gt;-->
<#--            </div>-->
<#--            <!--/row&ndash;&gt;-->
<#--        </div>-->
<#--        <!--/card-block&ndash;&gt;-->
<#--    </div>-->
<#--</div>-->





<h2>People you have not liked yet</h2>
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

                            <#if unLikedUsers?? >
                                <#list unLikedUsers as user>
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


</body>
</html>