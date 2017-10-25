### keras学习笔记—Sequential（序贯模型）
##### Activation层
    keras.layers.core.Activation(activation)
    激活层对一个层的输出施加激活函数
    预定义激活函数：
    softmax softplus softsign tanh relu sigmoid hard_sigmod linear等
    
##### Dropout层
    keras.layers.core.Dropout(p)   
    为输入数据施加Dropout，Dropout将在训练过程中每次更新参数时随机断开一定百分比(p)的输入神经元连接
    防止过拟合

##### Flatten层
    keras.layers.core.Flatten
    Flatten层用来将输入"压平"，即把多维的输入一维化，常用在从卷积层到全连接层的过渡。
    Flatten不影响batch的大小
    例如：
        model = Sequential()
        model.add(Convolution2D(64,3,3,border_mode='same',input_shape=(3,32,32)))
        # now:model.output_size=(None,64,3,3)
        model.add(Flatten())
        #now:model.output_size=(None,65536)   64*3*3 = 65536
    
##### Dense全连接层
    keras.layers.core.Dense(units,activation=None, use_bias=True, kernel_initializer='glorot_uniform',
    bias_initializer='zeros',kernel_regularizer=None, bias_regularizer=None,activity_regularizer=None, 
    kernel_constraint=None, bias_constraint=None)
    
    units:输出单元的数量，即全连接层神经元的数量，作为第一层的Dense层必须指定input_shape或input_dim
    
##### Sequential模型compile方法
    compile(self,optimizer,loss,metrics=[],sample_weight_mode=None)
    编译用来配置模型的学习过程，其参数有：
    optimizer:字符串(预定义优化器名)或优化器对象
    loss:字符串（预定义损失函数名）或目标函数
    metrics:列表，包含评价模型在训练和测试时的网络性能的指标，里面可以放入需要计算的 cost，accuracy，score 等,典型用法是metrics=['accuracy']
    
##### Sequential模型的fit方法
    fit(self,x,y,batch_size=32,epochs=10,verbose=1,callbacks=None,validation_split=0.0,validation_data=None,
    shuffle=True,class_weight=None,sample_weight=None,initial_epoch=0)
    
    verbose:日志显示，0为不在标准输出流输出日志信息，1为输出进度条记录，2为每个epoch输出一行记录
    validation_split:0～1之间的浮点数，用来指定训练及的一定比例数据作为验证集。验证集将不参与训练，并在每个epoch结束后测试模型的指标，如损失函数、准确率等
    validation_data:形式为(X,Y)的tuple，是指定的验证集。此参数将覆盖validattion_spilt
   
##### Sequential模型evaluate方法
    evaluate(self,x, y, batch_size=32, verbose=1, sample_weight=None)
    验证的方法，返回损失值和准确率
    例如：
        loss, accuracy = model.evaluate(X_test, y_test) 