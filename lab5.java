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
        System.out.println(GetLikeabilityScores(pPrefArray, cPrefArray));

        input.close();

    }

    /* Creates the preferences for a single programmer
     * Uses input n to create an array from 1-n
     * Then shuffles the array to randomize the order
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
     * Creates a hashmap of the programmers preferences
     * Spot 1 is mapped to programmer one's preferences,
     * Spot 2 is mapped to programmer two's preferences,
     * etc.
     */
    public static HashMap<Integer, ArrayList<Character>> makeProgMap(int n) {
        HashMap<Integer, ArrayList<Character>> pmap = new HashMap<Integer, ArrayList<Character>>();
        for (Integer i = 1; i <= n; i++) {
            pmap.put(i, ProgPrefArray(n));
        }
        return pmap;
    }

    /* Creates the preference for a single company
     * Uses input n to create an array from 1-n
     * Then shuffles the array to randomize the order
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
     * Creates a hashmap of the companies preferences
     * Spot 1 is mapped to programmer one's preferences
     * Spot 2 is mapped to programmer two's preferences
     * etc.
     */
    public static HashMap<Character, ArrayList<Integer>> makeComMap(int n) {
        HashMap<Character, ArrayList<Integer>> pmap = new HashMap<Character, ArrayList<Integer>>();
        for (Integer i = 0; i < n; i++) {
            pmap.put((char)(i+'a'), ComPrefArray(n));
        }
        return pmap;
    }

    /* Determines how optimal each pairing of
     * a company and a programmer is
     */
    public static int[] GetLikeabilityScores(HashMap<Integer, ArrayList<Character>> programPref, HashMap<Character, ArrayList<Integer>> compPref) {
        int[] likeabilityScores = new int[programPref.entrySet().size()]; // Creates an array of the same size as the programPref entry set
        // for (int i=1; i <= programPref.entrySet().size();) {
        //     likeabilityScores[i] = ComputeLikeability(i, programPref, compPref); // Computing how optimal a specific pairing is
        // }
        return likeabilityScores;
    }

    /* Computes companies likeability
     * with different programmers
     */
    public static int[] ComputeCompanyLikeability(int programmer, HashMap<Integer, ArrayList<Character>> programPref, HashMap<Character, ArrayList<Integer>> compPref) {
        // list of programmers company preferences
        ArrayList<Character> companies = programPref.get(programmer);

        // list containing the preferences of each company, where index 0 is company a, 1 b etc.
        ArrayList<ArrayList<Integer>> programmers = new ArrayList<ArrayList<Integer>>();
        for (int i=0; i < companies.size(); i++) {
            programmers.add(i, compPref.get((Character)(char)(i+'a')));
        }

        // array of numerical value for how fit a programmer is to each company
        int[] likeabilities = new int[companies.size()];

        // max fitness to a company (first choice)
        int maxLike = companies.size();
        
        // find the programmers fitness to each company, assign value to likeabilities array
        // start at company a (0), see which position company is in programmers preferred company array
        for (int com = 0; com < companies.size(); com++) {
            int dislike = 0;

            // for each position in the array a company is away from the programmers top preference, increment dislike
            for (int i=0; (Character)(char)(i+'a') == companies.get(i); i++) {
                dislike++;
            }
            
            likeabilities[com] = maxLike-dislike;
        }
        
        return likeabilities;
    }

    /* Computes programmers likeability
     * with different companies
     */
    public static int[] ComputeProgrammerLikeability(int company, HashMap<Integer, ArrayList<Character>> programPref, HashMap<Character, ArrayList<Integer>> compPref) {
        // list of companies programmer preferences
        ArrayList<Integer> programmers = compPref.get(company);
        
        // list containing the preferences of each programmer, where index 0 is company a, 1 b etc.
        ArrayList<ArrayList<Character>> companies = new ArrayList<ArrayList<Character>>();
        for (int i=0; i < programmers.size(); i++) {
            companies.add(i, programPref.get((Integer)(int)(i - 'a')));
        }

        // array of numerical value for how fit a programmer is to each company
        int[] likeabilities = new int[programmers.size()];
        
        // max fitness to a company (first choice)
        int maxLike = programmers.size();

        // find the programmers fitness to each company, assign value to likeabilities array
        // start at company a (0), see which position company is in programmers preferred company array
        for(int prog = 0; prog < programmers.size(); prog++) {
            int dislike = 0;

            // for each position in the array a company is away from the programmers top preference, increment dislike
            for (int i = 0; (Integer)(int)(i - 'a') == programmers.get(i); i++) {
                dislike++;
            }

            likeabilities[prog] = maxLike - dislike;
        }

        return likeabilities;
    }
}