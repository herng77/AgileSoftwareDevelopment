
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class AddStaffDA {
    private String host = "jdbc:derby://localhost:1527/RESTAURANTDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "STAFF";
    private Connection conn;
    private PreparedStatement stmt;
  
    public AddStaffDA() {
        createConnection();
  
    }
     public void AddStaff(String ID, String PASSWORD, String NAME, String PHONE, String EMAIL, String POSITION) throws ParseException,SQLException{
         String InsertStaffStr = "INSERT INTO STAFF VALUES(?, ?, ?, ?, ?, ?, ?)";      
         try{
              stmt = conn.prepareStatement(InsertStaffStr);
              
              stmt.setString(1,ID);
              stmt.setString(2,PASSWORD);
              stmt.setString(3,NAME);
              stmt.setString(4,PHONE);
              stmt.setString(5,EMAIL);
              stmt.setString(6,POSITION);
              stmt.setString(7,"AVAILABLE");
              stmt.executeUpdate();
              
         }catch(SQLException ex){
             JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
         }
     }
     
     public ResultSet getStaff(){
         
          String queryID = "SELECT StaffID FROM " + tableName;
          ResultSet rs = null;
         try{
             stmt = conn.prepareStatement(queryID);
             rs = stmt.executeQuery(); 
         }catch(SQLException ex){
             ex.getMessage();
         }
         return rs;
     }
    
    
    
    
     private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***Hostel: Connection established.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
   
    private void shutDown() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}
