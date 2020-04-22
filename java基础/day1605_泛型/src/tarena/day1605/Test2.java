package tarena.day1605;

public class Test2 {
	public static void main(String[] args) {
		Holder<Integer> h1 = new Holder<>();
		Holder<Double> h2 = new Holder<>();
		Holder<String> h3 = new Holder<>();
		
		h1.setValue(9527);
		h2.setValue(3.14);
		h3.setValue("abc");
		
		f(h1);
		f(h2);
		f(h3);
		
		g(h1);
		g(h2);
		//g(h3); //���巽��g() ֻ�������֣���������������
	}

	/*
	 * <?>
	 * ĳ���ض�����
	 */
	private static void f(Holder<?> h) {
		Object v = h.getValue();
		System.out.println(v);
		//���ܷ�������
		//h.setValue("abc");
		//ֻ��nullֵ�ܷ���
		h.setValue(null);
	}
	
	/*
	 * <? extends Number>
	 * ĳ���ض����ͣ�������Number������
	 */
	private static void g(Holder<? extends Number> h) {
		
		Number v = h.getValue();
		System.out.println(v);
		
		//h.setValue(3.14);
	}
}
