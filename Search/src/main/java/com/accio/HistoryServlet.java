package com.accio;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
       // System.out.println("this is history servlet");

        Connection connection=DatabaseConnection.getConnection();
        try{
            ResultSet resultSet=connection.createStatement().executeQuery("select * from histroy;");
            ArrayList<HistoryResult> result= new ArrayList<>();
            while(resultSet.next()){
                HistoryResult historyResult=new HistoryResult();
                historyResult.setKeyword(resultSet.getString("keyword"));
                historyResult.setLink(resultSet.getString("link"));
                result.add(historyResult);
            }
            request.setAttribute("result",result);
            request.getRequestDispatcher("/histroy.jsp").forward(request,response);

            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
        }
        catch (SQLException | IOException | ServletException sqlException){
            sqlException.printStackTrace();
        }
    }
}
