package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class meterInfo extends JFrame implements ActionListener {
    JLabel meterNum,ml,meterText, mltype,pc,bl;
    TextField mlText;
    Choice mlChoice, mltypeChoice,pcChoice,blChoice;
    JButton submit;
    String meterNo;
    meterInfo(String meterNo){
        this.meterNo = meterNo;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252,186,3));
        add(panel);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(heading);

        JLabel meterNum = new JLabel("Meter Number");
        meterNum.setBounds(50,120,100,20);
        panel.add(meterNum);

        meterText = new JLabel(meterNo);
        meterText.setBounds(180,120,150,20);
        panel.add(meterText);

        ml = new JLabel("Meter Location"); // Meter Location
        ml.setBounds(50,160,100,20);
        panel.add(ml);

        mlChoice = new Choice();
        mlChoice.add("Outside");
        mlChoice.add("Inside");
        mlChoice.setBounds(180,160,150,20);
        panel.add(mlChoice);

        mltype= new JLabel("Meter Type"); // Meter Location Type
        mltype.setBounds(50,200,100,20);
        panel.add(mltype);

        mltypeChoice = new Choice();
        mltypeChoice.add("Electric Meter");
        mltypeChoice.add("Solar Meter");
        mltypeChoice.add("Smart Meter");
        mltypeChoice.setBounds(180,200,150,20);
        panel.add(mltypeChoice);

        pc= new JLabel("Meter Type"); // phase code
        pc.setBounds(50,240,100,20);
        panel.add(pc);

        pcChoice = new Choice();
        pcChoice.add("011");
        pcChoice.add("022");
        pcChoice.add("033");
        pcChoice.add("044");
        pcChoice.add("055");
        pcChoice.add("066");
        pcChoice.add("077");
        pcChoice.add("088");
        pcChoice.add("099");
        pcChoice.setBounds(180,240,150,20);
        panel.add(pcChoice);

        bl= new JLabel("Bill Type"); // phase code
        bl.setBounds(50,280,100,20);
        panel.add(bl);

        blChoice = new Choice();
        blChoice.add("Normal");
        blChoice.add("Industrial");
        blChoice.setBounds(180,280,150,20);
        panel.add(blChoice);

        JLabel day= new JLabel("30 days billing time...");
        day.setBounds(50,320,150,20);
        panel.add(day);

        JLabel note= new JLabel("Note:- By default bill is Calculated for 30 days Only");
        note.setBounds(50,360,400,20);
        panel.add(note);

        submit= new JButton("Submit");
        submit.setBackground(Color.black);
        submit.setBounds(120,390,100,25);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        panel.add(submit);



        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icon/details2.png"));
        Image i2 = i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel i4 = new JLabel(i3);
        add(i4,"East");



        setSize(700,500);
        setLocation(400,200);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){
            String smeterno = meterNo;
            String sml = mlChoice.getSelectedItem();
            String smltype = mltypeChoice.getSelectedItem();
            String spc = pcChoice.getSelectedItem();
            String sbtype = blChoice.getSelectedItem();
            String sday = "30";

            String query_meter  = "insert into meter values('"+smeterno+"', '"+sml+"', '"+smltype+"', '"+spc+"', '"+sbtype+"', '"+sday+"')";
            try{
                database c = new database();
                c.statement.executeUpdate(query_meter);

                JOptionPane.showMessageDialog(null, "Meter info submitted successfully");
                setVisible(false);
            }catch(Exception E){
                E.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
      new meterInfo("");
    }
}
