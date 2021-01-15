package simpleATM;
import java.util.Scanner;

public class Launcher {
static String name = "undefined"; static int id; static char seleccion; static boolean exitFlag=false;
//estáticos, ya que no se crean ningun objeto Launcher
// solo se utilizan sin referencia, por lo que son estáticos y solo existen dentro de main

// inicializacion opciones
final static char OP_CURRENT_BALANCE = 'A';
final static char OP_WITHDRAW = 'B';
final static char OP_CASH_IN = 'C';
final static char OP_INTEREST = 'D';
final static char OP_EXIT = 'E';

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.print("Hello and welcome to FirstBank"
				+ "\n please create your demo account"
				+ "\n ------------------------------"
				+ "\n your name: ");
		name = input.next();
		System.out.print("\n thanks " + name + 
				"\n assign yourself an id:");
		id = input.nextInt();
		System.out.print("\n creating your account");
		User account = new User(name,id);
		seleccion = account.menu(); // creates a local copy of selection, account.currentSelection cannot be used as it's private
		
		while (!exitFlag) {
			switch(seleccion) {
			case OP_CURRENT_BALANCE:
				System.out.println("Current balance " + account.balance);
				break;
			case OP_WITHDRAW:
				System.out.println("intitiating withdraw"
						+ "\n How much? --- ");
				float withdraw;
				withdraw = input.nextFloat();
				account.cashOut(withdraw);
				break;
			case OP_CASH_IN:
				System.out.println("\n How much? --- ");
				float cash = input.nextFloat();
				account.cashIn(cash);
				System.out.println("Current balance " + account.balance);
				break;
			case OP_INTEREST:
				System.out.println("Interest calculator!!!! "
						+ "\n Use current balance? Y/N" + account.balance);
				char useBalance = input.next().toUpperCase().charAt(0);
						if(useBalance == 'N') {//use balance no
							
						}else { // use balance yes
							System.out.println("you will be calculating the interest for the current balance" + account.balance);
							System.out.println("please input years: ");
							int years = input.nextInt();
							System.out.println("Please input your interest rate: ");
							float interest = input.nextFloat();
							float result = account.interestCalc(years, interest);
							System.out.println("For " + account.balance + "in " + years + "years, at " + interest+" interest rate "
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
					
				}
			}
		}
		
	}

}
