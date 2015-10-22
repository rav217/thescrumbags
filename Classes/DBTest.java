/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;
import java.util.Scanner;

/**
 *
 * @author Chris
 */
public class DBTest {
    
    public static void main (String args[]){
        Scanner in = new Scanner(System.in);
        DBHandler db = new DBHandler();
        boolean next = true; //outer loop conditional
        boolean go = true; //inner loop conditional
        while(go){
            System.out.println("Enter username:");
            String user = in.nextLine();
            System.out.println("Enter password:");
            String pswd = in.nextLine();
            System.out.println("");
            go = db.openConnection(user, pswd);
        }
        while (next){
            System.out.println("What would you like to do?");
            System.out.println("1. Search for item in the system");
            System.out.println("2. Add an item to the system");
            System.out.println("3. Remove an item from the system");
            System.out.println("5. Exit");
            int inst = in.nextInt();
            System.out.println("");
            if (inst == 1){
                go = true;
                while(go){
                    System.out.println("Enter item ID to search for in ProductDescription (0 to return to menu):");
                    int id = in.nextInt();
                    if (id ==0){
                        System.out.println("");
                        break;
                    }
                    go = db.fetchItem(id);
                }
            }
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
                    go = db.addItem(id, price, descr);
                }
            }
            else if (inst == 3){
                go = true;
                while(go){
                    System.out.println("Enter item ID to remove from system (0 to return to menu):");
                    int id = in.nextInt();
                    if (id == 0){
                        System.out.println("");
                        break;
                    }
                    go = db.removeItem(id);
                }
            }
            else if (inst == 5){
                next = false;
            }
        }
        db.closeConnection();
    }
}
