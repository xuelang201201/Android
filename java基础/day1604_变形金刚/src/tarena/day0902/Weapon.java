package tarena.day0902;

public interface Weapon {
	
	/*public static final*/ //��ʡ��
	/*int TYPE_COLD = 1;
	int TYPE_HEAT = 2;
	int TYPE_NUCLEAR = 3;*/
	
	/*public abstract*/ //�ӿ��п���ʡ��
	void kill();
	String getName();
	//int getType();
	WeaponType getType();
	
}
