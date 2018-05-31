import java.util.ArrayList;
import java.math.BigInteger;
import java.util.Arrays;

public class GenerateSimpleNum {
	/*
	static boolean CheckForSimpleByMiller(Natural number, int count_rounds) {
		if(number.x.get(number.x.size()-1) % 2 == 0) return false;
		
		Natural tt = Natural.SUB_NN_N(number, new Natural(1));
		int s = 0;
		
		
		while(Natural.MOD_NN_N(tt, new Natural(2)) == new Natural(0)) {
			tt = Natural.DIV_NN_N(tt, new Natural(2));
			s++;
		}
		for(int i = 0; i < count_rounds; i++) {
			Natural a = Natural.Generate_random_fift(2, number.x.size()-2);
			Natural xx = Natural.Stepen(a, tt, number);
			System.out.println("Puk");
			
			if(xx.x.equals(new Natural(1).x) || xx.x.equals(Natural.SUB_NN_N(number, new Natural(1)))) {
				continue;
			}
			long startTime = System.currentTimeMillis();
			for (int r = 1; r < s; r++) {
				xx = Natural.Stepen(xx, new Natural(2), number);
				
				if(xx.x.equals(new Natural(1).x)) {
					return false;
				}
				
				if(xx.x.equals(Natural.SUB_NN_N(number, new Natural(1)))) {
					break;
				}
			}
			long timeSpent = System.currentTimeMillis() - startTime;
			System.out.println("цикл выполнялась " + timeSpent + " миллисекунд");
			
			if(!xx.x.equals(Natural.SUB_NN_N(number, new Natural(1)))) {
				return false;
			}
			
		}

		return true;
	}*/
	
	
	 static boolean CheckForSimpleByMiller(Natural number, int count_rounds) {
		if(number.x.get(number.x.size()-1) % 2 == 0) return false;
		
		BigInteger num = new BigInteger(Natural.NaturalToNormalString(number));
		
		BigInteger t = num.subtract(BigInteger.valueOf(1));
		int s = 0;
		
		while(t.mod(BigInteger.valueOf(2)) == BigInteger.valueOf(0)) {
			t = t.divide(BigInteger.valueOf(2));
			s++;
		}
		
		for(int i = 0; i < count_rounds; i++) {
			Natural a = Natural.Generate_random_fift(2, number.x.size()-2);
			BigInteger a1 = new BigInteger(Natural.NaturalToNormalString(a));
			BigInteger x = a1.modPow(t, num);
			
			if(x.equals(BigInteger.valueOf(1)) || x.equals(num.subtract(BigInteger.valueOf(1)))) {
				continue;
			}
			
			for (int r = 1; r < s; r++) {
				
			}
			
			for (int r = 1; r < s; r++) {
				x = x.modPow(BigInteger.valueOf(2), num);
				
				if(x.equals(BigInteger.valueOf(1))) {
					return false;
				}
				
				if(x.equals(num.subtract(BigInteger.valueOf(1)))) {
					break;
				}
			}
			
			if(!x.equals(num.subtract(BigInteger.valueOf(1)))) {
				return false;
			}
			
		}
		
		return true;
	}
	 
	
	static Natural Diafant(Natural e, Natural fi) {
		ArrayList<Natural> evklid = new ArrayList<>();
		ArrayList<Natural> dop_evkl = new ArrayList<>();
		evklid.add(fi);
		evklid.add(e);
		int fuck = 1;
		Natural i = new Natural();
		ArrayList<Integer> x = new ArrayList(); x.add(1);
		
		Natural calc = new Natural(1);
		while(evklid.get(evklid.size()-1).x.get(0) != 0) {
			dop_evkl.add(Natural.DIV_NN_N(evklid.get(evklid.size()-2), evklid.get(evklid.size()-1)));
			evklid.add(Natural.MOD_NN_N(evklid.get(evklid.size()-2), evklid.get(evklid.size()-1)));
		}
		
		
		Zahlen d = new Zahlen();
		d.number.add(1);
		d.n = 2;
		Zahlen calc1 = new Zahlen();
		calc1.number.add(0);
		Zahlen ik = new Zahlen();
		for(int k = 0; k < dop_evkl.size()-1; k++) {
			ik = d;
			d = Zahlen.SUB_ZZ_Z(calc1, Zahlen.MUL_ZZ_Z(d, Zahlen.TRANS_N_Z(dop_evkl.get(k))));
			calc1 = ik;
		}
		
		if(d.n == 2) {
		}else if(d.n == 1) {
			d = Zahlen.ADD_ZZ_Z(d, Zahlen.TRANS_N_Z(fi));
		}else {
			System.out.println("Fantastic");
		}
		
		return Zahlen.TRANS_Z_N_NoMath(d);
	}
	
	
}
