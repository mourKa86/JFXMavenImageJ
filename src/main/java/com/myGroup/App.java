package com.myGroup;
import org.apache.log4j.BasicConfigurator;

public class App {
    public static void main(String [] args) {
        BasicConfigurator.configure();
        Launcher.main(args);

    }
}
