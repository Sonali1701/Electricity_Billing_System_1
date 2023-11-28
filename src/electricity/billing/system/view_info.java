package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class view_info extends JFrame {
    String view;
    view_info(String view){
        this.view=view;
        setBounds(350,150,850,650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(250,0,500,40);
        heading.setFont(new Font("serif",Font.BOLD,20));
        add(heading);

        JLabel name= new JLabel("Name");
        name.setBounds(70,80,100,20);
        add(name);

        JLabel nameText= new JLabel("");
        nameText.setBounds(200,80,150,20);
        add(nameText);


        JLabel meter= new JLabel("Meter Number");
        meter.setBounds(70,140,100,20);
        add(meter);

        JLabel meterText= new JLabel("");
        meterText.setBounds(200,140,150,20);
        add(meterText);

        JLabel address= new JLabel("Address");
        address.setBounds(70,200,100,20);
        add(address);

        JLabel addressText= new JLabel("");
        addressText.setBounds(200,200,150,20);
        add(addressText);

        JLabel city= new JLabel("City");
        city.setBounds(70,260,100,20);
        add(city);

        JLabel cityText= new JLabel("");
        cityText.setBounds(200,260,150,20);
        add(cityText);

        JLabel state= new JLabel("State");
        state.setBounds(500,80,100,20);
        add(state);

        JLabel stateText= new JLabel("");
        stateText.setBounds(600,80,150,20);
        add(stateText);


        JLabel email= new JLabel("Email");
        email.setBounds(500,140,100,20);
        add(email);

        JLabel emailText= new JLabel("");
        emailText.setBounds(600,140,150,20);
        add(emailText);

        JLabel phone= new JLabel("Phone");
        phone.setBounds(500,200,100,20);
        add(phone);

        JLabel phoneText= new JLabel("");
        phoneText.setBounds(600,200,150,20);
        add(phoneText);

        try{
            database c = new database();
            ResultSet result = c.statement.executeQuery("select * from newCustomer where meterno = '"+view+"'");
            if(result.next()){
                nameText.setText(result.getString("name"));
                meterText.setText(result.getString("meterno"));
                addressText.setText(result.getString("address"));
                cityText.setText(result.getString("city"));
                stateText.setText(result.getString("state"));
                emailText.setText(result.getString("email"));
                phoneText.setText(result.getString("phone_no"));


            }
        }catch(Exception e){
            e.printStackTrace();
        }

        setVisible(true);
    }
    public static void main(String[] args) {
        new view_info("");
    }
}
