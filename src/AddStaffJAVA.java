
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class AddStaffJAVA extends JFrame{
    
    private JLabel jlblName = new JLabel("Name");
    private JLabel jlblID = new JLabel("ID");
    private JLabel jlblCurrentID = new JLabel("ID");
    private JLabel jlblPassword = new JLabel("Password");
    private JLabel jlblPhone = new JLabel("Phone");
    private JLabel jlblEmail = new JLabel("Email");
    private JLabel jlblPosition = new JLabel("Position");
    private JTextField jtfName = new JTextField();
    private JTextField jtfID = new JTextField();
    private JTextField jtfPassword = new JTextField();
    private JTextField jtfPhone = new JTextField();
    private JTextField jtfEmail = new JTextField();
    private String[] Position = {"Manager","Staff","Deliveryman"};
    private JButton jbtConfirm = new JButton("Confirm");
    private ButtonGroup buttonGroup;
    private String radioButtonString;
     private JRadioButton[] jrbPosition = new JRadioButton[Position.length];
     
    AddStaffDA StaffDA=new AddStaffDA();
    
    
    
    public AddStaffJAVA(){
        ResultSet rs2 = StaffDA.getStaff();
     try{ while(rs2.next()){
            jlblCurrentID.setText("S"+(Integer.parseInt(rs2.getString(1).replaceAll("S",""))+1));
        }}catch(Exception ex){ JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);}
    JPanel jpNorth = new JPanel(new GridLayout(5,2));
        jpNorth.add(jlblID);
        jpNorth.add(jlblCurrentID);
        jpNorth.add(jlblName);
        jpNorth.add(jtfName);
        jpNorth.add(jlblPassword);
        jpNorth.add(jtfPassword);
        jpNorth.add(jlblPhone);
        jpNorth.add(jtfPhone);
        jpNorth.add(jlblEmail);
        jpNorth.add(jtfEmail);
        add(jpNorth,BorderLayout.NORTH);
       
        
        buttonGroup = new ButtonGroup();
        JPanel jpWest = new JPanel(new GridLayout(Position.length + 1,1));
        jpWest.add(jlblPosition);
        radioButtonListener rbtl = new radioButtonListener();
        for(int i = 0 ; i<Position.length ; i++){
        jrbPosition[i] = new JRadioButton(Position[i]);
        buttonGroup.add(jrbPosition[i]);
        jpWest.add(jrbPosition[i]);
        jrbPosition[i].addActionListener(rbtl);
    }
        
        add(jpWest,BorderLayout.WEST);      
        
        
        JPanel jpSouth = new JPanel();
        jpSouth.add(jbtConfirm);              
        add(jpSouth,BorderLayout.SOUTH);
        jbtConfirm.addActionListener(new ConfirmListener());
        
        setTitle("Register Staff System");
        setSize(650,300);
        setLocationRelativeTo(null);
        setVisible(true);
}
    private class ConfirmListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String StaffName=jtfName.getText();
            String StaffPassword=jtfPassword.getText();
            String StaffID=jtfID.getText();
            String StaffPhone=jtfPhone.getText();
            String StaffEmail=jtfEmail.getText();
            String StaffPosition=radioButtonString;
            ResultSet rs = StaffDA.getStaff();
           
            
        int returnVal = JOptionPane.showConfirmDialog(null,"Confirm register staff ?","Confirmation",JOptionPane.YES_NO_OPTION);
            if(returnVal == JOptionPane.YES_OPTION){
               
       try{
           int PreviousID=0;      
           JOptionPane.showMessageDialog(null, "Register Successfull");
                while(rs.next()){             
                       PreviousID=Integer.parseInt(rs.getString(1).replaceAll("S",""));             
                       
                } 
             
            StaffID="S"+(PreviousID+1);
           StaffDA.AddStaff(StaffID,StaffPassword,StaffName,StaffPhone,StaffEmail,StaffPosition);
           
           ClearText();
       }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
         
             }
            }
        }
    }
    private class radioButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        radioButtonString = e.getActionCommand();
                }
    }
    public void ClearText(){
        jtfName.setText("");
        jtfPassword.setText("");
        jtfID.setText("");
        jtfPhone.setText("");
        jtfEmail.setText("");
        buttonGroup.clearSelection();
        ResultSet rs3 = StaffDA.getStaff();
     try{ while(rs3.next()){
            jlblCurrentID.setText("S"+(Integer.parseInt(rs3.getString(1).replaceAll("S",""))+1));
        }}catch(Exception ex){ JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);}
    }
            
    public static void main(String[] args){
        new AddStaffJAVA();
    }
}
