import java.awt.List;
import java.util.ArrayList;

public class CombTest2 {
	
	public static void main(String[] args) {
//		System.out.println("hi");
//		int N = (int) Math.pow(2, 4); 
//		System.out.println(N);
//		System.out.println(Integer.toBinaryString(N));
//		System.out.println(Integer.toBinaryString(N|0).substring(1));
//		System.out.println(Integer.toBinaryString(N|1).substring(1));
//		System.out.println(Integer.toBinaryString(N|2).substring(1));
//		System.out.println(Integer.toBinaryString(N|3).substring(1));
//		System.out.println(Integer.toBinaryString(N|4).substring(1));
		
		
		int N = (int) Math.pow(2, 6);
		
		int[] input = {1,2,3,4,5,6};
		ArrayList<String> outList = new ArrayList<String>();
		
		
		for(int i = 0 ; i < N;  i++) {
			String bin = Integer.toBinaryString(N+i).substring(1);
			String out = "";
			int count = 0;
			for(int j =0 ; j < bin.length() ; j ++ ) {
				if(bin.charAt(j)=='1') {
					out += input[j];
					count ++;

				}

			}
			if(count == 3) {
				outList.add(out);
			}
		}
		
		for(String str : outList) {
			System.out.println(str);
		}
		
		
		
		
		
	}

}
