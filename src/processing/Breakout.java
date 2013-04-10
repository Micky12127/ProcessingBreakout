package processing;

import java.util.ArrayList;

import processing.collision.CollisionDecider;
import processing.collision.CollisionEvent;
import processing.collision.CollisionListener;
import processing.core.*;
import processing.shape.Bar;
import processing.shape.Block;
import processing.shape.Circle;
import processing.shape.MoveShape;
import processing.shape.OperateShape;

public class Breakout extends PApplet implements CollisionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<OperateShape> shapes = new ArrayList<OperateShape>();
	private CollisionDecider collisionDecider;
	
	// ブロックが描画されるX座標とY座標、バーとボールの初期位置のX座標とY座標
	private float firstPlaceX, firstPlaceY;
	
	public void setup() {
		size(1200, 700);
		smooth();
		
		collisionDecider = new CollisionDecider(this);
		collisionDecider.addListener(this);
		
		firstPlaceX = 600;
		firstPlaceY = 680;
		
		createBall();
		createBarAndBlock();
		
		for (OperateShape shape : shapes) {
			collisionDecider.add(shape);
		}
	}
	
	public void draw() {
		clearShape();
		
		collisionDecider.checkCollision();
		
		for (OperateShape shape : shapes) {
			shape.move();
		}
	}
	
	public void clearShape() {
		stroke(255, 255, 255);
		fill(255, 255, 255);
		rect(0, 0, width, height);
	}
	
	private void createBarAndBlock() {
		// バーの横幅と縦幅
		float barWidth;
		barWidth = 100;
		float barHeight;
		barHeight = 10;
		
		// ブロックの横幅と縦幅
		float blockWidth;
		blockWidth = 60;
		float blockHeight;
		blockHeight = 30;
		
		// ブロックの描画位置のX座標とY座標
		float blockXPoint;
		blockXPoint = 1;
		float blockYPoint;
		blockYPoint = 3;
		
		OperateShape bar = new Bar(this, barWidth, barHeight);
		bar.setX(firstPlaceX);
		bar.setY(firstPlaceY);
		bar.setColor(color(100, 100, 100));
		shapes.add(bar);
		
		for (int i = 1; i <= 108; i++) {
			if (blockXPoint + blockWidth * blockXPoint > width - blockWidth) {
				blockXPoint = 1;
				blockYPoint++;
			}
			OperateShape block = new Block(this, blockWidth, blockHeight);
			block.setX(blockXPoint + blockWidth * blockXPoint);
			block.setY(blockYPoint + blockHeight * blockYPoint);
			block.setColor(color(0, 0, 0));
			shapes.add(block);
			blockXPoint++;
		}
	}
	
	private void createBall() {
		// ボールの直径
		float ballDiameter;
		ballDiameter = 20;
		
		OperateShape ball = new Circle(this, ballDiameter);
		ball.setX(firstPlaceX);
		// バーより半径の分ずれる
		ball.setY(firstPlaceY - (ballDiameter / 3) * 2);
		ball.setColor(color(0, 0, 255));
		shapes.add(ball);
	}
	
	public void onCollision(CollisionEvent event) {
		MoveShape shape = event.getTarget();
	}
}