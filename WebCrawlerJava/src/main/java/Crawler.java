import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashSet;

public class Crawler {

    private HashSet<String> urlset;
    private int max_depth=2;

    private  static Connection connection=null;

    public Crawler(){
        urlset=new HashSet<>();
        connection=DataBaseConnection.getConnection();
    }
    public void getPageTextandLinks(String url,int depth){
        if(urlset.contains(url)){return ;}

        if(depth>=max_depth){
            return;
        }

        if(urlset.add(url)){
            System.out.println(url);
        }
        depth++;
        try {
        //connect to the document and get document object
            //
           Document document= Jsoup.connect(url).timeout(5000).get();
        String title=document.title();
            System.out.println(title);
        String text=document.text().length()<500?document.text():document.text().substring(0,499);
        String link=url;

            PreparedStatement preparedStatement=connection.prepareStatement("insert into pages values(?,?,?);");
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,link);
            preparedStatement.setString(3,text);
            preparedStatement.executeUpdate();

        //save data to the database


            Elements availableLinks=document.select("a[href]");
            for (Element currentLink : availableLinks) {
                getPageTextandLinks(currentLink.attr("abs:href"), depth);


            }
        }
        catch (Exception ioException){
            ioException.printStackTrace();
        }


    }
    public static void main(String[] args) {
        Crawler crawler=new Crawler();
        //crawler.getPageTextandLinks("https://www.javatpoint.com",0);
        //crawler.getPageTextandLinks("https://geeksforgeeks.org/",0);
        crawler.getPageTextandLinks("https://www.w3schools.com",0);

    }
}
