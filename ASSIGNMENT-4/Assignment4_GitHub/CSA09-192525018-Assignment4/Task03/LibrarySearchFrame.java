import java.awt.*;
import javax.swing.*;
public class LibrarySearchFrame extends JFrame {
    public LibrarySearchFrame() {
        super("Campus Library Search");
        setLayout(new BorderLayout(8,8));
        JPanel search=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField query=new JTextField(22);
        JComboBox<String> category=new JComboBox<>(new String[]{"All","Books","Journals"});
        JButton go=new JButton("Search");
        search.add(query); search.add(category); search.add(go);
        JTextArea results=new JTextArea("Search results appear here"); results.setEditable(false);
        JPanel actions=new JPanel(new FlowLayout());
        actions.add(new JButton("Prev")); actions.add(new JButton("Next")); actions.add(new JButton("Reserve"));
        add(new JLabel("Campus Library",SwingConstants.CENTER),BorderLayout.NORTH);
        add(search,BorderLayout.PAGE_START); add(new JScrollPane(results),BorderLayout.CENTER);
        add(actions,BorderLayout.SOUTH); add(new JLabel("Ready"),BorderLayout.LINE_END);
        go.addActionListener(e -> results.setText("Searching: "+query.getText()+" / "+category.getSelectedItem()));
        setSize(720,480); setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] a) { SwingUtilities.invokeLater(()->new LibrarySearchFrame().setVisible(true)); }
}
