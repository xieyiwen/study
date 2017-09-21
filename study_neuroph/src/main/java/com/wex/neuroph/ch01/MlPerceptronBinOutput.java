package com.wex.neuroph.ch01;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.transfer.Linear;
import org.neuroph.core.transfer.Step;
import org.neuroph.nnet.comp.neuron.BiasNeuron;
import org.neuroph.nnet.comp.neuron.InputNeuron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.ConnectionFactory;
import org.neuroph.util.LayerFactory;
import org.neuroph.util.NeuralNetworkFactory;
import org.neuroph.util.NeuralNetworkType;
import org.neuroph.util.NeuronProperties;
import org.neuroph.util.TransferFunctionType;
import org.neuroph.util.random.NguyenWidrowRandomizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 谢益文 on 2017/7/31.
 * 使用BP网络处理异或运算
 */
public class MlPerceptronBinOutput extends NeuralNetwork {

    public MlPerceptronBinOutput(TransferFunctionType transferFunctionType,int... neuronsLayers){
        NeuronProperties neuronProperties = new NeuronProperties();
        neuronProperties.setProperty("transferFunction",transferFunctionType);
        List<Integer> neuronsLayerVerctor = new ArrayList<Integer>();
        for(int layers:neuronsLayers){
            neuronsLayerVerctor.add(layers);
        }
        this.createNetWork(neuronsLayerVerctor,neuronProperties);
    }

    protected void createNetWork(List<Integer> neuronsLayers,NeuronProperties neuronProperties){
        //设置网络为多层
        this.setNetworkType(NeuralNetworkType.MULTI_LAYER_PERCEPTRON);

        NeuronProperties inputNeuronProperries = new NeuronProperties(InputNeuron.class, Linear.class);
        Layer layer = LayerFactory.createLayer(neuronsLayers.get(0),inputNeuronProperries);
        layer.addNeuron(new BiasNeuron());
        this.addLayer(layer);

        Layer pervLayer = layer;
        for(int layerId=1; layerId < neuronsLayers.size()-1;layerId ++){
            layer = LayerFactory.createLayer(neuronsLayers.get(layerId),neuronProperties);
            layer.addNeuron(new BiasNeuron());
            this.addLayer(layer);

            if(pervLayer != null){
                ConnectionFactory.fullConnect(pervLayer,layer);
            }

            pervLayer = layer;
        }
        //设置输出层
        NeuronProperties outputNeuronProperties = new NeuronProperties();
        outputNeuronProperties.put("transferFunction", Step.class);
        layer = LayerFactory.createLayer(neuronsLayers.get(neuronsLayers.size() -1 ),outputNeuronProperties);
        this.addLayer(layer);
        ConnectionFactory.fullConnect(pervLayer,layer);

        //必须放在下面代码的前面
        NeuralNetworkFactory.setDefaultIO(this);

        this.setLearningRule(new BackPropagation());
        this.randomizeWeights(new NguyenWidrowRandomizer(-0.7,0.7));

    }

    public static void main(String[] args){
        DataSet dataSet = new DataSet(2,1);

        dataSet.addRow(new double[]{1,1},new double[]{0});
        dataSet.addRow(new double[]{0,0},new double[]{0});
        dataSet.addRow(new double[]{0,1},new double[]{1});
        dataSet.addRow(new double[]{1,0},new double[]{1});

        /*NeuralNetwork myNetWork = new MlPerceptronBinOutput(TransferFunctionType.SIGMOID,2,2,1);
        LearningRule learningRule = myNetWork.getLearningRule();
            learningRule.addListener(this);
        myNetWork.learn(dataSet);
        myNetWork.save("src/main/resources/data/xor_perception.nnet");

        NeuralNetwork network = NeuralNetwork
                .createFromFile("src/main/resources/data/xor_perception.nnet");
        for(DataSetRow dataSetRow:dataSet){
            network.setInput(dataSetRow.getInput());
            network.calculate();
            double[] output = network.getOutput();
            System.out.print("input:" + Arrays.toString(dataSetRow.getInput()));
            System.out.print(" output:" + Arrays.toString(output));
        }*/

    }
}
