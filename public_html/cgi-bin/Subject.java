import java.sql.*;
import java.io.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;
import java.util.Arrays;
import java.util.stream.Stream;

public class Subject {
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
            ResultSet rset = stmt.executeQuery("select subject_name from subject");
            System.out.println("Content-type: text/html");
            int rowCounter = 0;
            while(rset.next()){
                System.out.println("<input type=\"checkbox\" name=\"subject\">" +  rset.getString(1) + "</input>");
                rowCounter++;
            }
            if(rowCounter == 0){
                System.out.println("<html><body><p>No results!</p></body></html>");
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
