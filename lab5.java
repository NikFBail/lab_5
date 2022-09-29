import java.util.Scanner;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;

public class lab5 {

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of programmers and companies: ");
        int amount = Integer.valueOf(input.nextLine());

        HashMap<Integer, ArrayList<Character>> pPrefArray = makeProgMap(amount);
        HashMap<Character, ArrayList<Integer>> cPrefArray = makeComMap(amount);
        System.out.println(pPrefArray);
        System.out.println(cPrefArray);

        input.close();

    }

    /* Creates the preferences for a single programmer
     */
    public static ArrayList<Character> ProgPrefArray(int n) { // Boolean: true, company pref, false, programmer pref
        ArrayList<Character> pArray = new ArrayList<Character>(n);

        for (int i = 0; i < n; i++) { 
            pArray.add(i, (char)(i+'a'));
        }
        Collections.shuffle(pArray);
        return pArray;
    }

    /* Creates lists of each programmers preferences
     */
    public static HashMap<Integer, ArrayList<Character>> makeProgMap(int n) {
        HashMap<Integer, ArrayList<Character>> pmap = new HashMap<Integer, ArrayList<Character>>();
        for (Integer i = 1; i <= n; i++) {
            pmap.put(i, ProgPrefArray(n));
        }
        return pmap;
    }

    /* Creates the preference for a single company
     */
    public static ArrayList<Integer> ComPrefArray(int n) { // Boolean: true, company pref, false, programmer pref
        ArrayList<Integer> pArray = new ArrayList<Integer>(n);

        for (int i = 0; i < n; i++) { 
            pArray.add(i, i+1);
        }
        Collections.shuffle(pArray);
        return pArray;
    }

    /* Creates lists of each companies preferences
     */
    public static HashMap<Character, ArrayList<Integer>> makeComMap(int n) {
        HashMap<Character, ArrayList<Integer>> pmap = new HashMap<Character, ArrayList<Integer>>();
        for (Integer i = 0; i < n; i++) {
            pmap.put((char)(i+'a'), ComPrefArray(n));
        }
        return pmap;
    }
}