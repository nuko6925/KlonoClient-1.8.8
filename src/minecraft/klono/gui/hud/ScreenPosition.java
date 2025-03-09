package klono.gui.hud;

import net.minecraft.client.Minecraft;

public class ScreenPosition {

	private static final Minecraft mc = Minecraft.getMinecraft();

	private double x, y;

	public ScreenPosition(double x, double y) {
		setRelative(x, y);
	}

	public ScreenPosition(int x, int y) {
		setAbsolute(x, y);
	}

	public static ScreenPosition fromRelativePosition(double x, double y) {
		return new ScreenPosition(x, y);
	}

	public static ScreenPosition fromAbsolute(int x, int y) {
		return new ScreenPosition(x, y);
	}

	public int getAbsoluteX() {
		ScaledResolution res= new ScaledResolution(mc);
		return (int) (x * res.getScaledWidth());
	}

	public int getAbsoluteY() {
		ScaledResolution res =new ScaledResolution(mc);
		return (int) (y * res.getScaledHeight());
	}

	public double getRelativeX() {
		return x;
	}

	public double getRelativeY() {
		return y;
	}

	public void setAbsolute(int x2, int y2) {
		ScaledResolution res = new ScaledResolution(mc);

		this.x = (double) x2 / res.getScaledWidth();
		this.y = (double) y2 / res.getScaledHeight();
	}

	public void setRelative(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return String.format(getClass().getSimpleName() + "[absoluteX=%d,absoluteY=%d,relativeX=%.1f,relativeY=%.1f]", this.getAbsoluteX(), this.getAbsoluteY(), this.getRelativeX(), this.getRelativeY());
	}
}
