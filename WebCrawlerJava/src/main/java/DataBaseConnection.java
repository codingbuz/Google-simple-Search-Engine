import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
    static Connection connection=null;
    public static Connection getConnection(){
        if(connection!=null){
            return connection;
        }
        String user="root";
        String pwd="SCAM_1992bhai";
        String db="simplesearchengine";
        return getConnection(user,pwd,db);
    }
    private static Connection getConnection(String user,String pwd,String db){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+db+"+?user="+user+"&password="+pwd);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return connection;
    }
}
