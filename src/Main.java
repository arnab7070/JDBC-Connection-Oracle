import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        String jdbc_url = null;
        String username = null;
        String password = null;
        Properties properties = new Properties();
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            if (fs != null) {
                properties.load(fs);
                jdbc_url = properties.getProperty("jdbc_url");
                username = properties.getProperty("username");
                password = properties.getProperty("password");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try (Connection con = DriverManager.getConnection(jdbc_url, username, password)) {
            if (con != null) {
                Statement st = con.createStatement();
                if (st != null) {
                    ResultSet rs = st.executeQuery("SELECT * FROM TOUR");
                    if (rs != null) {
                        while (rs.next()) {
                            System.out.println("Details for " + rs.getString(2));
                            System.out.println("TourID: "+rs.getString(1));
                            System.out.println("Price: "+rs.getInt(3));
                            System.out.println("Tour Type: "+rs.getString(4));
                            System.out.println("------------------------------------------------------");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
