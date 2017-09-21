package src.program01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程异常的捕获
 * Created by 谢益文 on 2017/3/6.
 */
public class ExceptionThread
{
    public static void main(String[] args){
        ExecutorService executorService= Executors.newCachedThreadPool();

        /*
        try {
            executorService.execute(new ExceptionEntity());
        }catch (RuntimeException e){
            System.out.println("捕获到线程抛出异常");
        }
*/
        /**
         *  以上try-catch无法捕获线程抛出的异常
        */


        /*
        * 以下为使用uncaughtException来追踪异常
        * */
        //method:set static domain in Thread class
        //theory:after javaSE 5.0,jdk allows each thread to bind an exception handler
        //use myUncaughtExceptionHandler as defaultUncaughtExceptionHandler
        //if the thread no proprietary uncaughtExceptionHandler,thread will use defalutUncaughtExceptionHandler to catch exception
        /*
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        executorService.execute(new ExceptionEntity());
        */

        executorService.shutdown();

    }
}
class ExceptionEntity implements Runnable{

    @Override
    public void run() {
        //throw an RuntimeException in Thread.run()
        throw new RuntimeException();
    }
}

//define an my uncaughtExceptionHandler
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught:"+e);
    }
}
