import java.io.*;import java.nio.file.*;
public class LineCounter {
 static long unbuffered(Path p)throws IOException{long n=0;try(InputStream in=Files.newInputStream(p)){int b;while((b=in.read())!=-1)if(b=='\n')n++;}return n;}
 static long buffered(Path p)throws IOException{long n=0;try(BufferedReader r=Files.newBufferedReader(p)){while(r.readLine()!=null)n++;}return n;}
 public static void main(String[] a)throws Exception{Path p=Files.createTempFile("log",".txt");Files.writeString(p,"one\ntwo\nthree\n");System.out.println("Unbuffered lines: "+unbuffered(p));System.out.println("Buffered lines: "+buffered(p));Files.delete(p);}
}
