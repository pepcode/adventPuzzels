
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**

 */
public class Puzzle3 {

    public static void main(String args[]){
        Puzzle3 puzzle  = new Puzzle3();
        puzzle.solution_case1(puzzle.getInputsFromFile("input/input3_1.txt"));
        puzzle.solution_case2(puzzle.getInputsFromFile("input/input3_1.txt"));

        // puzzle.solution_case1(puzzle.getInputsFromFile("input/input3.txt"));
        // puzzle.solution_case2(puzzle.getInputsFromFile("input/input3.txt"));
    }

    private String[] getInputsFromFile(String fileName){
        List<String> inputList = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                // find next line
                inputList.add(scanner.nextLine());
              }
              scanner.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } 
        return getStringArray(inputList);
    }

     private static String[] getStringArray(List<String> arr) 
     { 
         String str[] = new String[arr.size()]; 
         for (int j = 0; j < arr.size(); j++) { 
             str[j] = arr.get(j); 
         } 
         return str; 
     }

    private int solution_case1(String[] input) {
        int output = 0;

        //code for Case1

        return output;
    }

    private int solution_case2(String[] input) {
        int output = 0;

        //code for case2
        return output;
    }
    
}