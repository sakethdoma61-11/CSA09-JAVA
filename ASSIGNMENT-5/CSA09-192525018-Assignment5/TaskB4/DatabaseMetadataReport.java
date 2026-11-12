import java.sql.*;
public class DatabaseMetadataReport {
 public static void main(String[] args)throws SQLException {
  String url=System.getenv("JDBC_URL"), user=System.getenv("JDBC_USER"), pwd=System.getenv("JDBC_PASSWORD");
  if(url==null){System.out.println("Set JDBC_URL, JDBC_USER and JDBC_PASSWORD to inspect the configured database.");return;}
  try(Connection c=DriverManager.getConnection(url,user,pwd)){
   DatabaseMetaData m=c.getMetaData();
   System.out.println("Database: "+m.getDatabaseProductName());
   System.out.println("Driver: "+m.getDriverVersion());
   try(ResultSet rs=m.getTables(null,null,"%",new String[]{"TABLE"})){
    while(rs.next())System.out.println("Table: "+rs.getString("TABLE_NAME"));
   }
  }
 }
}
