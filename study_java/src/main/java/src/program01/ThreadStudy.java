package src.program01;

import java.util.concurrent.*;

/**
 * Created by 谢益文 on 2017/2/13.
 */
public class ThreadStudy {

    public static void main(String[] args){
   /*     LiftOff liftOff = new LiftOff();
        liftOff.run();*/

        /*Thread1 thread1 = new Thread1();
        for(int i=0;i<5;i++){
            new Thread(thread1,"thread-"+i).start();
        }*/

        ExecutorService executorService = Executors.newCachedThreadPool();
        /*for(int i =0;i<5;i++){
                executorService.execute(new LiftOff());
        }
        executorService.shutdown();*/

        Future<?> submit = executorService.submit(new CallableStudy());
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
class CallableStudy implements Callable{
    @Override
    public Object call() throws Exception {
        return Thread.currentThread().getName();
    }
}
class Thread1 implements Runnable{
    @Override
    public void run() {
        int i = 3;
        while(i-- > 0){
            System.out.println(Thread.currentThread().getName()+":"+i);
            Thread.currentThread().yield();
        }
        System.out.println(Thread.currentThread().getName()+" finish!");
    }
}

class LiftOff implements Runnable{
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff(){}
    public LiftOff(int countDown){
        this.countDown = countDown;
    }

    public String status(){
        return "#"+id+"("+(countDown > 0 ? countDown:"LiftOff!")+")";
    }

    @Override
    public void run() {
        while (countDown -- > 0){
            System.out.print(status());
         //   Thread.yield();
        }
    }
}
