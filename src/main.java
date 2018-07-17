import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
	
	static String ReturnStringNumber(int a) {
		String output = "";
		output += a;
		while(output.length() < 5) {
			output = "0" + output;
		}
		
		return output;
	}

	public static void main(String[] args) throws IOException {
	
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
			otvet = "";
		}
	
		output.close();
		System.out.println("The text is decrypted");
		
		long timeSpent = System.currentTimeMillis() - startTime;
		System.out.println("the program was executed " + timeSpent + " milliseconds");
	}

}