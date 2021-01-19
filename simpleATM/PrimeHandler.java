package simpleATM;
import java.util.Arrays;

//fully debugged
public class PrimeHandler {
	
	//dinamic array implementation
	private int startingSize = 4;
	private int arrayPos=0;
	private volatile int[] learned = new int[startingSize];

	public void order() { // as we are systematic, we don't need this
		Arrays.sort(learned);
	}
	
	public void ensureCapacity(int minCapacity){
        int temp[] = new int[startingSize*minCapacity];
        for (int i=0; i < startingSize; i++){
            temp[i] = learned[i];
        }
        learned = temp; //reference learned into new, bigger array
        startingSize = startingSize * minCapacity;
    }
	public void push(int element) {
		while(arrayPos==startingSize-1)
		{
			ensureCapacity(2); // allocates more
		}
		if(!check(element)) { //check duplicate
			return; //do not push
		} // not duplicate push
		learned[arrayPos] = element; 
		arrayPos++;
	}
	public int retrieve(int arrayPos) {
		return learned[arrayPos];
	}

	public void trim() {
		int emptyCounter =0;
		for(int i =0; i <learned.length;i++) {
			if(learned[i]==0) {
				emptyCounter++;
				continue;
			}
		}
		// the copy is done with an Arrays method
		int newSize = startingSize - emptyCounter;
		int[] temp = new int[newSize];
		temp = Arrays.copyOf(learned, newSize);
		//reference learned (global) to temp;
		learned = temp;
	}
	
	public boolean check(int element) {
		int tempPos = arrayPos;
	if(arrayPos == 0) { // array  -1 index exception handler
			tempPos = 1;
		}
		if(element == learned[tempPos-1]) { //element matches the last one
			return false;
		}
		return true;
	}
	public int[] retriveArray() {
		trim();
		return learned;
	}
	
	
	public String toString() {
		System.out.println("Learned[] is going string");
		trim();
		for(int i =0; i<learned.length;i++) {
			System.out.println("Element " + i + " is " + learned[i]);
		}
		return "Execute toString finished";
	}
	
	
}
