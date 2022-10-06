package glimmer.zsh.rpc;

import org.reflections.Reflections;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class test1 {
    public static void main(String[] args) {

        try {
            Reflections reflections = new Reflections("glimmer.zsh.rpc");
            Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(GlimmerClass.class);
            Set<SpecificClass> set = new HashSet<>();
            for (Class c : classSet) {
                Object obj = c.newInstance();
                SpecificClass specificClass = new SpecificClass();
                specificClass.classname = c.getName();
                set.add(specificClass);
                Method[] methods = c.getMethods();
                for (Method m : methods) {
                    for(Annotation a:m.getAnnotations()){
                        if(a.annotationType() == GlimmerMethod.class) {
                            System.out.println(m.getName() + "具有特定注释，它所在的类是glimmer.zsh.rpc." + c.getName());
                            specificClass.methodNames.add(m.getName());
                        }
                    }
                }
            }
            System.out.println(set);
        } catch (Exception e) {

        }
    }
}

@GlimmerClass
class demo{
    int a;
    int b;
    public demo(){
    }
    public demo(int a,int b){
        this.a = a;
        this.b = b;
    }
    @GlimmerMethod
    public int add(){
        return a+b;
    }
    @GlimmerMethod
    public void shout(){

    }
}

class SpecificClass{
    String classname;
    Set<String> methodNames = new HashSet<>();

}
