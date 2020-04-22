/* worm.js 描述一条蛇 */
// 声明方向
var UP = 0;
var LEFT = 1;
var RIGHT = 2;
var DOWN = 3;

var DIR = RIGHT;

var SCORE = 0;

function Worm() {
	this.nodes = [];
	this.nodes.push(new Node(20, 20));
	this.nodes.push(new Node(20, 21));
	this.nodes.push(new Node(20, 22));
	this.nodes.push(new Node(20, 23));
	this.nodes.push(new Node(20, 24));
	this.nodes.push(new Node(21, 24));
	this.nodes.push(new Node(22, 24));
	this.nodes.push(new Node(23, 24));
	this.nodes.push(new Node(24, 24));
	this.nodes.push(new Node(24, 25));
	this.nodes.push(new Node(24, 26));
	this.nodes.push(new Node(24, 27));
	this.nodes.push(new Node(24, 28));
	
	// 声明方法contains判断x,y是否是蛇身子的一部分
	this.contains = function(x, y) {
		var nodes = this.nodes;
		for (i=0; i<nodes.length; i++) {
			var n = nodes[i];
			if (n.equals(x, y)) {
				return true; // 找到了相同的Node
			}
		}
		return false;
	};
	
	// 随机生成食物
	this.randomFood = function() {
		var x = 0;
		var y = 0;
		do {
			x = Math.floor(Math.random() * 50);
			y = Math.floor(Math.random() * 50);
		} while (this.contains(x, y));
		return new Node(x, y);
	};
	
	this.food = this.randomFood();
	
	//声明走一步的方法
	this.step = function() {
		// 获取老 头结点
		var oldHead = this.nodes[0];
		// 根据老头节点与食物的相对位置算出方向
		if (this.food.x > oldHead.x) {
			DIR = RIGHT;
		} else if (this.food.x < oldHead.x) {
			DIR = LEFT;
		} else if (this.food.y < oldHead.y) {
			DIR = UP;
		} else if (this.food.y > oldHead.y) {
			DIR = DOWN;
		}
		
		// 根据方向算出新 头结点
		var newHead;
		switch (DIR) {
			case UP:
			newHead = new Node(oldHead.x, oldHead.y - 1);
			if (newHead.y == -1) {
				newHead.y = 49;
			}
			break;
			
			case LEFT:
			newHead = new Node(oldHead.x - 1, oldHead.y);
			if (newHead.x == -1) {
				newHead.x = 49;
			}
			break;
			
			case RIGHT:
			newHead = new Node(oldHead.x + 1, oldHead.y);
			if (newHead.x == 50) {
				newHead.x = 0;
			}
			break;
			
			case DOWN:
			newHead = new Node(oldHead.x, oldHead.y + 1);
			if (newHead.y == 50) {
				newHead.y = 0;
			}
			break;
		}
		
		// 在数组的头部添加newHead
		this.nodes.unshift(newHead);
		// 删除尾结点
		// 如果没有吃到食物
		if (! this.food.equals(newHead.x, newHead.y)) {
			this.nodes.pop();
		} else {
			this.food = this.randomFood();
			SCORE += 10;
		}
	}
}
