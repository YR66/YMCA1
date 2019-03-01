package YMCa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }*/

/** application.properties en onderstaande autowired (hoort in controller) werkt niet meer sinds aanpassing naar Restcontroller.
 * Vraag: waar ligt dat aan?

@Autowired
private DataSource dataSource;

private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
        }
*/

    public class Application {
        static String HOST_NAME = "jdbc:mysql://localhost:3306/offerte?serverTimezone= Europe/Amsterdam";
        static String USER_NAME = "root";
        static String PASSWORD = "houTk@ch3L";

        public static void main(String[] args) {
            /**       if (args.length != 3) {
             System.out.println ("Usage: java -jar database-uri username password");
             System.exit(0);
             }*/
            Application.HOST_NAME = "jdbc:mysql://localhost:3306/offerte?serverTimezone= Europe/Amsterdam";
            Application.USER_NAME = "root";
            Application.PASSWORD = "houTk@ch3L";

            SpringApplication.run(Application.class, args);
        }

}