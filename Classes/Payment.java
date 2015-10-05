//Payment class: has attributes amt, isCredit, and cardNum(default is NULL)
//still need implementation of external tax calculator and credit check

public class Payment{
	
	private Money amt;
	private boolean credit;
	private String cardNum;
	public Money calculateTax();
	public boolean doCreditCheck();
	
	//constructor w/ Money argument
	public Payment(Money amt, boolean credit, String cardNum = NULL){
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
	
	public Money calculateTax(){
		Money tax = getTax(this.amt);
		this.amt = amt + tax;
	}
	
	public boolena doCreditCheck(){
		return checkCard(this.cardNum)
	}
}