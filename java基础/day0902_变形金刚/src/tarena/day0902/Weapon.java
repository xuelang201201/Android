package tarena.day0902;

public interface Weapon {
	
	/*public static final*/ //可省略
	int TYPE_COLD = 1;//武器类型常量，方便记忆和使用
	int TYPE_HEAT = 2;
	int TYPE_NUCLEAR = 3;
	
	/*public abstract*/ //接口中可以省略
	void kill();//定义武器使用方法
	String getName();//获得武器名称
	int getType();//获得武器类型
	
}
