import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

class login extends JFrame implements ActionListener {
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField textField1, textField2;

    login() {
        userLabel = new JLabel();
        userLabel.setText("      Username :");
        textField1 = new JTextField(15);
        passLabel = new JLabel();
        passLabel.setText("      Password :");
        textField2 = new JPasswordField(8);
        b1 = new JButton("   SUBMIT   ");
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(b1);
        add(newPanel, BorderLayout.CENTER);
        b1.addActionListener(this);
        setTitle("Login");
    }

    public void actionPerformed(ActionEvent ae) {
        String userValue = textField1.getText();
        String passValue = textField2.getText();
        if (!passValue.equals(""))
            new OnlineExamBegin(userValue);
        else {
            textField2.setText("Enter Password");
            actionPerformed(ae);
        }
    }
}

class OnlineExamBegin extends JFrame implements ActionListener {
    JLabel l;
    JLabel l1;
    JRadioButton jb[] = new JRadioButton[6];
    JButton b1, b2, log;
    ButtonGroup bg;
    int co = 0, c = 0, x = 1, y = 1, now = 0;
    int m[] = new int[10];
    Timer timer;
    JLabel timerLabel; 

    OnlineExamBegin(String s) {
        super(s);
        l = new JLabel();
        l1 = new JLabel();
        add(l);
        add(l1);
        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            jb[i] = new JRadioButton();
            add(jb[i]);
            bg.add(jb[i]);
        }
        b1 = new JButton("Save & Next");
        b2 = new JButton("Save for later");
        b1.addActionListener(this);
        b2.addActionListener(this);
        add(b1);
        add(b2);
        set();
        l.setBounds(30, 40, 450, 20);
        l1.setBounds(20, 20, 450, 20);
        jb[0].setBounds(50, 80, 100, 20);
        jb[1].setBounds(50, 110, 100, 20);
        jb[2].setBounds(50, 140, 100, 20);
        jb[3].setBounds(50, 170, 100, 20);
        b1.setBounds(95, 240, 140, 30);
        b2.setBounds(270, 240, 150, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);

        timerLabel = new JLabel();
        timerLabel.setFont(new Font("Agency FB", Font.BOLD, 15));
        timerLabel.setBounds(500, 10, 100, 30);
        add(timerLabel);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int minutes = 20;
            int seconds = 0;

            public void run() {
                if (seconds == 0 && minutes == 0) {
                    timer.cancel();
                    autoSubmit();
                } else {
                    timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
                    if (seconds == 0) {
                        seconds = 59;
                        minutes--;
                    } else {
                        seconds--;
                    }
                }
            }
        }, 0, 1000);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if (check())
                co++;
            c++;
            set();
            if (c == 9) {
                b1.setEnabled(false);
                b2.setText("Total Result");
            }
        }
        if (e.getActionCommand().equals("Save for later")) {
            JButton bk = new JButton("Review" + x);
            bk.setBounds(480, 20 + 30 * x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            m[x] = c;
            x++;
            c++;
            set();
            if (c == 9)
                b2.setText("Total Result");
            setVisible(false);
            setVisible(true);
        }
        for (int i = 0, y = 1; i < x; i++, y++) {
            if (e.getActionCommand().equals("Review" + y)) {
                if (check())
                    co++;
                now = c;
                c = m[y];
                set();
                ((JButton) e.getSource()).setEnabled(false);
                c = now;
            }
        }
        if (e.getActionCommand().equals("Total Result")) {
            if (check())
                co++;
            c++;
            JOptionPane.showMessageDialog(this, "Score =" + co);
            closeSession();
        }
    }

    void set() {
        jb[4].setSelected(true);
        switch (c) {
            case 0:
                l.setText("Question 1: Which component is used to compile, debug and execute the java programs?");
                jb[0].setText("JRE");
                jb[1].setText("JIT");
                jb[2].setText("JDK");//
                jb[3].setText("JVM");
                break;
            case 1:
                l.setText("Question 2: Which one of the following is not a Java feature?");
                jb[0].setText("Object-oriented");
                jb[1].setText("Use of pointers");//
                jb[2].setText("Portable");
                jb[3].setText(" Dynamic and Extensible");
                break;
            case 2:
                l.setText("Question 3: Which of these cannot be used for a variable name in Java?");
                jb[0].setText("identifier & keyword");
                jb[1].setText("identifier");
                jb[2].setText("keyword");//
                jb[3].setText("None");
                break;
            case 3:
                l.setText("Question 4: What is the extension of java code files?");
                jb[0].setText(".js");
                jb[1].setText(".txt");
                jb[2].setText(".class");//
                jb[3].setText(" .java");
                break;
            case 4:
                l.setText("Question 5: What is not the use of “this” keyword in Java?");
                jb[0].setText("Referring to the instance variable when a local variable has the same name");
                jb[1].setText("Passing itself to the method of the same class");//
                jb[2].setText("Passing itself to another method");
                jb[3].setText("Calling another constructor in constructor chaining");
                break;
            case 5:
                l.setText("Question 6: Which exception is thrown when java is out of memory?");
                jb[0].setText("MemoryError");
                jb[1].setText(" OutOfMemoryError");//
                jb[2].setText(" MemoryOutOfBoundsException");
                jb[3].setText("MemoryFullException");
                break;
            case 6:
                l.setText("Question 7: Which of these are selection statements in Java? ");
                jb[0].setText("break");
                jb[1].setText("continue");
                jb[2].setText(" for()");
                jb[3].setText("if()");//
                break;
            case 7:
                l.setText("Question 8: Which of these keywords is used to define interfaces in Java?");
                jb[0].setText("intf");
                jb[1].setText("Intf");
                jb[2].setText("interface");//
                jb[3].setText("Interface");
                break;
            case 8:
                l.setText("Question 9: Which of the following is a superclass of every class in Java? ");
                jb[0].setText("ArrayList");
                jb[1].setText("Abstract class");
                jb[2].setText("Object class");//
                jb[3].setText("String");
                break;
            case 9:
                l.setText("Question 10: Which of these keywords are used for the block to be examined for exceptions? ");
                jb[0].setText("check");
                jb[1].setText("throw");
                jb[2].setText("catch");
                jb[3].setText(" try");//
                break;
        }
        l.setBounds(30, 40, 450, 20);
        for (int i = 0, j = 0; i <= 90; i += 30, j++)
            jb[j].setBounds(50, 80 + i, 200, 20);
    }

    boolean check() {
        switch (c) {
            case 0:
                return jb[2].isSelected();
            case 1:
                return jb[1].isSelected();
            case 2:
                return jb[2].isSelected();
            case 3:
                return jb[2].isSelected();
            case 4:
                return jb[2].isSelected();
            case 5:
                return jb[1].isSelected();
            case 6:
                return jb[3].isSelected();
            case 7:
                return jb[2].isSelected();
            case 8:
                return jb[2].isSelected();
            case 9:
                return jb[3].isSelected();
            default:
                return false;
        }
    }

    void closeSession() {
        timer.cancel();
        JOptionPane.showMessageDialog(this, "Closing this panel...");
        System.exit(0); 
    }

    void autoSubmit() {
        if (check())
            co++;
        JOptionPane.showMessageDialog(this, "Your time is up! Score =" + co);
        closeSession();
    }
}

class OnlineExamation {
    public static void main(String args[]) {
        try {
            login form = new login();
            form.setSize(400, 150);
            form.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
