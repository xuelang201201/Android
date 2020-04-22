package tarena.day0902;

public interface Weapon {
	
	/*public static final*/ //可省略
	/*int TYPE_COLD = 1;
	int TYPE_HEAT = 2;
	int TYPE_NUCLEAR = 3;*/
	
	/*public abstract*/ //接口中可以省略
	void kill();
	String getName();
	//int getType();
	WeaponType getType();
	
}
