package tarena.day1301;

public class Test2 {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("abc");
		
		sb.append("def")            //abcdef
		.append("ghi")              //abcdedghi
		.insert(0, "123")           //123abcdedghi
		.replace(2,  6, "hello")    //12hellodedghi
		.delete(3, 5)               //12hlodefghi
		.deleteCharAt(6)            //12hlodfghi
		.reverse()                  //ihgfdolh21
		.setCharAt(0, '*');         //*hgfdolh21
		
		System.out.println(sb);
	}
}
