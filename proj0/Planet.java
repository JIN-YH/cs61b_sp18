public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static double G = 6.67*1e-11;
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;		
	}
	public double calcDistance(Planet p){
		double distance;
		distance = (this.xxPos-p.xxPos)*(this.xxPos-p.xxPos)+(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos);
		distance = Math.sqrt(distance);
		return distance;
	}
	public double calcForceExertedBy(Planet p){
		double force;
		force = (Planet.G*this.mass*p.mass)/(calcDistance(p)*calcDistance(p));
		return force;
	}
	public double calcForceExertedByX(Planet p){
		double fx;
		fx = calcForceExertedBy(p)*(p.xxPos-this.xxPos)/calcDistance(p);
		return fx;
	}
	public double calcForceExertedByY(Planet p){
		double fy;
		fy = calcForceExertedBy(p)*(p.yyPos-this.yyPos)/calcDistance(p);
		return fy;
	}
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double fnx=0;
		for (Planet p : allPlanets){
			if (this.equals(p)){
				continue;
			}else{
				fnx = fnx + calcForceExertedByX(p);
			}
		}
		return fnx;
	}
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double fny=0;
		for (Planet p : allPlanets){
			if (this.equals(p)){
				continue;
			}else{
				fny = fny + calcForceExertedByY(p);
			}
		}
		return fny;
	}
	public void update(double dt, double fX, double fY){
		xxVel = xxVel + dt*fX/mass;
		yyVel = yyVel + dt*fY/mass;
		xxPos = xxPos + dt*xxVel;
		yyPos = yyPos + dt*yyVel;
	}
	public void draw(){
		StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
	}
}
