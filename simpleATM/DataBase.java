package simpleATM;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
public class DataBase {
	Timer t = new Timer();
	private int operation;
	private final int D_GET=0;
	private final int D_PUT=1;
	private final int D_VIEW=2;
	private final int D_CREATE=3;
	int id;
	private float balance;
	private boolean verified = false;
	private String password; //ciphers each users data
	String line;
	String database = "database";
	private int passA,passB;
DataBase(int id,int passA,int passB){
	this.passA = passA;
	this.passB = passB;
	this.id = id;
}
public void check() throws PasswordMissmatchException, IdNotFoundException {
	comparator();
}
private void isPrime(int a,int b) throws PasswordMissmatchException{
	try {
			if(a==0 || b==0) {
				throw(new ArithmeticException("password field cannot be 0"));
			}
			// Arithmetic Exception end
			if(a%2 == 0) {
				throw(new PasswordMissmatchException(b));
			}
		for(int i=3;i*i<b;i+=2) {
			//using division method to stablish primes
			if(a%i==0) {
				throw(new PasswordMissmatchException(b));
			}
		}// not a prime at this point
			if(b%2 == 0) {
				throw(new PasswordMissmatchException(b));
			}
		for(int i=3;i*i<b;i+=2) {
				//using division method to stablish primes
			if(b%i==0) {
				throw(new PasswordMissmatchException(b));
			}
				}
		// not a prime at this point
			throw (new PasswordMissmatchException(b));
	}catch(PasswordMissmatchException e) {
		e.printStackTrace();
	}}
// is prime check concluded

private boolean comparator() throws PasswordMissmatchException,IdNotFoundException{ // will compare password
	isPrime(passA,passB); //double checks for prime
	//use id as name file
	try(FileReader fr = new FileReader(id+".txt")){
		//search for password
		while(String.valueOf(fr.read()) != "\n") {
			password += fr.read();
		}
		// at this point we got the password
	}catch(IOException e) { //IOException is super of Filenotfoundexception
		e.printStackTrace();
		System.out.println("File name not found or mismatch your id");
		throw (new IdNotFoundException()); // We self implement exception
	}
		
if(passA*passB == Integer.parseInt(password)) 
	{
	System.out.println("Password verified");
	verified=true;
		}else{
				throw (new PasswordMissmatchException(passA,passB));
			}
// two prime passwords checked, verified id, check password. Ready
return verified;
}//end comparator

private void fileWriter(String s) {
	try (FileWriter fw = new FileWriter(database)) {
		fw.write(s);
	}catch(IOException e) {
		System.out.println(e);
}
	}}
