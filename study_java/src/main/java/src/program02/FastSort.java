package src.program02;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 谢益文 on 2017/3/10.
 */
public class FastSort {

    private static <T extends Comparable<? super T>> void quickSort(T[] targetArr,int start,int end)
    {


        if(start > end){

            for(int n=0;n<targetArr.length;n++){
                System.out.print(targetArr[n]+",");
            }
            System.out.println();
            return;
        }
        int i=start,j=end;
        T key=targetArr[start];
        while(i<j)
        {
            /*按j--方向遍历目标数组，直到比key小的值为止*/
            while(j>i&&targetArr[j].compareTo(key)>=0)
            {
                j--;
            }
            if(i<j)
            {
                /*targetArr[i]已经保存在key中，可将后面的数填入*/
                targetArr[i]=targetArr[j];
                i++;
            }
            /*按i++方向遍历目标数组，直到比key大的值为止*/
            while(i<j&&targetArr[i].compareTo(key)<=0)
            /*此处一定要小于等于零，假设数组之内有一亿个1，0交替出现的话，而key的值又恰巧是1的话，那么这个小于等于的作用就会使下面的if语句少执行一亿次。*/
            {
                i++;
            }
            if(i<j)
            {
                /*targetArr[j]已保存在targetArr[i]中，可将前面的值填入*/
                targetArr[j]=targetArr[i];
                j--;
            }
        }
        /*此时i==j*/
        targetArr[i]=key;

        /*递归调用，把key前面的完成排序*/
        quickSort(targetArr,start,i-1);


        /*递归调用，把key后面的完成排序*/
        quickSort(targetArr,j+1,end);

    }
    public static void main(String[] args){
        Integer[] integers = {1, 3, 51, 23, 412, 31, 21, 12};
        //quickSort(integers,0,integers.length-1);


        List<Integer> a1 = new ArrayList<>();
        List<Integer> a2 = new ArrayList<>();
       // a1.add(123);
        a2.add(123);
        a2.add(123);
        a2.add(123);
        a2.add(123);

        a1.retainAll(a2);

        System.out.println(a1.size()+" "+a2.size());


        String s = "{\"a\":1,\"b\":2,\"d\":1}";

        A a = JSONObject.parseObject(s, A.class);
        System.out.println(a.toString());
    }
}
class A{
    private String a ;
    private String b;
    private String c;

    @Override
    public String toString() {
        return "A{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                '}';
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}
