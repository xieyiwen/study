import numpy as np
import pylab as pl

# http://blog.csdn.net/ywjun0919/article/details/8692018
# 折线图
x = [1, 2, 3, 4, 5]# Make an array of x values
y = [1, 4, 9, 16, 25]# Make an array of y values for each x value
# pl.plot(x, y,'r')# use pylab to plot x and y
# pl.show()# show the plot on the screen

# 散点图
# pl.plot(x, y,'o')  # change the mode "o"
# pl.plot(x, y,'ro')  #set red for  line or dot's color
# pl.show()

# 柱状图
data = np.random.normal(5.0, 3.0, 1000)
# pl.hist(data)

# pl.hist(data, histtype='stepfilled')
# pl.xlabel('data')
# bins = np.arange(-5., 16., 1.) #浮点数版本的range
# pl.hist(data, bins, histtype='stepfilled')
# pl.show()


# 一张图板画多个图
f1 = pl.figure(1)
pl.subplot(221)  # 把画板分为两行两列，该画本位于第一个区域，即第一行第一列
pl.subplot(222) # 把画板分为两行两列，该画本位于第二个区域，即第一行第二列
pl.subplot(212) # 把画板分为两行一列，该画本位于第二个区域，即第二行
pl.show()