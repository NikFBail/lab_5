import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;

public class lab5 {

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of programmers and companies: ");
        int amount = Integer.valueOf(input.nextLine());

        HashMap<Integer, ArrayList<Character>> preferenceArray = makePMap(amount);
        System.out.println(preferenceArray);

    }

    public static ArrayList<Character> preferenceArray(int n) {
        ArrayList<Character> pArray = new ArrayList<Character>(n);

        for (int i = 0; i < n; i++) { 
            pArray.add(i, (char)(i+'a'));
        }
        Collections.shuffle(pArray);
        return pArray;
    }

    public static HashMap<Integer, ArrayList<Character>> makePMap(int n) {
        HashMap<Integer, ArrayList<Character>> pmap = new HashMap<Integer, ArrayList<Character>>();
        for (Integer i = 1; i <= n; i++) {
            pmap.put(i, preferenceArray(n));
        }
        return pmap;
    }
}