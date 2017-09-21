package com.wex.neuroph.ch01;

import com.wex.neuroph.util.NetWorkUtil;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.Perceptron;

/**
 * Created by 谢益文 on 2017/7/28.
 */
public class AndPerceptronSample {

    /**
     * 训练and操作的神经网络
     */
    private static void trainAndNetWork(){
        NeuralNetwork network = new Perceptron(2, 1);
        DataSet data = new DataSet(2,1);
        data.addRow(new double[]{1,1},new double[]{1});
        data.addRow(new double[]{1,0},new double[]{0});
        data.addRow(new double[]{0,0},new double[]{0});
        data.addRow(new double[]{0,1},new double[]{0});

        network.learn(data);

        network.save("src/main/resources/data/and_perception.nnet");
    }


    public static void main(String[] args){
        NeuralNetwork network = NetWorkUtil
                .getNetWorkFromFile("src/main/resources/data/and_perception.nnet");
        network.setInput(1,1);
        network.calculate();
        double[] output = network.getOutput();
        System.out.println(output[0]);  //输出只有一个
    }
}
