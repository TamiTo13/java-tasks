import java.util.Arrays;

public class IPValidator {
    public static boolean validateIPv4Address(String str) {
        if(str == null || str.isEmpty() || str.isBlank()
           || str.charAt(0) < '0' || str.charAt(0) > '9' || str.contains(" ")) return false;



        String[] okteti = str.split("\\.");
        System.out.println(okteti.length);
        if(okteti.length != 4) {
            return false;
        }


        for(String oktet : okteti) {
            if(oktet.equals("0")) continue;
            if(oktet.charAt(0) == '0') return false;
            Integer number = Integer.parseInt(oktet);
            if(number < 0 || number > 255) return false;
        }

        return true;
    };
}
