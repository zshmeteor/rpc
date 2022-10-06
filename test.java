package glimmer.zsh.rpc;

import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.Set;

@Component
class Apex{
    String name;
    String rank;
    public Apex() {};

    public Apex(String name, String rank) {
        this.name = name;
        this.rank = rank;
    };
    void rank(){
        System.out.println("You Died, pp - 1000");
    }

    Integer takeMyMoney(Integer yourMoney) {
        return yourMoney - 1000;
    }
}

public class test {
    public static  void main(String[] args){
        try {
            //键盘读入
            InputStreamReader sysin = new InputStreamReader(System.in);
            BufferedReader sysbuf = new BufferedReader(sysin);
            String str = sysbuf.readLine();

            Reflections reflections = new Reflections("glimmer.zsh.rpc" );
            Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(Component.class);
            for(Class c: classSet){
                Object obj = c.newInstance();
                Method[] methods = c.getMethods();
                int k = 0;  //标记是否找到对应方法
                for(Method m:methods){
                    if(m.getName().equals(str)){
                        k = 1;
                        int num = m.getParameterCount();
                        Class[] cls = m.getParameterTypes();
                        Object[] o = new Object[num];
                        int i;
                        for(i = 0;i < num;i++){
                            Scanner scanner = new Scanner(System.in);
                            if(cls[i] == int.class){
                                o[i] = scanner.nextInt();
                            }else if(cls[i] == String.class){
                                o[i] = scanner.next();
                            }else if(cls[i] == double.class){
                                o[i] = scanner.nextDouble();
                            }
                        }
                        m.invoke(c,o);
                    }
                }
                if(k == 0){
                    System.out.println("未找到对应方法");
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
