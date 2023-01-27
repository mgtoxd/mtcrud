import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {

        try {
            System.out.println("dada");
        }catch (Exception e){
            System.out.println("aaa");
        }finally {
            System.out.println("awdawd");
        }

        String str = "select * from {d} where aaa={id};";
        Pattern compile = Pattern.compile("\\{.*?}");
        Matcher matcher = compile.matcher(str);
        while (matcher.find()){
            System.out.println(str.substring(matcher.start(),matcher.end()));
        }
        System.out.println(matcher.matches());


    }
}
