import java.awt.*;
import java.awt.event.*;
public class FeeWindow extends Frame {
    private final TextField reg = new TextField(18), amount = new TextField(18);
    private final Label status = new Label("Enter payment details");
    private final FeeService service = new FeeService();
    public FeeWindow() {
        super("Student Fee Payment");
        setLayout(new GridLayout(4,2,8,8));
        add(new Label("Register number")); add(reg);
        add(new Label("Amount")); add(amount);
        Button pay = new Button("Pay"), clear = new Button("Clear");
        add(pay); add(clear); add(status); add(new Label(""));
        pay.addActionListener(e -> status.setText(service.pay(reg.getText(), amount.getText())));
        clear.addActionListener(e -> { reg.setText(""); amount.setText(""); status.setText("Cleared"); });
        addWindowListener(new WindowAdapter(){ public void windowClosing(WindowEvent e){dispose();} });
        pack();
    }
    public static void main(String[] args){ EventQueue.invokeLater(()->new FeeWindow().setVisible(true)); }
}
