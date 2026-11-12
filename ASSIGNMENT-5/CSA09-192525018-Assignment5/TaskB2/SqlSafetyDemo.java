public class SqlSafetyDemo {
 public static void main(String[] args){
  String attack="' OR '1'='1";
  String unsafe="SELECT * FROM users WHERE id='"+attack+"' AND pwd='x'";
  System.out.println("Unsafe SQL concatenates attacker input: "+unsafe);
  System.out.println("PreparedStatement: SELECT * FROM users WHERE id=? AND pwd=?");
  System.out.println("Parameters remain data, not executable SQL.");
 }
}
