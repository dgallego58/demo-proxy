package co.com.demo.proxy.componentaspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        MiClaseA a = new MiClaseA();
        a.setAttribute1("aasdas");

        //main, ainm, mian, MAIN, maIN
        Method[] losMetodosDeA = a.getClass().getMethods();
        for (Method method : losMetodosDeA) {
            if (method.getName().equals("getAttribute1")){
                String llamado = (String) method.invoke(a, null);
                System.out.println(llamado);
            }
        }

    }
}
