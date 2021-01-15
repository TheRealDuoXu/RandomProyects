package simpleATM;
import java.util.Scanner;

public class User {
	int id,currentBalance;
	String name;
	private char currentSelection='n';
	private boolean menuNOTdone = true;
	float balance=0;
User(String name,int id){
		this.id=id;
		this.name= name;
	}
char menu() {
	Scanner input = new Scanner(System.in);
	
	do {
	System.out.println("");
	System.out.println("Welcome to the FirstBank");
	System.out.println("Select A to see current balance");
	System.out.println("Select B to withdraw");
	System.out.println("Select C to hand in cash");
	System.out.println("Select D to calculate interest rate");
	System.out.println("Select E to exit");
	System.out.println("------------------------ ");
	System.out.println("Select... ");
	// currentSelection will be passed to the while loop as a condition to break
	currentSelection = input.next().toUpperCase().charAt(0);
	char pool[] = {'A','B','C','D','E'};
	for(char x : pool) {
		if(x != currentSelection) {
			menuNOTdone = false;
			}else {
				menuNOTdone = true;
			}
		}
	}while(menuNOTdone);
	input.close();
	return currentSelection;
}//end menu()

void cashIn(float cash) {
	balance += cash;
	System.out.print(cash + "  correctly handed in");
}

void cashOut(float withdraw){
	if (balance < withdraw) {
		System.out.println("Sry, we cannot process your transference \n current balance" + balance +
				"\n you are trying to withdraw" + withdraw);
		return;
	}
	// enough money to execute transaction
	balance -= withdraw;
	System.out.println("Withdraw successful"
			+ "\n current balance" +  balance +
			"\n you have withdrawn" + withdraw);
}// withdraw ended, balance changed

void setBalance(float balance) {
	this.balance = balance;
	System.out.println("Current balance set to: " + balance);
}

// multiple overriden interest calcs, 
// balance not defined
float interestCalc(int years,float interest) {
	float result = balance;
	//check years and interest are correctly inputed
	// create local scanner for input
	Scanner input = new Scanner(System.in);
	while(years <= 0 && interest <= 0) {
		if (years < 0) {
			System.out.println("The number of years must be an integer greater than 0"
					+ " \n your input -" + years);
			years = input.nextInt();
		}
		if (interest <= 0) {
			System.out.println("The interest rate must be a float greater than 0"
					+ " \n your input -" + interest);
			interest = input.nextFloat();
		}
	}// checked starting conditions, leasing input
	input.close();
	System.out.println("Everything has been setup correctly" + years + " years, " + interest + " interest"
			+ "\n aplaying standart inflation rate 2.0%" );
	interest -= 2;
	// calculate interest by for loop
	for (int i=3;i<years;i++) { // condicion an = a1 * exponent(r (n-1)), no quería buscar la funcion en Math
	System.out.println("year: " + (i-2) + "balance: " + result);
		result = balance * interest;
	}
	
	return result;
	
}

}// class User ends
