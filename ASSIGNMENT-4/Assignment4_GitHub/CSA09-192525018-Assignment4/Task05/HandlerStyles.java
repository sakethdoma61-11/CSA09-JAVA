import java.awt.event.*;
import javax.swing.*;
public class HandlerStyles extends JFrame implements ActionListener {
    private final JTextField amount=new JTextField(8); private final JLabel result=new JLabel(" ");
    public HandlerStyles(){
        super("Handler styles"); JButton frameButton=new JButton("Frame listener");
        JButton innerButton=new JButton("Named inner"); JButton anonButton=new JButton("Anonymous"); JButton lambdaButton=new JButton("Lambda");
        frameButton.addActionListener(this);
        innerButton.addActionListener(new NamedHandler());
        anonButton.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){convert();}});
        lambdaButton.addActionListener(e->convert());
        setLayout(new java.awt.FlowLayout());add(amount);add(frameButton);add(innerButton);add(anonButton);add(lambdaButton);add(result);
        setDefaultCloseOperation(EXIT_ON_CLOSE);pack();
    }
    public void actionPerformed(ActionEvent e){convert();}
    private class NamedHandler implements ActionListener{public void actionPerformed(ActionEvent e){convert();}}
    private void convert(){try{result.setText(String.format("%.2f",Double.parseDouble(amount.getText())*0.012));}catch(Exception e){result.setText("Invalid amount");}}
    public static void main(String[] a){SwingUtilities.invokeLater(()->new HandlerStyles().setVisible(true));}
}
