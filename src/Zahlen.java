import java.util.ArrayList;

public class Zahlen {
	int n;
	ArrayList<Integer> number = new ArrayList<>();

	public Zahlen(int n, ArrayList<Integer> n1) {
		this.n = n;
		for (int i = 0; i < n1.size(); i++) {
			number.add(n1.get(i));
		}
	}

	public Zahlen() {
		number = new ArrayList<>();
	}
	
	static void Print_Zahlen(Zahlen a){
		System.out.printf("%d	",a.n);
		String str_a = a.number.toString();
		System.out.println(str_a);
	}
	
	public static Zahlen StringToZahlen(String a) {
		int n = (a.charAt(0) == '+') ? 2 : 1;
		ArrayList<Integer> x = new ArrayList<>();
		
		for(int i = 1; i < a.length(); i++) {
			x.add(a.charAt(i) - '0');
		}
		
		Zahlen c = new Zahlen(n,x);
		return c;
	}
	
	public static String ZahlenToString(Zahlen a) {
		String c = "";
		if (a.n == 2) c += "(+)";
		if (a.n == 1) c += "(-)";
		if (a.n == 0) c += "";
		
		for (int i = 0; i < a.number.size(); i++) {
			c += a.number.get(i);
		}
		
		return c;
	}
	
	static Natural ABS_Z_N(Zahlen z) {
		Natural c = new Natural(new ArrayList<>(z.number));
		return c;
	}
	
	static int POZ_Z_D(Zahlen z) {
		if (z.n == 2 && z.number.get(0) != 0) {
			return 2;
		}else if (z.n == 1 && z.number.get(0) != 0) {
			return 1;
		}else {
			return 0;
		}
	}
	
	static Zahlen MUL_ZM_Z(Zahlen a) {
		Zahlen a1 = new Zahlen(a.n, new ArrayList<>(a.number));
		if (a1.n == 1) {
			a1.n = 2;
		}else if(a1.n == 2){
			a1.n = 1;
		}
		
		return a1;
	}
	
	static Zahlen TRANS_N_Z(Natural a) {
		Zahlen z = new Zahlen(2,new ArrayList<>(a.x));
		return z;
	}
	
	static Natural TRANS_Z_N(Zahlen z) {
		if (z.n == 2) {
			Natural a = new Natural(new ArrayList<>(z.number));
			return a;
		}else {
			return null;
		}
	}
	
	static Natural TRANS_Z_N_NoMath(Zahlen z) {
		Natural a = new Natural(new ArrayList<>(z.number));
		return a;
	}
	
	static Zahlen ADD_ZZ_Z(Zahlen a,Zahlen b) {
		Zahlen a1 = new Zahlen(a.n,new ArrayList<>(a.number));
		Zahlen b1 = new Zahlen(b.n,new ArrayList<>(b.number));
		Zahlen c = new Zahlen();
		
		if(POZ_Z_D(a1) + POZ_Z_D(b1) == 0) {
			c.n = 0;
			c.number.add(0);
			return c;
		}
		
		if(POZ_Z_D(a1) + POZ_Z_D(b1) == 4 || POZ_Z_D(a1) + POZ_Z_D(b1) == 2 || POZ_Z_D(a1) + POZ_Z_D(b1) == 1 ) {
			c = TRANS_N_Z(Natural.ADD_NN_N(TRANS_Z_N_NoMath(a1),TRANS_Z_N_NoMath(b1)));
			if(POZ_Z_D(a1) == 0 || POZ_Z_D(b1) == 0) {
				c.n = (POZ_Z_D(a1) + POZ_Z_D(b1));
			}else {
				c.n = (POZ_Z_D(a1) + POZ_Z_D(b1))/2;
			}
		} else if (POZ_Z_D(a1) + POZ_Z_D(b1) == 3) {
			if (Natural.COM_MN_D(TRANS_Z_N_NoMath(a1), TRANS_Z_N_NoMath(b1)) == 2) {
				c = TRANS_N_Z(Natural.SUB_NN_N(TRANS_Z_N_NoMath(a1),TRANS_Z_N_NoMath(b1)));
				c.n = POZ_Z_D(a1);
			}
			if (Natural.COM_MN_D(TRANS_Z_N_NoMath(a1), TRANS_Z_N_NoMath(b1)) == 1) {
				c = TRANS_N_Z(Natural.SUB_NN_N(TRANS_Z_N_NoMath(a1),TRANS_Z_N_NoMath(b1)));
				c.n = POZ_Z_D(b1);
			}
			if (Natural.COM_MN_D(TRANS_Z_N_NoMath(a1), TRANS_Z_N_NoMath(b1)) == 0) {
				c.n = 0;
				c.number.add(0);
			}
		}
		return c;
	}
	
	static Zahlen SUB_ZZ_Z(Zahlen a, Zahlen b) {
		Zahlen a1 = new Zahlen(a.n,new ArrayList<>(a.number));
		Zahlen b1 = new Zahlen(b.n,new ArrayList<>(b.number));
		Zahlen c = new Zahlen();
		
		b1 = MUL_ZM_Z(b1);
		c = ADD_ZZ_Z(a1,b1);
		
		return c;
	}
	
	static Zahlen MUL_ZZ_Z(Zahlen a, Zahlen b) {
		Zahlen a1 = new Zahlen(a.n,new ArrayList<>(a.number));
		Zahlen b1 = new Zahlen(b.n,new ArrayList<>(b.number));
		Zahlen c = new Zahlen();
		
		c = TRANS_N_Z(Natural.MUL_NN_N(TRANS_Z_N_NoMath(a1), TRANS_Z_N_NoMath(b1)));
		
		if (POZ_Z_D(a1) + POZ_Z_D(b1) == 3) c.n = 1;
		if (POZ_Z_D(a1) + POZ_Z_D(b1) == 4) c.n = 2;
		if (POZ_Z_D(a1) + POZ_Z_D(b1) == 2 || POZ_Z_D(a1) + POZ_Z_D(b1) == 1 || POZ_Z_D(a1) + POZ_Z_D(b1) == 0) {
			if(POZ_Z_D(a1) == 0 || POZ_Z_D(b1) == 0) {
				c.n = 0;
			}else{
				c.n = 2;
			}
		}
			
		return c;
	}
	
	static Zahlen DIV_ZZ_Z(Zahlen a, Zahlen b) {
		Zahlen a1 = new Zahlen(a.n,new ArrayList<>(a.number));
		Zahlen b1 = new Zahlen(b.n,new ArrayList<>(b.number));
		Zahlen c = new Zahlen();
		
		if(POZ_Z_D(b1) == 0) {
			return null;
		}
		
		c = TRANS_N_Z(Natural.DIV_NN_N(TRANS_Z_N_NoMath(a1), TRANS_Z_N_NoMath(b1)));
		
		if (POZ_Z_D(a1) + POZ_Z_D(b1) == 3) c.n = 1;
		if (POZ_Z_D(a1) + POZ_Z_D(b1) == 4) c.n = 2;
		if (POZ_Z_D(a1) + POZ_Z_D(b1) == 2 || POZ_Z_D(a1) + POZ_Z_D(b1) == 1 || POZ_Z_D(a1) + POZ_Z_D(b1) == 0) {
			if(POZ_Z_D(a1) == 0 || POZ_Z_D(b1) == 0) {
				c.n = 0;
			}else{
				c.n = 2;
			}
		}
		
		return c;
	}
	
	static Zahlen MOD_ZZ_Z(Zahlen a, Zahlen b) {
		Zahlen a1 = new Zahlen(a.n,new ArrayList<>(a.number));
		Zahlen b1 = new Zahlen(b.n,new ArrayList<>(b.number));
		Zahlen c = new Zahlen();
		
		if((POZ_Z_D(a1) == 2 && POZ_Z_D(b1) == 2) || (POZ_Z_D(a1) == 2 && POZ_Z_D(b1) == 1)) {
			c = TRANS_N_Z(Natural.MOD_NN_N(TRANS_Z_N_NoMath(a1),TRANS_Z_N_NoMath(b1)));
			c.n = 2;
		}else if((POZ_Z_D(a1) == 1 && POZ_Z_D(b1) == 2) || (POZ_Z_D(a1) == 1 && POZ_Z_D(b1) == 1)) {
			//a1 = MUL_ZM_Z(a1);
			c = SUB_ZZ_Z(TRANS_N_Z(ABS_Z_N(b1)),(TRANS_N_Z(Natural.MOD_NN_N(TRANS_Z_N_NoMath(a1), TRANS_Z_N_NoMath(b1)))));
			c.n = 2;
		}else if(POZ_Z_D(a1) == 0 && POZ_Z_D(b1) != 0) {
			c.number.add(0);
			c.n = 2;
		}else {
			return null;
		}
		
		return c;
	}
}