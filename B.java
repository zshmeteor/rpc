package glimmer.zsh.rpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class B {
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket1 = serverSocket.accept();

            Socket socket2 = new Socket("127.0.0.1",9999);
            //键盘读入
            InputStreamReader sysin = new InputStreamReader(System.in);
            BufferedReader sysbuf = new BufferedReader(sysin);
            //Socket读入
            InputStreamReader socin = new InputStreamReader(socket1.getInputStream());
            BufferedReader socbuf = new BufferedReader(socin);
            //写出
            PrintWriter socout = new PrintWriter(socket2.getOutputStream());

            System.out.println("A:"+socbuf.readLine());
            String str = sysbuf.readLine();
            socout.println(str);
            socout.flush();
            //关闭IO和Socket
            sysbuf.close();
            socbuf.close();
            socket1.close();
            serverSocket.close();
        } catch (Exception e) {

        }
    }
}
