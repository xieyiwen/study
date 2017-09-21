package src.program01;

/**
 *
 * join方法为Thrad定义的方法
 * 功能：
 *      在a的线程里面调用b.join()，即a进入阻塞，等待b的完成。该状态可被中断
 * Created by 谢益文 on 2017/2/27.
 */
public class JoinThread {

    public static void main(String[] args){
        Thread producer = new Producer();
        Thread consumer = new Thread(new Consumer(producer));

        consumer.start();
        producer.start();

    }

}

//定义生产者。
class Producer extends Thread{

    @Override
    public void run() {
        System.out.println("producer is producing!please wait a moment...");
        try {
            //生产者生产
            Thread.currentThread().sleep(2000);
            System.out.println("producer is complete！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//定义消费者，等待生产者的生产完成，再进行消费
class Consumer implements Runnable{

    private Thread producer;

    public Consumer(Thread producer){
        this.producer = producer;
    }

    @Override
    public void run() {
        System.out.println("wait producer`s product,please wait a moment.....");
        try {
            //等待生产者完成产品生产
            producer.join();
            System.out.println("get producer`s product,consume!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
