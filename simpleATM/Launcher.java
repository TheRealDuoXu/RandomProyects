package simpleATM;
import java.util.Scanner;
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

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Hello and welcome to FirstBank"
				+ "\n please create your demo account"
				+ "\n ------------------------------"
				+ "\n your name: ");
		String name = input.next();
		System.out.print("\n thanks " + name + 
				"\n assign yourself an id:");
		int id = input.nextInt();
		assert input.hasNextLine();
		input.nextLine();
		System.out.print("\n creating your account");
		User account = new User(name,id);
		char option = account.menu();
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
				
			} // outside switch, select again
			if(!exitFlag) { // switch cae aqui, o si no no se puede romper el bucle
			System.out.println("What else do you need? " +  account.name + "\n");
			option = account.menu();
			}
		}
		input.close();
	}

}