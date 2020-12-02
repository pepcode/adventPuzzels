import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
--- Day 2: Password Philosophy ---
Your flight departs in a few days from the coastal airport; the easiest way down to the coast from here is via toboggan.

The shopkeeper at the North Pole Toboggan Rental Shop is having a bad day. "Something's wrong with our computers; we can't log in!" You ask if you can take a look.

Their password database seems to be a little corrupted: some of the passwords wouldn't have been allowed by the Official Toboggan Corporate Policy that was in effect when they were chosen.

To try to debug the problem, they have created a list (your puzzle input) of passwords (according to the corrupted database) and the corporate policy when that password was set.

For example, suppose you have the following list:

1-3 a: abcde
1-3 b: cdefg
2-9 c: ccccccccc
Each line gives the password policy and then the password. The password policy indicates the lowest and highest number of times a given letter must appear for the password to be valid. For example, 1-3 a means that the password must contain a at least 1 time and at most 3 times.

In the above example, 2 passwords are valid. The middle password, cdefg, is not; it contains no instances of b, but needs at least 1. The first and third passwords are valid: they contain one a or nine c, both within the limits of their respective policies.

How many passwords are valid according to their policies?
 */
public class Puzzle2 {

    public static void main(String args[]){
        final String[] input1 = {"1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc"};
        Puzzle2 p2  = new Puzzle2();
        // p2.getCorrectPasswords(input1);
        // p2.getCorrectPasswords((String[])p2.getInputsFromFile("puzzle2_input.txt"));

        p2.getCorrectPasswords_case2(input1);
        p2.getCorrectPasswords_case2((String[])p2.getInputsFromFile("puzzle2_input.txt"));
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

     public static String[] getStringArray(List<String> arr) 
     { 
         String str[] = new String[arr.size()]; 
         for (int j = 0; j < arr.size(); j++) { 
             str[j] = arr.get(j); 
         } 
         return str; 
     }

    private int getCorrectPasswords(String[] input) {
        int output = 0;

        for(int i=0; i < input.length; i++){
            String[] details = input[i].split(" ");
            String[] passwordPolicy = details[0].split("-");
            int passwordPolicy_lowerlimit = Integer.parseInt(passwordPolicy[0]);
            int passwordPolicy_highlimit = Integer.parseInt(passwordPolicy[1]);

            char passwordKey = details[1].charAt(0);
            String password = details[2];

            long count = password.chars().filter(ch -> ch == passwordKey).count();
            if(count >= passwordPolicy_lowerlimit && count <= passwordPolicy_highlimit){
                System.out.println(" passowrd is valid ::: " + input[i]);
                output++;
            }
        }
        System.out.println("output :: " + output );
        return output;
    }

    private int getCorrectPasswords_case2(String[] input) {
        int output = 0;

        for(int i=0; i < input.length; i++){
            String[] details = input[i].split(" ");
            String[] passwordPolicy = details[0].split("-");
            int passwordPolicy_lowerlimit = Integer.parseInt(passwordPolicy[0]);
            int passwordPolicy_highlimit = Integer.parseInt(passwordPolicy[1]);

            char passwordKey = details[1].charAt(0);
            String password = details[2];

            //long count = password.chars().filter(ch -> ch == passwordKey).count();
            // if(count >= passwordPolicy_lowerlimit && count <= passwordPolicy_highlimit){
            //     System.out.println(" passowrd is valid ::: " + input[i]);
            //     output++;
            // }

            if(passwordKey == password.charAt(passwordPolicy_lowerlimit-1)  &&  passwordKey == password.charAt(passwordPolicy_highlimit-1) ) {

            } else if(passwordKey == password.charAt(passwordPolicy_lowerlimit-1)  ||  passwordKey == password.charAt(passwordPolicy_highlimit-1) ) {
                System.out.println(" passowrd is valid ::: " + input[i]);
                output++;
            }

        }
        System.out.println("output :: " + output );
        return output;
    }
    
}