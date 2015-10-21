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
        boolean go = true;
        while(go){
            System.out.println("Enter username:");
            String user = in.nextLine();
            System.out.println("Enter password:");
            String pswd = in.nextLine();
            System.out.println("");
            go = db.openConnection(user, pswd);
        }
        go = true;
        while(go){
            System.out.println("Enter product ID:");
            int id = in.nextInt();
            if (id == 0) break;
            go = db.fetchItem(id);
        }
        db.closeConnection();
    }
}
