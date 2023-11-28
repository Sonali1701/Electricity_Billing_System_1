package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class Splash extends JFrame {


    Splash() {
        URL imageURL = ClassLoader.getSystemResource("Icon/Splash.jpg");
       if(imageURL != null){
           ImageIcon imageIcon = new ImageIcon(imageURL);
          // ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/splash/Splash.jpg"));
           Image imageOne = imageIcon.getImage().getScaledInstance(400,400,Image.SCALE_DEFAULT);
           ImageIcon imageIcon2 = new ImageIcon(imageOne);
           JLabel imageLabel = new JLabel(imageIcon2);
           add(imageLabel);
       } else{
           System.out.println("Image not found");
       }

        setSize(600, 400);
        setLocation(500, 200);
        setVisible(true);

        try{
           Thread.sleep(3000);
           setVisible(false);
           new Login();
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        new Splash();
    }

 }




















