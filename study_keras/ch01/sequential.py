from keras.models import Sequential
from keras.layers import Dense, Activation
from keras import optimizers

model = Sequential()
model.add(Dense(32, input_shape=(1000,10)))
model.add(Activation('relu'))

sgd = optimizers.SGD(lr=0.01, clipnorm=1.)
model.compile(loss='mean_squared_error', optimizer=sgd)

# Generate dummy data
import numpy as np
data = np.random.random((1000, 100))
labels = np.random.randint(2, size=(1000, 1))



# Train the model, iterating on the data in batches of 32 samples
model.fit(data, labels, epochs=10, batch_size=32)

test = np.random.random((1,100))
r = model.predict(test)
print(r)
