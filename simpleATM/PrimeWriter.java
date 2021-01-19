package simpleATM;
import java.io.*;
// fully debugged
//learn file control, array control,writing, and threads
public class PrimeWriter{
	static String fileName = null;
	static final String pid1 = "T1";
	static final String MERGE_DESTINY_FNAME = "destiny.txt";
	static boolean FILE_PERSISTENT = true;
	static final int THREAD_AMOUNT = 6;
	
public static void main(String[] args) throws InterruptedException {
	RSA p1 = new RSA(pid1,1,100000);
	String[] mpid = {"T2","T3","T4","T5","T6","T7"};
	int[] mstart = {100001,200001,300001,400001,
			500001,600001};
	int[] mstop = {200000,300000,400000,500000,
			600000,700000};
	RSA mRSA[] = RSA.factory(mpid, mstart, mstop, THREAD_AMOUNT);
	
	p1.t.start();
	for(int i = 0 ; i <THREAD_AMOUNT; i++) {
		mRSA[i].t.start();
	}
	
	p1.t.join();
	for(int i = 0 ; i <THREAD_AMOUNT; i++) {
		mRSA[i].t.join();
	}
	//debugging purposes
	System.out.println(p1.ph.toString() + " called by " + p1.t.getName());
	System.out.println(p1.t.getState() + " " + p1.t.getName());
	for(int i = 0 ; i <THREAD_AMOUNT; i++) {
		System.out.println(mRSA[i].t.getState()+ " " + mRSA[i].t.getName());
	}
	///////////////////////////////////////////
	//pwMerger("T1.txt","T2.txt"); BUG
	
}

//no need for synchronized as there are different files
public static /*synchronized*/ void wrt(String pid,int[] pHandler) throws IOException {
	fileName= pid +".txt";	
	File textFile = new File(fileName);

	try(FileWriter f = new FileWriter (textFile,FILE_PERSISTENT)){
			for(int info : pHandler ) { //PrimeHandler toString(); not used, could be
			System.out.println("Writing " + info + " by " + pid);
			f.write(info+"\n");
			System.out.print(" Finished");
			}
		}catch(FileNotFoundException e) {
			System.out.print(e+" creating file");
			textFile.createNewFile();
		}
}
@Bug(des = "unresolved")
public static void pwMerger(String name1,String name2) { // merges two documents
	File destiny = new File(MERGE_DESTINY_FNAME);
	try(FileReader fr1 = new FileReader(name1);
			FileWriter fw = new FileWriter(destiny)){ // to not open the two files, we go one by one
		while(fr1.read()!=-1) { //end of file 1
			fw.write(fr1.read()); // copies file 1
		}
		try(FileReader fr2 = new FileReader(name1)){ // to not open the two files, we go one by one
			while(fr2.read()!=-1) { //end of file 2
				fw.write(fr2.read()); // copies file 2
			}
			}catch(FileNotFoundException e) {
				System.out.println("failure tu read " + name2);
			}
		
		// destiny catch clauses
	}catch(FileNotFoundException e) {
		try {
			destiny.createNewFile();
		} catch (IOException e1) {
			System.out.println(" failure to create " + MERGE_DESTINY_FNAME);
			System.out.println(" or failure tu read " + name1);
			System.exit(0);
		}
		System.out.println(" Destiny file not found, created one: " + MERGE_DESTINY_FNAME);
	} catch (IOException e2) {
		System.out.println(" other IO exception " + MERGE_DESTINY_FNAME + e2);
		e2.printStackTrace();
	}
}
}
