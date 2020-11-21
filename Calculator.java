
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class Calculator{
    private JFrame frame;
    private int shiftState = 0;
    public Calculator(){
        
        frame = new JFrame("AUT Calculator");
        frame.setSize(420,550);
        frame.setLocation(100,200);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel simplePanel = new JPanel(new BorderLayout(1, 1));
        //simplePanel.setBorder(new EmptyBorder(1, 1, 1, 1));

        JTextArea simpleDisplay = new JTextArea(3,10); 
        simpleDisplay.setEditable(true); 
        simpleDisplay.setFont(new Font("Arial", 15,15));
        JScrollPane scrollPane = new JScrollPane(simpleDisplay); 
        scrollPane.setPreferredSize(new Dimension(100, 170)); 
        scrollPane.setLocation(50,20); 
        simplePanel.add(scrollPane);

        JPanel Keyboard = new JPanel();
        
        Keyboard.setLocation(10,150);
        Keyboard.setLayout(new GridLayout(4,4));       
        Keyboard.setSize(400,400);
        JButton btn7 = new JButton(); 
        btn7.setText("7"); 
        Keyboard.add(btn7); 
        JButton btn8 = new JButton(); 
        btn8.setText("8"); 
        Keyboard.add(btn8); 
        JButton btn9 = new JButton(); 
        btn9.setText("9"); 
        Keyboard.add(btn9); 
        JButton sumBtn = new JButton(); 
        sumBtn.setText("+"); 
        Keyboard.add(sumBtn);     
        JButton btn4 = new JButton(); 
        btn4.setText("4"); 
        Keyboard.add(btn4); 
        JButton btn5 = new JButton(); 
        btn5.setText("5"); 
        Keyboard.add(btn5); 
        JButton btn6 = new JButton(); 
        btn6.setText("6"); 
        Keyboard.add(btn6); 
        JButton minBtn = new JButton(); 
        minBtn.setText("-"); 
        Keyboard.add(minBtn); 
        JButton btn1 = new JButton(); 
        btn1.setText("1"); 
        Keyboard.add(btn1); 
        JButton btn2 = new JButton(); 
        btn2.setText("2"); 
        Keyboard.add(btn2); 
        JButton btn3 = new JButton(); 
        btn3.setText("3"); 
        Keyboard.add(btn3); 
        JButton crosBtn = new JButton(); 
        crosBtn.setText("*"); 
        Keyboard.add(crosBtn); 
        JButton taqsimBtn = new JButton(); 
        taqsimBtn.setText("/"); 
        Keyboard.add(taqsimBtn); 
        JButton zeroBtn = new JButton(); 
        zeroBtn.setText("0"); 
        Keyboard.add(zeroBtn); 
        JButton doBtn = new JButton(); doBtn.setText("="); 
        Keyboard.add(doBtn);
        JButton restBtn = new JButton(); 
        restBtn.setText("%"); 
        Keyboard.add(restBtn); 
        Keyboard.setPreferredSize(new Dimension(300,300));
        simplePanel.add(scrollPane, BorderLayout.NORTH);
        simplePanel.add(Keyboard, BorderLayout.SOUTH);       

        JPanel proPanel = new JPanel(new BorderLayout(1, 1));
        proPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
        proPanel.setSize(400, 400);
        
        JTextArea proDisplay = new JTextArea(3,10); 
        proDisplay.setEditable(false); 
        proDisplay.setFont(new Font("Arial", 12,12));
        JScrollPane scrollPanepro = new JScrollPane(proDisplay); 
        scrollPanepro.setPreferredSize(new Dimension(100, 100)); 
        scrollPanepro.setLocation(50,20); 
        simplePanel.add(scrollPanepro);


        JPanel proKeyboard = new JPanel();
        
        proKeyboard.setLocation(10,120);
        proKeyboard.setLayout(new GridLayout(4,5));
        proKeyboard.setPreferredSize(new Dimension(200, 200));

        JButton pbtn7 = new JButton(); 
        pbtn7.setText("7"); 
        proKeyboard.add(pbtn7); 
        JButton pbtn8 = new JButton(); 
        pbtn8.setText("8"); 
        proKeyboard.add(pbtn8); 
        JButton pbtn9 = new JButton(); 
        pbtn9.setText("9"); 
        proKeyboard.add(pbtn9); 
        JButton psumBtn = new JButton(); 
        psumBtn.setText("+"); 
        proKeyboard.add(psumBtn);      
        JButton shift = new JButton(); 
        shift.setText("shift"); 
        proKeyboard.add(shift); 
        JButton pbtn4 = new JButton(); 
        pbtn4.setText("4"); 
        proKeyboard.add(pbtn4); 
        JButton pbtn5 = new JButton(); 
        pbtn5.setText("5"); 
        proKeyboard.add(pbtn5); 
        JButton pbtn6 = new JButton(); 
        pbtn6.setText("6"); 
        proKeyboard.add(pbtn6); 
        JButton pminBtn = new JButton(); 
        pminBtn.setText("-"); 
        proKeyboard.add(pminBtn);
        if(shiftState == 0){
            JButton PI = new JButton(); 
            PI.setText("PI"); 
            proKeyboard.add(PI); 
        }   
        else{
            JButton E = new JButton(); 
            E.setText("e"); 
            proKeyboard.add(E); 
        }      
        JButton pbtn1 = new JButton(); 
        pbtn1.setText("1"); 
        proKeyboard.add(pbtn1); 
        JButton pbtn2 = new JButton(); 
        pbtn2.setText("2"); 
        proKeyboard.add(pbtn2); 
        JButton pbtn3 = new JButton(); 
        pbtn3.setText("3"); 
        proKeyboard.add(pbtn3); 
        JButton pcrosBtn = new JButton(); 
        pcrosBtn.setText("*"); 
        proKeyboard.add(pcrosBtn); 
        if(shiftState == 0){
            JButton log = new JButton(); 
            log.setText("log"); 
            proKeyboard.add(log); 
        }   
        else{
            JButton exp = new JButton(); 
            exp.setText("exp"); 
            proKeyboard.add(exp); 
        } 
        JButton ptaqsimBtn = new JButton(); 
        ptaqsimBtn.setText("/"); 
        proKeyboard.add(ptaqsimBtn); 
        JButton pdoBtn = new JButton(); 
        doBtn.setText("="); 
        JButton prestBtn = new JButton(); 
        prestBtn.setText("%"); 
        proKeyboard.add(prestBtn);
        JButton pzeroBtn = new JButton(); 
        pzeroBtn.setText("0"); 
        proKeyboard.add(pzeroBtn); 
         
        
        if(shiftState == 0){
            JButton tan = new JButton(); 
            tan.setText("tan"); 
            proKeyboard.add(tan); 
        }   
        else{
            JButton cot = new JButton(); 
            cot.setText("cot"); 
            proKeyboard.add(cot); 
        } 
         
        if(shiftState == 0){
            JButton sin = new JButton(); 
            sin.setText("sin"); 
            proKeyboard.add(sin); 
        }   
        else{
            JButton cos = new JButton(); 
            cos.setText("cos"); 
            proKeyboard.add(cos); 
        } 
        pdoBtn.setPreferredSize(new Dimension(100,50));
        pdoBtn.setText("=");
        proKeyboard.setPreferredSize(new Dimension(100,50));
        proPanel.add(scrollPanepro, BorderLayout.NORTH);
        proPanel.add(proKeyboard, BorderLayout.CENTER);
        proPanel.add(pdoBtn,BorderLayout.SOUTH);

        JTabbedPane tp = new JTabbedPane();  
        tp.setBounds(1,1,400,500); 
        tp.add("pro",proPanel);  
        tp.add("simple",simplePanel);  
         
 
        frame.add(tp);    
        frame.setVisible(true);  
    }

    public static void main(String[] args) {
        Calculator cal = new Calculator();
    }

}
