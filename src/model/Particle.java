package model;

import processing.core.PApplet;

public class Particle {

	public final int SIZE = 7;
	private int particleSpeed;
	private float particleDirection;
	private float backupAngle;
	private boolean canMove;
	private int type;
	private String name;
	private int r,g,b;
	
	private int posX, posY;
	private double realPosX, realPosY;
	private double changeY;
	private double changeX;
	private int posXOrigin, posYOrigin;
	private int base;
	private int mx, my;
	
	private PApplet app;
	
	public Particle(int s, float dir, int t, int px, int py, PApplet app, int maxW, int maxH, String name) {
		canMove = true;
		particleDirection = dir;
		particleSpeed = s;
		type = t;
		posXOrigin = px;
		posYOrigin = py;
		
		realPosX = px;
		realPosY = py;
		this.app = app;
		mx = maxW;
		my = maxH;
		this.name = name;

		calculateAngles();
		
		calculateColor();
	}
	
	private void calculateColor() {
		switch (type) {
		case 1:
			r = 100;
			g = 100;
			b = 250;
			break;
		case 2:
			r = 40;
			g = 230;
			b = 40;
			break;
		case 3:
			r = 230;
			g = 40;
			b = 40;
			break;
		}				
	}

	public void drawParticle() {
		app.fill(r,g,b);
		app.pushMatrix();
		app.translate(posXOrigin, posYOrigin);
		app.rotate(app.radians(particleDirection));
		app.circle(posX, posY, SIZE);
		app.popMatrix();
		
		moveParticle();
	}
	
	public void moveParticle() {
		
		if(canMove) {
			//When the particles exceeds the boundaries it bounce
			if((realPosX > mx || realPosY > my || realPosX < 0 || realPosY < 0 )) {
				bounceParticle();
			}
			posX+= particleSpeed;
			
			//Update the real positions of the particle with the "exchange rate" of each axis
			if( particleDirection > 0 && particleDirection < 90) {
				realPosX+= changeX*particleSpeed;
				realPosY+= changeY*particleSpeed;
			}else if( particleDirection > 90 && particleDirection < 180) {
				realPosX-= changeX*particleSpeed;
				realPosY+= changeY*particleSpeed;
			}else if( particleDirection > 180 && particleDirection < 270) {
				realPosX-= changeX*particleSpeed;
				realPosY-= changeY*particleSpeed;
			}else if( particleDirection > 270 && particleDirection < 360) {
				realPosX+= changeX*particleSpeed;
				realPosY-= changeY*particleSpeed;
			}else if(particleDirection == 0) {
				realPosX+= particleSpeed;
			}else if(particleDirection == 90) {
				realPosY+= particleSpeed;
			}else if(particleDirection == 180) {
				realPosX-= particleSpeed;
			}else if(particleDirection == 270) {
				realPosY-= particleSpeed;
			}
			
		}else {
			//Show the group to which the particle belongs while static
			app.textSize(12);
			app.text(name, (int)realPosX-2*name.length(), (int)realPosY+30);
		}		
	}
	
	private void bounceParticle() {
		
		//Define which case of bouncing applies to the current particles action
		//right
		if((realPosX > mx && particleDirection > 0 && particleDirection < 90) ||
				(realPosY > my && particleDirection > 90 && particleDirection < 180) ||
				(realPosX < 0 && particleDirection > 180 && particleDirection < 270)||
				(realPosY < 0 && particleDirection > 270 && particleDirection < 360)) {
			//calculate the new angle of the plane
			float n = 180-(2*(particleDirection%90));
			float pd = (n+particleDirection)%360;
			particleDirection = pd;
			
			//left
		}else if((realPosY > my && particleDirection > 0 && particleDirection < 90) ||
				(realPosX < 0 && particleDirection > 90 && particleDirection < 180) ||
				(realPosY < 0 && particleDirection > 180 && particleDirection < 270)||
				(realPosX> mx && particleDirection > 270 && particleDirection < 360)) {
			//calculate the new angle of the plane
			int a = (int) (particleDirection/90);
			particleDirection = ((360-(particleDirection%90))+(90*a))%360;
			
		}else if(particleDirection%90 == 0) {
			particleDirection = (180+particleDirection)%360;
		}
		
		//Adjust real coordinates before changing the plane for assuring that the particle will be within the bounds
		if(realPosX > mx){
			realPosX = mx-particleSpeed;
		}
		if(realPosY > my) {
			realPosY = my-particleSpeed;
		}
		if(realPosX < 0){
			realPosX = particleSpeed;
		}
		if(realPosY < 0) {
			realPosY = particleSpeed;
		}
		
		//update the origin position to the place where the particle left the canvas
		posXOrigin = (int) realPosX;
		posYOrigin = (int) realPosY;
		
		//reset the "imaginary" positions of the particle
		posX = 0;
		posY = 0;		
	}

	public void calculateAngles() {
		
		//calculate the quadrant where the particle is moving and choose a base for the triangle depending on it
		if( particleDirection > 0 && particleDirection < 90) {
			base = mx-posXOrigin;
		}else if( particleDirection > 90 && particleDirection < 180) {
			base = my-posYOrigin;
		}else if( particleDirection > 180 && particleDirection < 270) {
			base = posXOrigin;
		}else if( particleDirection > 270 && particleDirection < 360) {
			base = posYOrigin;
		}
		
		//adjust the angles > 90 to fit a single case
		backupAngle = particleDirection%90;		
		
		//compute the tangent of the angle used in the operation
		double inDegrees = backupAngle;
		double inRadians = Math.toRadians(inDegrees);
		double tan = Math.tan(inRadians);

		//find the opposite leg of the rectangle using the base and the tangent
		double y = tan*base;
	
		//find the hypotenuse which will be equivalent to the entire trajectory of the particle before it "leaves the canvas"
		double d = Math.sqrt((Math.pow(base, 2))+(Math.pow(y, 2)));
		
		//Compute the exchange rate of both axis to find out for each pixel moved on the hypotenuse, how man pixels does the particle really moves on the x and y real axis
		if( (particleDirection > 0 && particleDirection < 90) || ( particleDirection > 180 && particleDirection < 270)) {
			changeY = y/d;
			changeX = base/d;
		}else if( (particleDirection > 90 && particleDirection < 180) || ( particleDirection > 270 && particleDirection < 360)) {
			changeX = y/d;
			changeY = base/d;
		}
	}
	
	//Getter and Setters

	public double getRealPosX() {
		return realPosX;
	}

	public double getRealPosY() {
		return realPosY;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}
	
	
}
