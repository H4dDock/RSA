import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		Scanner in = new Scanner(System.in);
		short flag = 0;
		
		Natural p = Natural.Generate_random_fift(60,60);
		Natural q = new Natural();
		while(flag != 2) {
			p = Natural.ADD_1N_N(p);
			if(GenerateSimpleNum.CheckForSimpleByMiller(p,12)) {
				flag++;
				if(flag == 1){
					q = p;
					p = Natural.Generate_random_fift(60,60);
				}
			}
		}
		
		Natural N = Natural.MUL_NN_N(p, q); //Checked
		Natural fi = Natural.MUL_NN_N(Natural.SUB_NN_N(p, new Natural(1)), Natural.SUB_NN_N(q, new Natural(1))); //Checked
		
		Natural e = Natural.Generate_random_fift(30,59);
		
		
		while(!GenerateSimpleNum.CheckForSimpleByMiller(e,7)) {
			e = Natural.ADD_1N_N(e);
		}
		
		Natural d = new Natural();
		do {
			d = GenerateSimpleNum.Diafant(e, fi);
		}while(d.x.equals(new Natural(1).x));
		
		FileReader input = new FileReader("input.txt");
		FileWriter output = new FileWriter("output.txt");
		Scanner InputIn = new Scanner(input);
		String stroka = InputIn.nextLine();
		input.close();
		int symb;
		
		for(int i = 0; i<stroka.length(); i++) {
			symb = stroka.charAt(i);
			output.write(Natural.NaturalToNormalString(Natural.ModPow(new Natural(""+symb), e, N)));
			output.write("\n");
		}
		output.close();
		
		input = new FileReader("output.txt");
		output = new FileWriter("Otvet.txt");
		Scanner NewIn = new Scanner (input);
		char a;
		while(NewIn.hasNextLine()) {
			stroka = NewIn.nextLine();
			symb = Integer.parseInt(Natural.NaturalToNormalString(Natural.ModPow(new Natural(stroka), d, N)));
			output.write(""+(char)symb);
		}
		output.close();
		input.close();
		
		
		
		long timeSpent = System.currentTimeMillis() - startTime;
		System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
		
	}

}
