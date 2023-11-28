package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;

public class deposit_details extends JFrame implements ActionListener {
    Choice searchCho, monthCho;
    JTable table;
    JButton searchButton,print,close;
    deposit_details(){
        super("Deposit Details");
        getContentPane().setBackground(new Color(0, 253, 165));
        setSize(700,500);
        setLocation(400,200);
        setLayout(null);

        JLabel search = new JLabel("Search By Meter Number");
        search.setBounds(20,20,150,20);
        add(search);

        searchCho = new Choice();
        searchCho.setBounds(180,20,150,20);
        add(searchCho);

        try{
            database c = new database();
            ResultSet result = c.statement.executeQuery("select * from bill");
            while(result.next()){
                searchCho.add(result.getString("meter_no"));

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel month = new JLabel("Search By Name");
        month.setBounds(400,20,100,20);
        add(month);

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
        monthCho.setBounds(520,20,150,20);
        add(monthCho);



        table = new JTable();
        try{
            database c= new database();
            ResultSet result = c.statement.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(result));
        }catch(Exception e){
            e.printStackTrace();
        }


        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(0,100,700,500);
        scroll.setBackground(Color.WHITE);
        add(scroll);

        searchButton = new JButton("Search");
        searchButton.setBounds(20,70,80,20);
        searchButton.setBackground(Color.WHITE);
        searchButton.addActionListener(this);
        add(searchButton);

        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.setBackground(Color.WHITE);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBounds(600,70,80,20);
        close.setBackground(Color.WHITE);
        close.addActionListener(this);
        add(close);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==searchButton){
            String query_search = "select * from bill where meter_no = '"+searchCho.getSelectedItem()+"' and month = '"+monthCho.getSelectedItem()+"'";
            try{
                database c= new database();
                ResultSet result = c.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(result));

            }catch(Exception E){
                E.printStackTrace();
            }
        }else if(e.getSource()==print){
            try {
                table.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new deposit_details();
    }
}
