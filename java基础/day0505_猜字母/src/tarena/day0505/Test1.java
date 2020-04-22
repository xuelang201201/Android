package tarena.day0505;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		/*
		 * 准备26个大写字母数组
		 * 1.新建char[] 数组赋给chars
		 * 2.遍历数组，放入大写字母A-Z
		 *     0       1      2
		 *    'A'+0   'A'+1  'A'+2
		 */

		char[] chars = new char[26];
		for(int i=0; i<26; i++) {
			chars[i] = (char) ('A'+i);
		}
		//System.out.println(Arrays.toString(chars));

		/*
		 * 1.循环i从0到<5递增
		 *     2.从[i,26)范围随机挑选下标j
		 *           i+[0,26-i)
		 *     3.交换chars数组i和j位置的值
		 *     
		 * 4.从chars数组截取前5个值变成一个新数组赋给变量r
		 */

		for(int i=0; i<5; i++) {
			int j = i + new Random().nextInt(26-i);

			char c = chars[i];
			chars[i] = chars[j];
			chars[j] = c;
		}

		//复制前5个位置成新数组
		char[] r = Arrays.copyOf(chars, 5);
		System.out.println(Arrays.toString(r));

		System.out.println("已经产生5个不重复的大写字母");
		System.out.println("请猜这5个字母");
		/*
		 * 1.死循环
		 *     2.提示“猜：”
		 *     3.获得输入的字符串赋给s
		 *     4.字符串s转成char[]数组赋给c
		 *     5.比较 c 和 r 数组得到比较结果赋给result
		 *     6.显示result
		 *     7.如果result 是"5A0B"
		 *         8.break
		 */
		while(true) {
			System.out.print("猜：");
			String s = new Scanner(System.in).nextLine();
			//s.toCharArray();
			//将字符串s转成char[] 数组
			char[] c = s.toCharArray();
			String result = compare(c, r);
			System.out.println(result);
			if(result.equals("5A0B")) {
				break;
			}
		}
	}//main结束

	static String compare(char[] c, char[] r) {

		/* a=0, b=0
		 * 
		 * r TKBNW
		 *    j
		 * 
		 * c KTALW
		 *   i
		 *   
		 * 1.定义计数变量a=0,b=0
		 * 2.循环i 遍历c数组
		 *     3.循环 j 遍历r数组
		 *         4.如果r[j]==c[i]
		 *             5.如果i==j, a++
		 *             6.否则, b++
		 * 7.返回a+"A"+b+"B"
		 */

		int a = 0;
		int b = 0;
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(c[i] == r[j]) {
					if(i == j) {
						a++;
					} else {
						b++;
					}
				}
			}
		}
		return a+"A"+b+"B";
	}
}
