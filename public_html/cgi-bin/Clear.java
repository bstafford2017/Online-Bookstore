import java.sql.*;
import java.io.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;
import java.util.Arrays;
import java.util.stream.Stream;

public class Create {
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
            ResultSet rset1 = stmt.executeQuery("truncate table subjects cascade");
            ResultSet rset2 = stmt.executeQuery("truncate table book cascade");
            ResultSet rset3 = stmt.executeQuery("truncate table store_users cascade");
            ResultSet rset4 = stmt.executeQuery("truncate table subject cascade");
            rset1.close();
            rset2.close();
            rset3.close();
            rset4.close();
            stmt.close();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        conn.close( );
    }
}