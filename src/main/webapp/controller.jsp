<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDateTime" %>
<!DOCTYPE html>
<html>
 <head>
  <title>Authentication</title>
 </head>
 <body>
     <%-- Print to message the current date and time --%>
     <%! String getCurrentDateTime() {
      LocalDateTime now = LocalDateTime.now();
      return now.toString();
     }
     %>
     <% if ("foo".equals(request.getParameter("username"))
        && ("bar".equals(request.getParameter("password")))) {%>
            <h3>Hello <%=request.getParameter("username") %>!</h3>
     <% } else { %>
            <h3>Authentication failed!</h3>
     <% } %>
 </body>
</html>