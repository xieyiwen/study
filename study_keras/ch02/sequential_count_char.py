# 跑成功的第一个keras代码，赞
#对输入的字符串进行热编码，然后让模型学会计算a的数量
import numpy as np

data = ['aaaaXbbb','aXaaXaaaXbbb','aaaaaXbbbbb','aaaaaaaXbbbbbbb','aXaXaaaaXbbbbb']

max_len = 3;

def genData():
	d_1 = np.random.random_integers(1,10,(1000,3))
	# label
	a_1 = d_1[:,0]
	return d_1,a_1

def encode(data):
	v_date = []
	ans_data = []
	for str in data:
		count = 0
		l = list(str)
		v = {}
		for s in l:
			if 'a'.__eq__(s) :
				count += 1
			if s in v:
				v[s] += 1
			else:
				v[s] = 1

		d = []
		for a in v:
			d.append(v[a])
		v_date.append(d)
		ans_data.append(count)
	return v_date,ans_data


from keras.models import Sequential
from keras.layers import Dense, Activation
from keras import optimizers
from keras import losses

# train_data_x,train_data_y =encode(data)
train_data_x,train_data_y =genData()

model = Sequential()
model.add(Dense(output_dim=1, input_dim=3))
model.add(Activation('relu'))

sgd = optimizers.SGD(lr=0.01, clipnorm=1.)
model.compile(loss=losses.mean_squared_error, optimizer=sgd)

test = ['aaaaaXbbbbb','aaaaaaaXbbbbbbb']
test_x,test_y = encode(test)
# Train the model, iterating on the data in batches of 32 samples
model.fit(train_data_x, train_data_y, epochs=50, batch_size=32,validation_data=(test_x, test_y))

r = model.predict(test_x)

print(r)
