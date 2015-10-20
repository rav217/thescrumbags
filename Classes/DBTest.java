/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

/**
 *
 * @author Chris
 */
public class DBTest {
    
    public static void main (String args[]){
        DBHandler db = new DBHandler();
        boolean go = true;
        while(go){
            //have user enter their credentials here
            go = db.openConnection("ChrisByam", "tolland17");
        }
    }
}
