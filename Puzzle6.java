import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import java.util.TreeSet;
// import java.io.PrintWriter;
// import java.io.FileWriter;

public class Puzzle6 {
    
    public static void main(String args[]) {
        Puzzle6 puzzle = new Puzzle6();
        //System.out.println("case1 output :: "+ puzzle.solution_case1());
        System.out.println("case1 output :: "+ puzzle.solution_case2());
      
     }

     private long solution_case2() {
        long output = 0;
        try {
            Scanner scanner = new Scanner(new File("input/input6.txt"));
            String groupInput = "";
            boolean groupcompare = false;
            while (scanner.hasNextLine()) {
                // find next line
                String row = scanner.nextLine();
                
                
                if(row != null && row.length() !=0)
                 {
                    if(groupInput == "" && !groupcompare) {
                        groupInput = row;
                        groupcompare = true;
                    }else {
                        String newUnique = "";
                        char[] gc = groupInput.toCharArray();
                        char[] rc = row.toCharArray();

                        for(int i=0; i < gc.length;i++) {
                            for(int j=0; j< rc.length; j++) {
                                if(gc[i] == rc[j]) {
                                    newUnique = newUnique + rc[j];
                                }
                            }
                        }
                        groupInput = newUnique;
                    }
                    
                } else {
                    //System.out.println("groupInput ::" + groupInput);
                    output = output +  groupInput.length();
                    groupInput = "";
                    groupcompare = false;
                }
            }

            //perform the action for loast row.
            //System.out.println("groupInput ::" + groupInput);
            output = output +  groupInput.length();
            scanner.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        
        return output;
     }

     private long solution_case1() {
        List<String> inputList = new ArrayList<String>();
        long output = 0;
        try {
            Scanner scanner = new Scanner(new File("input/input6.txt"));
            String groupInput = "";
            
            while (scanner.hasNextLine()) {
                // find next line
                String row = scanner.nextLine();
                if(row != null && row.length() !=0)
                 {
                    groupInput = groupInput + row;
                } else {
                    int size = Stream.of(groupInput)
                    .map(c -> c.split(""))
                    .flatMap(Arrays::stream)
                    .distinct()
                    .collect(Collectors.toList()).size();
                    output = output +  size;
                    
                    inputList.add(groupInput);
                    groupInput = "";
                }
            }

            //perform the action for lost row.
            output = output +  Stream.of(groupInput)
                                .map(c -> c.split(""))
                                .flatMap(Arrays::stream)
                                .distinct()
                                .collect(Collectors.toList())
                                .size();
            inputList.add(groupInput);

            scanner.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        
        return output;
     }
}