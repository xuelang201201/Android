package tarena.day1101.service;

import com.tarena.abs.datasource.DataService;

public class UserService {

	public boolean login(String n, String p) {
		/*
		 * 1.�ӷ������˻��ϵͳ�����û���Ϣ���� a
		 *     [[a,b,c], [*,*,*]]
		 *      -------  -------
		 *         0        1
		 * 2.�����û�������
		 *     3.���n�� a[0][i] ��Ȳ���p�� a[1][i] ���
		 *         
		 *         4.���� true
		 *         
		 * 5.���� false
		 */
		
		String[][] a = DataService.findAllUsers();
		
		for(int i=0; i<a[0].length; i++) {
			if(n.equals(a[0][i]) && p.equals(a[1][i])) {
				return true;
			}
		}
		
		return false;
		
	}
}
