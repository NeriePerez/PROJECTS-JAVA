package com.mycompany.grading;
import java.util.*;

public class Grading_1 {
    
    //================================================================================
    // VARIABLES FOR LOG IN AND SIGN UP FOR STUDENT 
    // VARIABLES FOR LOG IN FOR ADMIN
    private static final String Admin_Username = "Admin1";
    private static final String Admin_Password = "pass";
    
    public static final int USERS = 100;
    public static User[] users = new User[100]; 
    public static final String[] UserNames = new String[USERS];
    public static final String[] PassWords = new String[USERS];
    public static final int[] Year = new int[USERS];
    public static final int[] Sem = new int[USERS];
    private static int userCount = 0; // To track the number of registered users

    public static String[] yearsem = new String[10];
    public static String[] year1sem1 = {"Programming", "Science", "History", "Computing"};
    public static String[] year1sem2 = {"Programming2", "Mathematics", "Communication", "Arts"};
    public static String[] year2sem1 = {"Info Management", "Discrete Math", "Contemporary", "Web Systems"};
    public static String[] year2sem2 = {"Gender and Society", "Accounting", "Networking 1", "Advanced Database System"};
    public static String[] year3sem1 = {"Philippine Culture", "Project Management", "Networking 2", "Quantitative Methods"};
    public static String[] year3sem2 = {"Data Mining", "Technopreneurship", "Capstone Project 1", "Social and Professional Issues 1"};
    public static String[] year4sem1 = {"System Integration and Architecture", "Information Assurance and Security 2", "Advanced Seminar", "System Administration and Maintenance"};
    public static String[] year4sem2 = {"Practicum", "Capstone Project 2", "Practicum2", "Capstone"};
    
    public static int[][] writtenScores = new int[4][10]; // 4 courses, max 10 written scores per course
    public static int[][] examScores = new int[4][5];    // 4 courses, max 5 exam scores per course
    public static int[][] writtenMax= new int[4][10];
    public static int[][] examMax = new int[4][5];
    public static String[][] writtenTitles = new String[4][10];


    //================================================================================
    // VARIABLES FOR STORING INPUTS OF EACH USERS
    public class User {
        private String username;
        private String password;
        private int year;
        private int semester;
        private int[][] writtenScores;
        private int[][] writtenMax;
        private String[][] writtenTitles;
        private int[][] examScores;
        private int[][] examMax;
        
        // Constructor
        public User(String username, String password, int year, int semester) {
            this.username = username;
            this.password = password;
            this.year = year;
            this.semester = semester;
            this.writtenScores = new int[4][10];
            this.writtenMax = new int[4][10];
            this.writtenTitles = new String[4][10];
            this.examScores = new int[4][5];
            this.examMax = new int[4][5];
        }
        
        // Getters and Setters
        public String getUsername() {
            return username;
        }
        
        public String getPassword() {
            return password;
        }
        
        public int getYear() {
            return year;
        }
        
        public int getSemester() {
            return semester;
        }
        
        public int[][] getWrittenScores() {
            return writtenScores;
        }
        
        public int[][] getWrittenMax() {
            return writtenMax;
        }
        
        public String[][] getWrittenTitles() {
            return writtenTitles;
        }
        
        public int[][] getExamScores() {
            return examScores;
        }
        
        public int[][] getExamMax() {
            return examMax;
        }
        
        // Setters for username and password
        public void setUsername(String username) {
            this.username = username;
        }
        
        public void setPassword(String password) {
            this.password = password;
        }
        
        // Setters for year and semester
        public void setYear(int year) {
            this.year = year;
        }
        
        public void setSemester(int semester) {
            this.semester = semester;
        }
        
        private boolean pendingDeletion = false;
        
        public boolean isPendingDeletion() {
            return pendingDeletion;
        }
        
        public void setPendingDeletion(boolean pendingDeletion) {
            this.pendingDeletion = pendingDeletion;
        }
    }

    
 
    //================================================================================
    //================================================================================
    //Interface for choosing ADMIN OR STUDENT
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("==================== STUDENT PERSONAL ACCOUNT MANAGEMENT====================\n");
        
        boolean mainMenu = true;
        System.out.print("\n========== MAIN MENU ==========");
        while (mainMenu) {
            try {
                System.out.println("Are you an Admin or a Student?");
                System.out.println("1. Admin\n2. Student");
                System.out.print("Choose: ");
                int roleChoice = scan.nextInt();
                scan.nextLine(); // Consume the newline
                
                switch (roleChoice) {
                    case 1:
                        adminLogIn(scan);
                        break;
                    case 2:
                        studentMenu(scan);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.\n");
                scan.nextLine(); // Clear the invalid input
            }
        }
    }

    //================================================================================
    //================================================================================
    // Admin Menu Login Menu
    private static void adminLogIn(Scanner scan) {
        System.out.println("\n========== ADMIN LOGIN ==========");
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter admin username(or enter 0 to return to the main menu): ");
            String ADMIN_USERNAME = scan.nextLine();
            
            // Check if the user wants to return to the main menu
            if (ADMIN_USERNAME.equals("0")) {
                System.out.println("\nReturning to the main menu...\n\n========== MAIN MENU ==========\n");
                return;
            }
            
            System.out.print("Enter admin password: ");
            String ADMINPASSWORD = scan.nextLine();
                
            // Verify admin credentials
            boolean isAdminValid = false;
            for (int i = 0; i < 1; i++) {
                if (Admin_Username.equals(ADMIN_USERNAME) && Admin_Password.equals(ADMINPASSWORD)) {
                    isAdminValid = true;
                    break;
                }
            }
            
            if (isAdminValid) {
                System.out.println("Welcome, Admin: " + Admin_Username + "!");
                adminDashboard(scan);
                break;
            } else {
                System.out.println("Invalid admin credentials. Try again.\n");
            }
            attempts++;
        }
        if (attempts == 3) {
            System.out.println("Too many invalid attempts. Returning to the main menu.\n\n========== MAIN MENU ==========\n");
            return;
        }
    }

    //================================================================================
    //================================================================================
    // Admin Dashboard
    private static void adminDashboard(Scanner scan) {
        System.out.println("\n========== ADMIN DASHBOARD ==========");
        boolean adminActive = true;
        
        while (adminActive) {
            try {
                System.out.println("1. View All Students"
                    + "\n2. Remove a Student"
                    + "\n3. Approve Account Deletion Requests"
                    + "\n0. Log Out");
                System.out.print("Choose an option: ");
                int choice = scan.nextInt();
                scan.nextLine(); // Consume the newline
                
                switch (choice) {
                    case 0:
                        System.out.println("Logging out...\n\n========== MAIN MENU ==========");
                        adminActive = false;
                        break;
                    case 1:
                        viewAllStudents();
                        break;
                    case 2:
                        approveDeletionRequests(scan);
                        break;
                    default:
                    System.out.println("Invalid choice. Please select a valid option.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.\n");
                scan.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage() + "\n");
                scan.nextLine(); // Clear the buffer
            }
        }
    }
    
    //================================================================================
    //================================================================================
    //VIEW STUDENT
    private static void viewAllStudents() {
        System.out.println("\n========== ALL STUDENTS ==========");
        
        try {
            // Check if there are students
            if (userCount == 0) {
                System.out.println("No students registered yet.\n");
                return;
            }
            
            // Loop through the users array and print each student's details
            System.out.printf("%-5s %-15s %-10s %-10s %-20s%n", "No.", "Username", "Year", "Semester", "Deletion Status");
            System.out.println("--------------------------------------------------------------------------");
            
            for (int i = 0; i < userCount; i++) {
                String deletionStatus = users[i].isPendingDeletion() ? "Pending Deletion" : "Active";
                System.out.printf("%-5d %-15s %-10s %-10s %-20s%n",
                    (i + 1),
                    users[i].getUsername(),
                    users[i].getYear(),
                    users[i].getSemester(),
                    deletionStatus);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while displaying students: " + e.getMessage() + "\n");
        }
    }

    //================================================================================
    //================================================================================
    //DELETION REQUEST 
    private static void approveDeletionRequests(Scanner scan) {
        System.out.println("\n========== APPROVE ACCOUNT DELETION REQUESTS ==========");
        
        boolean hasRequests = false;
        
        for (int i = 0; i < userCount; i++) {
            if (users[i].isPendingDeletion()) {
                hasRequests = true;
                System.out.printf("%d. Username: %s%n", (i + 1), users[i].getUsername());
            }
        }
        
        if (!hasRequests) {
            System.out.println("No pending deletion requests.\n");
            return;
        }
        
        while (true) {
            System.out.print("Enter the number of the account to approve deletion (0 to return): ");
            int choice;
            
            try {
                choice = scan.nextInt() - 1;
                scan.nextLine(); // Consume newline
                
                if (choice == -1) {
                    System.out.println("Returning to Admin Dashboard.\n");
                    return;
                }
                
                if (choice >= 0 && choice < userCount && users[choice].isPendingDeletion()) {
                    users[choice] = null; // Delete user
                    userCount--;
                    System.out.println("Account deletion approved and user removed successfully.\n");
                    return;
                } else {
                    System.out.println("Invalid selection or no pending deletion for this user.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.\n");
                scan.nextLine();
            }
        }
    }

    
    
    //================================================================================
    //================================================================================
    // Student Menu
    private static void studentMenu(Scanner scan) {
        System.out.print("\n========== STUDENT MENU ==========");
        boolean studentMenu = true;
        while (studentMenu) {
            try {
                System.out.println("\n1. Sign Up"
                    + "\n2. Log In"
                    + "\nSelect 1 or 2, or \"0\" if you want go back to Main Menu.");
                System.out.print("Choose: ");
                int choice = scan.nextInt();
                scan.nextLine(); // Consume the newline
                
                switch (choice) {
                    case 0:
                        System.out.println("Returning to main menu...\n\n========== MAIN MENU ==========");
                        return;
                    case 1:
                        signUp(scan);
                        studentMenu = false;
                        break;
                    case 2:
                        logIn(scan);
                        studentMenu = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scan.nextLine(); // Clear the invalid input
            }
        } 
    }
    
    //================================================================================
    //================================================================================
    // Method for signing up for students
    private static void signUp(Scanner scan) {
        System.out.println("\n========== STUDENT SIGNUP ==========");
        
        while (true) {
            if (userCount >= users.length) { // Check if the users array is full
                System.out.println("User limit reached. Cannot sign up more users.");
                return;
            }
            
            try {
                System.out.print("Enter username(or enter 0 to return to the main menu): ");
                String newUsername = scan.nextLine();
                
                // Check if the user wants to return to the main menu
                if (newUsername.equals("0")) {
                    System.out.println("\nReturning to the student menu...\n");
                    studentMenu(scan);
                    return;
                }
                
                // Check if the username already exists
                boolean usernameExists = false;
                for (int i = 0; i < userCount; i++) {
                    if (users[i].getUsername().equals(newUsername)) {
                        usernameExists = true;
                        break;
                    }
                }
                
                if (usernameExists) {
                    System.out.println("Username already exists. Please choose a unique username.");
                    System.out.println("Enter username(or enter 0 to return to the main menu): ");
                    newUsername = scan.nextLine();
                    
                    // Check if the user wants to return to the main menu
                    if (newUsername.equals("0")) {
                        System.out.println("\nReturning to the student menu...\n");
                        studentMenu(scan);
                        return;
                    }
                    
                    continue; // Ask for the username again
                }
                
                System.out.print("Enter password: ");
                String newPassword = scan.nextLine();
                
                int newYear;
                while (true) {
                    System.out.print("Enter year (1, 2, 3, 4): ");
                    try {
                        newYear = scan.nextInt();
                        if (newYear < 1 || newYear > 4) {
                            System.out.println("Invalid year. Please enter a number between 1 and 4.");
                            continue;
                        }
                        break; // Valid input
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scan.nextLine(); // Clear invalid input
                    }
                }
                
                int newSemester;
                while (true) {
                    System.out.print("Enter semester (1, 2): ");
                    try {
                        newSemester = scan.nextInt();
                        if (newSemester < 1 || newSemester > 2) {
                            System.out.println("Invalid semester. Please enter 1 or 2.");
                            continue;
                        }
                        break; // Valid input
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scan.nextLine(); // Clear invalid input
                    }
                }
                
                scan.nextLine(); // Consume the newline left by nextInt()
                
                // Create and add the new user
                users[userCount] = new Grading_1().new User(newUsername, newPassword, newYear, newSemester); // Inner class instantiation
                userCount++;
                System.out.println("Sign-up successful!");
                studentMenu(scan); // Redirect to student menu
                return; // Exit sign-up
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again.");
                scan.nextLine(); // Clear the buffer
            }
        }
    }

    //================================================================================
    //================================================================================
    // Method for logging in
    public static void logIn(Scanner scan) {
        System.out.println("\n========== STUDENT LOG IN ==========");
        int attempt = 0;
        while (attempt < 3) {
            System.out.print("Enter username(or enter 0 to return to the student menu): ");
            String loginUsername = scan.nextLine();
            System.out.print("Enter password: ");
            String loginPassword = scan.nextLine();
            
            // Check if the user wants to return to the main menu
            if (loginUsername.equals("0")) {
                System.out.println("\nReturning to the student menu...\n");
                studentMenu(scan);
                return;
            }
            for (int i = 0; i < userCount; i++) {
                if (users[i].username.equals(loginUsername) && users[i].password.equals(loginPassword)) {
                    System.out.println("Login successful! Welcome, " + loginUsername + "!");
                    studentDashboard(users[i]);
                    return;
                }
            }
            
            System.out.println("Invalid username or password. Please try again.\n");
            attempt++;
        } if (attempt == 3) {
            System.out.println("Too many invalid attempts. Returning to the student menu.");
            studentMenu(scan);
        }    
    }
    
    private static String convertGrade(double grade) {
    if (grade >= 98 && grade <= 100) return "1.0 - Excellent";
    else if (grade >= 95 && grade <= 97) return "1.25 - Outstanding";
    else if (grade >= 92 && grade <= 94) return "1.5 - Very Good";
    else if (grade >= 89 && grade <= 91) return "1.75 - Good";
    else if (grade >= 86 && grade <= 88) return "2.0 - Very Satisfactory";
    else if (grade >= 83 && grade <= 85) return "2.25 - Satisfactory";
    else if (grade >= 80 && grade <= 82) return "2.5 - Moderately Fair";
    else if (grade >= 77 && grade <= 79) return "2.75 - Fair";
    else if (grade >= 75 && grade <= 76) return "3.0 - Passing";
    else if (grade >= 72 && grade <= 74) return "4.0 - Conditional";
    else if (grade >= 0 && grade <= 71) return "5.0 - Failure";
    else return "INC - Incomplete";
}
    
    
    
    //================================================================================
    //================================================================================
    // Method for each student's dashboard
    public static void studentDashboard(User user) {
    Scanner scan = new Scanner(System.in);

    while (true) {
        System.out.println("\n========== STUDENT DASHBOARD ==========");
        System.out.println("Student Name: " + user.getUsername());
        System.out.println("Year: " + user.getYear());
        System.out.println("Semester: " + user.getSemester());

        // Determine courses based on year and semester
        String[] yearsem = (user.getYear() == 1 && user.getSemester() == 1) ? year1sem1 :
                           (user.getYear() == 1 && user.getSemester() == 2) ? year1sem2 :
                           (user.getYear() == 2 && user.getSemester() == 1) ? year2sem1 :
                           (user.getYear() == 2 && user.getSemester() == 2) ? year2sem2 :
                           (user.getYear() == 3 && user.getSemester() == 1) ? year3sem1 :
                           (user.getYear() == 3 && user.getSemester() == 2) ? year3sem2 :
                           (user.getYear() == 4 && user.getSemester() == 1) ? year4sem1 :
                           (user.getYear() == 4 && user.getSemester() == 2) ? year4sem2 :
                           null;

        if (yearsem == null) {
            System.out.println("No courses available for your year and semester.");
            return; // Exit the dashboard
        }

        // Display the grades table
        System.out.printf("\n%-5s %-20s %-10s %-30s%n", "No.", "Course", "Grade", "Converted Grade");
        System.out.println("----------------------------------------------------------------");

        int[][] writtenScores = user.getWrittenScores();
        int[][] writtenMax = user.getWrittenMax();
        int[][] examScores = user.getExamScores();
        int[][] examMax = user.getExamMax();

        double totalPredictedGrade = 0;
        int numCourses = yearsem.length;

        for (int i = 0; i < numCourses; i++) {
            int totalWrittenScore = 0;
            int totalWrittenMax = 0;
            int totalExamScore = 0;
            int totalExamMax = 0;

            // Calculate written grades
            for (int j = 0; j < writtenScores.length; j++) {
                if (writtenScores[j][i] > 0) {
                    totalWrittenScore += writtenScores[j][i];
                    totalWrittenMax += writtenMax[j][i];
                }
            }

            // Calculate exam grades
            for (int j = 0; j < examScores.length; j++) {
                if (examScores[j][i] > 0) {
                    totalExamScore += examScores[j][i];
                    totalExamMax += examMax[j][i];
                }
            }

            double writtenGrade = (totalWrittenMax > 0) ? ((double) totalWrittenScore / totalWrittenMax * 20) : 0;
            double examGrade = (totalExamMax > 0) ? ((double) totalExamScore / totalExamMax * 60) : 0;
            double predictedGrade = (writtenGrade + examGrade)/2 + 50;

            totalPredictedGrade += predictedGrade;

            // Convert grade and display
            String convertedGrade = convertGrade(predictedGrade);
            System.out.printf("%-5d %-20s %-10.2f %-30s%n", (i + 1), yearsem[i], predictedGrade, convertedGrade);
        }

        // Calculate and display overall predicted grade
        double overallPredictedGrade = (numCourses > 0) ? totalPredictedGrade / numCourses : 0;
        System.out.printf("\nOverall Predicted Grade: %.2f%%\n", overallPredictedGrade);

        int courseChoice = -1;
        boolean validInput = false;

        // Keep looping until valid input is provided
        while (!validInput) {
            try {
                System.out.println("\nEnter the number corresponding to a course to view grades, '5' to manage account or '0' to logout:");
                System.out.print("Your choice: ");
                courseChoice = scan.nextInt();
                scan.nextLine(); // Consume newline

                // Validate the input
                if (courseChoice < 0 || courseChoice > yearsem.length && courseChoice != 5) {
                    throw new IllegalArgumentException("Invalid choice. Please enter a number between 0 and " + yearsem.length + ", or 5 to manage your account.");
                }
                validInput = true; // Input is valid, break the loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scan.nextLine(); // Clear invalid input
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // Print custom error message
            }
        }

        if (courseChoice == 0) {
            System.out.println("Logging out...\n\n========== MAIN MENU ==========");
            return; // Exit the dashboard
        }

        if (courseChoice == 5) {
            manageAccount(user, scan);
            continue;
        }

        // Call method to view or manage grades for the selected course
        String selectedCourse = yearsem[courseChoice - 1];
        viewCourseGrades(user, selectedCourse, yearsem, scan);
    }
}
    
    //================================================================================
    //================================================================================
    // Method to view course grades
    public static void viewCourseGrades(User user, String courseName, String[] yearsem, Scanner scan) {
    System.out.println("\n========== COURSE: " + courseName + " ==========");
        
        try {
            // Find the index of the course in the user's year/semester data
            int courseIndex = -1;
            for (int i = 0; i < yearsem.length; i++) {
                if (yearsem[i].equals(courseName)) {
                    courseIndex = i;
                    break;
                }
            }
            
            if (courseIndex == -1) {
                System.out.println("Course not found.");
                return;
            }
            
            // Access user-specific data
            int[][] writtenScores = user.getWrittenScores();
            int[][] writtenMax = user.getWrittenMax();
            int[][] examScores = user.getExamScores();
            int[][] examMax = user.getExamMax();
            
            // Validate array dimensions
            if (courseIndex >= writtenScores[0].length || courseIndex >= examScores[0].length) {
                System.out.println("Data inconsistency detected for course index " + courseIndex);
                return;
            }
            
            // Calculate total written scores
            int totalWrittenScore = 0;
            int totalWrittenMax = 0;
            for (int i = 0; i < writtenScores.length; i++) {
                if (writtenScores[i][courseIndex] > 0) {
                    totalWrittenScore += writtenScores[i][courseIndex];
                    totalWrittenMax += writtenMax[i][courseIndex];
                }
            }
            
            // Calculate total exam scores
            int totalExamScore = 0;
            int totalExamMax = 0;
            for (int i = 0; i < examScores.length; i++) {
                if (examScores[i][courseIndex] > 0) {
                    totalExamScore += examScores[i][courseIndex];
                    totalExamMax += examMax[i][courseIndex];
                }
            }
            
            // Calculate grades
            double writtenGrade = (totalWrittenMax > 0) ? ((double) totalWrittenScore / totalWrittenMax) * 20 : 0;
                double examGrade = (totalExamMax > 0) ? ((double) totalExamScore / totalExamMax) * 60 : 0;
                    double finalGrade = (writtenGrade + examGrade) / 2 + 50;
                    
                    // Display calculated grades
                    System.out.printf("1. Written Assignments Grade: %.2f%%\n", writtenGrade);
                System.out.printf("2. Exam Grade: %.2f%%\n", examGrade);
            System.out.printf("Final Grade: %.2f%%\n", finalGrade);
            
            // Convert and display grades
        String convertedGrade = convertGrade(finalGrade);
        System.out.printf("%-20s %-10s %-30s%n", "Course", "Grade", "Converted Grade");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-20s %-10.2f %-30s%n", courseName, finalGrade, convertedGrade);
            
            // Allow user to manage assignments or exams
            while (true) {
                try {
                    System.out.print("Enter 1 to manage written scores, 2 to manage exam scores, or 0 to exit: ");
                    int action = scan.nextInt();
                    scan.nextLine(); // Consume newline
                    
                    switch (action) {
                        case 0:
                            return;
                        case 1:
                            manageWrittenScores(user, courseIndex, scan);
                            break;
                        case 2:
                            manageExamScores(user, courseIndex, scan);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.\n");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.\n");
                    scan.nextLine(); // Clear invalid input
                }
            }
            
        } catch (NullPointerException e) {
            System.out.println("Error: User data is incomplete. Please contact the administrator.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Course index is out of bounds. Please verify the course data.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    
    
    
    //================================================================================
    //================================================================================
    // Function to manage detailed written scores
    private static void manageWrittenScores(User user, int courseIndex, Scanner scan) {
        int[][] writtenScores = user.getWrittenScores();
        int[][] writtenMax = user.getWrittenMax();
        String[][] writtenTitles = user.getWrittenTitles();
        
        System.out.println("\n== MANAGE WRITTEN ASSIGNMENTS ==");
        while (true) {
            try {
                boolean hasInputs = false;
                for (int i = 0; i < writtenScores.length; i++) {
                    if (writtenScores[i][courseIndex] > 0 || writtenMax[i][courseIndex] > 0) {
                        hasInputs = true;
                        System.out.printf("Assignment %d: %d/%d (Title: %s)\n", (i + 1),
                            writtenScores[i][courseIndex],
                            writtenMax[i][courseIndex],
                            writtenTitles[i][courseIndex] != null ? writtenTitles[i][courseIndex] : "Untitled");
                    }
                }
                
                if (!hasInputs) {
                    System.out.println("No inputs yet.");
                }
                
                System.out.println("1. Add new assignment");
                System.out.println("2. Edit an assignment");
                System.out.println("3. Remove an assignment");
                System.out.println("0. Go back");
                
                System.out.print("Enter your choice: ");
                int choice = scan.nextInt();
                scan.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        // Add new assignment
                        System.out.print("\nEnter title: ");
                        String title = scan.nextLine();
                        System.out.print("Enter score: ");
                        int score = scan.nextInt();
                        System.out.print("Enter maximum score: ");
                        int maxScore = scan.nextInt();
                        scan.nextLine(); // Consume newline

                        // Find the first empty slot in the arrays
                        int newAssignmentIndex = -1;
                        for (int i = 0; i < writtenScores.length; i++) {
                            if (writtenMax[i][courseIndex] == 0 && writtenScores[i][courseIndex] == 0) {
                                newAssignmentIndex = i;
                                break;
                            }
                        }
                    
                        if (newAssignmentIndex == -1) {
                            System.out.println("Maximum assignments reached. Cannot add more.\n");
                        } else {
                            writtenScores[newAssignmentIndex][courseIndex] = score;
                            writtenMax[newAssignmentIndex][courseIndex] = maxScore;
                            writtenTitles[newAssignmentIndex][courseIndex] = title;
                            System.out.println("Assignment added successfully!\n");
                        }
                        break;
                    case 2:
                        // Edit an assignment
                        System.out.print("\nEnter assignment number to edit: ");
                        int editIndex = scan.nextInt() - 1;
                        scan.nextLine(); // Consume newline

                        if (editIndex >= 0 && editIndex < writtenScores.length) {
                            System.out.print("Enter new title (or press Enter to keep current): ");
                            String newTitle = scan.nextLine();
                            if (!newTitle.isEmpty()) {
                                writtenTitles[editIndex][courseIndex] = newTitle;
                            }
                            
                            System.out.print("Enter new score (or -1 to keep current): ");
                            int newScore = scan.nextInt();
                            if (newScore != -1) {
                                writtenScores[editIndex][courseIndex] = newScore;
                            }
                            
                            System.out.print("Enter new maximum score (or -1 to keep current): ");
                            int newMaxScore = scan.nextInt();
                            if (newMaxScore != -1) {
                                writtenMax[editIndex][courseIndex] = newMaxScore;
                            }
                            
                            System.out.println("Assignment updated successfully!\n");
                        } else {
                            System.out.println("Invalid assignment number.\n");
                        }
                        break;
                    
                    case 3:
                        // Remove an assignment
                        System.out.print("\nEnter assignment number to remove: ");
                        int removeIndex = scan.nextInt() - 1;
                        scan.nextLine(); // Consume newline
                    
                        if (removeIndex >= 0 && removeIndex < writtenScores.length) {
                            writtenScores[removeIndex][courseIndex] = 0;
                            writtenMax[removeIndex][courseIndex] = 0;
                            writtenTitles[removeIndex][courseIndex] = null;
                            
                            System.out.println("Assignment removed successfully!\n");
                        } else {
                            System.out.println("Invalid assignment number.\n");
                        }
                        break;
                    
                    case 0:
                        return; // Exit the manage written assignments
                    
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.\n");
                scan.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage()+"\n");
            }
        }
    }
    
    //================================================================================
    //================================================================================
    // Function to manage detailed exam scores
    private static void manageExamScores(User user, int courseIndex, Scanner scan) {
        System.out.println("\n========== MANAGE EXAM SCORES ==========");
        
        int[][] examScores = user.getExamScores();
        int[][] examMax = user.getExamMax();
        
        boolean hasExamScores = false;
            for (int i = 0; i < examScores.length; i++) {
                if (examScores[i][courseIndex] > 0) {
                    hasExamScores = true;
                    System.out.printf("Exam %d: %d/%d\n", (i + 1),
                        examScores[i][courseIndex],
                        examMax[i][courseIndex]);
                }
            }
            
            if (!hasExamScores) {
                System.out.println("No exam scores yet.");
            }
        
        boolean manExam = true;
        while (manExam) {
            System.out.println("1. Add new exam");
            System.out.println("2. Edit an exam");
            System.out.println("3. Remove an exam");
            System.out.println("0. Go back");
            
            try {
                System.out.print("Enter your choice: ");
                int choice = scan.nextInt();
                scan.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        // Add new exam
                        System.out.print("\nEnter score: ");
                        int score = scan.nextInt();
                        System.out.print("Enter maximum score: ");
                        int maxScore = scan.nextInt();
                        scan.nextLine(); // Consume newline

                        for (int i = 0; i < examScores.length; i++) {
                            if (examScores[i][courseIndex] == 0) {
                                examScores[i][courseIndex] = score;
                                examMax[i][courseIndex] = maxScore;
                                System.out.println("Exam added successfully!\n");
                                break;
                            }
                        }
                        break;
                    
                    case 2:
                        // Edit an exam
                        System.out.print("\nEnter exam number to edit: ");
                        int editIndex = scan.nextInt() - 1;
                        scan.nextLine(); // Consume newline

                        if (editIndex >= 0 && editIndex < examScores.length) {
                            System.out.print("Enter new score (or -1 to keep current): ");
                            int newScore = scan.nextInt();
                            if (newScore != -1) {
                                examScores[editIndex][courseIndex] = newScore;
                            }
                            
                            System.out.print("Enter new maximum score (or -1 to keep current): ");
                            int newMaxScore = scan.nextInt();
                            if (newMaxScore != -1) {
                                examMax[editIndex][courseIndex] = newMaxScore;
                            }
                            
                            System.out.println("Exam updated successfully!\n");
                        } else {
                            System.out.println("Invalid exam number.\n");
                        }
                        break;
                    
                    case 3:
                        // Remove an exam
                        System.out.print("\nEnter exam number to remove: ");
                        int removeIndex = scan.nextInt() - 1;
                        scan.nextLine(); // Consume newline
                    
                        if (removeIndex >= 0 && removeIndex < examScores.length) {
                            examScores[removeIndex][courseIndex] = 0;
                            examMax[removeIndex][courseIndex] = 0;
                            
                            System.out.println("Exam removed successfully!\n");
                        } else {
                            System.out.println("Invalid exam number.\n");
                        }
                        break;
                    
                    case 0:
                        return; // Exit the manage exam scores
                    
                    default:
                    System.out.println("Invalid choice.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.\n");
                scan.nextLine(); // Clear the buffer
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage()+"\n");
            }
        }
    }
    
    
    
    //================================================================================
    //================================================================================
    //Manage users account
    private static void manageAccount(User user, Scanner scan) {
        System.out.println("\n========== ACCOUNT MANAGEMENT ==========");
        
        boolean manage = true;
        while (manage) {
            System.out.println("1. Change username");
            System.out.println("2. Change password");
            System.out.println("3. Change year and/or semester");
            System.out.println("4. Delete account");
            System.out.println("0. Go back");
            System.out.print("Enter your choice: ");
            
            try {
                int choice = scan.nextInt();
                scan.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        changeUsername(user, scan);
                        break;
                    case 2:
                        changePassword(user, scan);
                        break;
                    case 3:
                        changeYearSemester(user, scan);
                        break;
                    case 4:
                        deleteAccount(user, scan);
                        break; // Exit the method after account deletion
                    case 0:
                        studentDashboard(user);
                        break; // Go back to the previous menu
                    default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scan.nextLine(); // Clear invalid input
            }
            manage = false;
        }
    }
    
    //================================================================================
    //================================================================================
    //CHANGE USERNAME
    private static void changeUsername(User user, Scanner scan) {
        // Ask for the new username
        System.out.println("\n========== SETTING NEW USERNAME ==========");
        System.out.print("Enter your new username(or enter 0 to cancel): ");
        String newUsername = scan.nextLine().trim();
        
        // Check if the user wants to return to the main menu
        if (newUsername.equals("0")) {
            System.out.println("\nCancelling...\n");
            manageAccount(user, scan);
            return;
        }
        
        // Check if the input is empty
        while (newUsername.isEmpty()) {
            System.out.println("Username cannot be empty. Please try again.");
            System.out.print("\nEnter your new username(or enter 0 to cancel): ");
            newUsername = scan.nextLine();
            
            // Check if the user wants to return to the main menu
            if (newUsername.equals("0")) {
                System.out.println("\nCancelling...\n");
                manageAccount(user, scan);
                return;
            }
        }
        
        // Check if the username already exists
            boolean usernameExists = false;
        for (int i = 0; i < userCount; i++) {
            if (users[i] != null && users[i].getUsername().equalsIgnoreCase(newUsername)) {
                usernameExists = true;
                break;
            }
        }
        
        // If username exists, notify and return
            while (usernameExists) {
                System.out.println("This username already exists. Please choose a different username.");
                System.out.print("\nEnter your new username(or enter 0 to cancel): ");
                newUsername = scan.nextLine();
                
                // Check if the user wants to return to the main menu
                if (newUsername.equals("0")) {
                    System.out.println("\nCancelling...\n");
                    manageAccount(user, scan);
                    return;
                }
                
                // Recheck if the new username already exists
                usernameExists = false;
                
                for (int i = 0; i < userCount; i++) {
                    if (users[i] != null && users[i].getUsername().equalsIgnoreCase(newUsername)) {
                        usernameExists = true;
                        break;
                    }
                }
            }
        // Ask for the current password to confirm the change
        int attempts = 0;
        boolean passwordValid = false;
        
        while (attempts < 3 && !passwordValid) {
            System.out.print("Enter your password to confirm the change: ");
            String currentPassword = scan.nextLine();
            
            // Validate the entered password
            if (user.getPassword().equals(currentPassword)) {
                // If the password is correct, update the username
                user.setUsername(newUsername);
                System.out.println("Username successfully updated to: " + newUsername + "\n");
                passwordValid = true;  // Password is valid, exit the loop
            } else {
                attempts++;
                if (attempts < 3) {
                    System.out.println("Incorrect password. You have " + (3 - attempts) + " attempt(s) left.\n");
                } else {
                    System.out.println("Incorrect password. You have exceeded the maximum number of attempts.\n");
                }
            }
        }
        
        // If 3 attempts are exceeded, cancel the operation
        if (!passwordValid) {
            System.out.println("You were unable to validate your password in 3 attempts. Username change canceled.\n");
        }
    }
    
    //================================================================================
    //================================================================================
    //CHANGE PASSWORD
    private static void changePassword(User user, Scanner scan) {
        System.out.println("\n========== SETTING NEW PASSWORD ==========");
        
        int attempts = 0; // Track the number of incorrect attempts
        boolean passwordValid = false;
        
        while (attempts < 3 && !passwordValid) {
            System.out.print("Enter your current password(or enter 0 to cancel): ");
            String currentPassword = scan.nextLine();
            
            // Check if the user wants to return to the main menu
            if (currentPassword.equals("0")) {
                System.out.println("\nCancelling... \n");
                manageAccount(user, scan);
                return;
            }
            
            // Validate the entered current password
            if (user.getPassword().equals(currentPassword)) {
                String newPassword = "";
                
                // Ensure new password is not empty
                while (newPassword.trim().isEmpty()) {
                    System.out.print("Enter your new password(or enter 0 to cancel): ");
                    newPassword = scan.nextLine();
                    
                    // Check if the user wants to return to the main menu
                    if (currentPassword.equals("0")) {
                        System.out.println("\nCancelling... \n");
                        manageAccount(user, scan);
                        return;
                    }
                    
                    if (newPassword.trim().isEmpty()) {
                        System.out.println("Password cannot be empty. Please try again.\n");
                    }
                }
                
                // If new password is valid, update
                user.setPassword(newPassword);
                System.out.println("Password changed successfully.\n");
                passwordValid = true;  // Exit the loop since password is valid
            } else {
                attempts++;
                if (attempts < 3) {
                    System.out.println("Incorrect password. You have " + (3 - attempts) + " attempt(s) left.\n");
                } else {
                    System.out.println("Incorrect password. You have exceeded the maximum number of attempts.\n");
                }
            }
        }
        
        // If 3 attempts are exceeded, cancel the operation
        if (!passwordValid) {
            System.out.println("You were unable to validate your password in 3 attempts. Password change canceled.\n");
        }
    }
    
    //================================================================================
    //================================================================================
    //CHANGE YEAR AND SEMESTER
    private static void changeYearSemester(User user, Scanner scan) {
        System.out.println("\n========== SETTING NEW YEAR AND/OR SEMESTER ==========");
        try {
            // Step 1: Select Year and Semester
            System.out.print("\nEnter your new year (1-4): ");
            int newYear = scan.nextInt();
            while (newYear < 1 || newYear > 4) {
                System.out.print("Invalid year. Enter a valid year (1-4): ");
                newYear = scan.nextInt();
            }
            
            System.out.print("Enter your new semester (1-2): ");
            int newSemester = scan.nextInt();
            while (newSemester < 1 || newSemester > 2) {
                System.out.print("Invalid semester. Enter a valid semester (1-2): ");
                newSemester = scan.nextInt();
            }
            scan.nextLine(); // Consume newline
            
            // Step 2: Ask for Password to Confirm the Change
            int attempts = 0;
            boolean passwordValid = false;
            
            while (attempts < 3 && !passwordValid) {
                System.out.print("Enter your password to confirm the change(or enter 0 to cancel): ");
                String currentPassword = scan.nextLine();
                
                // Check if the user wants to return to the main menu
                if (currentPassword.equals("0")) {
                    System.out.println("\nCancelling... \n");
                    manageAccount(user, scan);
                    return;
                }
                
                // Validate the entered password
                if (user.getPassword().equals(currentPassword)) {
                    passwordValid = true;  // Password is valid, exit the loop
                } else {
                    attempts++;
                    if (attempts < 3) {
                        System.out.println("Incorrect password. You have " + (3 - attempts) + " attempt(s) left.\n");
                    } else {
                        System.out.println("Incorrect password. You have exceeded the maximum number of attempts.\n");
                    }
                }
            }
            
            // If 3 attempts are exceeded, cancel the operation
            if (!passwordValid) {
                System.out.println("You were unable to validate your password in 3 attempts. Year and semester change canceled.\n");
                return;
            }
            
            // Step 3: Show Warning about Erasing Data
            System.out.println("\nWARNING: Changing your year and semester will erase all your existing scores for the year and semester.");
            System.out.print("Do you wish to continue? (yes/no): ");
            String confirm = scan.nextLine().trim().toLowerCase();
            
            if (!confirm.equals("yes")) {
                System.out.println("Year and semester change canceled.\n");
                manageAccount(user, scan);
                return;
            }
            
            // Step 4: Erase the old scores and reset data
            for (int i = 0; i < 4; i++) {  // Assuming there are 4 courses
                for (int j = 0; j < 10; j++) {  // Assuming there are max 10 written scores
                    user.getWrittenScores()[i][j] = 0;  // Reset written scores
                    user.getWrittenMax()[i][j] = 0;     // Reset max written scores
                    user.getWrittenTitles()[i][j] = ""; // Reset written titles
                }
                for (int k = 0; k < 5; k++) {  // Assuming there are max 5 exam scores
                    user.getExamScores()[i][k] = 0;  // Reset exam scores
                    user.getExamMax()[i][k] = 0;     // Reset max exam scores
                }
            }
            
            // Step 5: Update the year and semester
            user.setYear(newYear);
            user.setSemester(newSemester);
            System.out.println("Year and semester updated successfully to Year " + newYear + ", Semester " + newSemester+"\n");
            
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.\n");
            scan.nextLine(); // Clear invalid input
        }
    }
    
    //================================================================================
    //================================================================================
    //DELETE ACCOUNT
    private static boolean deleteAccount(User user, Scanner scan) {
        System.out.println("\n========== DELETE ACCOUNT ==========");
        
        // Step 1: Ask the user if they want to delete their account
        System.out.print("Are you sure you want to delete your account? (yes/no): ");
        String initialConfirmation = scan.nextLine().trim().toLowerCase();
        
        if (!initialConfirmation.equals("yes")) {
            System.out.println("Account deletion canceled.\n");
            return false; // User chose not to proceed
        }
        
        // Step 2: Confirm password with 3 attempts
        int attempts = 0;
        boolean passwordVerified = false;
        
        while (attempts < 3) {
            System.out.print("Enter your password to confirm account deletion (or '0' to cancel): ");
            String inputPassword = scan.nextLine();
            
            if (inputPassword.equals("0")) {
                System.out.println("Account deletion canceled.\n");
                return false; // Cancel operation
            }
            
            if (inputPassword.equals(user.getPassword())) {
                passwordVerified = true;
                break;
            } else {
                attempts++;
                System.out.println("Incorrect password. Attempt " + attempts + " of 3.\n");
            }
        }
        
        if (!passwordVerified) {
            System.out.println("Too many incorrect attempts. Account deletion request canceled.\n");
            manageAccount(user, scan);
            return false;
        }
        
        // Step 3: Final confirmation and notify admin
        System.out.println("Your account deletion request has been sent to the admin for approval.\nLogging out...\n\n========== MAIN MENU ==========");
        user.setPendingDeletion(true); // Mark the account as pending deletion
        return false; // Account not deleted yet, pending admin approval
    }
    
}