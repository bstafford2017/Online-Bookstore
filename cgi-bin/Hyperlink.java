import java.sql.*;
import java.io.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.stream.Stream;

class Tuple {
    int isbn;
    String title;
    double price;
    String subjects;

    public Tuple(int isbn, String title, double price, String subjects){
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.subjects = subjects;
    }
}

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
            String query = "select book.isbn, book.title, book.price from book where isbn = " + args[0].trim();
            ResultSet set = stmt.executeQuery(query);
            while(set.next()){
                System.out.println(set.getString(1) + "\n" + set.getString(2) + "\n" + set.getString(3));
            }
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        rset.close();
        stmt.close();
        conn.close();
    }
}