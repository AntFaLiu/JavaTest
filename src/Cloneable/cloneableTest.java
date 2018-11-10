package Cloneable;

class Student{
    private String name;   //姓名
    private int age;       //年龄
    private StringBuffer sex;  //性别
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public StringBuffer getSex() {
        return sex;
    }
    public void setSex(StringBuffer sex) {
        this.sex = sex;
    }
    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", sex=" + sex + "]";
    }
}

class School implements Cloneable{
    private String schoolName;   //学校名称
    private int stuNums;         //学校人数
    private Student stu;         //一个学生
    public String getSchoolName() {
        return schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    public int getStuNums() {
        return stuNums;
    }
    public void setStuNums(int stuNums) {
        this.stuNums = stuNums;
    }
    public Student getStu() {
        return stu;
    }
    public void setStu(Student stu) {
        this.stu = stu;
    }
    @Override
    protected School clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return (School)super.clone();
    }
    @Override
    public String toString() {
        return "School [schoolName=" + schoolName + ", stuNums=" + stuNums + ", stu=" + stu + "]";
    }
}

public class cloneableTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        School s1 = new School();
        s1.setSchoolName("实验小学");
        s1.setStuNums(100);
        Student stu1 = new Student();
        stu1.setAge(20);
        stu1.setName("zhangsan");
        stu1.setSex(new StringBuffer("男"));
        s1.setStu(stu1);
        System.out.println("s1: "+s1+" s1的hashcode:"+s1.hashCode()+"  s1中stu1的hashcode:"+s1.getStu().hashCode());
        School s2 = s1.clone();  //调用重写的clone方法，clone出一个新的school---s2
        System.out.println("s2: "+s2+" s2的hashcode:"+s2.hashCode()+" s2中stu1的hashcode:"+s2.getStu().hashCode());
//        可以看出s1与s2的hashcode不同，也就是说clone方法并不是把s1的引用赋予s2，而是在堆中重新开辟了一块空间，将s1复制过去，将新的地址返回给s2。   
//        但是s1中stu的hashcode与s2中stu的hashcode相同，也就是这两个指向了同一个对象，修改s2中的stu会造成s1中stu数据的改变。但是修改s2中的基本数据类型与Stirng类型时，不会造成s1中数据的改变，基本数据类型例如int，在clone的时候会重新开辟一个四个字节的大小的空间，将其赋值。而String则由于String变量的唯一性，如果在s2中改变了String类型的值，则会生成一个新的String对象，对之前的没有影响。  这就是浅度克隆。

    }
}
