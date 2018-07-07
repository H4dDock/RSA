import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GenerateSimpleNum {
	
	static BigInteger ONE = new BigInteger("1");
	static BigInteger TWO = new BigInteger("2");
	static BigInteger ZERO = new BigInteger("0");
	
	static BigInteger GenerateRandom(long min_size, long max_size) {
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
	
	static BigInteger Diafant(BigInteger e, BigInteger fi) {
		
		ArrayList<BigInteger> evklid = new ArrayList<>();
		ArrayList<BigInteger> dop_evkl = new ArrayList<>();
		evklid.add(fi);
		evklid.add(e);
		ArrayList<Integer> x = new ArrayList(); x.add(1);
		
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
