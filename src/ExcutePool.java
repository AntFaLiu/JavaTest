import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExcutePool {
    //一个指定大小的线程池
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

}
