package co.com.demo.proxy.componentaspect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        MiClaseA a = new MiClaseA();
        a.setAttribute1("MY Atributo como ID");

        //main, ainm, mian, MAIN, maIN
        Method[] losMetodosDeA = a.getClass().getMethods();
        for (Method method : losMetodosDeA) {
            System.out.println(method.getName());//imprimiendo metodos
            if (method.getName().equals("getAttribute1")) {
                String idSeteado = (String) method.invoke(a, null);
                System.out.println("El id seteado es" + idSeteado);
            }
        }

        Constructor constructor = MiClaseA.class.getConstructor();
        MiClaseA contructWithInstantiate = (MiClaseA) constructor.newInstance();

        for (Method method : contructWithInstantiate.getClass().getMethods()) {
            if (method.getName().equals("setAttribute1")){
                method.invoke(contructWithInstantiate, "My NEW ATTRIBUTE");
            }
            if (method.getName().equals("getAttribute1")){
                String elNuevovalor = (String) method.invoke(contructWithInstantiate, null);
                System.out.println("WithOutConstructor attribute value " + elNuevovalor);
            }
        }

    }
}
