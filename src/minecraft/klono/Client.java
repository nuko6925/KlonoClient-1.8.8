package klono;

import com.mojang.realmsclient.gui.ChatFormatting;

import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import klono.discordrpc.DiscordRP;
import klono.event.EventManager;
import klono.event.EventTarget;
import klono.event.impl.ClientTickEvent;
import klono.gui.hud.HUDManager;
import klono.mods.ModInstances;
import klono.mods.ModMurder;
import klono.plugin.PluginLoader;
import klono.sync.Sync;
import net.klono.lib.FileManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ChatComponentText;

public class Client {
	private static Minecraft mc = Minecraft.getMinecraft();
    private static final Client INSTANCE = new Client();
    private DiscordRP discordRP = new DiscordRP();
    private HUDManager hudManager;
    public static JFrame frame;
    public static JTextField text;
    public static JFrame browser;
    public static JTextField urlField;
    public static JEditorPane html;
    public static JScrollPane scroll;
    public static JFrame calc;
    public static JFrame notepad;
    public static String nickname;
    public static boolean nicked;
    public static String name;

    public Client() {
    }

    public static final Client getInstance() {
        return INSTANCE;
    }

	public void init() {
		FileManager.init();
//		SplashScreen.setProgress("Initializing KlonoClient...");
		discordRP.start();
		EventManager.register(this);
	}

	public void start() {
		/*
		String account = "";
		try {
    		File file = new File(getReAuth(), mc.getSession().getProfile().getId()+".json");
    		if (file.exists()) {
    			User user = FileManager.readFromJson(file, User.class);
    			account = ""+user.email+":"+user.password;
    		}
    	} catch (Exception e) {
			e.printStackTrace();
		}
		String[] accountInfo = account.split(":");
		if (accountInfo.length == 2) {
			if (accountInfo[0].contains("@")) {
				final String email = accountInfo[0];
				final String password = accountInfo[1];
				if (!password.isEmpty()) {
					YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
					authentication.setUsername(email);
					authentication.setPassword(password);
					try {
						authentication.logIn();
						mc.session = new Session(authentication.getSelectedProfile().getName(), authentication.getSelectedProfile().getId().toString(), authentication.getAuthenticatedToken(), "mojang");
						System.out.println("Login successful");
						try {
				    		File file = new File(getReAuth(), mc.getSession().getProfile().getId()+".json");
				    		if (file.exists()) {
				    			User user = new User(email, password);
				    			FileManager.writeJsonToFile(file, user);
				    		}
				    	} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (AuthenticationException e) {
						System.out.println("Login failed");
					}
				}
			}
		}
		System.out.println("PlayerID:"+mc.getSession().getPlayerID());
		System.out.println("SessionID:"+mc.getSession().getSessionID());
		System.out.println("Username:"+mc.getSession().getUsername());
		System.out.println("SessionType:"+mc.getSession().getSessionType());
		System.out.println("Profile Name:"+mc.getSession().getProfile().getName());
		System.out.println("Profile ID:"+mc.getSession().getProfile().getId());
		*/
//		name = mc.getSession().getProfile().getId().toString();
		name = "00000000-0000-0000-0000-000000000000";
		if (SessionAPI.method.equals("name")) {
			name = mc.getSession().getUsername();
		}
//		SessionAPI.loginSession();
		this.nickname = mc.getSession().getUsername();
		this.nicked = false;
		this.hudManager = HUDManager.getInstance();
		ModInstances.register(this.hudManager);
//		PluginLoader.load();
//		ModMurder.init();
//		Sync.loadConfig();
		
//    	createCalc();
//    	calc.setVisible(false);
//    	createNote();
//    	notepad.setVisible(false);
    	/*
    	browser = new JFrame();
    	browser.setTitle("browser");
    	browser.setBounds(screenSize.width / 4, screenSize.height / 4, screenSize.width / 2, screenSize.height / 2);
    	browser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	urlField = new JTextField("https://google.com", 100);
    	JPanel panel2 = new JPanel();
    	panel2.add(urlField);
    	Container container = browser.getContentPane();
    	container.add(panel2, BorderLayout.NORTH);
    	urlField.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyReleased(KeyEvent e) {
    			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
    				try {
    					URL url = new URL("https://www.google.com/");
    					if (urlField.getText().contains("http://") || urlField.getText().contains("https://")) {
    						url = new URL(urlField.getText());
    						browser.setTitle(urlField.getText());
    					} else {
    						url = new URL("https://www.google.com/search?client=klonoclient&q=" + urlField.getText());
    						browser.setTitle("Search " + urlField.getText());
    					}
    					html = new JEditorPane(url);
    					scroll = new JScrollPane(html);
    					container.add(scroll, BorderLayout.CENTER);
    				} catch (Exception e1) {
						e1.printStackTrace();
					}
    			}
    		}
		});
    	browser.setAlwaysOnTop(true);
    	browser.setVisible(true);
    	browser.setVisible(false);*/
	}
/*    private ActionListener enterActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	String s = text.getText();
			if (!s.isEmpty() && s.length() <= 100)
            {
                mc.thePlayer.sendChatMessage(s);
            }
			frame.setVisible(false);
            mc.displayGuiScreen((GuiScreen)null);
        }
    };*/
    
/*    private static void createCalc() {
    	calc = new JFrame();
    	calc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//    	MyPanel h = new MyPanel();
    	calc.add(h, BorderLayout.CENTER);
    	calc.pack();
    	calc.setMinimumSize(new Dimension(frame.getSize().width, frame.getSize().height));
    	calc.setAlwaysOnTop(true);
    	calc.setVisible(true);
    }*/
    
    private static File getFolder() {
    	File folder = new File(FileManager.getModsDirectory(), "Notepad");
		folder.mkdirs();
		return folder;
    }
    
    private static File getReAuth() {
    	File folder = new File(FileManager.getModsDirectory(), "ReAuth");
		folder.mkdirs();
		return folder;
    }
    
    private static void createNote() {
    	notepad = new JFrame();
    	notepad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	notepad.setTitle("メモ帳");
    	notepad.setBounds(10, 10, 300, 200);
    	JTextPane textPane = new JTextPane();
    	try {
    		File file = new File(getFolder(), "notepad.txt");
    		if (file.exists()) {
    			BufferedReader reader = new BufferedReader(new FileReader(file));
    			String str = reader.readLine();
    			String ss = "";
    			while (str != null) {
    				ss = ss + str + System.lineSeparator();
    				str = reader.readLine();
    			}
    			reader.close();
    			textPane.setText(ss);
    		}
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	textPane.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    			if (e.getKeyCode() == KeyEvent.VK_S) {
    				if ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0) {
    					try {
    						File file = new File(getFolder(), "notepad.txt");
    						if (!file.exists()) {
    							file.createNewFile();
    						}
    						FileWriter writer = new FileWriter(file);
    						writer.write(textPane.getText());
    						writer.close();
    						mc.thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.GREEN + "Notepad - Saved to " + file.getAbsolutePath()));
    					} catch (Exception e1) {
							e1.printStackTrace();
						}
    				}
    			}
    		}
		});
    	JScrollPane scrollPane = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	notepad.getContentPane().add(scrollPane);
    	notepad.setAlwaysOnTop(true);
    	notepad.setVisible(true);
    }

	public void shutdown() {
		discordRP.shutdown();
		/*if (SessionAPI.response.contains("Success") || SessionAPI.response.contains("Already logged in")) {
			SessionAPI.logoutSession();
			Sync.saveConfig();
		}*/
	}

	public DiscordRP getDiscordRP() {
		return discordRP;
	}

	@EventTarget
	public void onTick(ClientTickEvent e) {
		if(GameSettings.CLIENT_GUI_MOD_POS.isPressed()) {
			this.hudManager.openConfigScreen();
		}
	}
}

/*
class MyPanel extends JPanel implements ActionListener { // JPanelを継承
    JButton    btn1, btn2;
    JTextField txt1, txt2;
    ArrayList<String> sHis = new ArrayList<String>();
    int iCnt = 0;
    
    // 追加ボタンの設定
    String [] sBtD = {                          // 表示用
        "arc","hyp","sin","cos","tan",
        "x^y","，","exp","ln","log",
        "10^x","1/x","3√","π","ｅ",
        
        "７","８","９","÷","CE",
        "４","５","６","×","√",
        "１","２","３","−","％",
        "０","．","（","＋","）"
    };
    String [] sBtO = {                          // 出力用
        "a","h(","sin","cos","tan",
        "pow(",",","exp(","ln(","log10(",
        "ten(","rcp(","cbrt(","PI","E",
        
        "7","8","9","/","",
        "4","5","6","*","sqrt(",
        "1","2","3","-","%",
        "0",".","(","+",")"
    };
    int nBtn = sBtD.length;                     // 追加するボタンの数
    JButton[] aBtn = new JButton[nBtn];         // 追加ボタン
    
    public MyPanel() {
        setBackground(SystemColor.control);     // 背景を灰色にする

        txt1 = new JTextField(14);              // txtの設定
        txt1.addActionListener(this);
        txt1.addKeyListener(new MyKeyListener());   // リスナーオブジェクトを指定
        txt1.setFont(new Font("SansSerif",Font.PLAIN,16));

        txt2 = new JTextField(14);              // txtの設定
        txt2.setFont(new Font("SansSerif",Font.ITALIC,16));
        txt2.setHorizontalAlignment(JTextField.RIGHT);

        btn1 = new JButton("＝");
        btn1.addActionListener(this);

        btn2 = new JButton("Ｃ");
        btn2.addActionListener(this);

        for(int i = 0; i< nBtn; i++)
            aBtn[i] = new JButton(sBtD[i]);
        for(JButton b : aBtn)
            b.addActionListener(this);

        JPanel pa = new JPanel();
        pa.setLayout(new GridLayout(2,1,2,2));
        pa.add(txt1);
        pa.add(txt2);
        
        JPanel pb = new JPanel();
        pb.setLayout(new GridLayout(2,1,2,2));
        pb.add(btn1);
        pb.add(btn2);
        
        JPanel pc = new JPanel();
        pc.setLayout(new GridLayout(nBtn/5,5,2,2));
        for(JButton b : aBtn)
            pc.add(b);
        
        setLayout(new BorderLayout(2,2));
        add(pa, BorderLayout.CENTER);
        add(pb, BorderLayout.EAST);
        add(pc, BorderLayout.SOUTH);
        
        sHis.add(""); iCnt = sHis.size()-1;
        MyJsCalc.defUserFunc();                 // ユーザー定義関数の設定
    }

    public void actionPerformed(ActionEvent e) {    // ボタンクリック
        if(e.getSource()==btn1||e.getSource()==txt1) {
            String s = txt1.getText(); sHis.add(s); iCnt = sHis.size()-1;
            txt2.setText(MyJsCalc.sEval(s));
        } else if(e.getSource()==btn2) {
            txt1.setText(""); txt2.setText("");
        } else if(e.getSource()==aBtn[Arrays.asList(sBtD).indexOf("CE")]) {
            String s = txt1.getText();          // [CE]ボタンの処理
            if(s.length()> 0) s = s.substring(0, s.length()-1);
            txt1.setText(s);
        }
        for(int i = 0; i< nBtn; i++)
            if(e.getSource()==aBtn[i])
                txt1.setText(txt1.getText()+sBtO[i]);
        txt1.requestFocus();
    }

    class MyKeyListener extends KeyAdapter {    // リスナークラス
        public void keyPressed(KeyEvent e){ 
            int k = e.getKeyCode();
            
            if       (k == KeyEvent.VK_DOWN) {
                iCnt++;
                if(iCnt> sHis.size()) iCnt = sHis.size();
                txt2.setText(""); txt1.setText(sHis.get(iCnt % sHis.size()));
            } else if(k == KeyEvent.VK_UP) {
                iCnt--;
                if(iCnt< 1) iCnt = 1;
                txt2.setText(""); txt1.setText(sHis.get(iCnt % sHis.size()));
            }
        }
    }
}
class MyJsCalc extends JsCalc {
    // ユーザー定義関数の設定
    static void defUserFunc() {
        JsCalc.defUserFunc();   // staticメソッドはオーバーライドできない
        // 関数を追加
        String sScript = "";
        sScript = "sign = function(x) {x=+x;"
                + "if(x===0||isNaN(x)){return x;}"
                + "return (x> 0)?1:-1;}";
        sEval(sScript);
        sEval("cbrt  = function(x) {return sign(x)*pow(abs(x),1/3);}");
        sEval("rcp   = function(x) {return 1/x;}");
        sEval("Rnd   = function()  {return random();}");
        sEval("sinh  = function(x) {return (exp(x)-exp(-x))/2;}");
        sEval("sinh  = function(x) {return (exp(x)-exp(-x))/2;}");
        sEval("cosh  = function(x) {return (exp(x)+exp(-x))/2;}");
        sScript = "tanh = function(x) {"
                + "if(abs(x)===Infinity){return sign(x);}"
                + "else{var y=exp(2*x);return (y-1)/(y+1);}}";
        sEval(sScript);
        sEval("asinh = function(x) {return(x===-Infinity)?x:log(x+sqrt(x*x+1));}");
        sEval("acosh = function(x) {return log(x+sqrt(x*x-1));}");
        sEval("atanh = function(x) {return log((1+x)/(1-x))/2;}");
        sEval("ln    = function(x) {return log(x);}");
        sEval("log10 = function(x) {return log(x)/LN10;}");
        sEval("ten   = function(x) {return pow(10,x);}");
        // 後はご自由に追加してください。 sEval("");
    }
}
class JsCalc {                                        // JsCalc.java [java]
    static ScriptEngineManager factory = new ScriptEngineManager();
    static ScriptEngine engine = factory.getEngineByName("JavaScript");

    // JavaでJavaScriptのeval()関数を呼び出す
    static String sEval(String sExpr) {
        String sScript="with(Math){"+sExpr+"}";       // 「Math.」を省略可能にするため
        try{
            return( engine.eval(sScript).toString() );
        }
        catch(NullPointerException e){               // 関数定義でエラー防止
            return(sExpr);
        }
        catch(Exception e){
            return("error: "+e);
        }
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
		Scanner stdIn = new Scanner(System.in);

        defUserFunc();                               // ユーザー定義関数の設定
        System.out.println("");
        for(;;){                                     // 無限ループ
            System.out.print("> ");
            String sExpr = stdIn.nextLine();         // １行分の文字列を読み込む
            if(sExpr.equalsIgnoreCase("@Q")) break;  // 無限ループの出口:「@Q」で終了
            System.out.println(" "+sEval(sExpr));    // 表示
        }
    }

    // ユーザー定義関数の設定
    static void defUserFunc() {
        String sScript="";
        sEval("function radians(x){return(x/180*PI);}");
        sEval("function degrees(x){return(x/PI*180);}");
        sEval("function sinDeg(x) {return(sin(radians(x)));}");
        sEval("function cosDeg(x) {return(cos(radians(x)));}");
        sEval("function tanDeg(x) {return(tan(radians(x)));}");
        // 長文用
        sScript = "function sum(a){var s=0;"
                + "for(var i=0; i< a.length; i++) s+=a[i];"
                + "return(s);}";
        sEval(sScript);
        // 後はご自由に追加してください。
        
    }
}
*/