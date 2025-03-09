package net.klono.lib;

import java.awt.MouseInfo;
import java.awt.Point;

import org.lwjgl.opengl.Display;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class MouseUtil {
	
	public static double getMouseX() {
		double x = MouseInfo.getPointerInfo().getLocation().getX();
		double displayX = Display.getWidth();
		double width = (x / displayX) * 100;
		return width;
	}
	
	public static double getMouseY() {
		double y = MouseInfo.getPointerInfo().getLocation().getY();
		double displayY = Display.getHeight();
		double width = (y / displayY) * 100;
		return width;
	}
	
	public static int getRawX() {
		int x = MouseInfo.getPointerInfo().getLocation().x;
		return x;
	}
	
	public static int getRawY() {
		int y = MouseInfo.getPointerInfo().getLocation().y;
		return y;
	}
	
	public static double getAbsoluteX() {
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		double x = MouseInfo.getPointerInfo().getLocation().getX();
		double displayX = Display.getWidth();
		double width = (x / displayX) * 100;
		double scaledX = res.getScaledWidth_double();
		double width2 = (scaledX / 100) * width;
		return width2;
	}
	
	public static double getAbsoluteY() {
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		double y = MouseInfo.getPointerInfo().getLocation().getY();
		double displayY = Display.getHeight();
		double height = (y / displayY) * 100;
		double scaledY = res.getScaledHeight_double();
		double height2 = (scaledY / 100) * height;
		return height2;
	}

}
