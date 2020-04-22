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
		//g(h3); //定义方法g() 只允许数字，不允许其他类型
	}

	/*
	 * <?>
	 * 某种特定类型
	 */
	private static void f(Holder<?> h) {
		Object v = h.getValue();
		System.out.println(v);
		//不能放入数据
		//h.setValue("abc");
		//只有null值能放入
		h.setValue(null);
	}
	
	/*
	 * <? extends Number>
	 * 某种特定类型，并且是Number的子类
	 */
	private static void g(Holder<? extends Number> h) {
		
		Number v = h.getValue();
		System.out.println(v);
		
		//h.setValue(3.14);
	}
}
