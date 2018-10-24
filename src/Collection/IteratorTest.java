package Collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//使用三种方法进行遍历list
public class IteratorTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("HAHAHAHA");
        //第一种遍历方法使用foreach遍历List
//        for (String str : list) {            //也可以改写for(int i=0;i<list.size();i++)这种形式
//            System.out.println(str);
//        }
//
//        //第二种遍历，把链表变为数组相关的内容进行遍历
//        String[] strArray = new String[list.size()];
//        list.toArray(strArray);
//        for (int i = 0; i < strArray.length; i++) //这里也可以改写为  foreach(String str:strArray)这种形式
//        {
//            System.out.println(strArray[i]);
//        }
//
//        //第三种遍历 使用迭代器进行相关遍历
//
        Iterator<String> ite = list.iterator();
//        while (ite.hasNext())//判断下一个元素之后有值
//        {
//            System.out.println(ite.next());
//        }
        ite.forEachRemaining(ele->System.out.println(ele));
    }
}

//使用4种方法遍历Map
/*总结
      1）map的key采用简单形式和复杂形式时，查找的效率是不同的，简单的key值效率更高
      2）当数据量大的时候，采用entrySet遍历key+value的效率要高于keySet
      3）当我们只需要取得value值时，采用values来遍历效率更高
* */
//public class IteratorTest {
//    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        map.put("1", "value1");
//        map.put("2", "value2");
//        map.put("3", "value3");
//
//        //第一种：普遍使用，二次取值
//        System.out.println("通过Map.keySet遍历key和value：");
//        for (String key : map.keySet()) {
//            System.out.println("key= "+ key + " and value= " + map.get(key));
//        }
//
//        //第二种
//        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
//        java.util.Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, String> entry = it.next();
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//        }
//
//        //第三种：推荐，尤其是容量大时
//        System.out.println("通过Map.entrySet遍历key和value");
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//        }
//
//        //第四种 当我们只需要取得value值时，采用values来遍历效率更高
//        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
//        for (String v : map.values()) {
//            System.out.println("value= " + v);
//        }
//    }
//}
