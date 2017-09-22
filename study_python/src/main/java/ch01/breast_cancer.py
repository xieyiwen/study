'''
  <p>注释</p>
  @author xieyw
  @version ch01.py, v 0.1 2017/8/30 13:51 谢益文 Exp $
'''
import pandas as pd

df_train = pd.read_csv('../../resources/data/Breast-Cancer/breast-cancer-train.csv')
df_test = pd.read_csv('../../resources/data/Breast-Cancer/breast-cancer-test.csv')

# feature ：'Clump Thickness' 'Cell Size'
df_test_negative = df_test.loc[df_test['Type'] == 0][['Clump Thickness','Cell Size']]
df_test_position = df_test.loc[df_test['Type'] == 1][['Clump Thickness','Cell Size']]

from matplotlib import pyplot as plt
plt.scatter(df_test_negative['Clump Thickness'],df_test_negative['Cell Size'],c='red',marker='o',s=70)
plt.scatter(df_test_position['Clump Thickness'],df_test_position['Cell Size'],c='black',marker='o',s=70)

plt.xlabel('Clump Thickness')
plt.ylabel('Cell Size')
plt.show()

import numpy as np
#
# intercept = np.random.random([1])
# coef = np.random.random([2])
# lx = np.arange(0, 12)
# ly = (-intercept - lx * coef[0])/coef[1]
# plt.plot(lx,ly,c='yellow')

from sklearn.linear_model import LogisticRegression
lr = LogisticRegression()
lr.fit(df_train[['Clump Thickness','Cell Size']][:10],df_train['Type'][:10])
print("testing accuracy (10 training samples):",
      lr.score(df_test[['Clump Thickness','Cell Size']][:10],df_test['Type'][:10]))
# testing accuracy (10 training samples): 0.9

intercept = lr.intercept_
coef = lr.coef_[0,:]

lx = np.arange(0, 12)
ly = (-intercept - lx * coef[0])/coef[1]

plt.plot(lx,ly,c='green')
plt.scatter(df_test_negative['Clump Thickness'],df_test_negative['Cell Size'],c='green',marker='o',s=70)
plt.scatter(df_test_position['Clump Thickness'],df_test_position['Cell Size'],c='yellow',marker='x',s=70)
plt.xlabel('Clump Thickness')
plt.ylabel('Cell Size')
plt.show()

lr = LogisticRegression()
# 使用所有样本学习直线的系数和截距
lr.fit(df_train[['Clump Thickness','Cell Size']],df_train['Type'])
print("testing accuracy (all training samples):",lr.score(df_test[['Clump Thickness','Cell Size']],df_test['Type']))


intercept = lr.intercept_
coef = lr.coef_[0,:]

lx = np.arange(0, 12)
ly = (-intercept - lx * coef[0])/coef[1]

plt.plot(lx,ly,c='yellow')
plt.scatter(df_test_negative['Clump Thickness'],df_test_negative['Cell Size'],c='red',marker='o',s=70)
plt.scatter(df_test_position['Clump Thickness'],df_test_position['Cell Size'],c='black',marker='x',s=70)
plt.xlabel('Clump Thickness')
plt.ylabel('Cell Size')
plt.show()




