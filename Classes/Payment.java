//Payment class: has attributes amt, isCredit, and cardNum(default is NULL)
//still need implementation of external tax calculator and credit check

//import Java.String;
  
public class Payment{
 
 private Money amt;
 private boolean credit;
 private String cardNum;
 
 //constructor for cash payment
 public Payment(Money amt, boolean credit){
  this.amt = amt;
  this.credit = credit;
 }
 
 //constructor for credit payment
 public Payment(Money amt, boolean credit, String cardNum){
   this.amt = amt;
   this.credit = credit;
   this.cardNum = cardNum;
 }
 
 //get the amount
 public Money getAmt(){
  return this.amt;
 }
 
 //return bool for credit
 public boolean isCredit(){
  return this.credit;
 }
 
 //return credit card number
 public String getCardNum(){
  return this.cardNum;
 }
 
 public Money calculateAmtPlusTax(){
  double taxRate = .06; //constant tax rate for now
  Money tax = this.amt.times(taxRate + 1);
  return this.amt.add(tax);
 }
 
 public boolean doCreditCheck(){
  return true; //for demo purposes, always returns true
 }
}