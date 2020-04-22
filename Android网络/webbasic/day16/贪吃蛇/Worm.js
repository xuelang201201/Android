/* worm.js 描述一条蛇 */
// 声明方向
var UP = 0;
var LEFT = 1;
var RIGHT = 2;
var DOWN = 3;

var DIR = RIGHT;

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
	
	//声明走一步的方法
	this.step = function() {
		// 获取老 头结点
		var oldHead = this.nodes[0];
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
		this.nodes.pop();
	}
}
