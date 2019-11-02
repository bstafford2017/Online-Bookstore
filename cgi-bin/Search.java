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
            String query = "select book.isbn, book.title, book.price, subject.subject_name ";
            query += "from book join subjects on book.isbn = subjects.isbn join subject on subject.subject_id = subjects.s_id ";
            if(args.length != 0){
                query = query + "where book.isbn = " + args[0].trim() + " or book.price = " + args[0].trim() + " or book.title = '" +  args[0].trim() + "' or subject.subject_name = '" + args[0].trim() + "'";
                query = query + "where book.title = '" + args[0].trim() + "' or subject.subject_name = '" + args[0].trim() + "' or book.isbn = " +  args[0].trim() + " or book.price = " + args[0].trim();
            }
            System.out.println(query);
            ResultSet rset = stmt.executeQuery(query);
            LinkedList<Tuple> list = new LinkedList<>();
            while(rset.next()){
                if(!Search.containsOrAdd(list, rset.getString(4), Integer.parseInt(rset.getString(1)))){
                    list.add(new Tuple(Integer.parseInt(rset.getString(1)), rset.getString(2), Double.parseDouble(rset.getString(3)), rset.getString(4)));
                }
            }
            if(list.size() == 0){
                System.out.println("<p>No results!</p>");
            }
            Iterator<Tuple> it = list.iterator();
            int rowCounter = 0;
            while(it.hasNext()){
                Tuple current = it.next();
                System.out.println("<tr id=\"" + rowCounter + "\" scope=\"col\">");
                System.out.println("<td id=\"isbn\">" + current.isbn + "</td>");
                System.out.println("<td id=\"title\" scope=\"col\"><a href=\"javascript:submit();\">" + current.title + "</a></td>");
                System.out.println("<td id=\"price\" scope=\"col\">" + current.price + "</td>");
                System.out.println("<td id=\"subjects\" scope=\"col\">" + current.subjects + "</td></tr>");
                rowCounter++;
            }
            rset.close();
            stmt.close();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        conn.close();
    }

    // True = added to list (already in list)
    // False = not in list (add to list)
    public static boolean containsOrAdd(LinkedList<Tuple> list, String subject, int isbn){
        // Check if 'subject' is in list
        Iterator<Tuple> it = list.iterator();
        boolean flag = false;
        while(it.hasNext()){
            Tuple current = it.next();
            if(current.isbn == isbn && current.subjects.contains(subject)){
                flag = true;
            }
        }
        
        // If in list, return avoid inserting into list
        if(flag) return false;

        // If in in list, add 'subject' into isbn's record
        Iterator<Tuple> newit = list.iterator();
        while(it.hasNext()){
            Tuple current = newit.next();
            if(current.isbn == isbn){
                current.subjects += subject;
                return true;
            }
        }
        return false;
    }
}