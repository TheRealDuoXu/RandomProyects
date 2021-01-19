package simpleATM;

public class IdNotFoundException extends Exception{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public String toString() {
	return "your id does not appear in our database"
			+ "\n do you wish to create an account?";
}
}
