package processing;

import java.util.ArrayList;
import processing.breakoutShape.Bar;
import processing.breakoutShape.Block;
import processing.collision.CollisionDecider;
import processing.collision.CollisionEvent;
import processing.collision.CollisionListener;
import processing.core.*;
import processing.shape.Circle;
import processing.shape.MoveShape;
import processing.shape.OperateShape;
import processing.watcher.BoundAction;
import processing.watcher.CollisionCheckWithBarCondition;
import processing.watcher.CollisionCheckWithBlockCondition;
import processing.watcher.RemoveAction;
import processing.watcher.ShapeAction;
import processing.watcher.ShapeCondition;
import processing.watcher.ShapeWatcher;
import processing.EdgeType;

public class Breakout extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<OperateShape> shapes = new ArrayList<OperateShape>();
	private ShapeWatcher shapeWatcher;
	private ShapeCondition withBarCondition;
	private ShapeCondition withBlockCondition;
	private ShapeAction boundAct;
	private ShapeAction removeAct;
	
	// ブロックが描画されるX座標とY座標、バーとボールの初期位置のX座標とY座標
	private float firstPlaceX, firstPlaceY;
	
	public void setup() {
		size(1200, 700);
		frameRate(240);
		smooth();
		
		shapeWatcher = new ShapeWatcher();
		
		boundAct = new BoundAction();
		withBarCondition = new CollisionCheckWithBarCondition();
		withBarCondition.addAction(boundAct);
		
		removeAct = new RemoveAction();
		withBlockCondition = new CollisionCheckWithBlockCondition();
		withBlockCondition.addAction(boundAct);
		withBlockCondition.addAction(removeAct);
		
		shapeWatcher.addCondition(withBarCondition);
		shapeWatcher.addCondition(withBlockCondition);
		
		firstPlaceX = 600;
		firstPlaceY = 680;
		
		createBall();
		createBarAndBlock();
		
		for (OperateShape shape : shapes) {
			shapeWatcher.addWatchShape(shape);
		}
	}
	
	public void draw() {
		clearShape();
		
		shapeWatcher.checkCollision();
		
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
	
}