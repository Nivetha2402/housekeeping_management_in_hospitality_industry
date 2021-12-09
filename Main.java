/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class Main {
            static int i=0,z=0;
           
        public static JButton b;
      static JRadioButton radio[]=new JRadioButton[10];
      static JRadioButton radio2[]=new JRadioButton[10];

  
    public static class MesurePanel extends JPanel implements ActionListener {
        private final ButtonGroup group;
        static int count=0;
        public MesurePanel(int m,final String mesure,
                           final String... options) { 
            super(new BorderLayout());
            super.add(new JLabel(mesure, JLabel.CENTER), BorderLayout.LINE_START);
            final JPanel lineEnd = new JPanel();
            group = new ButtonGroup();
            
            
                radio2[i]=new JRadioButton(options[0]);
                radio[i]= new JRadioButton(options[1]);
                group.add(radio2[i]);
                group.add(radio[i]);
                lineEnd.add(radio2[i]);
                lineEnd.add(radio[i]);
           
           
            super.add(lineEnd, BorderLayout.CENTER);
             z=m;
            count=count+1;
            if(count==m)
            {
               
                JPanel borderPanel = new JPanel(new BorderLayout());
                
             b=new JButton("submit");
            JPanel flowPanel = new JPanel(new FlowLayout());
            flowPanel.add(b);
            borderPanel.add(BorderLayout.CENTER, flowPanel);
            b.addActionListener(this);
            
           add(flowPanel,BorderLayout.EAST);
            }
          
            System.out.println(i);
             i=i+1;
        
       }
                         
public void actionPerformed(ActionEvent ae)
{
    try{
     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing","root","9787021313");
    Statement stmt = connection.createStatement();
     

   
    i=0;
   
    for (String s:a)
     {
         System.out.println(i);
                 if(radio2[i].isSelected())
    {
        String sql;
                     sql = "update  names set  attandace='"+radio2[i].getText()+"' where id ='"+s+"'";
    // Execute deletion
    stmt.executeUpdate(sql);
        System.out.println(radio2[i].getText());
        
    }  
                 else if(radio[i].isSelected())
    {
         String sql;
                     sql = "update  names set  attandace='"+radio[i].getText()+"' where id ='"+s+"'";
    // Execute deletion
    stmt.executeUpdate(sql);
        System.out.println(radio[i].getText());
        
    
    }
                 else
                 {
                     JOptionPane.showMessageDialog(null,"some radio buttons are unfilled","Error",JOptionPane.ERROR_MESSAGE);
                 
                 }
   
     i=i+1;
     }
    }catch(Exception e)
    {
        System.out.println(e);
    }
JOptionPane.showMessageDialog(null,"saved");
}
           
                
        
        
    }
   static  ArrayList<String> a = new ArrayList<>();

    public static void main(final String[] args) {
       
      
        

          ArrayList<String> mesures = new ArrayList<>();
        
         try{
         java.lang.Class.forName("com.mysql.jdbc.Driver");

            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/billing","root","9787021313");

           Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from names ");
            int count=0;
        while(rs.next())
        {
             a.add(rs.getString("id"));
            String s1=rs.getString("id")+"          "+rs.getString("name");
            System.out.println(s1);
           
            mesures.add(s1);
            
            }}catch(Exception e)
            {
            System.out.println(e);
            }
            
                
        //Create options for each mesure:
        JPanel contents = new JPanel(new GridLayout(0, 1)); //1 column, any number of rows...
        mesures.forEach(mesure -> contents.add(new MesurePanel(mesures.size(),mesure, "present", "absent")));
         JScrollPane scroll = new JScrollPane(contents);
        scroll.setPreferredSize(new Dimension(400, 200));
        
       
         JFrame frame = new JFrame("List of Employee name.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scroll);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
}