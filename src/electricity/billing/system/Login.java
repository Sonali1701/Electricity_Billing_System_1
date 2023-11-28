package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField userText,passText;
    Choice loginChoice;
    JButton loginbtn,cancelbtn,signbtn;
    Login(){
        super("Login");//it should always be 1st statement

        JLabel username = new JLabel("UserName");
        username.setBounds(300,60,100,20);
        add(username);

        userText=new JTextField();
        userText.setBounds(400,60,150,20);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(300,100,100,20);
        add(password);

        passText = new JTextField();
        passText.setBounds(400,100,150,20);
        add(passText);

        JLabel loginInAs = new JLabel("Login In As");
        loginInAs.setBounds(300,140,100,20);
        add(loginInAs);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(400,140,150,20);
        add(loginChoice);

        loginbtn = new JButton("Login");
        loginbtn.setBounds(330,180,100,20);
        loginbtn.addActionListener(this);
        add(loginbtn);

        cancelbtn = new JButton("Cancel");
        cancelbtn.setBounds(460,180,100,20);
        cancelbtn.addActionListener(this);
        add(cancelbtn);

        signbtn = new JButton("Signin");
        signbtn.setBounds(400,210,100,20);
        signbtn.addActionListener(this);
        add(signbtn);

        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("Icon/img.png"));
        Image profileTwo = profileOne.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon(profileTwo);
        JLabel profileLable = new JLabel(fprofileOne);
        profileLable.setBounds(5,5,250,250);
        add(profileLable);



        setSize(640,300);
        setLocation(400,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== loginbtn){
            String susername = userText.getText();
            String spass = passText.getText();
            String suser= loginChoice.getSelectedItem();

            try{
              database c = new database();
              String query =" select * from SignUp where username = '"+susername+"' and password = '"+spass+"' and usertype = '"+suser+"'";
              ResultSet result = c.statement.executeQuery(query);

              if(result.next()){
                  String meter = result.getString("meter_no");
                  setVisible(false);
                  new main_class(suser,meter);
              }else{
                  JOptionPane.showMessageDialog(null,"Invalid Login");
              }

            }catch(Exception E) {
                E.printStackTrace();;
            }

        }else if(e.getSource()==cancelbtn){
            setVisible(false);
        }else if(e.getSource()==signbtn){
            setVisible(false);
            new SignUp();
        }
    }

    public static void main(String[] args) {
        new Login();
    }

}
