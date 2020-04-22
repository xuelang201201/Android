package tarena.day0902;

public class Transformer {
	/*
	 * ���ν��ʹ��һ�������ӿڣ�
	 * �����ӿ���Ϊ��Ա����װ�ڱ��ν����
	 */
	private Weapon w;//Ĭ��ֵ��null
	
	public void setWeapon(Weapon w) {
		this.w = w;
	}
	
	public void attack() {
		if(w == null) {
			System.out.println("����ҧ");
			return;
		} 
		
		String type = "";
		switch(w.getType()) {
		case COLD:
			type = "�����";
			break;
		case HEAT:
			type = "�ȱ���";
			break;
		case NUCLEAR:
			type = "������";
			break;
		}
		
		System.out.println("ʹ��" + type + w.getName() + "����");
		w.kill();
	}
}
