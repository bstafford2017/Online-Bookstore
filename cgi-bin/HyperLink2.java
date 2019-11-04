import java.sql.*;
import java.io.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;

public class Hyperlink2 {
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
            String query = "select book.title, book.isbn, subject.subject_name from book join subjects on subjects.isbn = book.isbn join subject on subject.subject_id = subjects.s_id where subject.subject_name like '%" + args[0].trim().replace("-", " ") + "%'";
            System.out.println(query);
            ResultSet rset = stmt.executeQuery(query);
            int counter = 0;
            while(rset.next()){
                if(counter == 0) {
                    System.out.println("<p>Title: <a href=\"cgi-bin/hyperlink.cgi?isbn="+ rset.getString(2) + "\">" + rset.getString(1) + "</a></p><p>Subjects: " + rset.getString(3) + "</p>");
                } else {
                    System.out.println(rset.getString(4));
                }
                counter++;
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