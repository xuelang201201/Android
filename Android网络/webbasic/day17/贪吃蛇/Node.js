/* Node.js 描述一个节点 */
function Node(x, y) {
	this.x = x;
	this.y = y;
	// 判断两个点是否描述的同一个点
	this.equals = function (i, j) {
		return this.x == i && this.y == j;
	}
}
