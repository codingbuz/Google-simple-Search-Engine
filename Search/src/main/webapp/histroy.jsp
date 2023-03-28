<%@page import="java.util.ArrayList"%>
<%@page import="com.accio.HistoryResult"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h1>Google search Engine</h1>
<img src="imged.gif" alt=""  width="400" height="379">
<form action="SearchServlet">
   <input type="text" name="keyword">
   <button type="submit">Search</button>
   </form>
<table border=2 class="resultTable">

         <tr>
         <th>Keyword</th>
         <th>Link</th>
         </tr>
         <%
         ArrayList<HistoryResult> result=(ArrayList<HistoryResult>)request.getAttribute("result");
         for(HistoryResult results:result){
         %>
         <tr>
             <td><%out.println(results.getKeyword());%></td>
             <td><a href="<%out.println(results.getLink());%>"><%out.println(results.getLink());%></a></td>
          </tr>

          <%
              }
              %>

              </table>
</body>
</html>