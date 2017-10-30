# 函数式模型的开始实例
# 预测一条新闻会被转发和点赞的次数。
# 主要输入：新闻本身（一段词序列），额外输入：新闻的发布日期
# 基于新闻本身的预测由辅助损失函数评估，主要的损失函数评估基于新闻和额外信息的预测情况
# 主要的输入接收新闻本身，即一个整数的序列（每个整数编码了一个词）。这些整数位于1到10000之间（即我们的字典有10，000个词）。这个序列有100个单词。

from keras.layers import Dense,Input,LSTM,Embedding
from keras.models import Model
import keras

main_input = Input(shape=(100,),dtype='int32',name='main_input')
x = Embedding(output_dim=512,input_shape=10000,input_length=100)(main_input)

# a LSTM will transform the vector sequence to single vector
lstm_out = LSTM(32)(x)

auxiliary_output = Dense(1,activation='sigmoid',name='aux_output')(lstm_out)

# 额外输入信息
auxiliary_input = Input(shape=(6,),name='auxiliary_input')
#将额外输入信息和新闻本体进行合并
keras.layers.concatenate(lstm_out,auxiliary_input)

x =Dense(64,activation='relu')(x)
x =Dense(64,activation='relu')(x)
x =Dense(64,activation='relu')(x)

main_output = Dense(1,activation='sigmoid',name='main_output')(x)

model = Model(inputs=[main_input,auxiliary_input],outputs=[main_output,auxiliary_output])

model.compile(optimizer='rmsprop',loss='binary_crossentropy',loss_weights={'main_output':1.,'aux_output':0.2})

import numpy as np
# 1表示这就话中有这个词  0表示没有
headline_data = np.random.randint(2,size=(1000,100))
additional_data = np.random.randint(low=200001,high=201711,size=(1000,1))
labels = np.random.randint(100000000,size=(1000,1))

model.fit([headline_data,additional_data],[labels,labels],epochs=5,batch_size=32)


headline_data_test = np.random.randint(2,size=(100,100))
additional_data_test = np.random.randint(low=200001,high=201711,size=(100,1))
labels_test = np.random.randint(100000000,size=(100,1))

result = model.evaluate(x=[headline_data_test,additional_data_test],y=[labels_test,labels_test])
print(result)




