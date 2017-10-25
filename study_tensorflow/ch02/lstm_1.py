import pandas as pd
import matplotlib.pyplot as plt
import tensorflow as tf

import numpy as np


data = ['aaaaXbbb','aXaaXaaaXbbb']

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


train_data_x,train_data_y =encode(data)


# Plot 输入数据
fig = plt.figure()
ax = fig.add_subplot(1, 1, 1)
ax.scatter(pd.DataFrame(train_data_x)[0], train_data_y)
plt.ion()
plt.show()

# 定义数据大小
n_samples = len(data)


# 定义占位符
X = tf.placeholder(tf.float32, shape=(None, 3))
y = tf.placeholder(tf.float32, shape=(None, ))

# 定义学习的变量
W = tf.get_variable("weight", (3, 2),
					initializer=tf.random_normal_initializer())
b = tf.get_variable("bais", (1,),
					initializer=tf.constant_initializer(0.0))
y_pred = tf.matmul(X, W) + b
loss = tf.reduce_mean(tf.reduce_sum(tf.square(y - y_pred)))

# 梯度下降
# 定义优化函数
opt = tf.train.GradientDescentOptimizer(0.001)
operation = opt.minimize(loss)

with tf.Session() as sess:
	# 初始化变量
	sess.run(tf.initialize_all_variables())

	lines = None
	for i in range(50):
		_, loss_val = sess.run([operation, loss],
							   feed_dict={X: train_data_x, y: train_data_y})

		if i % 5 == 0:
			if lines:
				ax.lines.remove(lines[0])

			prediction_value = sess.run(y_pred, feed_dict={X: train_data_x})
			lines = ax.plot(pd.DataFrame(train_data_x)[0], prediction_value, 'r-', lw=5)
			plt.pause(0.1)

	plt.pause(5)
	test = ['aaaaaXbbbbb','aaaaaaaXbbbbbbb']
	test_data_x,test_data_y = encode(test)
