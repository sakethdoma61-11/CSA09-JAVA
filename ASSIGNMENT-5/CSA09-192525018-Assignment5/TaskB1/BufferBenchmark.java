import java.io.*;
import java.nio.file.*;
public class BufferBenchmark {
    static long countUnbuffered(Path p)throws IOException{
        long lines=0; int last=-1;
        try(InputStream in=Files.newInputStream(p)){
            int b; while((b=in.read())!=-1){if(b=='\n') lines++; last=b;}
        }
        if(last!=-1 && last!='\n') lines++;
        return lines;
    }
    static long countBuffered(Path p)throws IOException{
        long lines=0; try(BufferedReader r=Files.newBufferedReader(p)){
            while(r.readLine()!=null) lines++;
        } return lines;
    }
    public static void main(String[] args)throws Exception{
        for(int mb:new int[]{1,10,50}){
            Path p=Files.createTempFile("log",".txt");
            String block="attendance,192525018,Present\n".repeat(4096);
            try(OutputStream o=Files.newOutputStream(p)){
                byte[] b=block.getBytes(); long target=mb*1024L*1024L;
                for(long n=0;n<target;n+=b.length)o.write(b,0,(int)Math.min(b.length,target-n));
            }
            long t=System.nanoTime(); long a=countUnbuffered(p); long u=(System.nanoTime()-t)/1_000_000;
            t=System.nanoTime(); long c=countBuffered(p); long v=(System.nanoTime()-t)/1_000_000;
            System.out.println(mb+"MB lines="+a+" unbuffered_ms="+u+" buffered_ms="+v+" verified="+(a==c));
            Files.delete(p);
        }
    }
}
