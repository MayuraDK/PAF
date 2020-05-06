package model;
import java.sql.*;
public class App {
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/App", "root", "1234");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertApp(int app_id, String pat_id, String DOC_id, String time, String date)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into App3(`app_id`,`pat_id`,`DOC_id`,`time`,`date`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, app_id);
	 preparedStmt.setString(2, pat_id);
	 preparedStmt.setString(3, DOC_id);
	 preparedStmt.setString(4, time); 
	 preparedStmt.setString(5, date);
	
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the Appointment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readApp()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>Appointment</th><th>Patiant</th><th>Doctor</th><th>Time</th><th>Date</th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from App3";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String app_id = Integer.toString(rs.getInt("app_id"));
	 String pat_id = rs.getString("pat_id");
	 String DOC_id = rs.getString("DOC_id");
	 String time = rs.getString("time");
	 String date = rs.getString("date");
	 // Add into the html table
	 output += "<tr><td>" + app_id + "</td>";
	 output += "<td>" + pat_id + "</td>";
	 output += "<td>" + DOC_id + "</td>";
	 output += "<td>" + time + "</td>";
	 output += "<td>" + date + "</td>";
	 // buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"App.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
	 + "<input name=\"app_id\" type=\"hidden\" value=\"" + app_id
	 + "\">" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the Apps.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	public String updateApp(String app_id, String pat_id, String DOC_id, String time, String date)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE App3 SET pat_id=?,DOC_id=?,time=?,date=? WHERE app_id=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1,pat_id );
	 preparedStmt.setString(2,DOC_id );
	 preparedStmt.setString(3, time );
	 preparedStmt.setString(4,date );
	 preparedStmt.setInt(5, Integer.parseInt(app_id));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the Appointment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deleteApp(String app_id)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from App3 where app_id=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(app_id));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the Appointment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
}
