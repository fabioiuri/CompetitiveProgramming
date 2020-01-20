package UVA.DataStructures.OneDimArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Prob755 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, Character> keypad = new HashMap<>(); // ascii -> number
        keypad.put(((int) 'A'), '2'); keypad.put(((int) 'B'), '2'); keypad.put(((int) 'C'), '2');
        keypad.put(((int) 'D'), '3'); keypad.put(((int) 'E'), '3'); keypad.put(((int) 'F'), '3');
        keypad.put(((int) 'G'), '4'); keypad.put(((int) 'H'), '4'); keypad.put(((int) 'I'), '4');
        keypad.put(((int) 'J'), '5'); keypad.put(((int) 'K'), '5'); keypad.put(((int) 'L'), '5');
        keypad.put(((int) 'M'), '6'); keypad.put(((int) 'N'), '6'); keypad.put(((int) 'O'), '6');
        keypad.put(((int) 'P'), '7'); keypad.put(((int) 'R'), '7'); keypad.put(((int) 'S'), '7');
        keypad.put(((int) 'T'), '8'); keypad.put(((int) 'U'), '8'); keypad.put(((int) 'V'), '8');
        keypad.put(((int) 'W'), '9'); keypad.put(((int) 'X'), '9'); keypad.put(((int) 'Y'), '9');
        int nDatasets = Integer.parseInt(br.readLine());
        while (nDatasets-- > 0) {
            HashMap<String, Integer> directory = new HashMap<>(); // phone_number -> frequency
            br.readLine(); // blank line
            int n = Integer.parseInt(br.readLine().trim());
            // Normalize phone numbers and add to directory w/ occurrence count
            for (int i = 0; i < n; i++) {
                String number = br.readLine().trim();
                number = number.replaceAll("-", "");
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < number.length(); j++) {
                    char digit;
                    try {
                        digit = keypad.get((int) number.charAt(j));
                    } catch (Exception e) {
                        digit = number.charAt(j);
                    }
                    sb.append(digit);
                    if (j == 2) sb.append("-");
                }
                String processed = sb.toString();
                Integer occurrences = directory.get(processed);
                if (occurrences == null) {
                    directory.put(processed, 1);
                } else {
                    directory.replace(processed, occurrences + 1);
                }
            }
            // Scan directory and sort duplicate records lexicographically
            ArrayList<String> duplicateNumbers = new ArrayList <>(); // ArrayList faster for sorting
            for (String number : directory.keySet()) {
                if (directory.get(number) > 1) duplicateNumbers.add(number);
            }
            Collections.sort(duplicateNumbers);
            // Print answer
            for (String duplicateNumber : duplicateNumbers) {
                System.out.println(duplicateNumber + " " + directory.get(duplicateNumber));
            }
            if (duplicateNumbers.size() == 0) System.out.println("No duplicates.");
            if (nDatasets > 0) System.out.println(); // blank line
        }
    }
}
