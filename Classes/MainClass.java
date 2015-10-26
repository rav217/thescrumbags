import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TJ
 */
//presents start menu
public class MainClass {
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int input;
        boolean exit = false;
        
        while(true)
        {
            if(exit)
            {
                System.out.println("Goodbye");
                break;
            }
            System.out.println("Enter 1 for Process Sale, 2 for Returns, 3 for Rentals, 4 for User Management, 5 for On/Off, or 0 to exit");
            try
            {
                input = s.nextInt();
            }
            
            catch(java.util.InputMismatchException ex)
            {
                System.out.println("Invalid input");
                s.next();
                continue;
            }
            switch(input)
            {
                case 0:
                    exit = true;
                    break;
                    
                case 1:
                    HandleProcessSale.doProcessSale();
                    break;
                    
                case 2:
                  //  HandleReturns.doReturn();
                    break;
                    
                case 3: 
                  //  HandleRentals.doRental();
                    break;
                    
                case 4:
                   // HandleUserManagement.doUserManagement();
                    break;
                    
                case 5:
                  //  HandleOnOff.doOnOff();
                    break;
                    
                default:
                    if(input != 0)
                    {
                        System.out.println("Invalid input");
                    }                          
            }
        }
    }
}
