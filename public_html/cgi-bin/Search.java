import java.sql.*;
import java.io.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;
import java.util.Arrays;
import java.util.stream.Stream;

public class Search {
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
            query += "from book inner join subjects on book.isbn = subjects.isbn inner join subject on subject.subject_id = subjects.s_id ";
            if(args.length != 0){
                query = query + "where book.isbn =" + args[0].trim() + " or book.price = " + args[0].trim() + " or book.title = '" +  args[0].trim() + "' or subject.subject_name = '" + args[0].trim() + "'";
            }
            ResultSet rset = stmt.executeQuery(query);
            int rowCounter = 0;
            while(rset.next()){
                System.out.println("<tr id=\" + rowCounter + \">");
                System.out.println("<td>" + rset.getString(1) + "</td>");
                System.out.println("<td><a href=\"#\">" + rset.getString(2) + "</a></td>");
                System.out.println("<td>" + rset.getString(3) + "</td>");
                System.out.println("<td>" + rset.getString(4) + "</td></tr>");
                rowCounter++;
            }
            if(rowCounter == 0){
                System.out.println("<p>No results!</p>");
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