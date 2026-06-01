//PEREZ, NERIE ANNE M   BSIT 2A

package hashing;

import java.util.Scanner;

public class Hashing {
    
    static final int size = 100; //fixed table size
    static int[] hashTable = new int[size]; //initializing hash table
    //needed variables
    static int key; 
    static int hashingMethod;
    static int hash; //stores the hashing index
    static int probingMethod;
    
    
    //=====HASH METHOD: PICKS THE INDEX IN THE HASH TABLE=====
    //DIRECT METHOD: returns the key directly as hash index so 100 and above is not allowed
    static int direct() {
        return key;
    }
    
    //SUBTRACTION METHOD: the constant decided of me is 100; 100 and above difference is not allowed
    static int subtraction() {
        int constant = 100;
            return Math.abs(key - constant);
    }
    
    //MODULO-DIVISION METHOD
    static int moduloDivision() {
        return key % size;
    }
    
    //DIGIT EXTRACTION METHOD: returns the combined first and last digit as  decided by me
    static int digitExtraction() {
        int tempkey = key; //stores the key. prevents direct edit to the key.
            int lastDigit = tempkey % 10; //stores last digit

            //helps extract the first digit 
            while (tempkey >= 10) {
                tempkey = tempkey / 10; 
            }

            int firstDigit = tempkey;

            return firstDigit * 10 + lastDigit;
    }
    
    //MID-SQUARE METHOD: extract the 2 mid digits after square. if odd, it doubles the digit ex. 1; then 11
    static int midSquare() {
        int square = key * key;
            String s = String.valueOf(square); //converts to string
            int length = s.length(); // stores length to identify odd or even, and the mid digit/s

            if (length % 2 == 1) { //if odd
                int midIndex = length / 2;
                return s.charAt(midIndex) - '0'; //returns mid digit double as int
            } else { //if even
                int midIndex = length / 2;
                return Integer.parseInt(s.substring(midIndex - 1, midIndex + 1)); //return 2 mid digits as int
            }
    }
    
    //FOLD-SHIFTING: divides key into part with 2 digits then add the parts
    static int foldShifting() {
        int tempkey = key; //stores the key. prevents direct edit to the key.
            int sum = 0;
            
            //divides key to parts and add the part into the sum
            while(tempkey > 0) {
                sum += tempkey % 100;
                tempkey = tempkey / 100;
            }
            
            return sum % size;
    }
    
    //FOLD BOUNDARY: divides key into part with 2 digits, reverses the digits position then add the parts
    static int foldBoundary() {
        String num = String.valueOf(key); //converts to string
            int sum = 0;

            //takes the part by 2 digits then reverses and add to sum respectively
            for (int i = 0; i < num.length(); i += 2) {
                String part;

                if (i + 2 <= num.length())
                    part = num.substring(i, i + 2);
                else
                    part = num.substring(i); // last part if 1 digit

                // Reverse ALL parts
                part = new StringBuilder(part).reverse().toString();

                sum += Integer.parseInt(part);
            }

            return sum % size;
    }
    
    //ROTATION METHOD: divides key into parts with 2 digits, moves the last digit each part to the first then add.
    //conclusion:: as size of hash table is 100 to decided make the parts have 2 digits on the methods thus FOLD BOUNDARY
    //and ROTATION METHOD is technically same hash index outputs
    static int rotation() {
        String num = String.valueOf(key); //converts to string
            int sum = 0;
            
            for (int i = 0; i < num.length(); i += 2) {
                String part;
                if (i + 2 <= num.length()) {
                    part = num.substring(i, i + 2); // take 2 digits
                } else {
                    part = num.substring(i); // last 1 digit
                }

                // Rotate the part: last digit -> start
                if (part.length() > 1) {
                    part = part.substring(1) + part.charAt(0);
                }

                sum += Integer.parseInt(part);
            }

            return sum % size;
    }
    
    
    //=====OPEN ADDRESSING=====
    static void openAddressing() {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("COLLISION OCCURED: The hash index returned is already occupied.");
        //will not end unless the input is in the option
        while(true) {
            System.out.println("(1) Linear Probing\n"
                    + "(2) Quadratic Probing");
            System.out.print("Enter probing method: ");

            //checks if the user entered valid inputs and will not skip unless corrected
            while(!scan.hasNextInt()) {
                System.out.print("Invalid entry. Enter number only: ");
                scan.next();
            }
            probingMethod = scan.nextInt();
            scan.nextLine();
            
            if(probingMethod == 1 || probingMethod == 2) break;
            System.out.println("Number entered is invalid. Enter number 1 or 2 only..\n");
        }
        
        if(probingMethod == 1) hash = linear();
        else if(probingMethod == 2) hash = quadratic();
        
        if(hash == -1) { //if full
            System.out.println("Hash Table is full or cannot find available index.");
            System.out.println("KEY INSERTION UNSUCCESSFUL...\n");
        } else { //if index is found available
            hashTable[hash] = key; //saves the key in the available index
            
            System.out.println("Key " + key + " is inserted in  index " + hash + " via Open Addressing.");
            System.out.println("KEY INSERTION SUCCESSFUL.\n");
        }
    }
    
    //LINEAR PROBING: adds 1 until there is empty index
    static int linear() {
        int start = hash;
        
        //iterates until empty index is found
        for(int i = 1; i < size; i++) {
            int index = (start + i) % size;
            System.out.println("Index " + index + "...");
            
            
            if(hashTable[index] == 0) {
                System.out.println("Final Index: " + index);
                return index;
            }
        }
        
        return -1; // if full
    }
    
    //QUADRATIC PROBING: adds sqaure of numbers until an empty index
    static int quadratic() {
        int start = hash;
        
        //iterates until empty index is found
        for(int i = 1; i < size; i++) {
            int index = (start + (i * i)) % size;
            
            System.out.println("Index " + index + "...");
            
            if(hashTable[index] == 0) {
                System.out.println("Final Index: " + index);
                return index;
            }
        }
        
        return -1; //if full
    }
    
    
    //======MAIN METHOD which serves as the home method for this java======
    public static void main(String[] args) {
        System.out.println("=====HASH TABLE METHODS=====\n");
        
        Scanner scan = new Scanner(System.in);
        
        //makes the program running by this loop
        while(true) {
            //will not end unless the input is in the range
            while(true) {
                System.out.print("Input key(integer): ");

                //checks if the user entered valid inputs and will not skip unless corrected
                while(!scan.hasNextInt()) {
                    System.out.print("Invalid entry. Enter number only: ");
                    scan.next();
                }
                key = scan.nextInt();
                scan.nextLine();
                
                if(key > 0) break; 
                System.out.println("Number entered is invalid. Enter positive number only..\n");
            }
            
            //will not end unless the input is in the option
            while(true) {
                System.out.println("(1) Direct Method\n"
                        + "(2) Subtraction Method\n"
                        + "(3) Modulo-Division Method\n"
                        + "(4) Digit Extraction Method\n"
                        + "(5) Mid-Square Method\n"
                        + "(6) Folding Shifting\n"
                        + "(7) Folding Boundary\n"
                        + "(8) Rotation Method");
                System.out.print("Select Hashing Method: ");
                
                //checks if the user entered valid inputs and will not skip unless corrected
                while(!scan.hasNextInt()) {
                    System.out.print("Invalid entry. Enter number only: ");
                    scan.next();
                }
                hashingMethod = scan.nextInt();
                scan.nextLine();
                
                if(hashingMethod >= 1 && hashingMethod <= 8) break;
                System.out.println("Number entered is invalid. Enter number between 1-8 only..\n");
            }
            
            
            //=====HASHING METHOD IF ELSE: FOR THE MESSAGE PARTICULAR TO THE METHOD======
            //DIRECT METHOD
            if(hashingMethod == 1) {
                System.out.println("Direct Method... hashing " + key);

                hash = direct();
                
                if(hash >= size) { //if hash index is 100 or above
                    System.out.println("Direct method doesn't allow key greater or equal to size.");
                    System.out.println("KEY INSERTION UNSUCCESSFUL.\n");
                } else {
                    insert(hash);
                }
            } 
            //SUBTRACTION METHOD
            else if(hashingMethod == 2) { 
                System.out.println("Sutraction Method... hashing " + key);
                
                hash = subtraction();
                
                if(hash >= 100) { //if hash index is 100 or above
                    System.out.println("Subtraction method doesn't allow key greater or equal to size.");
                    System.out.println("KEY INSERTION UNSUCCESSFUL.\n");
                } else insert(hash);
            } 
            //MODULO-DIVISION METHOD
            else if(hashingMethod == 3) {
                System.out.println("Modulo-Division Method... hashing " + key);
                
                hash = moduloDivision();
                
                insert(hash);
            }
            //DIGIT EXTRACTION METHOD
            else if(hashingMethod == 4) { //at least 2
                System.out.println("Digit Extraction Method... hashing " + key);

                hash = digitExtraction();
                
                insert(hash);
            } 
            //MID-SQUARE METHOD
            else if(hashingMethod == 5) {
                System.out.println("Mid-Square Method... hashing " + key);

                hash = midSquare();
                
                insert(hash);
            } 
            //FOLD SHIFTING
            else if(hashingMethod == 6) {
                System.out.println("Fold Shifting... hashing " + key);

                hash = foldShifting();
                
                insert(hash);
            }
            //FOLD-BOUNDARY
            else if(hashingMethod == 7) {
                System.out.println("Fold Boundary... hashing " + key);

                hash = foldBoundary();
                
                insert(hash);
            }
            //ROTATION METHOD
            else if(hashingMethod == 8) {
                System.out.println("Rotation Method... hashing " + key);

                hash = rotation();
                
                insert(hash);
            } 
            
        }
    }
    
    //INSERTING KEY TO HASH TABLE METHOD
    static void insert(int hash) {
        System.out.println("Calculated Index: " + hash);
        
        if(hashTable[hash] != 0) openAddressing(); //if collision occurs
        else {
            hashTable[hash] = key;
            
            System.out.println("Key " + key + " is inserted in  index " + hash + " WITHOUT COLLISION.");
            System.out.println("KEY INSERTION SUCCESSFUL.\n");
        }
    }
    
}
