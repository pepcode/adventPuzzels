import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;


import java.util.TreeSet;
import java.io.PrintStream;
// import java.io.PrintWriter;
// import java.io.FileWriter;

public class Puzzle8 {
    
    public static void main(String args[]) {
        Puzzle8 puzzle = new Puzzle8();
       //System.out.println("case1 output :: "+ puzzle.solution_case1());
        System.out.println("case2 output :: "+ puzzle.solution_case2());
     }

     private long solution_case1() {

        System.out.println("CASE 1");
        long output = 0;
        List<String> input = new ArrayList<String>();
        List<Integer> executedCmdRow = new ArrayList<Integer>(); 

        try {
            Scanner scanner = new Scanner(new File("input/input8.txt"));
            while (scanner.hasNextLine()) {
                // find next line
                input.add(scanner.nextLine());  
            }
            scanner.close();

            String[] a = input.toArray(new String[0]);
            // process the inputList
            for(int i=0; i< a.length;) {
                String[] inp = a[i].split(" ");
                String command = inp[0];
                int val = Integer.parseInt(inp[1]);
                if(!executedCmdRow.contains(i)) {
                    executedCmdRow.add(i);
                    if(command.equals("nop")) {
                        i++;
                    } else if(command.equals("acc")) {
                        output = output + val;
                        i++;
                    } else if(command.equals("jmp")) {
                        i = i + val;
                    }
                }  else {
                    break;
                }

            }

        } catch (Exception e) {
            System.out.println( "Error occured :: " +e.getMessage());
            e.printStackTrace();
        }
        return output;
     }

     private long solution_case2()  {
        long output = -1;
        List<String> input = new ArrayList<String>();
        List<Integer> executedCmdRow = new ArrayList<Integer>();
        List<Integer> modifiedRow = new ArrayList<Integer>();
    

        boolean fixApplied = false;
        try {
            // PrintStream fileStream = new PrintStream("log.txt");
            // System.setOut(fileStream);
            Scanner scanner = new Scanner(new File("input/input8.txt"));
            while (scanner.hasNextLine()) {
                // find next line
                input.add(scanner.nextLine());  
            }
            scanner.close();

            String[] a = input.toArray(new String[0]);
            long acVal = 0;
            // process the inputList
            for(int i=0; i< a.length;) {
                String[] inp = a[i].split(" ");
                String command = inp[0];
                int val = Integer.parseInt(inp[1]);
                if(!executedCmdRow.contains(i)) {
                    executedCmdRow.add(i);
                    if(command.equals("nop")) {
                        if(!fixApplied && !modifiedRow.contains(i)) {
                            modifiedRow.add(i);
                            output=i+1;
                            i = i+ val;
                            fixApplied = true;
                        } else {
                            i++;
                        }
                    } else if(command.equals("acc")) {
                        i++;
                        acVal = acVal + val;
                    } else if(command.equals("jmp")) {
                        if(!fixApplied && !modifiedRow.contains(i)) {
                            modifiedRow.add(i);
                            output=i+1;
                            i = i +1;
                            fixApplied = true;
                        } else {
                            i = i+ val;
                        }
                    }
                }  else {
                    i=0;
                    executedCmdRow = new ArrayList<Integer>();
                    fixApplied= false;
                    acVal = 0;
                }
                
                //System.out.println(output + " :: " + i + " acq :: " + acVal + "  " + executedCmdRow.size());
                //System.out.println(acVal);
            }

        } catch (Exception e) {
            System.out.println( "Error occured :: " +e.getMessage());
            e.printStackTrace();
        }
        return output;
     }
}