<html>
<body>
<p>
    logged in user:
    <#if user??>
    ${user.name}
        <#else >
        ${message}
    </#if>

</p>
</body>
</html>