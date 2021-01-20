package khosro;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import static java.awt.Color.*;

/**
 * makes display of calculator whit events handler.
 * this calculator just supports operators of " * - + / sin cos tan cot exp log"
 */
public class DisplayGUI extends JFrame{

    private int currentType;
    private Font fontKeyboard;
    private Font fontText;
    private Font fontBoldKey;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private ActionHandler actionHandler ;
    private boolean shift;

    /**
     * construct hole display.
     */
    public DisplayGUI ()
    {
        super("Calculator");
        fontKeyboard =new Font("Arial",Font.BOLD,17);
        fontText =new Font("Arial",Font.BOLD,25);
        fontBoldKey = new Font("Arial",Font.BOLD,20);
        currentType = 1;
        actionHandler = new ActionHandler();
        textArea1 = new JTextArea();
        textArea2 = new JTextArea();
        shift = false;

        makeFrame();
        makeMenuBar();
        makeTabsView();
    }

    /**
     * get current textarea .
     * @return textarea.
     */
    private JTextArea getCurrentTextArea ()
    {
        if(currentType == 1)
            return textArea1;
        else
            return textArea2;
    }


    /**
     * makes tabs of simple and scientific calculator display
     */
    private void makeTabsView()
    {
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel simpleCal =  new JPanel();
        JPanel scienceCal =  new JPanel();
        simpleCal.setLayout( new BorderLayout());
        scienceCal.setLayout( new BorderLayout());
        tabbedPane.add("simple",simpleCal);
        tabbedPane.add("scientific",scienceCal);
        tabbedPane.addChangeListener( new TabHandler());
        addTextArea(textArea1,simpleCal);
        makeKeyBoardPart(simpleCal);
        addTextArea(textArea2,scienceCal);
        makeKeyBoardPart(scienceCal);
        makeSincLeft(scienceCal);
        makeScienceRight(scienceCal);
        add(tabbedPane);
    }

    /**
     * make left part of scientific calculator display.
     * @param jPanel panel of scientific calculator.
     */
    private void makeSincLeft(JPanel jPanel)
    {
        JPanel operatorsScienceLeft = new JPanel();
        operatorsScienceLeft.setLayout( new GridLayout(8,1));
        Font fontLeft = new Font("Arial",Font.BOLD,fontKeyboard.getSize()-3);


        JButton s = new JButton("sin/cos");
        s.setToolTipText("'cos' if shift is down, else 'sin'");
        s.setActionCommand("sin/cos");
        s.setFont(fontLeft);
        s.setPreferredSize( new Dimension( 100,50));
        s.addActionListener(actionHandler);
        operatorsScienceLeft.add(s);

        JButton v = new JButton("tan/cot");
        v.setToolTipText("'cot' if shift is down, else 'tan'");
        v.addActionListener(actionHandler);
        v.setActionCommand("tan/cot");
        v.setFont(fontLeft);
        operatorsScienceLeft.add(v);

        JButton f = new JButton("asin/acos");
        f.setFont(fontLeft);
        operatorsScienceLeft.add(f);

        JButton h = new JButton("atan/acot");
        h.setFont(fontLeft);
        operatorsScienceLeft.add(h);

        JButton lnBt = new JButton("ln");
        lnBt.setFont(fontLeft);
        operatorsScienceLeft.add(lnBt);

        JButton expBt = new JButton("exp");
        expBt.setFont(fontLeft);
        expBt.setActionCommand("exp");
        expBt.addActionListener(actionHandler);
        operatorsScienceLeft.add(expBt);

        JButton logBt = new JButton("log");
        logBt.setFont(fontLeft);
        logBt.setActionCommand("log");
        logBt.addActionListener(actionHandler);
        operatorsScienceLeft.add(logBt);

        JButton o = new JButton("Shift");
        o.setToolTipText("switch between values of button like 'sin/cos'");
        o.setFont(fontLeft);
        o.setBackground(white);
        operatorsScienceLeft.add(o);
        o.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shift = !shift;
                if (shift) {
                    ((JButton) e.getSource()).setBackground(new Color(109,200,200));
                } else {
                    ((JButton) e.getSource()).setBackground(white);
                }
            }
        });
        jPanel.add(operatorsScienceLeft,BorderLayout.WEST);
    }

    /**
     * makes right part of scientific calculator.
     * @param jPanel panel of scientific calculator.
     */
    private void makeScienceRight(JPanel jPanel)
    {
        JPanel operatorsScienceRight = new JPanel();
        operatorsScienceRight.setLayout( new GridLayout(8,1));
        Font fontRight = new Font("Arial",Font.BOLD,fontKeyboard.getSize());

        JButton s = new JButton("10^x");
        s.setFont(fontRight);
        s.setPreferredSize( new Dimension( 85,50));
        operatorsScienceRight.add(s);

        JButton v = new JButton("n!");
        v.setFont(fontRight);
        operatorsScienceRight.add(v);

        JButton piBt = new JButton("\u03d6".toUpperCase());
        piBt.setToolTipText("3.14...");
        piBt.setFont(fontRight);
        piBt.setActionCommand("PI");
        piBt.addActionListener(actionHandler);
        operatorsScienceRight.add(piBt);

        JButton NpBt = new JButton("e");
        NpBt.setToolTipText("Napier's constant");
        NpBt.setFont(fontBoldKey);
        NpBt.setActionCommand("e");
        NpBt.addActionListener(actionHandler);
        operatorsScienceRight.add(NpBt);

        JButton xPowYBt = new JButton("x^y");
        xPowYBt.setFont(fontRight);
        operatorsScienceRight.add(xPowYBt);

        JButton squareBt = new JButton("x^2");
        squareBt.setFont(fontRight);
        operatorsScienceRight.add(squareBt);

        JButton absBt = new JButton("|x|");
        absBt.setFont(fontRight);
        operatorsScienceRight.add(absBt);

        JButton radicalBt = new JButton("\u221A".toUpperCase());
        radicalBt.setFont(fontRight);
        operatorsScienceRight.add(radicalBt);

        jPanel.add(operatorsScienceRight,BorderLayout.EAST);
    }


    /**
     * makes keyboard of calculator display. contains all buttons.
     * @param jPanel panel of calculator display.
     */
    private void makeKeyBoardPart(JPanel jPanel)
    {
        JPanel keyBoard = new JPanel();
        keyBoard.setLayout( new BorderLayout());
        jPanel.add(keyBoard,BorderLayout.CENTER);
        makeNumberPart(keyBoard);
        makeRightOperators(keyBoard);
        makeTopOperators(keyBoard);
        makeButtonOperators(keyBoard);
    }

    /**
     * makes bottom part of calculator.
     * @param keyboard keyboard panel.
     */
    private void makeButtonOperators(JPanel keyboard)
    {
        JPanel buttonOperators = new JPanel();
        buttonOperators.setLayout( new GridLayout(1,4));

        JButton dotBt = new JButton(".");
        dotBt.setFont(fontBoldKey);
        dotBt.setActionCommand(".");
        dotBt.addActionListener(actionHandler);
        dotBt.addKeyListener(actionHandler);
        dotBt.setPreferredSize( new Dimension( 200,100));
        buttonOperators.add(dotBt);

        JButton zeroBt = new JButton("0");
        zeroBt.setFont(fontBoldKey);
        zeroBt.setActionCommand("0");
        zeroBt.addActionListener(actionHandler);
        zeroBt.addKeyListener(actionHandler);
        buttonOperators.add(zeroBt);

        JButton parenthesesBt = new JButton("()");
        parenthesesBt.setActionCommand("()");
        parenthesesBt.addActionListener(actionHandler);
        parenthesesBt.addKeyListener(actionHandler);
        parenthesesBt.setFont(fontKeyboard);
        buttonOperators.add(parenthesesBt);

        JButton equalBt = new JButton("=");
        equalBt.setFont(fontBoldKey);
        equalBt.setActionCommand("=");
        equalBt.addActionListener(actionHandler);
        equalBt.addKeyListener(actionHandler);
        buttonOperators.add(equalBt);
        keyboard.add(buttonOperators,BorderLayout.SOUTH);
    }


    /**
     * makes numbers part of calculator.
     * @param keyboard keyboard panel.
     */
    private void makeNumberPart(JPanel keyboard) {
        JPanel keyBoardPanel =  new JPanel();
        keyBoardPanel.setLayout( new GridLayout(3,3));

        for(int j=2;j>=0; j--)
            for(int i=1 ; i<4 ; i++) {
                JButton button = new JButton("" + (i + j * 3));
                button.setFont(fontBoldKey);
                button.addKeyListener( actionHandler);
                button.setActionCommand(button.getText());
                button.addActionListener(actionHandler);
                keyBoardPanel.add(button);
            }
        keyboard.add(keyBoardPanel,BorderLayout.CENTER);
    }

    /**
     * makes top part of calculator's keyboard
     * @param keyboard keyboard panel.
     */
    private void makeTopOperators(JPanel keyboard)
    {
        JPanel topOperators = new JPanel();
        topOperators.setLayout( new GridLayout(1,4));
        JButton clearBt = new JButton("C");
        clearBt.setToolTipText("clear text");
        clearBt.setFont(fontKeyboard);
        clearBt.setActionCommand("C");
        clearBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea textArea = getCurrentTextArea();
                textArea.setText("");
            }
        });
        clearBt.addKeyListener(actionHandler);
        clearBt.setPreferredSize( new Dimension( 200,70));
        topOperators.add(clearBt);

        JButton deleteBt = new JButton("Del");
        deleteBt.setFont(fontKeyboard);
        deleteBt.setToolTipText("delete last char");
        deleteBt.setActionCommand("Del");
        deleteBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea textArea = getCurrentTextArea();
                String text = textArea.getText();
                textArea.setText(text.substring(0,text.length() - 1));
            }
        });
        deleteBt.addKeyListener(actionHandler);
        topOperators.add(deleteBt);

        JButton moduleBt = new JButton("%");
        moduleBt.setFont(fontBoldKey);
        topOperators.add(moduleBt);

        JButton multiplyBt = new JButton("\u00d7");
        multiplyBt.setFont(fontBoldKey);
        multiplyBt.setActionCommand("*");
        multiplyBt.addActionListener(actionHandler);
        multiplyBt.addKeyListener(actionHandler);
        topOperators.add(multiplyBt);

        keyboard.add(topOperators,BorderLayout.NORTH);
    }

    /**
     * make right part of keyboard.
     * @param keyboard keyboard panel
     */
    private void makeRightOperators(JPanel keyboard)
    {
        JPanel operators = new JPanel();
        operators.setLayout( new GridLayout(3,1));

        JButton divisionBt = new JButton("\u00f7");
        divisionBt.setPreferredSize( new Dimension( 100,200));
        divisionBt.setFont(fontBoldKey);
        divisionBt.addActionListener(actionHandler);
        divisionBt.addKeyListener(actionHandler);
        operators.add(divisionBt);
        divisionBt.setActionCommand("/");

        JButton summationBt = new JButton("+");
        summationBt.setFont(fontBoldKey);
        summationBt.addActionListener(actionHandler);
        summationBt.addKeyListener(actionHandler);
        operators.add(summationBt);
        summationBt.setActionCommand("+");

        JButton subtractionBt = new JButton("-");
        subtractionBt.setFont(fontBoldKey);
        subtractionBt.addActionListener(actionHandler);
        subtractionBt.addKeyListener(actionHandler);
        operators.add(subtractionBt);
        subtractionBt.setActionCommand("-");

        keyboard.add(operators,BorderLayout.EAST);
    }

    /**
     * makes menu bar.
     */
    private void makeMenuBar(){
        JMenuBar jMenuBar = new JMenuBar();
        JMenu menu = new JMenu("menu");
        JMenuItem exit =  new JMenuItem("Exit");
        exit.setMnemonic('E');
        exit.addActionListener(e -> System.exit(0));
        menu.add(exit);
        JMenuItem copy =  new JMenuItem("copy");
        copy.setAccelerator(KeyStroke.getKeyStroke("control C"));
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = getCurrentTextArea().getText();
                StringSelection stringSelection = new StringSelection(text);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });
        menu.add(copy);
        jMenuBar.add(menu);
        setJMenuBar(jMenuBar);
    }

    /**
     * makes frame .
     */
    private void makeFrame()
    {
        setSize(420,600);
        setLocation(500,100);
        //this.getContentPane().setBackground(BLACK);
        setResizable(false);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        addKeyListener(actionHandler);
    }

    /**
     * add textarea to north of specify panel
     * @param textArea text area to be added
     * @param jPanel panel that would contain this textarea.
     */
    private void addTextArea(JTextArea textArea,JPanel jPanel)
    {
        textArea.setForeground(BLACK);
        textArea.setFont(fontText);
        textArea.setText("");
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(20,90));
        jPanel.add(scrollPane,BorderLayout.NORTH);
        textArea.addKeyListener(actionHandler);
    }

    /**
     * handel the resizing of panel during change kind of calculator.
     */
    class TabHandler implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent e) {
            JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
            if(tabbedPane.getSelectedIndex() == 1) {
                setSize(590, 600);
                currentType = 2;
            }
            else {
                setSize(420, 600);
                currentType = 1;
            }
        }
    }

    /**
     * handel many of actions and keys.
     */
    private class ActionHandler extends KeyAdapter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doOperation(e.getActionCommand());
        }

        @Override
        public void keyPressed(KeyEvent e) {
            doOperation("" + e.getKeyChar());
        }

        /**
         * do changes in textarea depends on button name.
         * @param buttonName name of button. like "1" , "PI" , "sin/con" ...
         */
        private void doOperation(String buttonName)
        {
            String numbers = "0123456789.";
            String operators = "/*+-";
            String advancedOpers = "sin/cos tan/cot exp log";
            JTextArea textArea = getCurrentTextArea();

            if(numbers.contains(buttonName)) {
                System.out.println("d");
                if (!buttonName.equals(".") || (!textArea.getText().endsWith(" ") && !textArea.getText().endsWith(".")))
                {
                    textArea.append(buttonName);
                    System.out.println("d");
                }
            }
            else if(operators.contains(buttonName)) {
                if (!textArea.getText().endsWith(" "))
                    textArea.append(" " + buttonName + " ");
            }
            else if(advancedOpers.contains(buttonName))
            {
                switch (buttonName)
                {
                    case "sin/cos":
                    case "tan/cot":
                        String text;
                        if(!shift)
                            text = " " + buttonName.substring(0,buttonName.indexOf("/")) + "( ";
                        else
                            text = " " + buttonName.substring(buttonName.indexOf("/") + 1) + "( ";
                        textArea.append(text);
                        break;
                    case "exp":
                    case "log":
                        textArea.append( " " + buttonName + "( ");
                }
            }
            else if("()".contains(buttonName))
                addParentheses(textArea);
            else if(buttonName.equals("="))
            {
                String text = textArea.getText();
                String res = CalculatorOperation.calculate(text.substring(text.lastIndexOf("\n") + 1));
                textArea.append(" = " + res + "\n");
            }
            else if(buttonName.equals("PI"))
                textArea.append("PI");
            else if(buttonName.equals("e"))
                textArea.append("E");
        }

        /**
         * add "(" or ")" depends on text of textarea.
         * @param textArea text area to be examined.
         */
        private void addParentheses(JTextArea textArea)
        {
            String text = textArea.getText();
            if( text.endsWith(" "))
                textArea.append("( ");
            else
                textArea.append(" )");
        }
    }
}

