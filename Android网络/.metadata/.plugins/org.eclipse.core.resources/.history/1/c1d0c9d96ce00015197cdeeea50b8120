package util;
/** dao工厂  用于生产dao子类实例*/
public class DaoFactory {
	
	public static  Object  getInstance(String type){
		//通过type 读取配置文件获取实现类类名
		String className=DaoConfig.getValue(type);
		//通过反射创建对象并返回
		try {
			Class clazz=Class.forName(className);
			Object obj=clazz.newInstance();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
