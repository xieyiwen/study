package com.neuroph.logicalOperator;

import org.neuroph.core.Connection;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.learning.IterativeLearning;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;

import java.util.Random;

/**
 * Created by 谢益文 on 2017/7/31.
 */
public class Odevity implements LearningEventListener {

    public static void main(String[] args){
        new Odevity().run();
    }
    public void run(){
        //2000训练数据
        DataSet trainingSet = new DataSet(32, 4);
        for(int i=0;i<2000;i++){
            int in=new Random().nextInt();
            trainingSet.addRow(new DataSetRow(int2double(in), int2prop(in)));
        }

        MlPerceptronBinOutput mlPerceptron = new MlPerceptronBinOutput(TransferFunctionType.SIGMOID, 32, 10, 4);
        mlPerceptron.setLearningRule(new MomentumBackpropagation());
        LearningRule learningRule = mlPerceptron.getLearningRule();
        learningRule.addListener(this);

        mlPerceptron.learn(trainingSet);

        testNeuralNetwork(mlPerceptron);
    }

    public static double[] int2double(int n){
        double[] result = new double[32];
        for(int i=0;i<32;i++){
            result[i] = (n>>i)&1;
        }
        return result;
    }
    public static String networkOutputDisplay(double[] networkOutput){
        if(((int)networkOutput[3])==1)return "正偶数";
        if(((int)networkOutput[2])==1)return "负偶数";
        if(((int)networkOutput[1])==1)return "正奇数";
        if(((int)networkOutput[0])==1)return "负奇数";
        return "未知";
    }

    public static String correctClassify(int i){
        if(i>0 && i%2==0){
            return "正偶数";
        }else if(i<0 && i%2==0){
            return "负偶数";
        }else if(i>0 && i%2!=0){
            return "正奇数";
        }else if(i<0 && i%2!=0){
            return "负奇数";
        }
        return "0";
    }

    /**
     * 0001  正偶数
     * 0010  负偶数
     * 0100  正奇数
     * 1000  负奇数
     * @param i
     * @return
     */
    public static double[] int2prop(int i){
        double[] pe={0d,0d,0d,1d};
        double[] ne={0d,0d,1d,0d};
        double[] po={0d,1d,0d,0d};
        double[] no={1d,0d,0d,0d};
        if(i>0 && i%2==0){
            return pe;
        }else if(i<0 && i%2==0){
            return ne;
        }else if(i>0 && i%2!=0){
            return po;
        }else if(i<0 && i%2!=0){
            return no;
        }
        return pe;
    }

    public static void testNeuralNetwork(NeuralNetwork neuralNet) {
        int badcount=0;
        int COUNT=50000;
        for(int i=0;i<COUNT;i++){
            int in=new Random().nextInt();
            double[] inputnumber=int2double(in);
            neuralNet.setInput(inputnumber);
            neuralNet.calculate();
            double[] networkOutput = neuralNet.getOutput();

//            System.out.print("Input: " + in);
            String networkOutputDisplay=networkOutputDisplay(networkOutput);
//            System.out.println(" Output: " + Arrays.toString( networkOutput) + networkOutputDisplay );
            String cc=correctClassify(in);
            if(!cc.equals(networkOutputDisplay)){
                badcount++;
                System.out.print("判别错误:"+in);
                System.out.print(" correctClassify="+cc);
                System.out.println(" networkOutputDisplay="+networkOutputDisplay);
            }
        }
        System.out.println("正确率："+(COUNT-badcount*1.0)/COUNT*100.0+"%");
    }

    public void handleLearningEvent(LearningEvent learningEvent) {
        System.out.println("============");
        System.out.println(learningEvent.getClass().toString());
        IterativeLearning bp = (IterativeLearning)learningEvent.getSource();
        System.out.println("iterate:"+bp.getCurrentIteration());
        Neuron neuron=(Neuron)bp.getNeuralNetwork().getOutputNeurons().get(0);

        for(Connection conn:neuron.getInputConnections()){
            System.out.println(conn.getWeight().value);
        }
    }
}
