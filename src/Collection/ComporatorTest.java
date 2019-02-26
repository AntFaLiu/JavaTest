package collection;

import java.util.*;

//public class ComporatorTest {
//
//    class Dog {
//        public int age;
//        public String name;
//
//        public Dog(int age, String name) {
//            super();
//            this.age = age;
//            this.name = name;
//        }
//
//        @Override
//        public String toString() {
//            return "Dog [age=" + age + ", name=" + name + "]";
//        }
//    }
//
//    public static void main(String[] args) {
//        List<Dog> list = new ArrayList<>();
//        list.add(new ComporatorTest().new Dog(5, "DogA"));
//        list.add(new ComporatorTest().new Dog(6, "DogB"));
//        list.add(new ComporatorTest().new Dog(7, "DogC"));
//        Collections.sort(list, new Comparator<Dog>() {
//
//            @Override
//            public int compare(Dog o1, Dog o2) {
//                return o2.age - o1.age;
//            }
//        });
//        System.out.println("给狗狗按照年龄倒序：" + list);
//        Collections.sort(list, new Comparator<Dog>() {
//
//            @Override
//            public int compare(Dog o1, Dog o2) {
//                return o1.name.compareTo(o2.name);
//            }
//        });
//        System.out.println("给狗狗按名字字母顺序排序：" + list);
//    }
//}

public class ComporatorTest {
    class Apple {
        public String color;
        public int weight;

        public Apple(String color, int weight) {
            super();
            this.color = color;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Apple [color=" + color + ", weight=" + weight + "]";
        }
    }

    /**
     *            是否为同一组的判断标准
     * @return
     */
    public static <T> List<List<T>> divider(Collection<T> datas, Comparator<? super T> c) {
        List<List<T>> result = new ArrayList<List<T>>();
        for (T t : datas) {
            boolean isSameGroup = false;
            for (int j = 0; j < result.size(); j++) {
                if (c.compare(t, result.get(j).get(0)) == 0) {
                    isSameGroup = true;
                    result.get(j).add(t);
                    break;
                }
            }
            if (!isSameGroup) {
                // 创建
                List<T> innerList = new ArrayList<T>();
                result.add(innerList);
                innerList.add(t);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();
        list.add(new ComporatorTest().new Apple("红", 205));
        list.add(new ComporatorTest().new Apple("红", 131));
        list.add(new ComporatorTest().new Apple("绿", 248));
        list.add(new ComporatorTest().new Apple("绿", 153));
        list.add(new ComporatorTest().new Apple("黄", 119));
        list.add(new ComporatorTest().new Apple("黄", 224));
        List<List<Apple>> byColors = divider(list, new Comparator<Apple>() {

            @Override
            public int compare(Apple o1, Apple o2) {
                // 按颜色分组
                return o1.color.compareTo(o2.color);
            }
        });
        System.out.println("按颜色分组" + byColors);
        List<List<Apple>> byWeight = divider(list, new Comparator<Apple>() {

            @Override
            public int compare(Apple o1, Apple o2) {
                // 按重量级

                return (o1.weight / 100 == o2.weight / 100) ? 0 : 1;
            }
        });
        System.out.println("按重量级分组" + byWeight);
    }
}

