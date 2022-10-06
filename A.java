package glimmer.zsh.rpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class A {
    public static void main(String[] args){
        try{
            Socket socket1 = new Socket("127.0.0.1",8888);

            ServerSocket serverSocket = new ServerSocket(9999);
            Socket socket2 = serverSocket.accept();
            //键盘读入
            InputStreamReader sysin = new InputStreamReader(System.in);
            BufferedReader sysbuf = new BufferedReader(sysin);
            //Socket读入
            InputStreamReader socin = new InputStreamReader(socket2.getInputStream());
            BufferedReader socbuf = new BufferedReader(socin);
            //写出
            PrintWriter socout = new PrintWriter(socket1.getOutputStream());

            String str = sysbuf.readLine();
            socout.println(str);
            socout.flush();

            System.out.println("B:"+socbuf.readLine());

            //关闭IO和Socket
            sysbuf.close();
            socbuf.close();
            socket1.close();

        }catch(Exception e){

        }
    }
}
