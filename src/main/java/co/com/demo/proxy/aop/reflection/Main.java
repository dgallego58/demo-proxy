package co.com.demo.proxy.aop.reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        MiClaseA a = new MiClaseA().setAttribute1("MY Atributo como ID");

        Method[] losMetodosDeA = a.getClass().getMethods();
        for (Method method : losMetodosDeA) {
            if (method.getName().equals("getAttribute1")) {
                String idSeteado = (String) method.invoke(a, null);
                log.info("El id establecido es {}", idSeteado);
            }
        }

        Constructor<MiClaseA> constructor = MiClaseA.class.getConstructor();
        MiClaseA newInstance = constructor.newInstance();

        for (Method method : newInstance.getClass().getMethods()) {
            if (method.getName().equals("setAttribute1")) {
                method.invoke(newInstance, "My NEW ATTRIBUTE");
            }
            if (method.getName().equals("getAttribute1")) {
                String elNuevovalor = (String) method.invoke(newInstance, null);
                log.info("WithOutConstructor attribute value {}", elNuevovalor);
            }
        }

    }
}
