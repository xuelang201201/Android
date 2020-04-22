package tarena.day1501;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formula {
	private String f;
	
	public Formula(String f) {
		this.f = f.trim();
	}
	
	public class DieDaiQi {  //内部类
		int from;
		Matcher m = Pattern
				.compile("\\d+(\\.\\d+)?|[+/\\-*]")
				.matcher(f);
		
		public String next() {
			m.find(from); //从from向后找下一段
			String s = m.group(); //取出下一段
			from = m.end(); //from移动到这一段末尾
			return s;
		}
		public boolean hasNext() {
			//from在界内，还有下一段
			return from < f.length();
		}
	}
	
	public BigDecimal eval() { //求值运算
		LinkedList<BigDecimal> list1 = new LinkedList<>();
		LinkedList<String> list2 = new LinkedList<>();
		
		DieDaiQi d = /*this.*/new DieDaiQi();
		
		//迭代拆分，
		//
		while(d.hasNext()) {
			String s = d.next();
			if(s.matches("\\d+(\\.\\d+)?")) { //s是数字
				list1.add(new BigDecimal(s));
			} else if(s.matches("[+\\-]")) { //s是+-
				list2.add(s);
			} else { //s是*, /
				BigDecimal a = list1.removeLast();
				BigDecimal b = new BigDecimal(d.next());
				BigDecimal c = jiSuan(a, s, b);
				//结果放入尾部
				list1.add(c);
			}
		}
		
		///
		//当还有加减号
		while(list2.size() != 0) {
			BigDecimal a = list1.removeFirst();
			BigDecimal b = list1.removeFirst();
			String s = list2.removeFirst();
			BigDecimal c = jiSuan(a, s, b);
			//结果放入头部
			list1.addFirst(c);
		}
		
		return list1.get(0);
	}

	private BigDecimal jiSuan(BigDecimal a, String s, BigDecimal b) {
		BigDecimal r = BigDecimal.valueOf(0);
		//"+" --> '+'
		switch(s.charAt(0)) {
		case '+':
			r = a.add(b);
			break;
		case '-':
			r = a.subtract(b);
			break;
		case '*':
			r = a.multiply(b);
			break;
		case '/':
			r = a.divide(b);
			break;
		}
		return r;
	}
	
}
