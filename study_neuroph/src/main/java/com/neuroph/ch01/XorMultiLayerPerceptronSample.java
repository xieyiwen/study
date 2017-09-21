package com.neuroph.ch01;

import org.neuroph.core.Connection;
import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.Weight;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.comp.neuron.BiasNeuron;
import org.neuroph.nnet.comp.neuron.InputNeuron;
import org.neuroph.util.ConnectionFactory;
import org.neuroph.util.LayerFactory;
import org.neuroph.util.NeuralNetworkFactory;
import org.neuroph.util.NeuralNetworkType;
import org.neuroph.util.NeuronProperties;
import org.neuroph.util.TransferFunctionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 谢益文 on 2017/7/28.
 * 异或操作
 * 该算法中，输出层的计算提前计算出了公式，即权重和偏执  所以不需要训练
 * */
public class XorMultiLayerPerceptronSample extends NeuralNetwork {

    public XorMultiLayerPerceptronSample(TransferFunctionType transferFunctionType,
                                         int... neuronsLayers) {
        NeuronProperties neuronProperties = new NeuronProperties();
        neuronProperties.setProperty("transferFunction",transferFunctionType);
        List<Integer> neuronsInLayerVector = new ArrayList<Integer>();
        for(int n:neuronsLayers){
            neuronsInLayerVector.add(n);
        }
        this.createNetWork(neuronsInLayerVector,neuronProperties);

    }

    protected void createNetWork(List<Integer> neuronsInLayers, NeuronProperties neuronProperties){
        //设置神经网络类型
        this.setNetworkType(NeuralNetworkType.MULTI_LAYER_PERCEPTRON);

        NeuronProperties inputNeuronProperties = new NeuronProperties(InputNeuron.class);
        Layer layer = LayerFactory.createLayer(neuronsInLayers.get(0),inputNeuronProperties);
        layer.addNeuron(new BiasNeuron());
        this.addLayer(layer);

        Layer pervLayer = layer;
        for(int layerIds=1;layerIds<neuronsInLayers.size()-1;layerIds++){
            layer = LayerFactory.createLayer(neuronsInLayers.get(layerIds),neuronProperties);
            layer.addNeuron(new BiasNeuron());
            this.addLayer(layer);
            if(pervLayer != null){
                ConnectionFactory.fullConnect(pervLayer,layer);
            }
            pervLayer = layer;
        }

        //设置权重
        Neuron n1 = layer.getNeuronAt(0);
        List<Connection> c1 = n1.getInputConnections();
        c1.get(0).setWeight(new Weight(2));
        c1.get(1).setWeight(new Weight(2));
        c1.get(2).setWeight(new Weight(-1));

        Neuron n2 = layer.getNeuronAt(1);
        List<Connection> c2 = n2.getInputConnections();
        c2.get(0).setWeight(new Weight(-2));
        c2.get(1).setWeight(new Weight(-2));
        c2.get(2).setWeight(new Weight(3));

        //设置输出层
        NeuronProperties outNeuronProperties = new NeuronProperties();
        outNeuronProperties.put("inputFunction", org.neuroph.core.input.And.class);
        layer = LayerFactory.createLayer(neuronsInLayers.get(neuronsInLayers.size()-1),outNeuronProperties);
        this.addLayer(layer);
        ConnectionFactory.fullConnect(pervLayer,layer);

        NeuralNetworkFactory.setDefaultIO(this);
    }

    public static void main(String[] args){
        DataSet data = new DataSet(2,1);

        data.addRow(new double[]{0,0},new double[]{Double.NaN});
        data.addRow(new double[]{1,0},new double[]{Double.NaN});
        data.addRow(new double[]{0,1},new double[]{Double.NaN});
        data.addRow(new double[]{1,1},new double[]{Double.NaN});

        XorMultiLayerPerceptronSample myPerceptron = new XorMultiLayerPerceptronSample(
                TransferFunctionType.STEP, 2,2,1);

        for(DataSetRow testSetRow : data.getRows()) {
            myPerceptron.setInput(testSetRow.getInput());
            myPerceptron.calculate();
            double[] networkOutput = myPerceptron.getOutput();

            System.out.print("Input: " + Arrays.toString( testSetRow.getInput() ) );
            System.out.println(" Output: " + Arrays.toString( networkOutput) );
        }
    }


}
