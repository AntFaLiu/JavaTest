package IODemo.Decorator;

import jdk.internal.instrumentation.Logger;

public class DecoratorLogger implements Logger {

    public Logger logger;

    public DecoratorLogger(Logger logger) {

        this.logger = logger;
    }

    @Override
    public void error(String str) {}

    @Override
    public void warn(String s) {

    }

    @Override
    public void info(String str) {}

    @Override
    public void debug(String s) {

    }

    @Override
    public void trace(String s) {

    }

    @Override
    public void error(String s, Throwable throwable) {

    }

    //省略其他默认实现
}
