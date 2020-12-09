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

public class Puzzle9 {
    
    public static void main(String args[]) {
        try {
            // PrintStream fileStream = new PrintStream("log.txt");
            // System.setOut(fileStream);
            Puzzle9 puzzle = new Puzzle9();
           System.out.println("case1 output :: "+ puzzle.solution_case1());
           System.out.println("case2 output :: "+ puzzle.solution_case2());
        } catch (Exception e) {
            e.printStackTrace();
        }
       
     }

     private long solution_case1() {
        List<Long> output = new ArrayList<Long>();
        List<Long> input = new ArrayList<Long>();
        int preambleNum = 25;

        try {

            Scanner scanner = new Scanner(new File("input/input9.txt"));
            while (scanner.hasNextLine()) {
                // find next line
                input.add(Long.parseLong(scanner.nextLine()));  
            }
            scanner.close();
            for(int i=0; i < input.size()-(preambleNum +1);i++) {
                long checkVal = input.get(i+preambleNum);
                List<Long> subList = input.subList(i, i+preambleNum);
                if(!checkList(subList,  checkVal)) {
                    output.add(checkVal);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.get(0);
     }

     private boolean checkList(List<Long> list, long preambleVal) {
        boolean isPreamble = false;
        for(int  i=0; i < list.size(); i++) {
            for(int  j=0; j < list.size(); j++) {
                if(i != j) {
                    if(list.get(i) + list.get(j) == preambleVal) {
                        isPreamble = true;
                        break;
                    }
                }
            }
            
        }
        return isPreamble;
     }

     private long solution_case2()  {
        List<Long> output = new ArrayList<Long>();
        List<Long> input = new ArrayList<Long>();
        int preambleNum = 25;
        long output1 = 0;

        try {

            Scanner scanner = new Scanner(new File("input/input9.txt"));
            while (scanner.hasNextLine()) {
                // find next line
                input.add(Long.parseLong(scanner.nextLine()));  
            }
            scanner.close();
            for(int i=0; i < input.size()-(preambleNum +1);i++) {
                long checkVal = input.get(i+preambleNum);
                List<Long> subList = input.subList(i, i+preambleNum);
                if(!checkList(subList,  checkVal)) {
                    output.add(checkVal);
                }
            }

            long val =  output.get(0);
            long checkVal = 0;
            int checkCount = 0;

            List<Long> a = new ArrayList<Long>();
            for(int i = 0 ; i< input.size(); i++) {
                checkVal = checkVal + input.get(i);
                while (checkVal > val) {
                    checkVal = checkVal - input.get(i-checkCount);
                    checkCount--;
                }
        
                if(checkVal == val) {
                    a = input.subList(i-checkCount, i+1);
                    break;
                } 

                checkCount++;
            }

            Collections.sort(a);
            output1 =  a.get(0) + a.get(a.size()-1);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return output1;
     }
}