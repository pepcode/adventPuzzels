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
// import java.io.PrintWriter;
// import java.io.FileWriter;

public class Puzzle7 {
    
    public static void main(String args[]) {
        Puzzle7 puzzle = new Puzzle7();
       // System.out.println("case1 output :: "+ puzzle.solution_case1());
        System.out.println("case2 output :: "+ puzzle.solution_case2());
      
     }

     private long solution_case1() {
        long output = 0;
        try {
            Scanner scanner = new Scanner(new File("input/input7_1.txt"));
            Map<String, Map<String, Integer>> bags = new TreeMap();
            while (scanner.hasNextLine()) {
                // find next line
                String row = scanner.nextLine().replaceAll(" bags","").replaceAll(" bag","").replaceAll("\\.", "");
                String[] bagContents =row.split(" contain ");
                String bag = bagContents[0];
                
                String[] contents = bagContents[1].split(", ");
                Map<String, Integer> bgContents = null;
                for(String content : contents) {
                    if(!content.equals("no other")) {
                        if(bgContents == null) {
                            bgContents = new TreeMap<String, Integer>();
                        }
                        int bagcount = Integer.parseInt(content.substring(0, content.indexOf(" ")));
                        String bagName = content.substring(content.indexOf(" ")+1, content.length());
                        bgContents.put(bagName, bagcount);
                    }
                }
                bags.put(bag, bgContents);
            }


            Iterator it = bags.entrySet().iterator();
            while(it.hasNext()) {
                String checkBagKey= (String)((Map.Entry)it.next()).getKey();
                int findCount = findBagCount(bags, bags.get(checkBagKey), "shiny gold");
                if(findCount > 0) {
                    output++;
                }
                //System.out.println(checkBagKey +  " :: " + findCount);
            }
            
            scanner.close();
        } catch (Exception e) {
            System.out.println( "Error occured :: " +e.getMessage());
            e.printStackTrace();
        }
        
        return output;
     }


     private int findBagCount(Map bags, Map bg, String bagName) {
        int count = 0;
        if(bg != null) {
            Iterator it = bg.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry bag_ = (Map.Entry)it.next();
                String key = (String)bag_.getKey();
                if(key.equals(bagName)) {
                    count++;
                } else {
                    //((TreeMap)bags.get(bag_.getKey()))
                    count = count + findBagCount(bags, (TreeMap)bags.get(key), bagName);
                }
            }
        }
        return count;
     }

     private int findBagCountByBag(Map bags, Map bg) {
        int count = 1;
        if(bg != null) {
            Iterator it = bg.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry bag_ = (Map.Entry)it.next();
                String key = (String)bag_.getKey();
                int val = (Integer)bag_.getValue();

                System.out.println ( "****** " + key + "  :: " + val);

                int rVal = findBagCountByBag(bags, (TreeMap)bags.get(key));
                count = count +  val*rVal;
                System.out.println(key + " :: " + count);
            }
        }
        return count;
     }


     private long solution_case2() {
        long output = 0;
        try {
            Scanner scanner = new Scanner(new File("input/input7.txt"));
            System.out.println("CASE 2");
            Map<String, Map<String, Integer>> bags = new TreeMap();
            Map<String, Integer> bagwithCounts = new TreeMap();
            while (scanner.hasNextLine()) {
                // find next line
                String row = scanner.nextLine().replaceAll(" bags","").replaceAll(" bag","").replaceAll("\\.", "");
                String[] bagContents =row.split(" contain ");
                String bag = bagContents[0];
                
                String[] contents = bagContents[1].split(", ");
                Map<String, Integer> bgContents = null;
                int totalBags = 0;
                for(String content : contents) {
                    if(!content.equals("no other")) {
                        if(bgContents == null) {
                            bgContents = new TreeMap<String, Integer>();
                        }
                        int bagcount = Integer.parseInt(content.substring(0, content.indexOf(" ")));
                        String bagName = content.substring(content.indexOf(" ")+1, content.length());
                        bgContents.put(bagName, bagcount);
                        totalBags = totalBags + bagcount;
                    }
                }
                bags.put(bag, bgContents);
                bagwithCounts.put(bag, totalBags);
            }


            Iterator it = bags.entrySet().iterator();
            while(it.hasNext()) {
                String checkBagKey= (String)((Map.Entry)it.next()).getKey();
                if(checkBagKey.equals("shiny gold")) {
                    int findCount = findBagCountByBag(bags, bags.get(checkBagKey));
                    output = output + findCount;
                }
            }
            
            scanner.close();
        } catch (Exception e) {
            System.out.println( "Error occured :: " +e.getMessage());
            e.printStackTrace();
        }
        
        return output-1;
     }
}