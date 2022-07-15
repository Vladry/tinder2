<html>
<body>
<p>
    Users:
    <#if users??>
        <#list users as user>
            <p> ${user}
            <img src="/image?id=${user.id}" width="100">
            <a href="/update?id=${user.id}">Update</a>
        </#list>
    </#if>

</p>
</body>
</html>