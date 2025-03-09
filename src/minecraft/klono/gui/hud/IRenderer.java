package klono.gui.hud;

public interface IRenderer extends IRenderConfig {

	int getHeight();
	int getWidth();

	void render(ScreenPosition pos);

	default void renderDummy(ScreenPosition pos) {
		render(pos);
	}

	public default boolean isEnabled() {
		return true;
	}
}
