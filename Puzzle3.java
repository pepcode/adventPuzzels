
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
// import java.io.PrintWriter;
// import java.io.FileWriter;

/**

 */
public class Puzzle3 {

    public static void main(String args[]) {
        Puzzle3 puzzle = new Puzzle3();
        // puzzle.solution_case1(puzzle.getInputsFromFile("input/input3_1.txt"), 3, 1);
        // puzzle.solution_case2(puzzle.getInputsFromFile("input/input3_1.txt"));

        // puzzle.solution_case1(puzzle.getInputsFromFile("input/input3.txt"), 3, 1);
         puzzle.solution_case2(puzzle.getInputsFromFile("input/input3.txt"));
    }

    private String[] getInputsFromFile(String fileName) {
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

    private static String[] getStringArray(List<String> arr) {
        String str[] = new String[arr.size()];
        for (int j = 0; j < arr.size(); j++) {
            str[j] = arr.get(j);
        }
        return str;
    }

    private int solution_case1(String[] input, int right, int down) {
        int treeCount = 0;
        int i = down;
        int turnPoint = 0;

        while (i < input.length) {
            String line = input[i];
            int j = line.length();
           
            //point to check in the line
            turnPoint = turnPoint + right;

            //extend the pattern
            while (j <= turnPoint) {
                line = line + line;
                j = line.length();
            }

            if (line.toCharArray()[turnPoint] == '#') 
                treeCount++;
            
            i = i + down;
        }
        System.out.println("Tree Count : " + treeCount);
        return treeCount;
    }

    private long solution_case2(String[] input) {

        Puzzle3 puzzle = new Puzzle3();
        long output = puzzle.solution_case1(input, 1, 1);
        output*= puzzle.solution_case1(input, 3, 1);
        output*= puzzle.solution_case1(input, 5, 1);
        output*= puzzle.solution_case1(input, 7, 1);
        output*= puzzle.solution_case1(input, 1, 2);
        System.out.println("case2 output ::" + output);
        // code for case2
        return output;
    }

}