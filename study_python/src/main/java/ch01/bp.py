import pandas as pd
import pylab as pl

df_train = pd.read_csv('../../resources/data/Breast-Cancer/breast-cancer-train.csv')
df_test = pd.read_csv('../../resources/data/Breast-Cancer/breast-cancer-test.csv')

# feature ：'Clump Thickness' 'Cell Size'
df_test_negative = df_test.loc[df_test['Type'] == 0][['Clump Thickness','Cell Size']]
df_test_position = df_test.loc[df_test['Type'] == 1][['Clump Thickness','Cell Size']]

pl.scatter(df_test_negative['Clump Thickness'],df_test_negative['Cell Size'],c='red',marker='o',s=70)
pl.scatter(df_test_position['Clump Thickness'],df_test_position['Cell Size'],c='black',marker='x',s=70)
pl.xlabel('Clump Thickness')
pl.ylabel('Cell Size')
pl.show()


