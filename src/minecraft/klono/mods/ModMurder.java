package klono.mods;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import klono.gui.hud.RainbowWave;
import klono.gui.hud.ScreenPosition;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;

public class ModMurder extends ModDraggable {
	
	public static String murder, secondMurder;
	public static boolean ingame, found = false;
	public static ArrayList<Item> items = new ArrayList<Item>();
	public static ArrayList<String> bows = new ArrayList<String>();
	
	public static boolean state = false;
	
	public static void init() {
		items.add(new Item().getByNameOrId("minecraft:iron_sword"));
        items.add(new Item().getByNameOrId("minecraft:ender_chest"));
        items.add(new Item().getByNameOrId("minecraft:stone_sword"));
        items.add(new Item().getByNameOrId("minecraft:iron_shovel"));
        items.add(new Item().getByNameOrId("minecraft:stick"));
        items.add(new Item().getByNameOrId("minecraft:wooden_axe"));
        items.add(new Item().getByNameOrId("minecraft:wooden_sword"));
        items.add(new Item().getByNameOrId("minecraft:deadbush"));
        items.add(new Item().getByNameOrId("minecraft:stone_shovel"));
        items.add(new Item().getByNameOrId("minecraft:blaze_rod"));
        items.add(new Item().getByNameOrId("minecraft:diamond_shovel"));
        items.add(new Item().getByNameOrId("minecraft:quartz"));
        items.add(new Item().getByNameOrId("minecraft:pumpkin_pie"));
        items.add(new Item().getByNameOrId("minecraft:golden_pickaxe"));
        items.add(new Item().getByNameOrId("minecraft:apple"));
        items.add(new Item().getByNameOrId("minecraft:name_tag"));
        items.add(new Item().getByNameOrId("minecraft:sponge"));
        items.add(new Item().getByNameOrId("minecraft:carrot_on_a_stick"));
        items.add(new Item().getByNameOrId("minecraft:bone"));
        items.add(new Item().getByNameOrId("minecraft:carrot"));
        items.add(new Item().getByNameOrId("minecraft:golden_carrot"));
        items.add(new Item().getByNameOrId("minecraft:cookie"));
        items.add(new Item().getByNameOrId("minecraft:diamond_axe"));
        items.add(new Item().getByNameOrId("minecraft:double_plant"));
        items.add(new Item().getByNameOrId("minecraft:prismarine_shard"));
        items.add(new Item().getByNameOrId("minecraft:cooked_beef"));
        items.add(new Item().getByNameOrId("minecraft:netherbrick"));
        items.add(new Item().getByNameOrId("minecraft:golden_sword"));
        items.add(new Item().getByNameOrId("minecraft:diamond_sword"));
        items.add(new Item().getByNameOrId("minecraft:diamond_hoe"));
        items.add(new Item().getByNameOrId("minecraft:shears"));
        items.add(new Item().getByNameOrId("minecraft:fish"));
        items.add(new Item().getByNameOrId("minecraft:dye"));
        items.add(new Item().getByNameOrId("minecraft:boat"));
        items.add(new Item().getByNameOrId("minecraft:speckled_melon"));
        items.add(new Item().getByNameOrId("minecraft:book"));
	}
	
	@Override
	public int getHeight() {
		return 36;
	}
	@Override
	public int getWidth() {
		return font.getStringWidth("#1 Murderer: aaaaaaaaaaaaaaaa") + 4;
	}
	@Override
	public void render(ScreenPosition pos) {
		if (mc.gameSettings.MURDER_CHEAT.getKeyCode() == Keyboard.KEY_HOME) {
			if (murder != null) {
				RainbowWave.drawStringWaveShadow("#1 Murderer: "+murder, pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2);
			}
			if (secondMurder != null) {
				RainbowWave.drawStringWaveShadow("#2 Murderer: "+secondMurder, pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 18);
			}
			if (bows.size() >= 1) {
				for (String bow : bows) {
					int y = (bows.indexOf(bow) * 16) + 16;
					RainbowWave.drawStringWaveShadow("#"+bows.indexOf(bow)+" Bow: "+bow, pos.getAbsoluteX() + 2, pos.getAbsoluteY() + y + 18);
				}
			}
			if (ingame) {
				try {
					for (EntityPlayer player : mc.theWorld.playerEntities) {
						if (!player.isInvisible()) {
							ItemStack itemStack = player.getHeldItem();
							if (itemStack != null) {
								if (items.contains(itemStack.getItem())) {
									if (murder != null && !murder.equals(player.getName())) {
										if (secondMurder == null) {
											secondMurder = player.getName();
											mc.thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.RED + "#2 Murderer is " + secondMurder));
										}
									}
									if (murder == null) {
										if (secondMurder != null && secondMurder.equals(player.getName())) {
											secondMurder = null;
										}
										murder = player.getName();
										mc.thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.RED + "#1 Murderer is " + murder));
									}
								}
								if (itemStack.getItem().equals(Items.bow)) {
									if (bows.size() >= 1) {
										if (!bows.contains(player.getName())) {
											mc.thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.YELLOW + player.getName() + " has bow!"));
											bows.add(player.getName());
										}
									} else {
										mc.thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.YELLOW + player.getName() + " has bow!"));
										bows.add(player.getName());
									}
								}
							}
						}
						if (player.isInvisible() || !player.isEntityAlive()) {
							if (bows.size() >= 1) {
								if (bows.contains(player.getName())) {
									bows.remove(bows.indexOf(player.getName()));
								}
							}
							if (murder != null) {
								if (murder.equals(player.getName())) {
									murder = null;
								}
							}
							if (secondMurder != null) {
								if (secondMurder.equals(player.getName())) {
									secondMurder = null;
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (mc.gameSettings.TOGGLE_STATUS.isPressed()) {
			if (state) {
				state = false;
			} else {
				state = true;
			}
		}
		
	}

}
