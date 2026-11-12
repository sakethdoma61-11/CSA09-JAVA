import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class AppointmentDesk extends JFrame {
    private final JTextField patient = new JTextField(12);
    private final JComboBox<String> dept = new JComboBox<>(new String[]{"Cardiology","ENT","Orthopaedics"});
    private final JComboBox<String> doctor = new JComboBox<>();
    private final JComboBox<String> slot = new JComboBox<>(new String[]{"09:00","10:00","11:00"});
    private boolean booking = false;
    private final Map<String, java.util.List<String>> doctors = Map.of(
        "Cardiology", java.util.List.of("Dr Rao","Dr Kumar"),
        "ENT", java.util.List.of("Dr Meena","Dr John"),
        "Orthopaedics", java.util.List.of("Dr Das","Dr Iyer"));
    public AppointmentDesk(){
        super("Outpatient Appointment Desk"); setLayout(new GridLayout(5,2,8,8));
        add(new JLabel("Patient ID")); add(patient); add(new JLabel("Department")); add(dept);
        add(new JLabel("Doctor")); add(doctor); add(new JLabel("Time slot")); add(slot);
        JButton book=new JButton("Book"); add(book); add(new JLabel("Select and book"));
        dept.addActionListener(e->refreshDoctors()); refreshDoctors();
        book.addActionListener(e->book()); addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){ if(booking && JOptionPane.showConfirmDialog(AppointmentDesk.this,"Booking in progress. Close?")!=JOptionPane.YES_OPTION)return; dispose();}
        });
        pack();
    }
    private void refreshDoctors(){ doctor.removeAllItems(); for(String d:doctors.get((String)dept.getSelectedItem()))doctor.addItem(d); }
    private void book(){ if(patient.getText().isBlank()){JOptionPane.showMessageDialog(this,"Patient ID required");return;}
        booking=true; JOptionPane.showMessageDialog(this,"Appointment confirmed: "+patient.getText()+" / "+doctor.getSelectedItem()+" / "+slot.getSelectedItem()); booking=false; }
    public static void main(String[] a){SwingUtilities.invokeLater(()->new AppointmentDesk().setVisible(true));}
}
