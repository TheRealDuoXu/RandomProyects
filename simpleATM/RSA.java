package simpleATM;

import java.io.IOException;
//fully debugged
//RSA shall use the primes, at PrimeHandler to calculate new password
public class RSA implements Runnable{
int pass;
int start,stop;
int known[] = {2,
		3,	5,	7,	11,	13,	17,	19,	23,	29,31,	37,	41,	43,	47,	53,	59,	61,	67,	71
		,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167
		,173,179,181,191,193,197,199,211,223,227,229};
//= new int[100];

int result = 1;
long timer = System.currentTimeMillis();
Thread t;
PrimeHandler ph = new PrimeHandler();
RSA(String pid,int start,int stop){
 t = new Thread(this,pid);
 this.start=start;
 this.stop=stop;
}

public static RSA[] factory(String[] pid,int[] start,int[] stop,int amount) {
	RSA[] a = new RSA[amount];
	for(int i = 0;i<amount;i++) {
		a[i] = new RSA(pid[i],start[i],stop[i]);
	}
	return a;
}
// create another instance to get two pieces of password
	@Override
	public void run() {
		System.out.println(t.getName()+" started");			
	// writing primes to files
		int count = start;
	    int lastPrime = 2;
		int current = start;
			if(current%2==0) { //we don't use odd numbers here
				current++;
			}
		
		while (count < stop) {
            boolean prime = true;
// cicles through current's prime numbers skipping odd ones
// will only try an odd number if it's square is smaller than the number
// gets current's primes
            for (int i = 3; i*i <= current; i += 2) {
                if (current % i == 0) {
                    prime = false;
                    break;
                }
            }

            if (prime) {
                count++; //ending while condition
                lastPrime = current;
            }
            if (current == 2) {
             current++;
            } else {
                current += 2;
            }
            ph.push(lastPrime);
            System.out.println(t.getName() + " pushed " + lastPrime);
        } // at the end, write to file ONCE, no need to open and close
		
		try {
			PrimeWriter.wrt(t.getName(),ph.retriveArray());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to store data");
		}
		
	}//end run()
}
