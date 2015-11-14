/*
 class created to test the DB connection
 */
package thescrumbags.Classes;
import java.sql.DriverManager;
import java.util.Scanner;

/**
 *
 * @author Chris
 */
public class DBTest {
    
    public static void main (String args[]){
        Scanner in = new Scanner(System.in);
        DBHandler db = DBHandler.getInstance();
        boolean next = true; //outer loop conditional
        boolean go = true; //inner loop conditional
        
        /*establish connection to system
        while(go){
            System.out.println("Enter username:");
            String user = in.nextLine();
            System.out.println("Enter password:");
            String pswd = in.nextLine();
            System.out.println("");
            go = db.openConnection(user, pswd);
        }*/
        
        db.openConnection("sql595207", "nT1*rF4!");
        
        //start menu loop
        while (next){
            System.out.println("What would you like to do?");
            System.out.println("1. Search for item in the system");
            System.out.println("2. Add an item to the system");
            System.out.println("3. Remove an item from the system");
            System.out.println("4. View items in the system");
            System.out.println("5. Exit");
            System.out.println("other options?");
            int inst = in.nextInt();
            System.out.println("");
            
            //perform search
            if (inst == 1){
                go = true;
                while(go){
                    System.out.println("Enter item ID to search for in the system (0 to return to menu):");
                    int id = in.nextInt();
                    if (id ==0){
                        System.out.println("");
                        break;
                    }
                    //go = db.fetchItem(id);
                }
            }
            
            //perform add
            else if (inst == 2){
                go = true;
                while(go){
                    System.out.println("Enter item ID to add to system (0 to return to menu):");
                    int id = in.nextInt();
                    if (id == 0){
                        System.out.println("");
                        break;
                    }
                    System.out.println("Enter price of item:");
                    System.out.print("$");
                    double price = in.nextDouble();
                    in.nextLine(); //consume rest of line
                    System.out.println("Enter item description:");
                    String descr = in.nextLine();
                    //go = db.addItem(id, price, descr);
                }
            }
            
            //perform remove
            else if (inst == 3){
                go = true;
                while(go){
                    System.out.println("Enter item ID to remove from system (0 to return to menu):");
                    int id = in.nextInt();
                    if (id == 0){
                        System.out.println("");
                        break;
                    }
                    //go = db.removeItem(id);
                }
            }
            
            //perform display
            else if (inst == 4){
                //db.displayItems();
            }
            
            //exit
            else if (inst == 5){
                next = false;
            }
        }
        db.closeConnection();
    }
}
