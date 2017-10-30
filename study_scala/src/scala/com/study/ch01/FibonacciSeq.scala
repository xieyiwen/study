package com.study.ch01

import scala.annotation.tailrec

/**
  * <p>斐波那契  从1开始  1 1 2 3...</p>
  *
  * @author xieyw
  * @version FibonacciSeq.scala, v 0.1 2017/10/30 10:29 谢益文 Exp $
  */
object FibonacciSeq {

    def fs(n: Int): Int = {
        if (n < 0) throw new RuntimeException("输入有误");
        if (n == 0) return 0
        else if (n == 1) return 1
        @tailrec
        def run(num: Int, sum_1: Int, sum_2: Int): Int = {
            var curr_sum = 0
            var sum_a = 0
            var sum_b = 0
            if (num == 1) return sum_2 + sum_1;
            else {
                sum_a = sum_2;
                sum_b = sum_1 + sum_2;
            }
            run(num - 1, sum_a, sum_b)
        }

        return run(n-2, 1, 1)
    }

    def main(args:Array[String]): Unit ={
        println(s"result=%s", fs(7))
    }

}
