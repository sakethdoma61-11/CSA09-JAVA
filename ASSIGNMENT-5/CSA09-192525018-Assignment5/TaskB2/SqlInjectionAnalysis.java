public class SqlInjectionAnalysis {
    public static void main(String[] args) {
        String id="admin' OR '1'='1";
        String unsafe="SELECT * FROM users WHERE id='"+id+"' AND pwd='wrong'";
        String safe="SELECT * FROM users WHERE id=? AND pwd=?";
        System.out.println("Unsafe SQL shape: "+unsafe);
        System.out.println("PreparedStatement SQL: "+safe);
        System.out.println("Safe parameters are bound as data; they cannot change SQL structure.");
        System.out.println("Damaged CSV cases: wrong field count, non-numeric route/amount, empty line -> skip and log.");
    }
}
