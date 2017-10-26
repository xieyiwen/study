package src.program01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by 谢益文 on 2017/2/15.
 */
public class ThreadStudy2 {

    public static void main(String[] args){

        SleepThread sleepThread = new SleepThread();
       /*  for(int i=1;i<11;i++)
            new Thread(sleepThread,"thread-"+i).start();*/

       /*
        //sleep试例
        ExecutorService service = Executors.newFixedThreadPool(5);
        for(int i=1;i<11;i++)
           service.execute(sleepThread); //每次启用的sleepThread为新的线程，相当于new Thread(sleepThread)
        */

       //设置所有子线程为后台线程，main线程运行结束后，所有后台线程终止
       /* for(int i=1;i<11;i++){
            Thread thread = new Thread(sleepThread,"thread-"+i);
            if(i != 5)
                thread.setDaemon(true);
            thread.start();
        }*/

       //测试finally承诺机制
       /* try {

            System.exit(0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("finally");
        }
*/
        /*
        //SumEntity 示例
        ExecutorService service = Executors.newCachedThreadPool();

        for(int i=1;i<10;i++){
            Future submit = service.submit(new SumEntity(i));
            try {
                System.out.println(submit.get(1000, TimeUnit.MILLISECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

        service.shutdown();*/


        List<T1> arrayList = new ArrayList<>();
        List<T1> list2 = new ArrayList<>();
        for(int i=1;i<100;i++){
            T1 t = new T1();
            t.setNumber(i);
            arrayList.add(t);
        }


        arrayList.get(0).setLeft(arrayList.get(arrayList.size()-2));
        arrayList.get(0).setRight(arrayList.get(arrayList.size()-1));
        list2.add(arrayList.get(0));
        arrayList.remove(arrayList.size()-1);
        arrayList.remove(arrayList.size()-1);
        arrayList.remove(0);

        int jump_count = 0;
        boolean jump = false;

        while(true){
            T1 t1 = list2.get(list2.size() - 1);
            T1 right = t1.getRight();

            if(arrayList.size() == 1){
                right.setLeft(t1);
                right.setRight(list2.get(0).getLeft());
                break;
            }

            right.setLeft(t1);

           /* if(jump_count == (arrayList.size()-1)/2){
                right.setRight(arrayList.get(jump_count));
                arrayList.remove(jump_count);
            }else{
                right.setRight(arrayList.get(jump_count));
                arrayList.remove(jump_count);
            }*/

            right.setRight(arrayList.get(jump_count));
            arrayList.remove(jump_count);
            if(jump){
                jump_count ++;
            }

            list2.add(right);

            System.out.print(right.getNumber()+",");

            T1 t2 = list2.get(list2.size() - 1);
            T1 right2 = t2.getRight();
            right2.setLeft(t2);

            right.setRight(arrayList.get(arrayList.size()-jump_count));
            arrayList.remove(arrayList.get(arrayList.size()-jump_count));
            if(jump){
                jump_count ++;
            }

            list2.add(right2);
            System.out.print(right2.getNumber()+",");
            jump = !jump;
        }

  //      System.out.println(list2.size()+" "+list2.get(list2.size()-1).getNumber());
/*
        for(int i=0;i<50;i++){
            for(int j=0;j<26-i;i++){
                System.out.print(" ");
            }
            System.out.print(list2.get((list2.size()-1)-i).getNumber()+"");
            for(int k=0;k<i;k++){
                if(k +1 = i){
                    System.out.print(list2.get(i).getNumber()+"");
                }
                System.out.print(" ");
            }
        }*/
    }

}

class T1{
    int number;
    T1 left;
    T1 right;

    public T1(){}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public T1 getLeft() {
        return left;
    }

    public void setLeft(T1 left) {
        this.left = left;
    }

    public T1 getRight() {
        return right;
    }

    public void setRight(T1 right) {
        this.right = right;
    }
}

class SleepThread implements Runnable{


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" start");
        try {
            long sleepTime = new Random().nextInt(10);

            TimeUnit.MILLISECONDS.sleep(sleepTime);
            System.out.println(Thread.currentThread().getName()+" sleep "+sleepTime +"s!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 斐波那契数列 求和
 */
class SumEntity implements Callable{

    private int number;

    public SumEntity(int number){
        this.number = number;
    }

    @Override
    public Object call() throws Exception {
        if(number == 0){
            return 0;
        }
        if(number == 1){
            return 1;
        }
        if(number == 2){
            return 1;
        }

        int x = 1; //n-2
        int y = 1; //n-1
        int sum = 0;

        for(int i = 3;i<=this.number;i++){
             sum = x+y;
             x = y ;
             y = sum;
        }
        return sum;
    }
}
