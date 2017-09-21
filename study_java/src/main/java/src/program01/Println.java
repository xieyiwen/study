package src.program01;

import java.io.PrintStream;

/**
 * Created by 谢益文 on 2017/2/10.
 */
public class Println {

    public static void main(String[] args){
        /*int a = 10;
        int b = 10;
        method1(a,b);
        // 调用method(a,b)方法，使控制台只打印a=100;b=200
        System.out.println("a="+a);
        System.out.println("b="+b);*/


    }

    static void method1(int a,int b){
        //使用exit
        System.out.println("a=100");
        System.out.println("b=200");
        System.exit(0);
    }

    /**
     * 重写PrintStream的println方法
     * @param a
     * @param b
     */
    static void method2(int a,int b){
        PrintStream myStream = new PrintStream(System.out){
            @Override
            public void println(String x) {
                if("a=10".equals(x))
                    super.println("a=100");
                else if ("b=10".equals(x))
                    super.println("b=200");
            }
        };

        System.setOut(myStream);
    }

    static void method3(Integer a,Integer b){
        //修改Integer的静态去，未实现
    }
}
