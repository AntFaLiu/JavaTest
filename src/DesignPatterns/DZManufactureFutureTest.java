package designPatterns;

//目标接口
interface ManufactureFuture {//未来汽车改用电池驱动

    public void setBattery();

    public void setWheel();

    public Car getCar();
}

class DZManufacture {//生产发动机驱动汽车的大众制造商
    protected Car car;

    public DZManufacture() {
        car = new Car();
    }

    public void setEngine() {
        System.out.println("获取德国引擎");
        System.out.println("安装德国引擎");
        car.description = car.description + "引擎：德国造 ";
    }

    public void setWheel() {
        System.out.println("获取美国轮子");
        System.out.println("安装美国轮子");
        car.description = car.description + "轮胎：美国造 ";
    }

    public Car getCar() {
        System.out.println("制造了一辆大众汽车");
        return car;
    }
}

class Car {
    public String description = "";
}

//类的适配器模式（采用组合实现）
class DZManufactureFuture implements ManufactureFuture {
    protected DZManufacture manufacturer;

    public DZManufactureFuture(DZManufacture manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public void setBattery() {
        System.out.println("获取广州电池");
        System.out.println("安装广州电池");
        Car car = manufacturer.getCar();
        car.description = car.description + "电池：中国造 ";
    }

    @Override
    public void setWheel() {
        manufacturer.setWheel();
    }

    @Override
    public Car getCar() {
        return manufacturer.getCar();
    }
}

public class DZManufactureFutureTest {
    public static void main(String[] args) throws java.lang.Exception {
        //旧的生产过程
        DZManufacture manufacture = new DZManufacture();
        manufacture.setEngine();
        manufacture.setWheel();
        Car dz = manufacture.getCar();
        System.out.println(dz.description);
        //新的生产过程
        DZManufacture oldManu = new DZManufacture();
        ManufactureFuture futureManu = new DZManufactureFuture(oldManu);
        futureManu.setBattery();
        futureManu.setWheel();
        Car car = futureManu.getCar();
        System.out.println(car.description);
    }
}
