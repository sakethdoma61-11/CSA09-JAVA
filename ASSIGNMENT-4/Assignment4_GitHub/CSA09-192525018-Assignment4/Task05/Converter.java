import java.awt.FlowLayout;
import javax.swing.*;
import java.text.DecimalFormat;
public class Converter extends JFrame {
    private final JTextField amount=new JTextField(10);
    private final JComboBox<String> target=new JComboBox<>(new String[]{"USD","EUR"});
    private final JLabel result=new JLabel(" ");
    public Converter() {
        super("Currency Converter"); setLayout(new FlowLayout());
        JButton b=new JButton("Convert");
        add(new JLabel("INR")); add(amount); add(target); add(b); add(result);
        java.awt.event.ActionListener action=e->convert();
        b.addActionListener(action); amount.addActionListener(action);
        setDefaultCloseOperation(EXIT_ON_CLOSE); pack();
    }
    private void convert() {
        try {
            double n=Double.parseDouble(amount.getText());
            if(n<0) throw new NumberFormatException();
            double rate="USD".equals(target.getSelectedItem())?0.012:0.011;
            String value=new DecimalFormat("0.00").format(n*rate);
            result.setText(value); JOptionPane.showMessageDialog(this,value);
        } catch(NumberFormatException ex) {
            result.setText("Invalid amount"); JOptionPane.showMessageDialog(this,"Invalid amount");
        }
    }
    public static void main(String[] a){SwingUtilities.invokeLater(()->new Converter().setVisible(true));}
}
