<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Login</title>
 </head>
 <body>
     <form action="controller.jsp" method="post">
         <p>Username: <input type="text" name="username"/></p>
         <p>Password: <input type="password" name="password"/></p>
         <p><input type="submit" value="Login"/></p>
     </form>
 </body>
</html>