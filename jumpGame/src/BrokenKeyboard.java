import java.util.Arrays;

public class BrokenKeyboard {
    private static boolean checkForShare(String one, String two) {
        int sizeOne = one.length();
        int sizeTwo = two.length();
        for(int i = 0; i<sizeOne; i++) {
            for (int j = 0;  j < sizeTwo; j++) {
                if(one.charAt(i) == two.charAt(j)) return true;
            }
        }
        return false;
    }
    public static int calculateFullyTypedWords(String message, String brokenKeys){
            if(message.isBlank() || message.isEmpty() || message == null) return 0;
            message = message.strip().replaceAll(" +", " ");
            System.out.println(message);
            String[] wordsOne = message.split(" ");
            System.out.println(Arrays.toString(wordsOne));
            String[] wordsTwo = new String[wordsOne.length];

            int j = 0;
            for (int i = 0; i <wordsOne.length ; i++) {
                if(!wordsOne[i].isEmpty() && !wordsOne[i].isBlank() && wordsOne != null) {
                    wordsTwo[j++] = wordsOne[i];
                }
            }

            System.out.println(Arrays.toString(wordsTwo));
            int result = 0;

            for(String word : wordsTwo) {
                if(!checkForShare(word, brokenKeys)) result++;
            }
            return result;
    };
}
