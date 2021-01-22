package simpleATM;
import java.util.Scanner;
import java.lang.Math;
import java.io.*; //Especificar despu�s, no me se la biblioteca
public class Launcher {
static boolean exitFlag=false;
//estáticos, ya que no se crean ningun objeto Launcher
// solo se utilizan sin referencia, por lo que son estáticos y solo existen dentro de main
// inicializacion opciones
final static char OP_CURRENT_BALANCE = 'A';
final static char OP_WITHDRAW = 'B';
final static char OP_CASH_IN = 'C';
final static char OP_INTEREST = 'D';
final static char OP_EXIT = 'E';
final static char OP_GUEST = 'G';


	public static void main(String[] args) {
		int id;
		int[] primes = [int(learned[int(trunc(Math.random()*learned()))]), int(learned[int(trun(Math.random()*learned()))])];
		DataBase database;
		PrimeWriter pw;
		String name;
		Scanner input = new Scanner(System.in);
		System.out.print("Hello and welcome to FirstBank."
				+ "\n Do you already have an account? Y / N");
		String hasAccount = input.next();
		if (hasAccount == "N") {
			/*System.out.println("Introduce your name:"); //Si no buscamos una forma de implementar el nombre no deberiamos ponerlo
		name = input.next();

		System.out.println("Thanks " + name);*/
		do {
		System.out.println("Assign yourself an id (6 digits):");
		id = input.nextInt();
		input.nextLine();
		while (database.idTaken(id)) {
			System.out.println ("That id is taken, choose another one:");
			id = input.nextInt();
		}
		} while (id < 99999);
		try { try { FileWriter fwid = new FileWriter("IdList.txt");
		fwid.write(id + "\n");
		}catch (FileNotFoundException e) {
			File idList = new File("IdList.txt");
			FileWriter fwid = new FileWriter(idList);
			fwid.write(id + "\n");
		}}catch (IOException e) {System.out.println("There has been an error," + e);}
		pw.wrt(id, primes[]);
		int passwordAssigned = primes[0] * primes[1];
		System.out.println("This is your password" + passwordAssigned);
		System.out.println("Thank you for signing up.");
		}
		System.out.println("Introduce your id:");
		String s = input.next();
		id = Integer.parseInt(s);
		System.out.println("Introduce your password:");
		s = input.next();
		int password = Integer.parseInt(s);
		try {if (!pw.compare(password, id)) {
			throw PasswordMissmatchException;
		}}catch (PasswordMissmatchException e) {
			System.out.println ("Wrong password");
		}
		User account = new User(name,id);
		char option = account.menu();
		
		if(option == OP_GUEST) {
			do {
				option = askOption();
				atmInterface(account,option);
		}while(option != 'E');
			}else {
				do {
					option = askOption();
					atmInterface(account,option);
			}while(option != 'E');
		
	}input.close();
}
	
	public static char askOption() {
		boolean menuNOTdone = true; // NO ALMACENA INFORMACION, ES UN CONTROLADOR DE SUCESOS
		char currentSelection;
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
		currentSelection = input.nextLine().toUpperCase().charAt(0);
		input.nextLine();
		char pool[] = {'A','B','C','D','E'};
		for(char x:pool) {
			if(x == currentSelection) {
				menuNOTdone = false; // out of here
			}
		}
		}while(menuNOTdone);
		input.close();
		return currentSelection;
	}
	
	public static void atmInterface(User account,char option) {
		Scanner input = new Scanner(System.in);
		
		while (!exitFlag) {
			switch(option) {
			case OP_CURRENT_BALANCE:
				System.out.println("Current balance " + account.balance);
				break;
			case OP_WITHDRAW:
				System.out.println("intitiating withdraw"
						+ "\n How much? --- ");
				Float withdraw = input.nextFloat();
				account.cashOut(withdraw);
				break;
			case OP_CASH_IN:
				System.out.println("\n How much? --- ");
				float cash = input.nextFloat();
				input.nextLine();
				account.cashIn(cash);
				System.out.println("Current balance " + account.balance);
				break;
			case OP_INTEREST:
				System.out.println("Interest calculator!!!! "
						+ "\n Use current balance? Y/N " + account.balance);
				assert input.hasNextLine();
				char useBalance = input.nextLine().toUpperCase().charAt(0);
						if(useBalance == 'N') {//use balance no
							System.out.println("Input balance");
							float yourBalance = input.nextFloat();
							input.nextLine();
							System.out.println("Input years");
							int years = input.nextInt();
							input.nextLine();
							System.out.println("Input interest");
							float interest = input.nextFloat();
							input.nextLine();
							float result = account.interestCalc(years, interest,yourBalance);
							System.out.println("For " + yourBalance + " in " + years + " years, at " + interest +" interest rate "
									+ "will yield " +  result);
						}else { // use balance yes
							System.out.println("you will be calculating the interest for the current balance" + account.balance);
							System.out.println("please input years: ");
							int years = input.nextInt();
							input.nextLine();
							System.out.println("Please input your interest rate: ");
							float interest = input.nextFloat();
							input.nextLine();
							float result = account.interestCalc(years, interest);
							System.out.println("For " + account.balance + " in " + years + " years, at " + interest +" interest rate "
									+ "will yield " +  result);
						}
				break;
			case OP_EXIT:
				System.out.println("Thx for using FirstBanc software, all rights reversed");
				System.out.println("EXITING...");
				exitFlag=true;
				break;
			default:
				System.out.println("Default at Lancher switch selection has been toggled");
				try {
					Exception e = new Exception();
					throw (e);
				}catch(Exception e) {
					System.out.println("Exception has occured" + e);
				}
				
			}
		}
	}
	
}
