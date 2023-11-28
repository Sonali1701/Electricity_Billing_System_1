  package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class calculateBill extends JFrame implements ActionListener {
    Choice mnCho,monthCho;
    JLabel nameText,addText;
    TextField unitText;
    JButton submit,cancel;
    calculateBill(){

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214,195,247));
        add(panel);

        JLabel heading = new JLabel("CaLculate Electricity Bill");
        heading.setBounds(70,10,300,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(heading);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(50,80,100,20);
        panel.add(meterNo);

        mnCho = new Choice();
        try{
            database c = new database();
            ResultSet result = c.statement.executeQuery("select * from newCustomer");
            while (result.next()) {
                mnCho.add(result.getString("meterNo"));
            }
        }catch(Exception E){
          E.printStackTrace();
        }
        mnCho.setBounds(180,80,100,20);
        panel.add(mnCho);

        JLabel name = new JLabel("Name");
        name.setBounds(50,120,100,20);
        panel.add(name);


        nameText = new JLabel("");
        nameText.setBounds(180,120,150,20);
        panel.add(nameText);


        JLabel address = new JLabel("Address");
        address.setBounds(50,160,150,20);
        panel.add(address);

        addText = new JLabel("");
        addText.setBounds(180,160,100,20);
        panel.add(addText);

        try{
            database c = new database();
            ResultSet result = c.statement.executeQuery("select * from newCustomer where meterNo = '"+mnCho.getSelectedItem()+"' ");
            while (result.next()) {
                nameText.setText(result.getString("name"));
                addText.setText(result.getString("address"));
            }
        }catch(Exception E){
            E.printStackTrace();;
        }

        mnCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    database c = new database();
                    ResultSet result = c.statement.executeQuery("select * from newCustomer where meterNo = '"+mnCho.getSelectedItem()+"' ");
                    while (result.next()) {
                        nameText.setText(result.getString("name"));
                        addText.setText(result.getString("address"));
                    }
                }catch(Exception E){
                    E.printStackTrace();;
                }
            }
        });

        JLabel unit=new JLabel("Unit Consumed");
        unit.setBounds(50,200,100,20);
        panel.add(unit);

        unitText = new TextField();
        unitText.setBounds(180,200,150,20);
        panel.add(unitText);

        JLabel month= new JLabel("Month");
        month.setBounds(50,240,100,20);
        panel.add(month);

        monthCho = new Choice();
        monthCho.add("January");
        monthCho.add("February");
        monthCho.add("March");
        monthCho.add("April");
        monthCho.add("May");
        monthCho.add("June");
        monthCho.add("July");
        monthCho.add("August");
        monthCho.add("September");
        monthCho.add("October");
        monthCho.add("November");
        monthCho.add("December");
        monthCho.setBounds(180,240,150,20);
        panel.add(monthCho);

        submit = new JButton("Submit");
        submit.setBounds(80,300,100,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(220,300,100,25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel,"Center");
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("Icon/budget.png"));
        Image i2 = i1.getImage().getScaledInstance(250,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel i4 = new JLabel(i3);
        add(i4,"East");


        setSize(650,400);
        setLocation(400,200);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==submit){
           String smeter = mnCho.getSelectedItem();
           String sunit = unitText.getText();
           String smonth = monthCho.getSelectedItem();

           int total = 0;
           int unit = Integer.parseInt(sunit);
           String query = "select * from tax";

           try{
               database c= new database();
               ResultSet result = c.statement.executeQuery(query);
               while(result.next()){
                   total += unit * Integer.parseInt(result.getString("cost_per_unit"));
                   total += Integer.parseInt(result.getString("meter_rent"));
                   total += Integer.parseInt(result.getString("service_charge"));
                   total += Integer.parseInt(result.getString("service_tax"));
                   total += Integer.parseInt(result.getString("swachh_bharat"));
                   total += Integer.parseInt(result.getString("fixed_tax"));




               }
           }catch(Exception E){
               E.printStackTrace();
           }
           String query_bill= "insert into bill values('"+smeter+"',  '"+smonth+"', '"+sunit+"', '"+total+"', 'Not Paid')";
           try{
               database c= new database();
               c.statement.executeUpdate(query_bill);

               JOptionPane.showMessageDialog(null,"Customer Bill Updated Successfully");
               setVisible(false);
           }catch(Exception E){
               E.printStackTrace();
           }
       }else{
           setVisible(false);
       }
    }

    public static void main(String[] args) {
        new calculateBill();
    }
}
