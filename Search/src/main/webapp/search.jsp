
<%@page import="java.util.ArrayList"%>
<%@page import="com.accio.SearchResult"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
   <form action="SearchServlet">
        <input type="text" name="keyword">
         <button type="submit">Search</button>
   </form>
    <form action="HistoryServlet">
      <button type="submit">History</button>
      </form>

   <table border=2 class="resultTable">
     <tr>
        <th>Title</th>
        <th>Links</th>
     <tr>
     <%
       ArrayList<SearchResult> results=(ArrayList<SearchResult>)request.getAttribute("results");
       for(SearchResult result:results){
     %>
     <tr>
        <td><%out.println(result.getTitle());%></td>
        <td><a href="<%out.println(result.getLinks());%>"><%out.println(result.getLinks());%></a></td>
        </tr>
        <%
          }
         %>
   </table>
   </body>
</html>