package com.neuroph.util;

import org.neuroph.core.NeuralNetwork;

/**
 * Created by 谢益文 on 2017/7/28.
 */
public class NetWorkUtil {
    /**
     * @desribe 从文件获取训练好的神经网络
     * @param filePath
     * @return
     */
    public static NeuralNetwork getNetWorkFromFile(String filePath){
        return NeuralNetwork.createFromFile(filePath);
    }
}
