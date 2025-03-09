package klono;

import org.lwjgl.opengl.Display;

public class ClientInfo {

	public static String Client_Name = "KlonoClient";
	public static String Client_Version = "1.6.6";

	public static final ClientInfo KlonoClient = new ClientInfo();

    public ClientInfo() {
    }

    public static void StartClient() {
        Display.setTitle(Client_Name + "  Ver-" + Client_Version);
    }
}
