import java.sql.*;
import java.io.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;
import java.util.Arrays;
import java.util.stream.Stream;

public class SearchBook {
    public static void main(String[] args) throws SQLException{
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
            String query = "select (isbn, title, price, subject) from book join subjects on book.isbn = subjects.isbn join subject on subject.subject_id = book.subject";
            if(args.length != 0){
                Arrays.stream(args).forEach(x -> query = "book.isbn ='" + x.trim() + "' book.price = '" + x.trim() + "' book.title = '" +  x.trim() + "'");
            }
            query += ");";
            ResultSet rset = stmt.executeQuery(query);
            rset.close();
            stmt.close();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        conn.close( );
    }
}