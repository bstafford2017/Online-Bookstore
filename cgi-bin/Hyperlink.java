import java.sql.*;
import java.io.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;

public class Hyperlink {
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
            String query = "select book.isbn, book.title, book.price, subject.subject_name from book join subjects on subjects.isbn = book.isbn join subject subject.subject_id = subjects.s_id where book.isbn = " + args[0].trim();
            ResultSet rset = stmt.executeQuery(query);
            int counter = 0;
            while(rset.next()){
                if(counter == 0) {
                    System.out.println("ISBN: "+ rset.getString(1) + "\nTitle: " + rset.getString(2) + "\nPrice: " + rset.getString(3) + rset.getString(4));
                } else {
                    System.out.println(rset.getString(4));
                }
            }
            rset.close();
            stmt.close();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        conn.close();
    }
}