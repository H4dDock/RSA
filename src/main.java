import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		short flag = 0;
		int k = 0;
		System.out.print("Etner order of the number to generation (10^k, enter k): ");
		k = in.nextInt();
		long startTime = System.currentTimeMillis();
		
		Natural p = Natural.Generate_random_fift(k,k);
		Natural q = new Natural();
		System.out.println("Starting of the generation prime numbers. This may take some time.\n");
		while(flag != 2) {
			//p = Natural.ADD_1N_N(p);
			p = Natural.ADD_NN_N(p, new Natural(2));
			if(Natural.ReturnSumOf(p) % 3 == 0) continue;
			if(p.x.get(p.x.size()-1) == 0 || p.x.get(p.x.size()-1) == 5) continue;
			if(GenerateSimpleNum.CheckForSimpleByMiller(p,15)) {
				flag++;
				if(flag == 1){
					q = p;
					p = Natural.Generate_random_fift(k,k);
				}
			}
		}
		System.out.println("Generation of prime numbers end. Start key generation");
		
		Natural N = Natural.MUL_NN_N(p, q); 
		System.out.println("N calculated: "+Natural.NaturalToNormalString(N));
		Natural fi = Natural.MUL_NN_N(Natural.SUB_NN_N(p, new Natural(1)), Natural.SUB_NN_N(q, new Natural(1))); //Checked
		System.out.println("fi calculated: "+Natural.NaturalToNormalString(fi));
		
		Natural e = Natural.Generate_random_fift(1,k-5);
		
		flag = 0;
		while(flag == 0) {
			e = Natural.ADD_NN_N(e, new Natural(2));
			if(Natural.ReturnSumOf(e) % 3 == 0) continue;
			if(e.x.get(e.x.size()-1) == 0 || e.x.get(e.x.size()-1) == 5) continue;
			if (GenerateSimpleNum.CheckForSimpleByMiller(e,15)) {
				flag = 1;
			}
		}
		System.out.println("e is calculated: "+Natural.NaturalToNormalString(e));
		
		Natural d = new Natural();
		do {
			d = GenerateSimpleNum.Diafant(e, fi);
		}while(d.x.equals(new Natural(1).x));
		System.out.println("d is calculated: "+Natural.NaturalToNormalString(d));
		
		System.out.println("Open key:\n     "+Natural.NaturalToNormalString(e)+"\n     "+Natural.NaturalToNormalString(N));
		System.out.println("Close key:\n     "+Natural.NaturalToNormalString(d)+"\n     "+Natural.NaturalToNormalString(N));
		
		
		FileWriter output = new FileWriter("output.txt");
		Scanner InputIn = new Scanner(new File("input.txt"));
		String stroka;
		int symb;
		
		System.out.println("Text is encrypting, don't panic this can take some time, the program work fine.");
		
		while(InputIn.hasNext()){
			stroka = InputIn.nextLine();
			for(int i = 0; i<stroka.length(); i++) {
				symb = stroka.charAt(i);
				output.write(Natural.NaturalToNormalString(Natural.ModPow(new Natural(""+symb), e, N)));
				output.write("\n");
			}
		}
		output.close();
		System.out.println("Text is encrypted");
		
		
		output = new FileWriter("Otvet.txt");
		InputIn = new Scanner (new File("output.txt"));
		char a;
		
		while(InputIn.hasNext()) {
			stroka = InputIn.nextLine();
			symb = Integer.parseInt(Natural.NaturalToNormalString(Natural.ModPow(new Natural(stroka), d, N)));
			output.write(""+(char)symb);
			if(symb == '\n') output.write("\n");
		}
		output.close();
		
		
		
		long timeSpent = System.currentTimeMillis() - startTime;
		System.out.println("the program was executed " + timeSpent + " milliseconds");
		
	}

}
