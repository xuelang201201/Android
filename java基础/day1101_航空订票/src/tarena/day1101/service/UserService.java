package tarena.day1101.service;

import com.tarena.abs.datasource.DataService;

public class UserService {

	public boolean login(String n, String p) {
		/*
		 * 1.从服务器端获得系统所有用户信息赋给 a
		 *     [[a,b,c], [*,*,*]]
		 *      -------  -------
		 *         0        1
		 * 2.遍历用户名数组
		 *     3.如果n与 a[0][i] 相等并且p与 a[1][i] 相等
		 *         
		 *         4.返回 true
		 *         
		 * 5.返回 false
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
