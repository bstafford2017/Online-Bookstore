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
            /* Each have a value
               Assumed order is: {isbn, title, price, subject1, subject2, ...} */
            
            // Loop through all subjects
            ResultSet rset = null;
            for(int i = 3; i < args.length; i++){
                String getSubjectId = "select subject_id from subject where subject_name = '" + args[i];
                System.out.println(getSubjectId);
                rset = stmt.executeQuery(getSubjectId);
                String subjectId = rset.getString(1);
                if(subjectId == null || subjectId.equals("")) break;
                // If subject is already in table
                if(rset.next()){
                    // Only needs to be inserted into subjects (joining table)
                    String insertSubjects = "insert into subjects(isbn, s_id) values (" + args[0] + ", '" + subjectId + "')";
                    System.out.println(insertSubjects);
                    stmt.executeQuery(insertSubjects);
                } else {
                    // Insert into subject (stores names)
                    String insertSubject = "insert into subject(subject_name) values ('" + args[i] + "')";
                    System.out.println(insertSubject);
                    stmt.executeQuery(insertSubject);
                    // Insert into subjects (joining table)
                    String insertSubjects = "insert into subjects(isbn, s_id) values (" + args[0] + ", " + subjectId + ")";
                    System.out.println(insertSubjects);
                    stmt.executeQuery(insertSubjects);
                }
            }
            // Finally, insert into books table
            String insertBook = "insert into (isbn, title, price) values (" + args[0] + ", '" + args[1] + "', " + args[2] + ")";
            System.out.println(insertBook);
            stmt.executeQuery(insertBook);
            rset.close();
            stmt.close();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        conn.close( );
    }
}
