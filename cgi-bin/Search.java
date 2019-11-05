import java.sql.*;
import java.io.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.stream.Stream;

class Tuple {
    int count;
    long isbn;
    String title;
    double price;
    String subjects;

    public Tuple(int count, long isbn, String title, double price, String subjects){
        this.count = count;
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
        Statement stmt = conn.createStatement();
        try {
            String query = "book.isbn, book.title, book.price, subject.subject_name ";
            query += "from book join subjects on book.isbn = subjects.isbn join subject on subject.subject_id = subjects.s_id ";
            for(int i = 0; i < args.length; i++){
                if(i == 0){
                    query += "where ";
                }
                query = query + "(book.title like '%" + args[i].trim().replace("-", " ") + "%' or subject.subject_name like '%" + args[i].trim().replace("-", " ") + "%' or book.isbn like '%" +  args[i].trim().replace("-", " ") + "%' or book.price like '%" + args[i].trim().replace("-", " ") + "%')";
                // Always add 'and' to end if not the last iteration
                if(i < args.length - 1){
                    query += " and ";
                }
            }
            ResultSet rset = stmt.executeQuery("select " + query);
            ResultSet temp = stmt.executeQuery("select count(subject.subject_name) " + query);
            int count = temp.getString(1);
            LinkedList<Tuple> list = new LinkedList<>();
            while(rset.next()){
                //if(!Search.containsOrAdd(list, rset.getString(4), Long.parseLong(rset.getString(1)))){
                    list.add(new Tuple(count, Long.parseLong(rset.getString(1)), rset.getString(2), Double.parseDouble(rset.getString(3)), rset.getString(4)));
                //}
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
                System.out.println("<td id=\"title\" scope=\"col\"><a href=\"cgi-bin/hyperlink.cgi?isbn=" + current.isbn + "\">" + current.title + "</a></td>");
                System.out.println("<td id=\"price\" scope=\"col\">" + current.price + "</td>");
                System.out.println("<td id=\"subjects\" scope=\"col\"><a href=\"cgi-bin/hyperlink.cgi?subjects=" + current.subjects.replace(" ", "-") + "\">" + current.subjects + "</a></td></tr>");
                rowCounter++;
            }
            stmt.close();
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        conn.close();
    }

    // True = added to list (already in list)
    // False = not in list (add to list)
    public static boolean containsOrAdd(LinkedList<Tuple> list, String subject, long isbn){
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
        if(!flag) return false;

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
