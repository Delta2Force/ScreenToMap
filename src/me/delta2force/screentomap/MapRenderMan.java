package me.delta2force.screentomap;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapFont;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class MapRenderMan extends MapRenderer{
	private ScreenToMapPlugin plugin;
	private Robot robot;
	public MapRenderSettings settings;
	private BufferedImage displayImage;
	
	public MapRenderMan(ScreenToMapPlugin stmpl) {
		plugin = stmpl;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		try {
			displayImage = ImageIO.read(new URL("https://i.imgur.com/Wv3ssCO.png"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void render(MapView mapView, MapCanvas canvas, Player player) {
		if(settings == null) {
			canvas.drawImage(0, 0, displayImage);
		}else {
			BufferedImage bi = robot.createScreenCapture(new Rectangle(settings.x, settings.y, settings.width, settings.height));
			
			BufferedImage bufimg = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
			bufimg.createGraphics().drawImage(bi, 0, 0, 128, 128, null);
			
			canvas.drawImage(0, 0, bufimg);
		}
	}
	
	static class MapRenderSettings{
		public int x, y, width, height;
		
		public MapRenderSettings(int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
	}
}
