package Thread;

//import javax.annotation.Resource;
//import java.util.Collections;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.TimeUnit;
//import java.util.logging.Logger;
//
//public class ThreadUseCase {
//    private Logger logger = LoggerFactory.getLogger(ThreadUseCase.class);
//    @Resource
//    private MonitorProjectUrlMapper groupUrlMapper;
//    @Resource
//    private MonitorDetailBatchInsertMapper monitorDetailBatchInsertMapper;
//
//    public void sendData() {
//        //调用dao查询所有url
//        MonitorProjectUrlExample example = new MonitorProjectUrlExample();
//        List<MonitorProjectUrl> list = groupUrlMapper.selectByExample(example);
//        logger.info("此次查询数据库中监控url个数为" + list.size());
//
//        //获取系统处理器个数，作为线程池数量
//        int nThreads = Runtime.getRuntime().availableProcessors();
//
//        //定义一个装载多线程返回值的集合
//        List<MonitorDetail> result = Collections.synchronizedList(new ArrayList<MonitorDetail>());
//        //创建线程池，这里定义了一个创建线程池的工具类，避免了创建多个线程池，ThreadPoolFactoryUtil可以使用单例模式设计
//        ExecutorService executorService = ThreadPoolFactoryUtil.getExecutorService(nThreads);
//        //遍历数据库取出的url
//        if (list != null && list.size() > 0) {
//            for (MonitorProjectUrl monitorProjectUrl : list) {
//                String url = monitorProjectUrl.getMonitorUrl();
//                //创建任务
//                ThreadTask threadTask = new ThreadTask(url, result);
//                //执行任务
//                executorService.execute(threadTask);
//                //注意区分shutdownNow
//                executorService.shutdown();
//                try {//等待直到所有任务完成
//                    executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            //对数据进行操作
//            saveData(result);
//        }
//    }
//}
//
//public class ThreadTask implements Runnable {
//    //这里实现runnable接口
//    private String url;
//    private List<MonitorDetail> list;
//
//    public ThreadTask(String url, List<MonitorDetail> list) {
//        this.url = url;
//        this.list = list;
//    }
//
//    //把获取的数据进行处理
//    @Override
//    public void run() {
//        MonitorDetail detail = HttpClientUtil.send(url, MonitorDetail.class);
//        list.add(detail);
//    }
//
//}

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadUseCase {

    //1、配置线程池
    private static ExecutorService es = Executors.newFixedThreadPool(20);

    //2、封装响应Feature
    class BizResult {
        public String orderId;
        public String data;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }


    //3、实现Callable接口
    class BizTask implements Callable {

        private String orderId;

        private Object data;

        //可以用其他方式
        private CountDownLatch countDownLatch;

        public BizTask(String orderId, Object data, CountDownLatch countDownLatch) {
            this.orderId = orderId;
            this.data = data;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public Object call() {
            try {
                //todo business
                System.out.println("当前线程Id = " + this.orderId);
                BizResult br = new BizResult();
                br.setOrderId(this.orderId);
                br.setData("some key about your business" + this.getClass());
                return br;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //线程结束时，将计时器减一
                countDownLatch.countDown();
            }
            return null;
        }
    }

    public List<Future> beginBusiness() throws InterruptedException {
        //模拟批量业务数据
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(String.valueOf(i));
        }
        //设置计数器
        CountDownLatch countDownLatch = new CountDownLatch(list.size());

        //接收多线程响应结果
        List<Future> resultList = new ArrayList<>();
        //begin thread
        for (int i = 0, size = list.size(); i < size; i++) {
            //todo something befor thread
            resultList.add(es.submit(new BizTask(list.get(i), null, countDownLatch)));
        }
        //wait finish
        countDownLatch.await();
        es.shutdown();
        return resultList;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadUseCase ft = new ThreadUseCase();
        List<Future> futures = ft.beginBusiness();
        System.out.println("futures.size() = " + futures.size());
        //todo some operate
        System.out.println(" ==========================end========================= ");
    }
}

//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.*;
//
///**
// * @author 10400
// * @create 2018-04-19 20:38
// */
//public class ThreadUseCase {
//
//    //1、配置线程池
//    private static ExecutorService es = Executors.newFixedThreadPool(20);
//
//    //2、封装响应Feature
//    class BizResult {
//        public String orderId;
//        public String data;
//
//        public String getOrderId() {
//            return orderId;
//        }
//
//        public void setOrderId(String orderId) {
//            this.orderId = orderId;
//        }
//
//        public String getData() {
//            return data;
//        }
//
//        public void setData(String data) {
//            this.data = data;
//        }
//    }
//
//
//    //3、实现Callable接口
//    class BizTask implements Callable {
//
//        private String orderId;
//
//        private Object data;
//
//
//        public BizTask(String orderId, Object data) {
//            this.orderId = orderId;
//            this.data = data;
//        }
//
//        @Override
//        public Object call() {
//            try {
//                //todo business
//                System.out.println("当前线程Id = " + this.orderId);
//                BizResult br = new BizResult();
//                br.setOrderId(this.orderId);
//                br.setData("some key about your business" + this.getClass());
//                Thread.sleep(3000);
//                return br;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
//
//    /**
//     * 业务逻辑入口
//     */
//    public List<Future> beginBusiness() throws InterruptedException, ExecutionException {
//        //模拟批量业务数据
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            list.add(String.valueOf(i));
//        }
//
//        //接收多线程响应结果
//        List<Future> resultList = new ArrayList<>();
//        //begin thread
//        for (int i = 0, size = list.size(); i < size; i++) {
//            //todo something befor thread
//            Future future = es.submit(new BizTask(list.get(i), null));
//            resultList.add(future);
//        }
//
//        for (Future f : resultList) {
//            f.get();
//        }
//
//        System.out.println(" =====多线程执行结束====== ");
//        es.shutdown();
//        return resultList;
//    }
//
//    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        ThreadUseCase ft = new ThreadUseCase();
//        List<Future> futures = ft.beginBusiness();
//        System.out.println("futures.size() = " + futures.size());
//        //todo some operate
//        System.out.println(" ==========================end========================= ");
//    }
//}