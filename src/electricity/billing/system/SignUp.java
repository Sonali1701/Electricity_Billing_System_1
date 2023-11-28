package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class SignUp extends JFrame implements ActionListener {
    JTextField employText,meterText,userText,nameText,passText;
    Choice loginAs;
    JButton create,back;
    SignUp(){
        super("Signup Page");
        getContentPane().setBackground(new Color(168,203,255));

        JLabel createAs= new JLabel("Create Account As");
        createAs.setBounds(30,50,125,20);
        add(createAs);

        loginAs = new Choice();
        loginAs.setBounds(170,50,120,20);
        loginAs.add("Admin");
        loginAs.add("Customer");
        add(loginAs);

        JLabel meterNo= new JLabel("Meter Number");
        meterNo.setBounds(30,100,125,20);
        meterNo.setVisible(false);
        add(meterNo);

        meterText = new JTextField();
        meterText.setBounds(170,100,125,20);
        meterText.setVisible(false);
        add(meterText);

        JLabel employNo= new JLabel("Employer ID");
        employNo.setBounds(30,100,125,20);
        employNo.setVisible(true);
        add(employNo);

        employText = new JTextField();
        employText.setBounds(170,100,125,20);
        employText.setVisible(true);
        add(employText);

        JLabel username = new JLabel("UserName");
        username.setBounds(30,140,125,20);
        add(username);

        userText=new JTextField();
        userText.setBounds(170,140,125,20);
        add(userText);

        JLabel name = new JLabel("Name");
        name.setBounds(30,180,125,20);
        add(name);

        nameText=new JTextField("");
        nameText.setBounds(170,180,125,20);
        add(nameText);

        meterText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

                }


            @Override
            public void focusLost(FocusEvent e) {
                 try{
                    database c = new database();
                    ResultSet result = c.statement.executeQuery("select * from SignUp  where meter_no = '"+meterText.getText()+"'");
                    if (result.next()) {
                        nameText.setText(result.getString("name"));
                    }
                }catch(Exception E) {
                     E.printStackTrace();

                 }
            }
        });

        JLabel password = new JLabel("Password");
        password.setBounds(30,220,125,20);
        add(password);

        passText = new JTextField();
        passText.setBounds(170,220,125,20);
        add(passText);

        loginAs.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginAs.getSelectedItem();
                if(user.equals("Customer")){
                    employNo.setVisible(false);
                    nameText.setEditable(false);
                    employText.setVisible(false);
                    meterNo.setVisible(true);
                    meterText.setVisible(true);
                }else{
                    employNo.setVisible(true);
                    employText.setVisible(true);
                    meterNo.setVisible(false);
                    meterText.setVisible(false);
                }
            }
        });

         create= new JButton("Create");
         create.setBackground(new Color(66,127,219));
         create.setForeground(Color.black);
         create.setBounds(50,285,100,25);
         create.addActionListener(this);
         add(create);

        back= new JButton("Back");
        back.setBackground(new Color(66,127,219));
        back.setForeground(Color.black);
        back.setBounds(180,285,100,25);
        back.addActionListener(this);
        add(back);


        ImageIcon boyIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/img_1.png"));
        Image boyImg = boyIcon.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon boyIcon2 = new ImageIcon(boyImg);
        JLabel boyLable = new JLabel(boyIcon2);
        boyLable.setBounds(320,30,250,250);
        add(boyLable);



        setSize(600,380);
        setLocation(500,200);
        setLayout(null);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==create){
            String sloginAs = loginAs.getSelectedItem();
            String susername = userText.getText();
            String sname = nameText.getText();
            String spass = passText.getText();
            String smeter = meterText.getText();
           try{
                database c = new database();
                String query = null;
              //  if(loginAs.equals("Admin")) {
                    query = "insert into SignUp value('"+smeter+"', '"+susername+"', '"+sname+"', '"+spass+"', '"+sloginAs+"')";
               /* }else{
                    query = "update SignUp set username = '"+susername+"', password = '"+spass+"', usertype = '"+sloginAs+"' where meter_no = '"+smeter+"'";
                }*/

                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Created Account");
                setVisible(false);
                new Login();

           }catch(Exception E){
                E.printStackTrace();
            }
        }else if(e.getSource()==back){
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
         new SignUp();
    }

}
