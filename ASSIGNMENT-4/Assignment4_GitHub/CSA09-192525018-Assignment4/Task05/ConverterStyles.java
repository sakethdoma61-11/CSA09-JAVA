import javax.swing.*;import java.awt.*;import java.awt.event.*;
public class ConverterStyles extends JFrame {
    private final JTextField amount=new JTextField(10); private final JComboBox<String> target=new JComboBox<>(new String[]{"USD","EUR"});
    private final JLabel result=new JLabel(" ");
    public ConverterStyles(){
        super("Currency Converter — Lambda");setLayout(new FlowLayout());
        JButton convert=new JButton("Convert");add(new JLabel("INR"));add(amount);add(target);add(convert);add(result);
        ActionListener action=e->convert();convert.addActionListener(action);amount.addActionListener(action);
        addWindowListener(new WindowAdapter(){@Override public void windowClosing(WindowEvent e){dispose();}});
        setDefaultCloseOperation(EXIT_ON_CLOSE);pack();
    }
    private void convert(){try{double v=Double.parseDouble(amount.getText().trim());double rate=target.getSelectedItem().equals("USD")?.012:.011;
        String s=String.format("%.2f %s",v*rate,target.getSelectedItem());result.setText(s);JOptionPane.showMessageDialog(this,s);
    }catch(NumberFormatException ex){result.setText("Invalid amount");JOptionPane.showMessageDialog(this,"Invalid amount");}}
    public static void main(String[] a){SwingUtilities.invokeLater(()->new ConverterStyles().setVisible(true));}
}
