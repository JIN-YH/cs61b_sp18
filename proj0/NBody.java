public class NBody{
	public static double readRadius(String location){
		In in = new In(location);
		int N = in.readInt();
		double R = in.readDouble();
		return R;
	}
	public static Planet[] readPlanets(String location){
		In in = new In(location);
		int N = in.readInt();
		double R = in.readDouble();
		Planet[] planets = new Planet[N];
		int i = 0;
		double xxPos, yyPos, xxVel, yyVel, mass;
		String imgFileName;
		while(i<N){
			xxPos = in.readDouble();
			yyPos = in.readDouble();
			xxVel = in.readDouble();
			yyVel = in.readDouble();
			mass = in.readDouble();
			imgFileName = in.readString();
			planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			i = i + 1;
		}
		return planets;
	}
	public static void main(String args[]){
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];
		double r = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		StdDraw.setScale(-r,r);
		String background = "images/starfield.jpg";
		StdDraw.enableDoubleBuffering();
		StdDraw.picture(0,0,background);
		int n = planets.length;
		for (Planet p:planets){
			p.draw();
		}

		for(double t=0;t<T;t=t+dt){
			double[] xForces = new double[n];
			double[] yForces = new double[n];
			for (int i=0;i<n;i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int j=0;j<n;j++){
				planets[j].update(dt, xForces[j], yForces[j]);
			}
			StdDraw.picture(0,0,background);
			for(Planet p:planets){
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  		  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}

	}
}

