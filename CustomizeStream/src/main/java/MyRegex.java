public class MyRegex {
    public static void main(String[] args) {
       /* String s = "24544511231";
        boolean r = s.matches("1[0-9]\\d{9}");*/

       String s = "仅仅仅仅今天天天放33天假";
       String r = s.replaceAll("(\\D)\\1+","$1");
        System.out.println(r);
    }
}
