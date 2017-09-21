package com.wex.neuroph.Viterbi;

/**
 * Created by 谢益文 on 2017/8/2.
 * 题干：一个朋友每天根据天气{下雨，天晴}决定当天的活动{公园散步,购物,清理房间}中的一种，我每天只能在twitter上看到她发的推“啊，我前天公园散步、昨天购物、今天清理房间了！”，
 * 问题：根据她发的推特推断东京这三天的天气
 */
public class WeatherHMM {
    static enum Weather{
        Sunday,Rainy
    }
    static enum Active{
        Walk,Shop,Clean
    }
    //已知序列  walk shop clean
    private static int[] observations = new int[]{ Active.Walk.ordinal(), Active.Shop.ordinal(),
                                                   Active.Clean.ordinal()};
    //隐藏序列 Sunday Rainy
     private static int[] states = new int[]{ Weather.Sunday.ordinal(), Weather.Rainy.ordinal()};
    //初始状态概率  0.6 0.4
    private static double[] init_probability = new double[]{0.6,0.4};
    //转移状态
    private static double[][] transition_probability = new double[][]{
            //   S   R
        /*S*/  {0.6,0.4},
        /*R*/  {0.3,0.7}
    };
    //关联状态
    private static double[][] relation_probability = new double[][]{
            //     W    S    C
           /*S*/ {0.6, 0.3, 0.1},
           /*R*/ {0.1, 0.4, 0.5}
    };

    public static void main(String[] args){
        int[] result = Viterbi
                .compute(observations, states, init_probability, transition_probability,
                        relation_probability);
        for(int r:result){
            System.out.print(Weather.values()[r] + " ");
        }
        System.out.println();
    }


}
