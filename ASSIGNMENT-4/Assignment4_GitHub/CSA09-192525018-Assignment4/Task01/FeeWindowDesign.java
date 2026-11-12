/* Design skeleton: the Frame collects input; FeeService owns validation/payment rules.
   A production version adds JDBC through a repository and reports completion on the EDT. */
public class FeeWindowDesign {
    public static void main(String[] args) {
        FeeService service = new FeeService();
        System.out.println(service.pay("192525018", "2500"));
        System.out.println(service.pay("", "2500"));
        System.out.println(service.pay("192525018", "abc"));
    }
}
