package klono.gui.hud;

public interface IRenderConfig {

	public abstract void save(ScreenPosition pos);

	public abstract ScreenPosition load();

}
