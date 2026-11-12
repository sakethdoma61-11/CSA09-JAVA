import java.util.*;
public class AppointmentModel {
    private static final Map<String,List<String>> DOCTORS = Map.of(
        "Cardiology", List.of("Dr Rao","Dr Iyer"),
        "Orthopaedics", List.of("Dr Kumar","Dr Das"),
        "General", List.of("Dr Shah","Dr Reddy"));
    public List<String> doctorsFor(String department) {
        return DOCTORS.getOrDefault(department, List.of());
    }
    public String book(String patientId, String department, String doctor, String slot) {
        if (patientId == null || patientId.isBlank()) return "Patient ID required";
        if (!doctorsFor(department).contains(doctor)) return "Select a doctor in this department";
        if (slot == null || slot.isBlank()) return "Select a time slot";
        return "Appointment confirmed: "+patientId+" / "+doctor+" / "+slot;
    }
    public static void main(String[] args) {
        AppointmentModel m=new AppointmentModel();
        System.out.println(m.doctorsFor("Cardiology"));
        System.out.println(m.book("P100","Cardiology","Dr Rao","10:30"));
        System.out.println(m.book("","Cardiology","Dr Rao","10:30"));
    }
}
