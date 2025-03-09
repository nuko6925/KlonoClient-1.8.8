package net.klono.lib;

public class OldAnimation {
	
	public static long lastTick = System.currentTimeMillis();
	public static final double offset = 16.666666666666668D;
	
	public static void tick() {
		lastTick = System.currentTimeMillis();
	}
	
	public static double animate(double a, double b) {
		double time = (double) (System.currentTimeMillis() - lastTick) / offset;
		a *= Math.pow(b, time);
		return a;
	}

}
