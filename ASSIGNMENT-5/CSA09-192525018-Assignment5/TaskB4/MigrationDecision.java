import java.io.*;import java.nio.file.*;import java.util.*;
public class MigrationDecision {
 public static void main(String[] args)throws Exception{
  Path f=Files.createTempFile("buspasses",".csv");Files.writeString(f,"P1,192525018,Saketh,4,500\nBAD,LINE\nP2,192525019,Anu,5,600\n");
  int ok=0,bad=0;try(BufferedReader r=Files.newBufferedReader(f)){String line;while((line=r.readLine())!=null){
   String[] p=line.split(",",-1);if(p.length!=5){bad++;System.out.println("Rejected: "+line);continue;}
   try{Integer.parseInt(p[3]);Double.parseDouble(p[4]);ok++;}catch(NumberFormatException e){bad++;System.out.println("Rejected: "+line);}
  }}
  System.out.println("Valid records: "+ok);System.out.println("Rejected records: "+bad);Files.delete(f);
 }
}
