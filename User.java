package simpleATM;
import java.io.*;
import java.util.Scanner;


public class User {
	int id;
	float balance;
	String name;
	static Scanner input = new Scanner(System.in);
	char currentSelection;
	private int passA,passB;
	private char isGuest;
User(String name,int id){
		this.id=id;
		this.name= name;
	}
char menu() {
	
	try {
		askPass();
		DataBase dbase = new DataBase(id, passA, passB);
		dbase.check();
	} catch (PasswordMissmatchException e) {
		System.out.print(e.toString());
		System.exit(0);
	}catch(IdNotFoundException e) { // file not found will create file
		boolean exitFlag = false;
		do {
			try{ // try in catch, embeded
				//do while will finish either in guestmode or file created
				File newDatabaseFile = new File(id + ".txt");
				System.out.println("create file? Y/N");
				char YN;
				YN = input.nextLine().toUpperCase().charAt(0);
				if(YN == 'Y') {
					newDatabaseFile.createNewFile();
					exitFlag = true;
				}else {
					System.out.println("You have to create file "
							+ "\n fine, you will be using a guest account");
					isGuest = 'G';
					return isGuest; // menu is terminated with G returned
				}
			}catch(IOException e1) {
				System.out.println("Error creating file " + e1);
			}
		}while(!exitFlag);
		
	}
	
	boolean menuNOTdone = true; // NO ALMACENA INFORMACION, ES UN CONTROLADOR DE SUCESOS
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
	currentSelection = input.nextLine().toUpperCase().charAt(0);
	input.nextLine();
	char pool[] = {'A','B','C','D','E'};
	for(char x:pool) {
		if(x == currentSelection) {
			menuNOTdone = false; // out of here
		}
	}
	}while(menuNOTdone);
	return currentSelection;
}//end menu()

void cashIn(float cash) {
	balance += cash; 
	System.out.print(cash + "  correctly handed in \n");
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
	while(years <= 0 && interest <= 0) {
		if (years <= 0) {
			System.out.println("The number of years must be an integer greater than 0"
					+ " \n your input -" + years);
			years = input.nextInt();
			input.nextLine();
		}
		if (interest <= 0) {
			System.out.println("The interest rate must be a float greater than 0"
					+ " \n your input -" + interest);
			interest = input.nextFloat();
			input.nextLine();
		}
	}// checked starting conditions, leasing input
	System.out.println("Everything has been setup correctly " + years + " years, " + interest + " interest"
			+ "\n aplaying standart inflation rate 2.0%" );
	interest -= 2;
	interest /= 100;
	// calculate interest by for loop
	for (int i = 0;i<years;i++)  // condicion an = a1 * exponent(r (n-1)), no quería buscar la funcion en Math
		{ 
		System.out.println("year: " + (i+1) + "balance: " + result);
		result = result * interest; //interest is in percentage
		}
	
	return result;
	
}
float interestCalc(int years, float interest,float yourBalance) {
	float result = yourBalance;
	//check years and interest are correctly inputed
	// create local scanner for input
	while(years <= 0 && interest <= 0) {
		if (years <= 0) {
			System.out.println("The number of years must be an integer greater than 0"
					+ " \n your input -" + years);
			years = input.nextInt();
			input.nextLine();
		}
		if (interest <= 0) {
			System.out.println("The interest rate must be a float greater than 0"
					+ " \n your input -" + interest);
			interest = input.nextFloat();
			input.nextLine();
		}
	}// checked starting conditions, leasing input
	System.out.println("Everything has been setup correctly " + years + " years, " + interest + " interest"
			+ "\n aplaying standart inflation rate 2.0%"
			+ "\n you have chosen: " + yourBalance );
	interest -= 2;
	interest /= 100;
	interest += 1;
	// calculate interest by for loop
	for (int i = 0;i<years;i++)  // condicion an = a1 * exponent(r (n-1)), no quería buscar la funcion en Math
		{ 
		System.out.println("year: " + (i+1) + "balance: " + result);
		result = result * interest; //interest is in percentage
		}
	
	return result;
}

public void askPass() throws PasswordMissmatchException{ 
	//to avoid complications all password will be numbers
	System.out.println("We need to verify your identity for this operation "
			+ "\n passphrase part 1: ");
	passA=input.nextInt();
	input.nextLine();
	
	System.out.println("passphrase part 2: ");
	passB=input.nextInt();
	input.nextLine();
	// only breaks if passwordmissmatch is thrown
}
}// class User ends
