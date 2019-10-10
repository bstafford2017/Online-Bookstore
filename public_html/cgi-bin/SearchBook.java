import java.sql.*;
import java.io.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;
import java.util.Arrays;
import java.util.stream.Stream;

public class SearchBook {
    public static void main(String[] args) throws SQLException {
        String user     = "C##benjamin.stafford";
        String password = "stafford6248";
        String database = "65.52.222.73:1521/cdb1";
        OracleDataSource ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@" + database);
        ods.setUser(user);
        ods.setPassword(password);
        Connection conn = ods.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String query = "select book.isbn, book.title, book.price, subject.subject_name ";
            query += "from book inner join subjects on book.isbn = subjects.isbn inner join subject on subject.subject_id = book.subject";
            if(args.length != 0){
                query = "where book.isbn =" + args[0].trim() + " or book.price = " + args[0].trim() + " or book.title = " +  args[0].trim() + " or subject.subject_name = " + args[0].trim();
            }
            System.out.println(query);
            ResultSet rset = stmt.executeQuery(query);
            System.out.println("Content-type: text/html");
            System.out.println("<html><head></head><body>");
            int rowCounter = 0;
            while(rset.next()){
                System.out.println("<p> " + rset.getString(1) + "</p>");
                rowCounter++;
            }
            if(rowCounter == 0){
                System.out.println("<p>No results!</p>");
            }
            System.out.println("</body></html>");
            rset.close();
            stmt.close();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        conn.close( );
    }
}