package caiYuan.io.serializaTest;


public class NewInsPer {
    public static void main(String[] args) {
        Person person = null;
        try {
//            person = (Person) Class.forName("caiYuan.io.serializaTest.Person").newInstance();
            person = Person.class.newInstance();
            person.setSex("男");
            person.setAge(26);
            person.setName("lyp");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(person);
    }
}
