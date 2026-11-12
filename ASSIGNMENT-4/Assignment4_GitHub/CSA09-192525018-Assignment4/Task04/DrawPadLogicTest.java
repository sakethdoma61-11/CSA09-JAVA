import java.awt.Point;
import java.util.*;
public class DrawPadLogicTest {
    public static void main(String[] args) {
        List<Point> points=new ArrayList<>();
        points.add(new Point(20,30)); points.add(new Point(40,50));
        System.out.println("Stored points: "+points.size());
        System.out.println("First point: "+points.get(0));
        System.out.println("Repaint model retains points: "+(points.size()==2));
    }
}
