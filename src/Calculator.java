import  java.awt.*;
import  javax.swing.*;
import javax.swing.border.BevelBorder;
import  java.awt.event.*;
import java.io.IOException;

class MyFrame extends JFrame
{
    MyFrame()
    {
        super("Calculator");
        setSize(300,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        ImageIcon icon = new ImageIcon("images\\icon.png");
        setIconImage(icon.getImage());

    }
}
public class Calculator extends MyFrame implements ActionListener , KeyListener {

     JPanel button_panel;
     JButton [] operators = new JButton[24];
     Container container;
     JTextArea box;
     String button_labels[] = {"%","CE","C","clear","x^-1","x^2","sqrt(x)","/","7","8","9","x","4","5","6","-","1","2","3","+"," +/-","0",".","="};
     StringBuffer  ans ;

     Calculator() throws IOException {
        container = getContentPane();
        ans = new StringBuffer();
        box = new JTextArea();
        box.setBounds(5,5,275,125);
        box.setFont(new Font("Arial",Font.BOLD,20));
        box.setLineWrap(true);
        box.setBackground(Color.white);
        box.setEditable(false);
        container.add(box);


        GridBagLayout g  = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        button_panel = new JPanel();
        button_panel.setLayout(g);
        button_panel.setBounds(-5,100,300,400);

        Color my_color = new Color(117, 134, 197);
        container.setBackground(my_color);
        button_panel.setBackground(my_color);


        int in=0;
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<4;j++){

                gbc.gridx =j;
                gbc.gridy =i;
                gbc.ipadx = 5;
                gbc.ipady = 5;

                operators[in] = new JButton(button_labels[in]);
                operators[in].setMargin(new Insets(20,20,20,20));
                operators[in].setBorder(new BevelBorder(BevelBorder.RAISED));
                operators[in].setForeground(Color.white);
                operators[in].setBackground(new Color(76, 76, 80));

                if(in==3)
                    operators[in].setText("<-");


                if(in<8 || j==3) {

                     operators[in].setBackground(new Color(76, 76, 80));
                }
                else{
                    operators[in].setBackground(new Color(112, 112, 119));
                }

                if(in==button_labels.length-1)
                {
                    operators[in].setBackground(new Color(230, 170, 253));
                    operators[in].setForeground(Color.black);
                }

                operators[in].setPreferredSize(new Dimension(63,45));

                operators[in].addActionListener(this);
                operators[in].addKeyListener(this);

                button_panel.add(operators[in],gbc);


                in++;

            }

        }

        button_panel.addKeyListener(this);
        box.addKeyListener(this);
        container.add(button_panel);
        setVisible(true);
    }

    @Override
    public  void actionPerformed(ActionEvent e)
    {
        int total_button  = operators.length;
        if(e.getSource()==operators[1])   //for CE
        {
            int len = ans.length();
            if(len>0)
            {
                int index=0;
                for(int i = len-1;i>=0;i--)
                {
                    if(ans.charAt(i)=='+' || ans.charAt(i)=='-' || ans.charAt(i)=='*' || ans.charAt(i)=='/')
                    {
                        index=i+1;
                        break;
                    }
                }
                ans = new StringBuffer(ans.substring(0,index));
            }
            box.setText(new String(ans));
        }
        else if(e.getSource()==operators[total_button-4])   //for negation
        {
            int len = ans.length();
            if(len>0) {
                if (ans.charAt(0) == '-') {
                    ans.replace(0, 1, "+");
                } else {
                    ans.insert(0, '-');
                }
                box.setText(new String(ans));
            }
        }
       else if(e.getSource()==operators[6])   //for square root
        {

            double ans_before_square_root = EvaluateExpression.evaluate(new String(ans));
            String new_exp =String.valueOf(Math.sqrt(ans_before_square_root));

            box.setText(new_exp);
            ans = new StringBuffer(new_exp);

        }
        else if(e.getSource()==operators[5])   //for square root
        {

            double ans_before_square = EvaluateExpression.evaluate(new String(ans));
            String new_exp =String.valueOf(ans_before_square*ans_before_square);
            box.setText(new_exp);
            ans = new StringBuffer(new_exp);

        }
        else if(e.getSource()==operators[4])  //for the 1/x
        {
            String new_exp = "1" + "/(" + new String(ans) +")";
            box.setText(String.valueOf(EvaluateExpression.evaluate(new_exp)));
            ans = new StringBuffer(new_exp);

        }
        else
        if(e.getSource()==operators[3])         //fot clear button to remove one element
        {

            int len = ans.length();
            if(len!=0) {
                ans.replace(len - 1, len, "");
                box.setText(new String(ans));
            }
        }
        else if(e.getSource()==operators[2])         //for button C to clear the output
        {
            ans = new StringBuffer(" ");
            box.setText(new String(ans));
        }
        else if(e.getSource()==operators[total_button-1])   //for button = to evaluate string
        {
            String a = String.valueOf(EvaluateExpression.evaluate(new String(ans)));
            box.setText(a);
            ans = new StringBuffer(a);
        }
        else
        {
           for (JButton operator : operators) {
            if (e.getSource() == operator) {
                if(operator.getText().equals("x"))
                {
                    ans.append("*");
                }
                else{
                    ans.append(operator.getText());
                }
            }
           }
            box.setText(new String(ans));
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        for (int i = 0; i < 24; i++) {
            if(e.getKeyChar()=='*')
            {
                ans.append("*");
                box.setText(new String(ans));
                operators[11].setBackground(new Color(125, 125, 131));
                break;
            }
            else
            if (e.getKeyChar() == operators[i].getText().charAt(0)) {
                String st = Character.toString(e.getKeyChar());
                ans.append(new StringBuffer(st));
                box.setText(new String(ans));
                operators[i].setBackground(new Color(125, 125, 131));
                break;
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {

        //evaluate the answer
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            String a = String.valueOf(EvaluateExpression.evaluate(new String(ans)));
            box.setText(a);
            ans = new StringBuffer(a);

        }
        //fot clear the number by pressing backspace button
        if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)
        {

            int len = ans.length();
            if(len!=0) {
                ans.replace(len - 1, len, "");
                box.setText(new String(ans));
            }
        }
        System.out.println("I pressed");
        System.out.println(ans);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int in=0;
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<4;j++){
                if (e.getKeyChar() == operators[in].getText().charAt(0) || e.getKeyChar()=='*' && in!=23 ) {
                    if(in<8 || j==3 ) {
                        operators[in].setBackground(new Color(76, 76, 80));
                    }
                    else{
                        operators[in].setBackground(new Color(112, 112, 119));
                    }

                }
                in++;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new Calculator();
    }
}
