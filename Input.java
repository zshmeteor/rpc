package glimmer.zsh.rpc;

import java.lang.reflect.Method;

public class Input {
    Class c;
    Object obj;
    public Input(Class c){
        this.c = c;
        try {
            obj = c.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object method(String str){
        try {
            Method m = c.getDeclaredMethod(str);
            return m.invoke(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
