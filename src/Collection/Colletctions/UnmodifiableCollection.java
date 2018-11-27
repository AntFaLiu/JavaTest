package Collection.Colletctions;

import java.util.*;

public class UnmodifiableCollection {
    public static void main(String[] args) {
        List lt = Collections.emptyList();
        Set st = Collections.singleton("avs");

        Map mp = new HashMap();
        mp.put("a", 100);
        mp.put("b", 200);
        mp.put("c", 150);
        Map readOnlyMap = Collections.unmodifiableMap(mp);

        //下面会报错
//        lt.add(100);
//        st.add("sdf");
        readOnlyMap.put("e",655);


        //这句不会报错
        mp.put("d", 300);
    }
}
