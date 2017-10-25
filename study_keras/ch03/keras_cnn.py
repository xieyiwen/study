# 样例来源：keras中文文档示例 类似VGG的卷积神经网络
import numpy as np
import keras
from keras.models import Sequential
from keras.activations import relu
from keras.layers import Dense,Dropout,Flatten
from keras.layers import Conv2D,MaxPooling2D
from keras.optimizers import SGD

# generate train data
x_train = np.random.random((100,100,100,3))
y_train = keras.utils.to_categorical(np.random.randint(10,size=(100,1)),num_classes=10)
x_test = np.random.random((20,100,100,3))
y_test = keras.utils.to_categorical(np.random.randint(10,size=(20,1)),num_classes=10)

# build model
model = Sequential()
model.add(Conv2D(32,(3,3),activation=relu,input_shape=(100,100,3)))
