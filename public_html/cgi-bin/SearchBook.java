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
            String query = "select isbn, title, price, subject from book inner join subjects on book.isbn = subjects.isbn join subject on subject.subject_id = book.subject";
            if(args.length != 0){
                query = "book.isbn ='" + args[0].trim() + "' book.price = '" + args[0].trim() + "' book.title = '" +  args[0].trim() + "'";
            }
            query += ");";
            ResultSet rset = stmt.executeQuery(query);
            System.out.println("Content-type: text/html");
            System.out.println("<html><head></head><body>");
            if(rset.next() == null) System.out.println("No results!");
            while(rset.next()){
                System.out.println("<p> " + rset.getString(1) + "</p>");
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