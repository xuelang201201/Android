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
	
	public class DieDaiQi {  //�ڲ���
		int from;
		Matcher m = Pattern
				.compile("\\d+(\\.\\d+)?|[+/\\-*]")
				.matcher(f);
		
		public String next() {
			m.find(from); //��from�������һ��
			String s = m.group(); //ȡ����һ��
			from = m.end(); //from�ƶ�����һ��ĩβ
			return s;
		}
		public boolean hasNext() {
			//from�ڽ��ڣ�������һ��
			return from < f.length();
		}
	}
	
	public BigDecimal eval() { //��ֵ����
		LinkedList<BigDecimal> list1 = new LinkedList<>();
		LinkedList<String> list2 = new LinkedList<>();
		
		DieDaiQi d = /*this.*/new DieDaiQi();
		
		//������֣�
		//
		while(d.hasNext()) {
			String s = d.next();
			if(s.matches("\\d+(\\.\\d+)?")) { //s������
				list1.add(new BigDecimal(s));
			} else if(s.matches("[+\\-]")) { //s��+-
				list2.add(s);
			} else { //s��*, /
				BigDecimal a = list1.removeLast();
				BigDecimal b = new BigDecimal(d.next());
				BigDecimal c = jiSuan(a, s, b);
				//�������β��
				list1.add(c);
			}
		}
		
		///
		//�����мӼ���
		while(list2.size() != 0) {
			BigDecimal a = list1.removeFirst();
			BigDecimal b = list1.removeFirst();
			String s = list2.removeFirst();
			BigDecimal c = jiSuan(a, s, b);
			//�������ͷ��
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
