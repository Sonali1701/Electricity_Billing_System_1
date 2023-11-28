package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class customer_details extends JFrame implements ActionListener {
     Choice searchCho, nameCho;
     JTable table;
     JButton searchButton,print,close;
    customer_details(){
      super("Customer Details");
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
          ResultSet result = c.statement.executeQuery("select * from newCustomer");
          while(result.next()){
              searchCho.add(result.getString("meterno"));

          }
      }catch(Exception e){
          e.printStackTrace();
      }

        JLabel name = new JLabel("Search By Name");
        name.setBounds(400,20,100,20);
        add(name);

        nameCho = new Choice();
        nameCho.setBounds(520,20,150,20);
        add(nameCho);

        try {
            database c = new database();
            ResultSet result = c.statement.executeQuery("select * from newCustomer");
            while (result.next()) {
                nameCho.add(result.getString("name"));
            }
        }catch(Exception e){
            e.printStackTrace();
            }


        table = new JTable();
        try{
            database c= new database();
            ResultSet result = c.statement.executeQuery("select * from newCustomer");
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
            String query_search = "select * from newCustomer where meterno = '"+searchCho.getSelectedItem()+"' and name = '"+nameCho.getSelectedItem()+"'";
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
        new customer_details();
    }
}
