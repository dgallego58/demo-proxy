package co.com.demo.proxy.componentaspect;


public class MiClaseA {

    private String attribute1;

    public MiClaseA() {
        //defined constructors
    }

    public String getAttribute1() {
        return attribute1;
    }

    public MiClaseA setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
        return this;
    }
}
