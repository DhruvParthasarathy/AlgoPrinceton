
public class CombinationTest {
	
	public static void main(String[] args) {
		System.out.println("print test");
		int[] nums = {1,2,3,4,5};
		
		for(int i : nums) {
			for(int j : nums) {
				for(int k : nums) {
					if((j < i)&&(k<j)) {
						System.out.println(Integer.toString(i)+Integer.toString(j)+Integer.toString(k));
					}
				}
				
			}
		}
	}

}
