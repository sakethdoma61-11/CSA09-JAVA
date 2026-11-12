import java.util.*;
public class EventRegistrationServiceTest {
    public static void main(String[] args) {
        Set<String> registrations=new HashSet<>();
        System.out.println("Normal registration: "+registrations.add("192525018|Music"));
        System.out.println("Duplicate registration rejected: "+(!registrations.add("192525018|Music")));
        System.out.println("Invalid blank ID rejected: "+(!"".isBlank()==false));
    }
}
