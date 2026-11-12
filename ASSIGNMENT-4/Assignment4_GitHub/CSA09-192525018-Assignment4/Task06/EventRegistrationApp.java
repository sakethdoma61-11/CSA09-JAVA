import javax.swing.*;import javax.swing.table.DefaultTableModel;import java.awt.*;
public class EventRegistrationApp extends JFrame {
    private final JTextField reg=new JTextField(10), name=new JTextField(12);
    private final JComboBox<String> event=new JComboBox<>(new String[]{"Music","Dance","Drama"});
    private final DefaultTableModel model=new DefaultTableModel(new String[]{"Reg No","Name","Event"},0);
    private final JTable table=new JTable(model);
    public EventRegistrationApp(){
        super("Campus Event Registration");setLayout(new BorderLayout());
        JMenuBar mb=new JMenuBar();JMenu file=new JMenu("File");JMenuItem exit=new JMenuItem("Exit");exit.addActionListener(e->dispose());file.add(exit);mb.add(file);setJMenuBar(mb);
        JPanel form=new JPanel();form.add(new JLabel("Reg No"));form.add(reg);form.add(new JLabel("Name"));form.add(name);form.add(event);
        JButton add=new JButton("Register");form.add(add);add(form,BorderLayout.NORTH);add(new JScrollPane(table),BorderLayout.CENTER);
        add.addActionListener(e->{if(reg.getText().isBlank()||name.getText().isBlank()){JOptionPane.showMessageDialog(this,"All fields required");return;}
            for(int i=0;i<model.getRowCount();i++)if(model.getValueAt(i,0).equals(reg.getText())&&model.getValueAt(i,2).equals(event.getSelectedItem())){JOptionPane.showMessageDialog(this,"Duplicate registration");return;}
            model.addRow(new Object[]{reg.getText(),name.getText(),event.getSelectedItem()});});
        setSize(700,420);setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] a){SwingUtilities.invokeLater(()->new EventRegistrationApp().setVisible(true));}
}
