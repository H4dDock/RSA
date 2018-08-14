import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author H4dDock
 *
 */

public class main {
	
	static String ReturnStringNumber(int a) {
		String output = "";
		output += a;
		while(output.length() < 5) {
			output = "0" + output;
		}
		
		return output;
	}

	public static void main(String[] args) throws Exception{
	
		Scanner in = new Scanner(System.in);
		short flag = 0;
		int k = 0;
		System.out.print("Etner order of the number to generation (10^k, enter k): ");
		k = in.nextInt();
		
		long startTime = System.currentTimeMillis();
		BigInteger p = GenerateSimpleNum.GenerateRandom(k, k);
		BigInteger q = new BigInteger("1");
		System.out.println("Starting of the generation prime numbers. This may take some time.\n");
		
		while(flag != 2) {
			p = p.add(new BigInteger("2"));
			if ((p.mod(new BigInteger("2"))).equals(new BigInteger("0"))) continue;
			if ((p.mod(new BigInteger("3"))).equals(new BigInteger("0"))) continue;
			if ((p.mod(new BigInteger("5"))).equals(new BigInteger("0"))) continue;
			if ((p.mod(new BigInteger("11"))).equals(new BigInteger("0"))) continue;
			if ((p.mod(new BigInteger("17"))).equals(new BigInteger("0"))) continue;
			if(GenerateSimpleNum.CheckForSimpleByMiller(p,15)) {
					flag++;
					if(flag == 1){
						q = p;
						p = GenerateSimpleNum.GenerateRandom(k, k);
					}
			}
		}
		
		System.out.println("Prime numbers:\n" + p.toString() + "\n" + q.toString());
		System.out.println("Generation of prime numbers end. Start key generation");
		
		BigInteger N = p.multiply(q); 
		System.out.println("N calculated: " + N.toString());
		
		BigInteger fi = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1"))); //!
		System.out.println("fi calculated: " + fi.toString());
		
		BigInteger e = GenerateSimpleNum.GenerateRandom(10, k-5);
		
		flag = 0;
		while(flag == 0) {
			e = e.add(new BigInteger("2"));
			if (e.mod(new BigInteger("3")).equals(new BigInteger("0"))) continue;
			if (e.mod(new BigInteger("5")).equals(new BigInteger("0"))) continue;
			if (GenerateSimpleNum.CheckForSimpleByMiller(e,15)) {
				flag = 1;
			}
		}
		System.out.println("e is calculated: " + e.toString());
		
		BigInteger d;
		do {
			d = GenerateSimpleNum.Diafant(e, fi);
		}while(d.equals(new BigInteger("1")));
		System.out.println("d is calculated: "+d.toString());
		
		System.out.println("Open key:\n     "+e.toString()+"\n     "+N.toString());
		System.out.println("Close key:\n     "+d.toString()+"\n     "+N.toString());
		
		
		FileWriter output = new FileWriter("output.txt");
		Scanner InputIn = new Scanner(new File("input.txt"));
		String stroka;
		String karetka;
		String symb_str="";
		BigInteger symb;
		
		System.out.println("Text is encrypting");
		
		
		while(InputIn.hasNext()){
			stroka = InputIn.nextLine();
			for(int i = 0; i < stroka.length(); i++) {
				karetka = ""+(int)stroka.charAt(i);
				while(karetka.length() < 4) {
					karetka = "0" + karetka;
				}
				symb_str += karetka;
				if(symb_str.length() == 32) { //8 symbols
					symb = new BigInteger(symb_str);
					output.write(symb.modPow(e, N).toString());
					output.write("\n");
					symb_str = "";
				}
			}
			stroka = "";
		}
		output.close();
		karetka = "";
		System.out.println("Text is encrypted");
		
		
		output = new FileWriter("Otvet.txt");
		InputIn = new Scanner (new File("output.txt"));
		String otvet = "";
		
		while(InputIn.hasNext()) {
			stroka = InputIn.nextLine();
			symb = new BigInteger(stroka);
			symb = symb.modPow(d, N);
			for(int j = symb.toString().length()-4; j >= 0; j -= 4) {
				karetka = symb.toString().substring(j, j+4);
				otvet = (char)Integer.parseInt(karetka) + otvet;
				if(j < 4 && j != 0) {
					karetka = symb.toString().substring(0, j);
					otvet = (char)Integer.parseInt(karetka) + otvet;
				}
			}
			output.write(otvet);
			System.out.println();
			otvet = "";
		}
		
		InputIn.close();
	
		output.close();
		System.out.println("The text is decrypted");
		
		long timeSpent = System.currentTimeMillis() - startTime;
		System.out.println("the program was executed " + timeSpent + " milliseconds");
		
		in.close();
	}

}

class GenerateSimpleNum {
	
	static BigInteger ONE = new BigInteger("1");
	static BigInteger TWO = new BigInteger("2");
	static BigInteger ZERO = new BigInteger("0");
	
	/**
	 * 
	 * @param min_size
	 * @param max_size
	 * @return random number in [min_size; max_size]
	 */
	public static BigInteger GenerateRandom(long min_size, long max_size) {
		String num = "";
		Random rnd = new Random(System.currentTimeMillis());
		
		for (int i = 0; i < max_size; i++) {
			if(i == max_size - min_size) {
				num = num + (1 + (int)rnd.nextInt(9));
			}
			num = num + (int)rnd.nextInt(10);
		}
		
		BigInteger output = new BigInteger(num);
		if(output.mod(TWO).equals(ZERO)) {
			output = output.add(ONE);
		}
		return output;
	}
	
	/**
	 * 
	 * @param number
	 * @param count_rounds
	 * @return true, if number is simple
	 */
	static boolean CheckForSimpleByMiller(BigInteger number, int count_rounds) {
		BigInteger tt = number.subtract(ONE);
		int s = 0;
		
		while(tt.mod(TWO).equals(ZERO)) {
			tt = tt.divide(TWO);
			s++;
			
		}
		
		BigInteger a, xx;
		for(long i = 0; i < count_rounds; i++) {
			a = GenerateRandom(2, number.toString().length()-2);
			xx = a.modPow(tt, number);
			
			if(xx.equals(ONE) || xx.equals(number.subtract(ONE))) {
				continue;
			}
			
			for(int r = 1; r < s; r++) {
				xx = xx.modPow(TWO, number);
				
				if(xx.equals(ONE)) {
					return false;
				}
				
				if(xx.equals(number.subtract(ONE))) {
					break;
				}
			}
			
			if(!xx.equals(number.subtract(ONE))) {
				return false;
			}
			
		}
		return true;
	}
	
	
	/**
	 * 
	 * @param e
	 * @param fi
	 * @return 
	 */
	public static BigInteger Diafant(BigInteger e, BigInteger fi) {
		
		ArrayList<BigInteger> evklid = new ArrayList<>();
		ArrayList<BigInteger> dop_evkl = new ArrayList<>();
		evklid.add(fi);
		evklid.add(e);
		ArrayList<Integer> x = new ArrayList<>(); x.add(1);
		
		while(!evklid.get(evklid.size()-1).equals(ZERO)) {
			dop_evkl.add(evklid.get(evklid.size()-2).divide(evklid.get(evklid.size()-1)));
			evklid.add(evklid.get(evklid.size()-2).mod(evklid.get(evklid.size()-1)));
		}
		
		
		BigInteger d = new BigInteger("1");
		BigInteger calc1 = new BigInteger("0");
		BigInteger ik;
		for(int k = 0; k < dop_evkl.size()-1; k++) {
			ik = d;
			d = calc1.subtract(d.multiply(dop_evkl.get(k)));
			calc1 = ik;
		}
		
		if(d.compareTo(ZERO) > 0) {
		}else if(d.compareTo(ZERO) < 0) {
			d = d.add(fi);
		}else {
			System.out.println("Fantastic");
		}
		
		return d;
	}
	
}