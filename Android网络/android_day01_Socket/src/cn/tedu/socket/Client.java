package cn.tedu.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		// 连接服务端
		String ip = "172.16.44.131";
		try {
			// 构造方法中自动建立连接
			// 连接成功后返回socket
			Socket socket = new Socket(ip, 8888);
			// 获取输入流
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			Scanner console = new Scanner(System.in);
			while (true) {
				String line = dis.readUTF();
				System.out.println("Sever:" + line);
				if ("一路走好！".equals(line)) {
					break;
				}
				
				// 获取输出流
				String str = console.nextLine();
				dos.writeUTF(str);
				dos.flush();
			}
			
			dis.close();
			dos.close();
			console.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
