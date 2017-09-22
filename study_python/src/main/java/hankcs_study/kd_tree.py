
T = [[2, 3], [5, 4], [9, 6], [4, 7], [8, 1], [7, 2]]

x = ['9']
y = ['4']
for i in range(len(T)):
	x.append(T[i][0])
	y.append(T[i][1])

import pylab as pl
pl.figure()
pl.plot(x,y)
pl.show()


class node:
	def __init__(self,point):
		self.left = None
		self.right = None
		self.point = point
		self.parent = None
		pass
	def set_left(self, left):
		if left == None: pass
		left.parent = self
		self.left = left

	def set_right(self, right):
		if right == None: pass
		right.parent = self
		self.right = right

def median(lst):
	m = (int) (len(lst) / 2)
	return lst[m],m

def build_kd_tree(data,d):
	data = sorted(data, key=lambda x: x[d])  #按照第d个数排序
	point,m = median(data)
	tree = node(point)

	del data[m]

	if m > 0 :
		tree.set_left (build_kd_tree(data[:m],not d))  #第一位已经排好序了，排第二位的序
	if len(data) > 1:
		tree.set_right ( build_kd_tree(data[m:],not d))
	return tree

kd_tree = build_kd_tree(T,0)

def distance(a,b):
# 	((x1-x2)^2+(y1-y2)^2)^(1/2)
	return ((a[0]-b[0])**2+(a[1]-b[1])**2)**(1/2)

# 根据横坐标找到跟目标点最近的点
# 然后计算kd-tree附近的和目标点的距离做判断，找出最近的点
def search_kd_tree(tree,d,target):
	if target[d] < tree.point[d]:
		if tree.left != None:
			return search_kd_tree(tree.left,not d,target)
	else:
		if tree.right != None:
			return search_kd_tree(tree.right, not d, target)
	def update_best(t, best):
		if t == None: return
		t = t.point
		d = distance(t, target)
		if d < best[1]:
			best[1] = d
			best[0] = t

	#上面的代码找到了目标点
	#下面计算最邻近的距离
	best = [tree.point, 100000.0]
	while (tree.parent != None):
		update_best(tree.parent.left, best)
		update_best(tree.parent.right, best)
		tree = tree.parent
	return best

print (search_kd_tree(kd_tree, 0, [9, 4]))