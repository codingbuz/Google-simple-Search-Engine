package com.accio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String keyword=request.getParameter("keyword");
      System.out.println(keyword);
        Connection connection=DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("Insert into histroy values(?,?)");
             preparedStatement.setString(1,keyword);
             preparedStatement.setString(2,"http://localhost:8080/Search/SearchServlet?keyword="+keyword);
             preparedStatement.executeUpdate();



            ResultSet resultSet = connection.createStatement().executeQuery("select pageTitle,pageLinks, (length(lower(pageTexts))-length(replace(lower(pageTexts),'" + keyword + "','')))/length('" + keyword + "') as countoccurence from pages order by countoccurence desc limit 30;");

            ArrayList<SearchResult> result = new ArrayList<>();
            while (resultSet.next()) {
                SearchResult searchResult = new SearchResult();
                searchResult.setTitle(resultSet.getString("pagetitle"));
                searchResult.setLinks(resultSet.getString("pageLinks"));
                result.add(searchResult);
            }
            for(SearchResult searchResult:result){
                System.out.println(searchResult.getTitle()+" "+searchResult.getLinks()+"\n");


            }

            request.setAttribute("results",result);
            request.getRequestDispatcher("/search.jsp").forward(request,response);

            response.setContentType("text/html");
            PrintWriter out=response.getWriter();

        }
        catch (SQLException | ServletException sqlException){
            sqlException.printStackTrace();
        }


    }
}
