# 样例来源：keras中文文档示例 MLP的二分类
from keras.models import Sequential
from keras.layers import Dense,Dropout,Activation
from keras.optimizers import SGD
from keras import utils
from keras.losses import categorical_crossentropy

import numpy as np

x_train = np.random.random((1000,20))
y_train = utils.to_categorical(np.random.randint(10,size=(1000,1)),num_classes=10)
x_test = np.random.random((100,20))
y_test = utils.to_categorical(np.random.randint(10,size=(100,1)),num_classes=10)

model = Sequential()
# 64是输出维度  input_dim是输入的维度
model.add(Dense(64,activation='relu',input_dim=20))
# 为输入数据施加Dropout。Dropout将在训练过程中每次更新参数时随机断开一定百分比（p）的输入神经元连接
# Dropout层用于防止过拟合
model.add(Dropout(0.5))
model.add(Dense(64,activation='relu',input_dim=64))
model.add(Dense(10,activation='softmax'))

sgd = SGD(lr=0.01,decay=1e-6,momentum=0.9,nesterov=True)
model.compile(loss=categorical_crossentropy,
			  optimizer=sgd,
			  metrics=['accuracy'])

model.fit(x_train,y_train,batch_size=128,epochs=5000)

score = model.evaluate(x_test,y_test,batch_size=128)
# 第一个是损失值，第二个是准确率
print(score)