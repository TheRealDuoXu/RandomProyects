package simpleATM;

public class PasswordMissmatchException extends Exception{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String des;
PasswordMissmatchException(int a){
	des=String.valueOf(a);
}
PasswordMissmatchException(int a,int b){
	des=String.valueOf(a)+String.valueOf(b);
}
public String toString() {
	return "Password " + des + " not valid";
}
}