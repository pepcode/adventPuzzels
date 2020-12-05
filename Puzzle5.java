
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.util.Set;

import java.util.TreeSet;
// import java.io.PrintWriter;
// import java.io.FileWriter;

/**
--- Day 5: Binary Boarding ---
You board your plane only to discover a new problem: you dropped your boarding pass! You aren't sure which seat is yours, and all of the flight attendants are busy with the flood of people that suddenly made it through passport control.

You write a quick program to use your phone's camera to scan all of the nearby boarding passes (your puzzle input); perhaps you can find your seat through process of elimination.

Instead of zones or groups, this airline uses binary space partitioning to seat people. A seat might be specified like FBFBBFFRLR, where F means "front", B means "back", L means "left", and R means "right".

The first 7 characters will either be F or B; these specify exactly one of the 128 rows on the plane (numbered 0 through 127). Each letter tells you which half of a region the given seat is in. Start with the whole list of rows; the first letter indicates whether the seat is in the front (0 through 63) or the back (64 through 127). The next letter indicates which half of that region the seat is in, and so on until you're left with exactly one row.

For example, consider just the first seven characters of FBFBBFFRLR:

Start by considering the whole range, rows 0 through 127.
F means to take the lower half, keeping rows 0 through 63.
B means to take the upper half, keeping rows 32 through 63.
F means to take the lower half, keeping rows 32 through 47.
B means to take the upper half, keeping rows 40 through 47.
B keeps rows 44 through 47.
F keeps rows 44 through 45.
The final F keeps the lower of the two, row 44.
The last three characters will be either L or R; these specify exactly one of the 8 columns of seats on the plane (numbered 0 through 7). The same process as above proceeds again, this time with only three steps. L means to keep the lower half, while R means to keep the upper half.

For example, consider just the last 3 characters of FBFBBFFRLR:

Start by considering the whole range, columns 0 through 7.
R means to take the upper half, keeping columns 4 through 7.
L means to take the lower half, keeping columns 4 through 5.
The final R keeps the upper of the two, column 5.
So, decoding FBFBBFFRLR reveals that it is the seat at row 44, column 5.

Every seat also has a unique seat ID: multiply the row by 8, then add the column. In this example, the seat has ID 44 * 8 + 5 = 357.

Here are some other boarding passes:

BFFFBBFRRR: row 70, column 7, seat ID 567.
FFFBBBFRRR: row 14, column 7, seat ID 119.
BBFFBBFRLL: row 102, column 4, seat ID 820.
As a sanity check, look through your list of boarding passes. What is the highest seat ID on a boarding pass?
 */
public class Puzzle5 {

    public static void main(String args[]) {
        Puzzle5 puzzle = new Puzzle5();
       // puzzle.solution_case1(puzzle.getInputsFromFile("input/input5_1.txt"));
    //     puzzle.solution_case2(puzzle.getInputsFromFile("input/input3_1.txt"));

         puzzle.solution_case1(puzzle.getInputsFromFile("input/input5.txt"));
    //      puzzle.solution_case2(puzzle.getInputsFromFile("input/input3.txt"));
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

    private void solution_case1(String[] input) {
        int highestSeatId = 0;
        List<Integer> seatIds = new ArrayList<Integer>();
        for(String seatCode : input) {
            char[] sChars =  seatCode.toCharArray();

            int seatId = 0;
            int seatRowMIN = 0;  
            int seatRowMAX = 127; 
            int seatcolumMIN = 0;
            int seatcolumMAX = 7;
           // BFFFBBFRRR
            for(char c : sChars) {
                if(c=='F') {
                    seatRowMAX = seatRowMAX - (seatRowMAX - seatRowMIN + 1)/2;
                } else if (c == 'B') {
                    seatRowMIN = seatRowMIN + (seatRowMAX - seatRowMIN + 1)/2;
                }else if(c == 'L') {
                    seatcolumMAX = seatcolumMAX - (seatcolumMAX - seatcolumMIN + 1)/2;
                } else if(c == 'R') {
                    seatcolumMIN = seatcolumMIN + (seatcolumMAX - seatcolumMIN + 1)/2;
                }
            }


            int seatRow = seatRowMAX;
            int seatColumn = seatcolumMAX;
            seatId = seatRow * 8 + seatColumn;

            if(seatId > highestSeatId) {
                highestSeatId = seatId;
            }

            //System.out.println (seatCode + ": row " + seatRow + "(" +seatRowMIN +"," + seatRowMAX+ ")" + ", column: "+  seatColumn+  "(" +seatcolumMIN +"," + seatcolumMAX+ ")" + ", seat ID: "+ seatId );

            //System.out.println (seatCode + ": row " + ", column: "+  seatColumn+ ", seat ID: "+ seatId );

            
            seatIds.add(seatId);
        }
        Collections.sort(seatIds);
       //System.out.println(seatIds);

        System.out.println(getMissingSeatNo(seatIds.stream().mapToInt(i->i).toArray(), seatIds.size()));
        //System.out.println("highest SeatId :: " + highestSeatId);

    }

    static int getMissingSeatNo(int a[], int n)
    {
         int i, output = 0;
        for (i = 0; i < n; i++) {
            if((a[i+1] - a[i]) > 1  &&  (i+1) < n) {
                output = a[i] +1;
                break;
            }
        }
        return output;
    }

    private long solution_case2(String[] input) {
        long output = 0;

        // code for case2
        return output;
    }

}