package tarena.day0503;

import java.util.Arrays;
import java.util.Random;

public class Test2 {
	public static void main(String[] args) {
		int len = 5 + new Random().nextInt(6);
		int[] a = new int[len];
		for(int i=0; i<len; i++) {
			a[i] = new Random().nextInt(100);
		}
		System.out.println(Arrays.toString(a));
	}
}
