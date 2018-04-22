



<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录页面</title>
    <link rel="Stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/login.css" />
</head>
<body >


        <div id="login-box">
            <h3>Spring Security 自定义 Login Form (XML)</h3>
            <c:url var="退出" value="${logoutUrl}"/>
            <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                <div class="error">${SPRING_SECURITY_LAST_EXCEPTION.message}</div>
            </c:if>
            <form name='f' action='/login' method='POST'>
                <fieldset>
                    <legend>Please Login</legend>

                    <label >Username</label>
                    <input type='text' name='username' value=''>
                    <label >Password</label>
                    <input type='password' name='password'/>
                    <div class="form-actions">
                        <button type="submit" class="btn">Log in</button>
                    </div>
                </fieldset>
            </form>
        </div>
</body>
</html>



