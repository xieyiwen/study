package com.wex.neuroph.ch01;

import org.neuroph.core.Connection;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.learning.IterativeLearning;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.util.TransferFunctionType;

import java.util.Arrays;

/**
 * Created by 谢益文 on 2017/7/31.
 */
public class MlPerceptronBinOutputTest implements LearningEventListener {

    public static void main(String[] args){
        new MlPerceptronBinOutputTest().run();
    }
    public void run(){
        DataSet dataSet = new DataSet(2,1);
        dataSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
        dataSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{1}));
        dataSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{1}));
        dataSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{0}));

        NeuralNetwork myNetWork = new MlPerceptronBinOutput(TransferFunctionType.SIGMOID,2,4,1);
        LearningRule learningRule = myNetWork.getLearningRule();
        learningRule.addListener(this);
        myNetWork.learn(dataSet);
//        myNetWork.save("src/main/resources/data/xor_perception.nnet");

//        NeuralNetwork network = NeuralNetwork
//                .createFromFile("src/main/resources/data/xor_perception.nnet");
        for(DataSetRow dataSetRow:dataSet){
            myNetWork.setInput(dataSetRow.getInput());
            myNetWork.calculate();
            double[] output = myNetWork.getOutput();
            System.out.print("input:" + Arrays.toString(dataSetRow.getInput()));
            System.out.println(" output:" + Arrays.toString(output));
        }
    }

    public static void testNeuralNetwork(NeuralNetwork neuralNet, DataSet testSet) {

        for(DataSetRow testSetRow : testSet.getRows()) {
            neuralNet.setInput(testSetRow.getInput());
            neuralNet.calculate();
            double[] networkOutput = neuralNet.getOutput();

            System.out.print("Input: " + Arrays.toString( testSetRow.getInput() ) );
            System.out.println(" Output: " + Arrays.toString( networkOutput) );
        }
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
