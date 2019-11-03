import java.sql.*;
import java.util.LinkedList;
import java.io.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;

public class Delete {
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
            // Get list of all subject's in isbn record
            Statement stmt = conn.createStatement();
            String query = "select subject.subject_name ";
            query += "from book join subjects on book.isbn = subjects.isbn join subject on subject.subject_id = subjects.s_id ";
            query = query + "where isbn = " + args[0].trim();
            ResultSet rset = stmt.executeQuery(query);
            LinkedList<String> allSubjects = new LinkedList<>();
            while(rset.next()){
                allSubjects.add(rset.getString(1));
            }

            // Loop through book's subjects to see if need to delete or not
            ListIterator<String> it = allSubjects.listIterator();
            while(it.hasNext()){
                String current = it.next();
                String query = "count(select subject.subject_name ";
                query += "from book join subjects on book.isbn = subjects.isbn join subject on subject.subject_id = subjects.s_id ";
                query = query + "where isbn = " + args[0].trim() + ")";
                ResultSet set = stmt.executeQuery(query);
                
                // Delete if count of that subject is equal to 0
                if(set.getString(1) == 0){
                    String getId = "select subject.subject_id from subject where subject.subject_name = " + current;
                    ResultSet idSet = stmt.executeQuery(query);
                    int id = Integer.parseInt(idSet.getString(1));
                    stmt.executeUpdate("delete from subjects where s_id = " + id);
                    stmt.executeUpdate("delete from subject where subject_name = " + current);
                }
            }
            stmt.executeUpdate("delete from book where isbn = " + args[0].trim());
            stmt.close();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        conn.close();
    }
}