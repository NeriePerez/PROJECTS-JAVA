//PEREZ, NERIE ANNE M       BSIT 2A

package doublylinkedlists;


import java.util.Scanner;

//HELPER CLASS for the linked list; represents a single node
class Node{
    int data; //store the int data for the node
    Node prev; //the address for the previous node
    Node next; //the address for the next node
    
    //CONSTRUCTOR that initializes the node(saves the node)
    Node(int data) {
        this.data = data;
        prev = null;
        next = null;
    }
}

//MAIN CLASS where the methods are located
public class DoublyLinkedLists {
    static Node head; //the first node in the link list
    static Node tail; //the last node in the link list
    //needed variables
    static int action;
    static int action2;
    static int data;

    //ENQUEUEFRONT: INSERTION AT FRONT/HEAD
    static void enqueueFront(int data) {
        System.out.println("ENQUEUEFRONT: " + data);
        Node newNode = new Node(data);

        //checks if head is not empty/null
        if (head == null) head = tail = newNode;
        else {
            head.prev = newNode; //assigns the address of the newNode to the prev node of the current head
            newNode.next = head; //assigns the address of the current head to the next node of the newNode
            head = newNode; //assign the newNode as the head
        }
        
        System.out.println("--ENQUEUEFRONT SUCCESS--");
    }
    
    //ENQUEUEREAR: ISNERTION AT REAR/TAIL
    static void enqueueRear(int data) {
        System.out.println("ENQUEUEREAR: " + data);
        Node newNode = new Node(data);

        //checks if the head is null/empty
        if (head == null) head = tail = newNode; //assigns the newNode as the head and tail at the same time...
                                                 //meaning only 1 node is in the queue
        else {
            tail.next = newNode; //assigns the address of the newNode to the next node of the current tail
            newNode.prev = tail; //assigns the address of the current tail to the previous node of the newNode
            tail = newNode; //assign the newNode as the tail
        }
        
        System.out.println("--ENQUEUEREAR SUCCESS--");
    }
    
    //DEQUEUEFRONT: DELETION AT FRONT/HEAD
    static void dequeueFront() {
        System.out.println("DEQUEUEFRONT...");
        //checks if the head is null/empty
        if (head == null) {
            System.out.println("DEQUEUE ERROR: List is empty\n");
            return;
        }

        System.out.println("DELETING " + head.data);
        //checks if there is only 1 node in the queue
        if (head == tail) head = tail = null; //deletes the 1 node
        else {
            head = head.next; //assigns the next node of the current head as the head
            head.prev = null; //assigns the prev node of the head now as null.making it a proper head
        }
        
        System.out.println("--DEQUEUEFRONT SUCCESS--");
    }

    //DEQUEUEREAR: DELETION AT REAR/TAIL
    static void dequeueRear() {
        System.out.println("DEQUEUEREAR...");
        
        //checks if the head is null/empty
        if (head == null) {
            System.out.println("DEQUEUE ERROR: List is empty");
            return;
        }

        System.out.println("DELETING " + tail.data);
        //checks if there is only 1 node in the queue
        if (head == tail) head = tail = null; //deletes the 1 node
        else {
            tail = tail.prev; //assigns the prev node of the current tail as the tail
            tail.next = null; //assigns the next node of the tail now as null. making it a proper tail
        }
        
        System.out.println("--DEQUEUEREAR SUCCESS--");
    }
    
    //PEEKFRONT: RETURNS THE DATA IN THE FRONT/HEAD WITHOUT REMOVING
    static void peekFront() {
        System.out.println("PEEKFRONT...");
        
        //checks if the head is null/empty
        if (head == null) System.out.println("PEEK ERROR: List is empty");
        else System.out.println("FRONT: " + head.data);
    }
    
    //PEEKREAR: RETURNS THE DATA IN THE REAR/TAIL WITHOUT REMOVING
    static void peekRear() {
        System.out.println("PEEKREAR...");
        
        //checks if the head is null/empty
        if (head == null) System.out.println("List is empty");
        else System.out.println("REAR: " + tail.data);
    }
    
    // DISPLAY 
    static void display() {
        System.out.println("COMPLETE QUEUE DISPLAY");
        Node current = head;

        if(head == null) System.out.println("NULL\n");
        else{
            System.out.print("NULL <-> (front)");
            while (current != null) {
                System.out.print(current.data);
                if(current.next != null) System.out.print(" <-> ");
                current = current.next;
            }
            System.out.println("(rear) <-> NULL\n");
        }
    }
    
    //MAIN METHOD which serves as the home method for this java
    public static void main(String[] args) {
        //PRACTICE CALL
        enqueueFront(26);
        enqueueFront(15);
        enqueueRear(48);
        display();
        dequeueFront();
        display();
        enqueueFront(15);
        dequeueRear();
        display();
        enqueueRear(48);
        peekFront();
        peekRear();
        display();
        
        
        System.out.println("==== DOUBLE ENDED QUEUE IN SCANNER ====\n");
        Scanner scan = new Scanner(System.in);
        while(true) {
            while (true) {
                System.out.println("(1) Enqueue\n"
                        + "(2) Dequeue\n"
                        + "(3) Peek\n"
                        + "(4) Display All\n"
                        + "(0) Exit");
                System.out.print("Pick an action: ");

                //checks if the user entered valid inputs and will not skip unless corrected
                while(!scan.hasNextInt()) {
                    System.out.print("Invalid entry. Enter number only: ");
                    scan.next();
                }

                action = scan.nextInt();
                scan.nextLine();

                if(action == 0) {
                    System.out.println("EXITING PROGRAM...  FAREWELL!");
                    return;
                } else if(action >=  1 && action <= 3) {
                    while(true) {
                        System.out.println("(1) Front\n"
                                + "(2) Rear");
                        System.out.print("Pick an action: ");

                        //checks if the user entered valid inputs and will not skip unless corrected
                        while(!scan.hasNextInt()) {
                            System.out.print("Invalid entry. Enter number only: ");
                            scan.next();
                        }

                        action2 = scan.nextInt();
                        scan.nextLine();

                        if(action2 ==1 || action2 ==2) break; //ends the loop while true
                        System.out.println("Number entered is invalid. Enter number between 1-2 only..\n");
                    } break;
                } else if(action == 4) display(); 
                else System.out.println("Number entered is invalid. Enter number between 0-4 only..\n");
            }
            System.out.println();

            if(action == 1) {
                System.out.print("Enter number to insert: ");

                //checks if the user entered valid inputs and will not skip unless corrected
                while(!scan.hasNextInt()) {
                    System.out.print("Invalid entry. Enter number only: ");
                    scan.next();
                }

                data = scan.nextInt();
                scan.nextLine();

                if(action2 == 1) enqueueFront(data);
                else if(action2 == 2) enqueueRear(data);
            } else if(action == 2) {
                if(action2 == 1) dequeueFront();
                else if(action2 == 2) dequeueRear();
            } else if(action == 3) {
                if(action2 == 1) peekFront();
                else if(action2 == 2) peekRear();
            }
        }
            
    }
    
}
