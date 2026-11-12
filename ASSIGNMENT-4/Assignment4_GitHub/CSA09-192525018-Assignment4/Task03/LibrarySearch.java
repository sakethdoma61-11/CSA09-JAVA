import javax.swing.*;
import java.awt.*;
public class LibrarySearch extends JFrame {
    public LibrarySearch(){
        super("Campus Library Search"); setLayout(new BorderLayout(8,8));
        JLabel title=new JLabel("Campus Library",SwingConstants.CENTER); add(title,BorderLayout.NORTH);
        JPanel search=new JPanel(new FlowLayout(FlowLayout.LEFT)); JTextField query=new JTextField(20);
        JComboBox<String> category=new JComboBox<>(new String[]{"All","Books","Journals"});
        JButton find=new JButton("Search"); search.add(query);search.add(category);search.add(find);add(search,BorderLayout.PAGE_START);
        JTextArea results=new JTextArea("Search results appear here"); results.setEditable(false);add(new JScrollPane(results),BorderLayout.CENTER);
        JPanel bottom=new JPanel(new FlowLayout()); bottom.add(new JButton("Prev"));bottom.add(new JButton("Next"));bottom.add(new JButton("Reserve"));add(bottom,BorderLayout.SOUTH);
        JLabel status=new JLabel("Ready"); add(status,BorderLayout.PAGE_END);
        find.addActionListener(e->{results.setText("Search: "+query.getText()+" | "+category.getSelectedItem());status.setText("Search complete");});
        setSize(650,420);setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] a){SwingUtilities.invokeLater(()->new LibrarySearch().setVisible(true));}
}
