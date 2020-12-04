
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
// import java.util.stream.Stream;
// import java.util.stream.Collectors;
// import java.util.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**

 */

 enum EyeColor {
    amb, blu, brn, gry, grn, hzl, oth
 }

public class Puzzle4 {

    private final String colorRegx = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";
    private final String yearRegx = "^\\d{4}$";
    private final String digitRegx = "^[0-9]\\d*$";


    public static void main(String args[]){
        Puzzle4 puzzle  = new Puzzle4();
        // puzzle.getValidPassports(puzzle.getInputsFromFile("input/input4_1.txt"));
        // puzzle.getValidPassports(puzzle.getInputsFromFile("input/input4.txt"));

        // System.out.println(puzzle.isValidBirthYear("2002"));
        // System.out.println(puzzle.isValidBirthYear("2003"));

        // System.out.println(puzzle.isValidHeight("60in"));
        // System.out.println(puzzle.isValidHeight("190cm"));
        // System.out.println(puzzle.isValidHeight("190in"));
        // System.out.println(puzzle.isValidHeight("190"));


        // System.out.println(puzzle.isValidHairClr("#123abc"));
        // System.out.println(puzzle.isValidHairClr("#123abz"));
        // System.out.println(puzzle.isValidHairClr("123abc"));


        // System.out.println(puzzle.isValidEyeColor("brn"));
        // System.out.println(puzzle.isValidEyeColor("wat"));

        // System.out.println(puzzle.isValidPassportId("000000001"));
        // System.out.println(puzzle.isValidPassportId("0123456789"));



        puzzle.getValidPassports(puzzle.getInputsFromFile_case2("input/input4_1.txt"));
        puzzle.getValidPassports(puzzle.getInputsFromFile_case2("input/input4.txt"));
    }

    private List<Passport> getInputsFromFile(String fileName){
        List<Passport> passports = new ArrayList<Passport>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            Passport passport = new Passport();
            while (scanner.hasNextLine()) {
                // find next line
                String row = scanner.nextLine();

                if(row.isEmpty()) {
                    passports.add(passport);
                    passport = new Passport();
                } else {
                    //TODO: try with lamda
                    String[] detail = row.split(" ");
                    for(int i=0; i< detail.length; i++) {
                        String[] val_ = detail[i].split(":");
                        String key = val_[0];
                        String val = val_[1]; 
                        if(key.equalsIgnoreCase("byr"))  {
                            passport.setBirthYr(val);
                        }  else if(key.equalsIgnoreCase("iyr"))  {
                            passport.setIssueYr(val);
                        } else if(key.equalsIgnoreCase("eyr"))  {
                            passport.setExpiryYr(val);
                        } else if(key.equalsIgnoreCase("hgt"))  {
                            passport.setHeight(val);
                        } else if(key.equalsIgnoreCase("hcl"))  {
                            passport.setHairClr(val);
                        } else if(key.equalsIgnoreCase("ecl"))  {
                            passport.setEyeClr(val);
                        } else if(key.equalsIgnoreCase("pid"))  {
                            passport.setPassportId(val);
                        } else if(key.equalsIgnoreCase("cid"))  {
                            passport.setCountryId(val);
                        }
                    }
                }
              }
              //before closing the scanner add the last pasport to list.
              passports.add(passport);
              scanner.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } 
        return passports;
    }

    private List<Passport> getInputsFromFile_case2(String fileName){
        List<Passport> passports = new ArrayList<Passport>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            Passport passport = new Passport();
            while (scanner.hasNextLine()) {
                // find next line
                String row = scanner.nextLine();

                if(row.isEmpty()) {
                    passports.add(passport);
                    passport = new Passport();
                } else {
                    //TODO: try with lamda
                    String[] detail = row.split(" ");
                    for(int i=0; i< detail.length; i++) {
                        String[] val_ = detail[i].split(":");
                        String key = val_[0];
                        String val = val_[1]; 
                        if(key.equalsIgnoreCase("byr"))  {
                            if(isValidBirthYear(val)) passport.setBirthYr(val);
                        }  else if(key.equalsIgnoreCase("iyr"))  {
                            if(isValidIssueYear(val)) passport.setIssueYr(val);
                        } else if(key.equalsIgnoreCase("eyr"))  {
                            if(isValidExpiryYear(val)) passport.setExpiryYr(val);
                        } else if(key.equalsIgnoreCase("hgt"))  {
                            if(isValidHeight(val)) passport.setHeight(val);
                        } else if(key.equalsIgnoreCase("hcl"))  {
                            if(isValidHairClr(val)) passport.setHairClr(val);
                        } else if(key.equalsIgnoreCase("ecl"))  {
                            if(isValidEyeColor(val)) passport.setEyeClr(val);
                        } else if(key.equalsIgnoreCase("pid"))  {
                            if(isValidPassportId(val)) passport.setPassportId(val);
                        } else if(key.equalsIgnoreCase("cid"))  {
                            passport.setCountryId(val);
                        }
                    }
                }
              }
              //before closing the scanner add the last pasport to list.
              passports.add(passport);
              scanner.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } 
        return passports;
    }




    private boolean isValidBirthYear(String year) {
        boolean isValid = false;
        if(validateRegX(year, yearRegx)) {
            int yr = Integer.parseInt(year);
            if(yr >= 1920 && yr <= 2002) {
                isValid = true;
            }
        }
        return isValid;
    }

    private boolean isValidIssueYear(String year) {
        boolean isValid = false;
        if(validateRegX(year, yearRegx)) {
            int yr = Integer.parseInt(year);
            if(yr >= 2010 && yr <= 2020) {
                isValid = true;
            }
        }
        return isValid;
    }

    private boolean isValidExpiryYear(String year) {
        boolean isValid = false;
        if(validateRegX(year, yearRegx)) {
            int yr = Integer.parseInt(year);
            if(yr >= 2020 && yr <= 2030) {
                isValid = true;
            }
        }
        return isValid;
    }

    private boolean isValidHeight(String input){

        boolean isValid = false;
        String measurementType = "";
        String hVal = input;
        if (input.length() > 2){
            measurementType = input.substring(input.length() - 2);
            hVal = input.substring(0, input.length() - 2);
        }

        if(validateRegX(hVal, digitRegx)) {
            int height = Integer.parseInt(hVal);
            if(measurementType.equals("cm") && height >= 150 && height <= 193 ){
                isValid = true;
            }else if(measurementType.equals("in") && height >= 59 && height <= 76) {
                isValid = true;
            }
        }
        return isValid;
    }

    private boolean isValidHairClr(String val){
        return validateRegX(val, colorRegx);
    }

    private boolean isValidEyeColor(String val){
        return isValidEnum(EyeColor.class, val);
    }
    private <E extends Enum<E>> boolean isValidEnum(Class<E> eclass, String val) {
        return Arrays.stream(eclass.getEnumConstants()).anyMatch(e -> e.name().equals(val));
    }

    private boolean isValidPassportId(String val){
        boolean isValid = false;
        if(validateRegX(val, digitRegx)){
            if(val.length()==9) {
                isValid = true;
            }
        }
        return isValid;
    }

    private boolean validateRegX(String val, String regx) {
        return Pattern.compile(regx).matcher(val).matches();
    }

    private boolean isValidPassport(Passport passport) {
        return isValidString(passport.getBirthYr()) && isValidString(passport.getIssueYr())
                && isValidString(passport.getExpiryYr()) && isValidString(passport.getHeight())
                && isValidString(passport.getHairClr()) && isValidString(passport.getEyeClr())
                && isValidString(passport.getPassportId());
    }

    private static boolean isValidString(String input){
        boolean val = false;
        if(input != null && (input.trim().length() != 0)){
            val = true;
        }
        return val;
    }

    private int getValidPassports(List<Passport> passports) {
        int output = 0;
        for(Passport passport : passports) {
            if(isValidPassport(passport)) {
                output++;
            }
        }
        System.out.println("valid passport count :: " + output);
        return output;
    }
    
}