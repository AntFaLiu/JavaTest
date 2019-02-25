package designPatterns;

interface Person {
    void doCoding();
}

abstract class Manager implements Person {

    //装饰器增加功能
    public abstract void doCoding();

}

class Employee implements Person {

    @Override
    public void doCoding() {
        System.out.println("程序员加班写程序啊，写程序，终于写完了。。。");
    }

}

class ManagerA extends Manager {
    private Person person;//给雇员升职

    public ManagerA(Person person) {
        super();
        this.person = person;
    }

    @Override
    public void doCoding() {
        doEarlyWork();
        person.doCoding();
    }

    /**
     * 项目经理开始前期准备工作
     */
    public void doEarlyWork() {
        System.out.println("项目经理A做需求分析");
        System.out.println("项目经理A做架构设计");
        System.out.println("项目经理A做详细设计");
    }
}

class ManagerB extends Manager {
    private Person person;//给雇员升职

    public ManagerB(Person person) {
        super();
        this.person = person;
    }

    @Override
    public void doCoding() {
        person.doCoding();
        doEndWork();
    }

    /**
     * 项目经理开始项目收尾工作
     */
    public void doEndWork() {
        System.out.println("项目经理B 在做收尾工作");
    }

}

public class ManagerATest {
    public static void main(String args[]) {
        Person employee = new Employee();
        employee = new ManagerA(employee);//赋予程序猿项目经理A职责
        employee = new ManagerB(employee);//赋予程序猿项目经理B职责

        employee.doCoding();
    }
}
