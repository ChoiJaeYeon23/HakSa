package HakSa;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

   public static Connection databaseLink;

   public static Connection getDBConnection() {
      String databaseUser = "root";
      String databasePassword = "4598";
      String url = "jdbc:mysql://localhost:3306/test";

      try {

         Class.forName("com.mysql.cj.jdbc.Driver");
         databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

      } catch (Exception e) {
         e.printStackTrace();
      }

      return databaseLink;
   }

}