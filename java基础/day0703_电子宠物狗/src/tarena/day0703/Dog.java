package tarena.day0703;

public class Dog {
	String name;
	int hungry = 50;
	int happy = 50;
	
	public void feed() {
		if(hungry == 0) {
			System.out.println(name+"�Ա��ˣ�����Ҫιʳ��");
			return;
		}
		System.out.println("������"+name+"ιʳ");
		hungry -= 10;
		System.out.println("�����ȣ�"+hungry);
	}
	public void play() {
		if(hungry == 100) {
			System.out.println(name+"��ιʳ��");
			return;
		}
		
		System.out.println("�㹷��"+name+"���");
		happy += 10;
		hungry += 10;
		System.out.println("���ֶȣ�"+happy);
		System.out.println("�����ȣ�"+hungry);
	}
	public void punish() {
		System.out.println("�򹷹�"+name+"��pp");
		happy -= 10;
		System.out.println("���ֶȣ�"+happy);
	}
}
