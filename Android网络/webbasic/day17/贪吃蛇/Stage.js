/* 描述一个舞台类 */
function Stage() {
	this.width = 50;
	this.height = 50;
	this.worm = new Worm();
	
	/* 打印整个舞台的内容 */
	this.print = function (ctx) {
		// 先绘制背景
		ctx.fillStyle = "#ABCDEF";
		ctx.fillRect(0, 0, this.width*10, this.height*10);
		// 绘制食物
		var food = this.worm.food;
		ctx.fillStyle="#ff0000";
		ctx.fillRect(food.x*10, food.y*10, 9, 9);
		// 在背景上再绘制蛇
		ctx.fillStyle = "#623011";
		for (i = 0; i<this.worm.nodes.length; i++) {
			var n = this.worm.nodes[i];
			ctx.fillRect(n.x*10, n.y*10, 9, 9);
		}
		// 左上角写字
		ctx.fillStyle = "#000000";
		ctx.font = "25px Verdana";
		ctx.fillText("分数：" + SCORE, 10, 30);
	};
}
