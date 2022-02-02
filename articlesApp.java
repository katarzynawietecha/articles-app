import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.io.File;
import java.io.FileWriter;

public class articlesApp {
  public static void main(String args[])
  {
    // Connection
    try {
        URL url = new URL("https://newsapi.org/v2/top-headlines?country=pl&category=business&apiKey=ec8c5767456c45f3af8ba865c95b4723");
        HttpURLConnection connection = (HttpURLConnection)
        url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responsecode = connection.getResponseCode();
        InputStream inputStream;

        // Response handle
        if (responsecode != 200) {
          inputStream = connection.getErrorStream();
        } else {
          inputStream = connection.getInputStream();

          BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
          StringBuilder response = new StringBuilder();
          String currentLine;
          while ((currentLine = in.readLine()) != null)
          response.append(currentLine);
          in.close();

          // Push data to new file
          try {
            FileWriter articlesWriter = new FileWriter("articlesList.txt", true);
            articlesWriter.write(response + System.lineSeparator());
            articlesWriter.close();
          } catch (Exception e) {
            System.out.println(e);
          }
        }
      } catch (Exception e) {
          System.out.println(e);
      }
    }
}
