package src.program01;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * IntGenerator produce a number,judge the number`s parity
 * Created by 谢益文 on 2017/3/7.
 */
public class SynchronizedEvenGenerator extends AbstractIntGenerator{
    private int currentCount = 0;
    @Override
    public synchronized int next() {
        currentCount ++;
        Thread.yield();
    //    currentCount++;
        return currentCount;
    }


    public static void main(String[] args){
       // EventChecker.test(new SynchronizedEvenGenerator(),100);
//        MethodClass methodClass = new MethodClass();

        String param = "{\"userID\":1}";
        System.out.println(JSONObject.parseObject(param));
    }
}

class MethodClass{
    public synchronized void method1(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void method2(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void method3(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * build an abstract class,define some common behavior
 */
abstract class AbstractIntGenerator{
    private volatile boolean canceled = false;

    public abstract int next();  //product a next number
    public void cancel(){this.canceled = true;};
    public boolean isCanceled(){return this.canceled;};
}

//When generator produce a number,judge the number`s parity
class EventChecker implements Runnable{

    private AbstractIntGenerator generator;
    private final int id;

    public EventChecker(AbstractIntGenerator generator,int id){
        this.generator = generator;
        this.id = id;
    }
    @Override
    public void run() {
        while(! generator.isCanceled()){
            int val = generator.next();
            if(val % 2 != 0){
                System.out.println(val +" not even number!");
            }
            generator.cancel();  //cancels all evenChecker
        }
    }

    //judge parity from 0 to count
    public static void test(AbstractIntGenerator generator,int count){
        ExecutorService ex = Executors.newCachedThreadPool();
        for(int i=1;i<=count;i++){
            ex.execute(new EventChecker(generator,i));
        }
        ex.shutdown();
    }

    //default value for count
    public static void test(AbstractIntGenerator generator){
        test(generator,10);
    }
}


