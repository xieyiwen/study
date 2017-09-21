package com.wex.neuroph.Viterbi;

/**
 * Created by 谢益文 on 2017/8/2.
 * 维特比算法实现
 */
public class Viterbi {
    /**
     * @param observation
     * @param states
     * @param init_probability
     * @param transition_probability
     * @param relation_probability
     * @return 返回最可能的天气序列
     */
    public  static int[] compute(int[] observation,int[] states,double[] init_probability,double[][] transition_probability,
                                double[][] relation_probability){
        //已知序列就是一个确定的时间序列上的状态序列
        //路径概率表 V[时间][隐状态] = 概率
        double[][] V = new double[observation.length][states.length];
        //一个中间变量，代表当前状态是哪个隐状态 path[隐状态][状态]     path[天气][活动]
        int[][] path = new int[states.length][observation.length];

        // t == 0 的状态概率
        for(int state:states){
            V[0][state] = init_probability[state]*transition_probability[state][observation[0]];
            path[state][0] = state;
        }

        //时间推进
        for(int t=1;t<observation.length;t++){
            //对每一个状态求到上一个时间的最短路径
            //即计算当前t的每一个node到上一个t-1的每一个node的最大概率

            int[][] newPath = new int[states.length][observation.length];

            for(int y:states){  //当前状态

                double probability = -1;
                for(int y0:states){  //上一个时间的状态
                    double newProbability = V[t-1][y0]*transition_probability[y0][y]*relation_probability[y][observation[t]];
                    if(newProbability > probability){
                        //概率更大
                        probability = newProbability;
                        //记录最大的概率
                        V[t][y] = probability;
                        //记录路径
                        System.arraycopy(path[y0],0,newPath[y],0,t);
                        newPath[y][t] = y;
                    }
                }
            }
            //前面的时间的状态序列确定，后面的没有确定， 所以直接赋值即可
            path = newPath;
        }

        double probability = -1;
        int state = 0;
        for(int y:states){
            if(V[observation.length-1][y] > probability){
                probability = V[observation.length-1][y];
                state = y;
            }
        }

        return path[state];
    }
}
