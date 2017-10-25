# 样例来源：keras中文文档示例 用于序列分类的stateful LSTM的模型
# stateful LSTM的特点是，在处理过一个batch的训练数据后，其内部状态（记忆）会被作为下一个batch的训练数据的初始状态。状态LSTM使得我们可以在合理的计算复杂度内处理较长序列
# 在该模型中，我们将三个LSTM堆叠在一起，是该模型能够学习更高层次的时域特征表示。
# 开始的两层LSTM返回其全部输出序列，而第三层LSTM只返回其输出序列的最后一步结果，从而其时域维度降低（即将输入序列转换为单个向量）

from keras.models import Sequential
from keras.layers import LSTM, Dense
import numpy as np

data_dim = 16
timesteps = 8
num_classes = 10
batch_size = 32

# Expected input batch shape: (batch_size, timesteps, data_dim)
# Note that we have to provide the full batch_input_shape since the network is stateful.
# the sample of index i in batch k is the follow-up for the sample i in batch k-1.
model = Sequential()
# input_size=(None,8,16)
# return_sequences:returns a sequence of vectors of dimension 32
model.add(LSTM(32, return_sequences=True, stateful=True,
			   batch_input_shape=(batch_size, timesteps, data_dim)))
# input_size=(None,8,32)
model.add(LSTM(32, return_sequences=True, stateful=True))
# input_size=(None,8,32)
# return a single vector of dimension 32
model.add(LSTM(32, stateful=True))
# input_size=(None,32)  output_size=(None,10)
model.add(Dense(10, activation='softmax'))

model.compile(loss='categorical_crossentropy',
			  optimizer='rmsprop',
			  metrics=['accuracy'])

# Generate dummy training data
x_train = np.random.random((batch_size * 10, timesteps, data_dim))
y_train = np.random.random((batch_size * 10, num_classes))

# Generate dummy validation data
x_val = np.random.random((batch_size * 3, timesteps, data_dim))
y_val = np.random.random((batch_size * 3, num_classes))

model.fit(x_train, y_train,
		  batch_size=batch_size, epochs=5, shuffle=False,
		  validation_data=(x_val, y_val))