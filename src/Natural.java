import java.util.ArrayList;



class Natural {
	ArrayList<Integer> x = new ArrayList<Integer>();

	public  Natural(ArrayList<Integer> a) {
		for (int i = 0; i < a.size(); i++) {
			x.add(a.get(i));
		}
	}
	
	public Natural() {
		x = new ArrayList<Integer>();
	}
	
	public Natural(int one) {
		x.add(one);
	}
	
	public Natural(String str) {
		for(int i  = 0; i < str.length(); i++) {
			x.add(Character.getNumericValue(str.charAt(i)));
		}
	}
	
	static public void Print_natural(Natural a) {
		String str_a = a.x.toString();
		System.out.println(str_a);
	}
	
	public static void Swap_natural(Natural a, Natural b) {
		Natural c = new Natural (new ArrayList<Integer>(a.x));
		a = b;
		b = c;
	}
	
	static int Natural_to_int(Natural a) {
		int b = 0;
		
		for(int i = 0; i < a.x.size(); i++) {
			b += a.x.get(i);
			if(i != 0 ) b *= 10;
		}
		
		return b;
	}
	
	public static String NaturalToNormalString(Natural a) {
		String c = "";
		for (int i = 0; i<a.x.size(); i++) {
			c += a.x.get(i);
		}
		return c;
	}
	
	public static long ReturnSumOf(Natural a) {
		long s = 0;
		for(int i = 0; i < a.x.size(); i++) {
			s += a.x.get(i);
		}
		return s;
	}
	
	static Natural Generate_random_fift(int min_symb, int max_symb) {
		Natural a = new Natural();
		int i = 0;
		int check;
		
		if(min_symb == max_symb) {
			a.x.add(1 + (int)Math.floor(Math.random()*9));
			for(i = 1; i < max_symb; i++) {
				a.x.add((int)(Math.random()*10));
			}
		}else {
			for(i = 0; i < max_symb; i++) {
				if(i == max_symb-min_symb) {
					a.x.add(1 + (int)Math.floor(Math.random()*9));
				}else {
					check = (int)(Math.random()*10);
					if(check != 0) {
						a.x.add(check);
					}
				}
			}
		}
		if(a.x.get(a.x.size()-1) % 2 == 0) {
			a.x.set(a.x.size()-1, a.x.get(a.x.size()-1)+1);
		}
		
		return a;
	}
	static int COM_MN_D(Natural a, Natural b) {
		if(a.x.size() == b.x.size()) {
			int i = 0;
			
			while (i < a.x.size()) {
				if(a.x.get(i) > b.x.get(i)) {
					return 2;
				}else if(a.x.get(i) < b.x.get(i)) {
					return 1;
				}
				i++;
			}
		}else if(a.x.size() > b.x.size()) {
			return 2;
		}else if (b.x.size() > a.x.size()) {
			return 1;
		}
		
		return 0;
	}
	
	static int NZER_N_B(Natural a){
		if(a.x.get(0) == 0) return 1;
		return 0;
	}
	
	static Natural ADD_1N_N(Natural a) {
		Natural a1 = new Natural();
		int i = a.x.size()-1, test_v, bufer = 0;
		
		while(i >= 0) {
			test_v = a.x.get(i);
			
			if(bufer == 1 || i == a.x.size()-1) {
				test_v++;
				bufer = 0;
			}
			
			if (test_v > 9) {
				bufer = 1;
				test_v -= 10;
			}
			
			if(i == 0 && bufer == 1) {
				a1.x.add(0,test_v);
				a1.x.add(0,1);
			}else{
				a1.x.add(0,test_v);
			}
			
			i--;
		}
		
		return a1;
	}

	static Natural ADD_NN_N(Natural a, Natural b) {
		Natural a1 = new Natural(new ArrayList<Integer>(a.x));
		Natural b1 = new Natural(new ArrayList<Integer>(b.x));
		Natural c1 = new Natural();
		
		if (COM_MN_D(a1,b1) == 1) {
			Natural c = new Natural (new ArrayList<Integer>(a1.x));
			a1 = b1;
			b1 = c;
		}
		
		int i = a1.x.size()-1, j = b1.x.size()-1, test_v, bufer = 0;
		
		while (j >= 0 && i >= 0) {
			test_v = a1.x.get(i) + b1.x.get(j);
			
			if (bufer == 1) {
				test_v += bufer;
				bufer = 0;
			}
			
			if(test_v > 9) {
				test_v -= 10;
				bufer = 1;
			}
			
			if(i == 0 && j == 0 && bufer == 1) {
				c1.x.add(0,test_v);
				c1.x.add(0,1);
			}else {
				c1.x.add(0,test_v);
			}
			i--;
			j--;
		}
		
		while (i >= 0) {
			test_v = a1.x.get(i);
			
			if(bufer == 1) {
				test_v += bufer;
				bufer = 0;
			}
			
			if(test_v > 9) {
				test_v -= 10;
				bufer = 1;
			}
			
			if(i == 0 && bufer == 1) {
				c1.x.add(0,test_v);
				c1.x.add(0,1);
			}else {
				c1.x.add(0,test_v);
			}
			i--;
		}
		
		return c1;
	}

	static Natural SUB_NN_N(Natural a, Natural b) {
		Natural a1 = new Natural(new ArrayList<Integer>(a.x));
		Natural b1 = new Natural(new ArrayList<Integer>(b.x));
		Natural c1 = new Natural();
		
		if (COM_MN_D(a1,b1) == 1) {
			Natural c = new Natural (new ArrayList<Integer>(a1.x));
			a1 = b1;
			b1 = c;
		}
		
		int i = a1.x.size()-1, j = b1.x.size()-1, test_v, bufer = 0;
		
		while(i >= 0 && j >= 0) {
			test_v = a1.x.get(i) - b1.x.get(j);
			
			if(bufer == 1) {
				test_v--;
				bufer = 0;
			}
			
			if (test_v < 0) {
				bufer = 1;
				test_v += 10;
			}
			
			c1.x.add(0,test_v);
			i--;
			j--;
		}
		while (i >= 0) {
			test_v = a1.x.get(i) - bufer;
			bufer = 0;
			
			if(test_v < 0) {
				bufer = 1;
				test_v += 10;
			}
			
			c1.x.add(0,test_v);
			i--;
		}
		if (c1.x.get(0) < 0) return null;
		while (c1.x.size() != 0) {
			if (c1.x.get(0) == 0) {
				c1.x.remove(0);
			}else {
				break;
			}
		}
		if (c1.x.size() == 0) {
			c1.x.add(0);
			return c1;
		}
		return c1;
	}
	
	static Natural MUL_ND_N(Natural a, int mul) {
		Natural a1 = new Natural(new ArrayList<Integer>(a.x));
		Natural c1 = new Natural();
		int i = a.x.size()-1, test_v, bufer= 0;
		
		while (i >= 0) {
			test_v = a1.x.get(i) * mul;
			
			if (bufer > 0) {
				test_v += bufer;
				bufer = 0;
			}
			
			if(test_v > 9) {
				bufer = test_v / 10;
				test_v = test_v % 10;
			}
			
			if(i == 0 && bufer > 0) {
				c1.x.add(0,test_v);
				c1.x.add(0,bufer);
			}else {
				c1.x.add(0,test_v);
			}
			i--;
		}
		
		return c1;
	}
	
	static Natural MUL_NK_N(Natural a, int k) {
		Natural a1 = new Natural(new ArrayList<Integer>(a.x));
		int i = 0;
		
		while (i < k) {
			a1.x.add(0);
			i++;
		}
		
		return a1;
	}
	
	static Natural MUL_NN_N(Natural a, Natural b) {
		Natural a1 = new Natural(new ArrayList<Integer>(a.x));
		Natural b1 = new Natural(new ArrayList<Integer>(b.x));
		Natural c1 = new Natural();
		
		if (COM_MN_D(a1,b1) == 1) {
			Natural c = new Natural (new ArrayList<Integer>(a1.x));
			a1 = b1;
			b1 = c;
		}
		
		int i = 0, j = b1.x.size()-1;
		
		while(j >= 0) {
			c1 = ADD_NN_N(c1,MUL_NK_N(MUL_ND_N(a1,b1.x.get(j)),i));
			j--;
			i++;
		}
		
		return c1;
	}
	
	static Natural ModPow(Natural a, Natural b, Natural c) {
		Natural b1 = new Natural(new ArrayList<Integer>(b.x));
		Natural a1 = new Natural(new ArrayList<Integer>(a.x));
		ArrayList<Integer> h = new ArrayList<>();
		h.add(2);
		Natural bin = new Natural(h);
		Natural b_to_bin = new Natural();
		while(b1.x.size() != 0) {
			b_to_bin.x.add(0, Natural.Natural_to_int(Natural.MOD_NN_N(b1, bin)));
			b1 = Natural.DIV_NN_N(b1, bin);
		}
		
		for(int i = 1; i < b_to_bin.x.size(); i++) {
			a1 = Natural.MUL_NN_N(a1, a1);
			if(b_to_bin.x.get(i) == 1) {
				a1 = Natural.MUL_NN_N(a1, a);
			}
			a1 = Natural.MOD_NN_N(a1, c);
		}
		
		return a1;
	}
	
	static Natural SUB_NDN_N(Natural a, int mul, Natural b) {
		Natural a1 = new Natural(new ArrayList<Integer>(a.x));
		Natural b1 = new Natural(new ArrayList<Integer>(b.x));
		Natural c1 = new Natural();
		
		if (COM_MN_D(a1,b1) == 1) {
			Natural c = new Natural (new ArrayList<Integer>(a1.x));
			a1 = b1;
			b1 = c;
		}
		
		b1 = MUL_ND_N(b1,mul);
		c1 = SUB_NN_N(a1,b1);
		
		return c1;
	}
	
	static int DIV_NN_DK(Natural a, Natural b) {
		Natural a1 = new Natural(new ArrayList<Integer>(a.x));
		Natural b1 = new Natural(new ArrayList<Integer>(b.x));
		Natural c1 = new Natural();
		int div = 1;
		
		if (COM_MN_D(a1,b1) == 1) {
			Natural c = new Natural (new ArrayList<Integer>(a1.x));
			a1 = b1;
			b1 = c;
		}
		
		while (COM_MN_D(a1,MUL_NK_N(b1,1)) == 2) {
			b1 = MUL_NK_N(b1,1);
		}
		
		while (COM_MN_D(a1,c1) != 1) {
			c1 = MUL_ND_N(b1,div);
			div++;
		}
		
		return (div-2);
	}
	
	static Natural DIV_NN_N(Natural a, Natural b) {
		Natural a1 = new Natural(new ArrayList<Integer>(a.x));
		Natural b1 = new Natural(new ArrayList<Integer>(b.x));
		Natural b11 = new Natural();
		Natural c1 = new Natural();
		
		if(NZER_N_B(b1)==1 || b1.x.size() == 0) {
			return null;
		}
		
		if(NZER_N_B(a1)==1 || a1.x.size() == 0) {
			c1.x.add(0);
			return c1;
		}
		
		while(COM_MN_D(a1,b) != 1) {
			b1 = new Natural(new ArrayList<Integer>(b.x));
			c1.x.add(DIV_NN_DK(a1,b1));
			
			while (COM_MN_D(a1,MUL_NN_N(b1,MUL_NK_N(c1,1))) == 2) {
				c1 = MUL_NK_N(c1,1);
			}
			b11 = ADD_NN_N(b11,c1);
			a1 = SUB_NN_N(a1,MUL_NN_N(b1,c1));
			while(c1.x.size() != 0) {
				c1.x.remove(0);
			}
		}
		
		return b11;
	}
	
	static Natural MOD_NN_N(Natural a, Natural b) {
		Natural a1 = new Natural(new ArrayList<Integer>(a.x));
		Natural b1 = new Natural(new ArrayList<Integer>(b.x));
		Natural c1 = new Natural();
		
		
		while(COM_MN_D(a1,b) != 1) {
			int help = DIV_NN_DK(a1,b);
			c1.x.add(help);
			b1=new Natural(new ArrayList<Integer>(b.x));
			while (COM_MN_D(a1,MUL_NK_N(b1,1)) == 2) {
				b1 = MUL_NK_N(b1,1);
			}
			a1=SUB_NDN_N(a1,help,b1);
		}
		
		
		return a1;
	}
	
	static Natural GCF_NN_N(Natural a, Natural b) {
		Natural a1 = new Natural(new ArrayList<Integer>(a.x));
		Natural b1 = new Natural(new ArrayList<Integer>(b.x));
		Natural r1 = new Natural(new ArrayList<Integer>(b.x));
		
		if (COM_MN_D(a1,b1) == 1) {
			Natural c = new Natural (new ArrayList<Integer>(a1.x));
			a1 = b1;
			b1 = c;
		}
		
		while (NZER_N_B(r1) == 0) {
			r1 = MOD_NN_N(a1,b1);
			a1=b1;
			b1=r1;
		}
		
		return a1;
	}
	
	static Natural LCM_NN_N(Natural a, Natural b) {
		Natural a1 = new Natural(new ArrayList<Integer>(a.x));
		Natural b1 = new Natural(new ArrayList<Integer>(b.x));
		Natural r1 = new Natural(new ArrayList<Integer>(b.x));
		
		r1 = MUL_NN_N(a1,b1);
		r1 = DIV_NN_N(r1,GCF_NN_N(a1,b1));
		
		return r1;
	}
	
	/*static Natural ModPow(Natural osnovanie, Natural stepen, Natural mod) {
		BigInteger osnovanie1 = new BigInteger(Natural.NaturalToNormalString(osnovanie));
		BigInteger stepen1 = new BigInteger(Natural.NaturalToNormalString(stepen));
		BigInteger mod1 = new BigInteger(Natural.NaturalToNormalString(mod));
		BigInteger x = osnovanie1.modPow(stepen1, mod1);
		
		Natural answer = new Natural(x.toString());
		
		return answer;
	}*/
	
	
}