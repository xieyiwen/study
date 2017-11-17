# 简单的语言生成
data = open('input.txt','rw').read()
# 获取包含的字符
chars = list(set(data))
data_size,vocab_size = len(data),len(chars)
print("data has %d characters,%d unique."%(data_size,vocab_size))

# 给字符编号
char_to_ix = {ch:i for i,ch in enumerate(data)}
ix_to_char = {i:ch for i,ch in enumerate(data)}

# 模型参数
hidden_size = 100
seq_length = 25
learing_rate = 1e-1

import numpy as np

# 模型 初始权重和偏执
Wxh = np.random.randn(hidden_size,vocab_size)*0.01  # input to hidden
Whh = np.random.randn(hidden_size,hidden_size)*0.01 # hidden to hidden
Why = np.random.randn(vocab_size,hidden_size)*0.01  # hidden to output
bh = np.zeros((hidden_size,1))  # hidden bias
by = np.zeros((vocab_size,1))   #  output bais


Loss = []
Out = []

def lossFun(inputs, targets, hprev):
    xs, hs, ys, ps = {}, {}, {}, {}
    hs[-1] = np.copy(hprev)
    loss = 0
    for t in range(len(inputs)):
        xs[t] = np.zeros((vocab_size,1)) # encode in 1-of-k representation
        xs[t][inputs[t]] = 1
        hs[t] = np.tanh(np.dot(Wxh, xs[t]) + np.dot(Whh, hs[t-1]) + bh)
        ys[t] = np.dot(Why, hs[t]) + by
        ps[t] = np.exp(ys[t]) / np.sum(np.exp(ys[t]))
        loss += -np.log(ps[t][targets[t],0])
        dWxh, dWhh, dWhy = np.zeros_like(Wxh), np.zeros_like(Whh), np.zeros_like(Why)
        dbh, dby = np.zeros_like(bh), np.zeros_like(by)
        dhnext = np.zeros_like(hs[0])
        for t in reversed(range(len(inputs))):
            dy = np.copy(ps[t])
            dy[targets[t]] -= 1
        dWhy += np.dot(dy, hs[t].T)
        dby += dy
        dh = np.dot(Why.T, dy) + dhnext
        dhraw = (1 - hs[t] * hs[t]) * dh
        dbh += dhraw
        dWxh += np.dot(dhraw, xs[t].T)
        dWhh += np.dot(dhraw, hs[t-1].T)
        dhnext = np.dot(Whh.T, dhraw)
    for dparam in [dWxh, dWhh, dWhy, dbh, dby]:
        np.clip(dparam, -5, 5, out=dparam) # clip to mitigate exploding gradients
    return loss, dWxh, dWhh, dWhy, dbh, dby, hs[len(inputs)-1],ys

n = 0
mWxh, mWhh, mWhy = np.zeros_like(Wxh), np.zeros_like(Whh), np.zeros_like(Why)
mbh, mby = np.zeros_like(bh), np.zeros_like(by)
while True:
    if p+seq_length+1 > len(data) or n ==0 :
        hprev = np.zeros((hidden_size,1)) #重置模型
        p = 0  # data 重新开始
        inputs = [char_to_ix[ch] for ch in data[p:p+seq_length]]
        targets = [char_to_ix[ch] for ch in data[p+1:p+seq_length+1]]

    if n % 100 == 0:
        sample_ix = sample(hprev,inputs[0],200)
        txt=''.join(ix_to_char[ix] for ix in sample_ix)
        print( '----\n %s \n----' % (txt, ))

    loss,dWxh,dWhh,dWhy,dbh,dby,hprev,y = lossFun(inputs,targets,hprev)
    smooth_loss = smooth_loss * 0.999 + loss*0.001
    if n % 100 ==0:
        print( 'iter %d,loss: %f' % (n, smooth_loss))

    for param, dparam, mem in zip([Wxh, Whh, Why, bh, by], [dWxh, dWhh, dWhy, dbh, dby],  [mWxh, mWhh, mWhy, mbh, mby]):
        mem += dparam * dparam
        param += -learing_rate * dparam / np.sqrt(mem + 1e-8) # adagrad update
        p += seq_length # move data pointer
        n += 1 # iteration counter
        Loss.append(loss)
        Out.append(txt)

