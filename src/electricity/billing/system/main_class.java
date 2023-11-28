package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_class extends JFrame implements ActionListener {
    String acctype,meter_pass;
    main_class(String acctype,String meter_pass){
        this.meter_pass = meter_pass;
        this.acctype=acctype;
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon ebsIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/ebs.png"));
        Image Img = ebsIcon.getImage().getScaledInstance(1530,830,Image.SCALE_DEFAULT);
        ImageIcon ebsIcon2 = new ImageIcon(Img);
        JLabel ebsLable = new JLabel(ebsIcon2);
        ebsLable.setBounds(320,30,250,250);
        add(ebsLable);

        JMenuBar menubar= new JMenuBar();
        setJMenuBar(menubar);

        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("serif",Font.PLAIN, 15));


        JMenuItem newcustomer = new JMenuItem("New Customer");
        newcustomer.setFont(new Font("monospaced",Font.PLAIN, 14));
        ImageIcon customerIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/newcustomer.png"));
        Image cusImg = customerIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        newcustomer.setIcon(new ImageIcon(cusImg));
        newcustomer.addActionListener(this);
        menu.add(newcustomer);

        JMenuItem custDetails = new JMenuItem("Customer Details");
        custDetails.setFont(new Font("monospaced",Font.PLAIN, 14));
        ImageIcon custdetailsIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/customerDetails.png"));
        Image custdetailsImg = custdetailsIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        custDetails.setIcon(new ImageIcon(custdetailsImg));
        custDetails.addActionListener(this);
        menu.add(custDetails);


        JMenuItem depoDetails = new JMenuItem("Deposite Details");
        depoDetails.setFont(new Font("monospaced",Font.PLAIN, 14));
        ImageIcon depodetailsIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/depositDetails.png"));
        Image depodetailsImg = depodetailsIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        depoDetails.setIcon(new ImageIcon(depodetailsImg));
        depoDetails.addActionListener(this);
        menu.add(depoDetails);

        JMenuItem calculate = new JMenuItem("Calculate Bills");
        calculate.setFont(new Font("monospaced",Font.PLAIN, 14));
        ImageIcon calculateIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/calculator.png"));
        Image calculateImg = calculateIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        calculate.setIcon(new ImageIcon(calculateImg));
        calculate.addActionListener(this);
        menu.add(calculate);

       JMenu info = new JMenu("Information");
       info.setFont(new Font("serif",Font.PLAIN, 15));
       menubar.add(info);

       JMenuItem update = new JMenuItem("Update Information");
       update.setFont(new Font("monospaced",Font.PLAIN,14));
       ImageIcon updateIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/refresh.png"));
       Image updateImg = updateIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
       update.setIcon(new ImageIcon(updateImg));
       update.addActionListener(this);
       info.add(update);

        JMenuItem view = new JMenuItem("View Information");
        view.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon viewIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/information.png"));
        Image viewImg = viewIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        view.setIcon(new ImageIcon(viewImg));
        view.addActionListener(this);
        info.add(view);

        JMenu user = new JMenu("User");
        user.setFont(new Font("serif",Font.PLAIN, 15));

        JMenuItem pb = new JMenuItem("Pay Bills");// pay deatils
        pb.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon pbIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/pay.png"));
        Image pbImg = pbIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        pb.setIcon(new ImageIcon(pbImg));
        pb.addActionListener(this);
        user.add(pb);


        JMenuItem bd = new JMenuItem("Pay Bills"); //bill details
        bd.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon bdIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/detail.png"));
        Image bdImg = bdIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        bd.setIcon(new ImageIcon(bdImg));
        bd.addActionListener(this);
        user.add(bd);

        JMenu bill = new JMenu("Bill");
        bill.setFont(new Font("serif",Font.PLAIN, 15));

        JMenuItem generate = new JMenuItem("Generate bills"); //bill details
        generate.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon generateIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/bill.png"));
        Image generateImg = generateIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        generate.setIcon(new ImageIcon(generateImg));
        generate.addActionListener(this);
        bill.add(generate);

        JMenu utility = new JMenu("Utility");
        utility.setFont(new Font("serif",Font.PLAIN, 15));

        JMenuItem notepad = new JMenuItem("Notepad"); //bill details
        notepad.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/notepad.png"));
        Image notepadImg = notepadIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(notepadImg));
        notepad.addActionListener(this);
        utility.add(notepad);

        JMenuItem calculator = new JMenuItem("Calculator"); //bill details
        calculator.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon calculatorIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/calculator.png"));
        Image calculatorImg = calculatorIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(calculatorImg));
        calculator.addActionListener(this);
        utility.add(calculator);

        JMenu exit = new JMenu("Exit");
        exit.setFont(new Font("serif",Font.PLAIN, 15));

        JMenuItem eexit = new JMenuItem("Exit"); //bill details
        eexit.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon eexitIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/exit.png"));
        Image eexitImg = eexitIcon.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        eexit.setIcon(new ImageIcon(eexitImg));
        eexit.addActionListener(this);
        exit.add(eexit);

        if(acctype.equals("Admin")) {
            menubar.add(menu);

        }else {
            menubar.add(bill);
            menubar.add(user);
            menubar.add(info);
        }
        menubar.add(utility);
        menubar.add(exit);







        setLayout(new FlowLayout());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = e.getActionCommand();
        if(msg.equals("New Customer")){
            new newCustomer();
        }else if(msg.equals("Customer Details")){
            new customer_details();
        }else if(msg.equals("Deposite Details")){
            new deposit_details();
        }else if(msg.equals("Calculate Bills")){
            new calculateBill();
        }else if(msg.equals("View Information")){
            new view_info("meter_pass");
        }
    }

    public static void main(String[] args) {
        new main_class("","");
    }
}
