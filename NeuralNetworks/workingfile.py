import tensorflow as tf
from tensorflow import keras
import numpy as np
import matplotlib.pyplot as plt


data = keras.datasets.fashion_mnist

(train_images, train_labels), (test_images, test_labels) = data.load_data()
train_images = train_images/255
class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat',
               'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankle boot']

model = keras.Sequential([
    keras.layers.Flatten(input_shape=(28, 28)),
    keras.layers.Dense(128, activation="relu"),
    keras.layers.Dense(10, activation="softmax")
])

model.compile(optimizer="adam", loss="sparse_categorical_crossentropy", metrics=["accuracy"])

model.fit(train_images, train_images, epochs=5)

test_loss, test_acc = model.evaluate(test_images, test_labels)
print(test_acc)
# print(train_images.shape)
# print(train_labels)
#
# plt.imshow(train_images[0], cmap=plt.cm.binary)
# plt.show()
