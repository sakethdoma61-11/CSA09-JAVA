import java.io.*;import java.nio.file.*;import java.util.*;
public class PassStore {
 private final Path file; PassStore(Path f){file=f;}
 public void add(String id,String reg,String name,int route,double amount)throws IOException{
  try(BufferedWriter w=Files.newBufferedWriter(file,StandardOpenOption.CREATE,StandardOpenOption.APPEND)){
   w.write(String.join(",",id,reg,name,String.valueOf(route),String.valueOf(amount)));w.newLine();
  }
 }
 public String[] find(String id)throws IOException{
  try(BufferedReader r=Files.newBufferedReader(file)){String line;while((line=r.readLine())!=null){
   String[] p=line.split(",",-1);if(p.length!=5)continue;
   try{Double.parseDouble(p[4]);Integer.parseInt(p[3]);}catch(NumberFormatException ex){continue;}
   if(p[0].equals(id))return p;
  }}return null;
 }
 public static void main(String[] a)throws Exception{
  Path f=Files.createTempFile("passes",".csv");PassStore s=new PassStore(f);
  s.add("P1001","192311001","Arun",12,4500);Files.writeString(f,"P1002,DAMAGED LINE\n",StandardOpenOption.APPEND);
  s.add("P1003","192311003","Divya",7,3800);
  System.out.println(Arrays.toString(s.find("P1003")));System.out.println(Arrays.toString(s.find("P1002")));Files.delete(f);
 }
}
