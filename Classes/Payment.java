package thescrumbags.Classes;

//Payment class: has attributes amt, isCredit, and cardNum(default is NULL)
//still need implementation of external tax calculator and credit check

//import Java.String;
  
public interface Payment{
  
 public boolean verify(Transaction t);   
 
}