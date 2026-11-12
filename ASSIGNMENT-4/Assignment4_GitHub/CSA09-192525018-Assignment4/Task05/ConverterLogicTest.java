import java.text.DecimalFormat;
public class ConverterLogicTest {
    static String convert(String text,String currency) {
        try { double n=Double.parseDouble(text); if(n<0) throw new NumberFormatException();
            return new DecimalFormat("0.00").format(n*("USD".equals(currency)?0.012:0.011));
        } catch(NumberFormatException e){return "Invalid amount";}
    }
    public static void main(String[] a) {
        System.out.println("USD 1000 INR: "+convert("1000","USD"));
        System.out.println("Invalid input: "+convert("abc","EUR"));
    }
}
