package src.program01;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by 谢益文 on 2017/2/14.
 *
 * singleThreadExecutor同一时刻只会执行一个任务，
 * 当向singleThreadExecutor提交多个任务时，它自己会维护自己的（隐藏）一个有序队列，
 * 只有当前任务执行完成（线程执行完成）才会切换到下一个任务的执行
 */
public class SingleThreadExecutor {
    public static void main(String[] args){
       /* ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i=0;i<5;i++){
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();*/

        Random random = new Random();
     // 建立一个容量为5的固定尺寸的线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // 判断可是线程池可以结束
        int waitTime = 500;
        for (int i = 0; i < 10; i++) {
            String name = "线程 " + i;
            int time = random.nextInt(1000);
            waitTime += time;
            Runnable runner = new ExecutorThread(name, time);
            System.out.println("增加: " + name + " / " + time);
            executor.execute(runner);
        }
        try {
            Thread.sleep(waitTime);
            executor.shutdown();
            executor.awaitTermination(waitTime, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ignored) {
        }
    }
}

class ExecutorThread implements Runnable {
    private final String name;
    private final int delay;

    public ExecutorThread(String name, int delay) {
        this.name = name;
        this.delay = delay;
    }

    @Override
    public void run() {

        System.out.println("启动: " + name);

        //当FixedThreadPool中有一个正在运行的线程出中断进入等待时，不会退出FixedThreadPool的线程池
        if("线程 4".equals(this.name)){
            /*try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            //因为失败而当前线程进入销毁状态，该线程会被移出线程池，新任务进入线程池运行
            throw new IndexOutOfBoundsException();
        }

        try {
            Thread.sleep(delay);
        } catch (InterruptedException ignored) {
        }
        System.out.println("完成: " + name);
    }
}
