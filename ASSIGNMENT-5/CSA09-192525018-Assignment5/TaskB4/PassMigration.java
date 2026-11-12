import java.io.*;
import java.nio.file.*;
import java.sql.*;
public class PassMigration {
    public static void main(String[] args)throws Exception{
        String url=System.getenv("DB_URL"), user=System.getenv("DB_USER"), pwd=System.getenv("DB_PASSWORD");
        if(url==null){System.out.println("Set DB_URL, DB_USER and DB_PASSWORD to run migration.");return;}
        int good=0,bad=0;
        try(Connection c=DriverManager.getConnection(url,user,pwd)){
            c.setAutoCommit(false);
            String sql="INSERT INTO bus_pass(pass_id,reg_no,name,route_no,amount) VALUES(?,?,?,?,?)";
            try(BufferedReader r=Files.newBufferedReader(Path.of(args.length==0?"passes.csv":args[0]));
                PreparedStatement ps=c.prepareStatement(sql)){
                String line;
                while((line=r.readLine())!=null){
                    String[] f=line.split(",",-1);
                    try{
                        if(f.length!=5) throw new IllegalArgumentException("wrong field count");
                        ps.setString(1,f[0]);ps.setString(2,f[1]);ps.setString(3,f[2]);
                        ps.setInt(4,Integer.parseInt(f[3]));ps.setDouble(5,Double.parseDouble(f[4]));
                        ps.addBatch(); good++;
                        if(good%1000==0) ps.executeBatch();
                    }catch(RuntimeException ex){bad++;System.out.println("Rejected: "+line+" : "+ex.getMessage());}
                }
                ps.executeBatch(); c.commit();
            }catch(Exception e){c.rollback();throw e;}
            DatabaseMetaData md=c.getMetaData();
            System.out.println("Database: "+md.getDatabaseProductName());
            System.out.println("Driver: "+md.getDriverVersion());
            try(ResultSet rs=md.getTables(null,null,"%",new String[]{"TABLE"})){
                while(rs.next())System.out.println("Table: "+rs.getString("TABLE_NAME"));
            }
        }
        System.out.println("Accepted rows: "+good+" rejected rows: "+bad);
    }
}
