T = [[2, 3], [5, 4], [9, 6], [4, 3], [8, 1], [7, 2]]
data = sorted(T, key=lambda x: x[0])
print(data)
data = sorted(data, key=lambda x: x[1])
print(data)
