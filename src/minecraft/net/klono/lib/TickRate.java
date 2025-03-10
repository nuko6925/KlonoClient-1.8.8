package net.klono.lib;

import java.text.DecimalFormat;

import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S03PacketTimeUpdate;

public class TickRate {
    public static float TPS = 20.0f;

    public static long lastUpdate = -1;

    public static float[] tpsCounts = new float[10];

    public static DecimalFormat format = new DecimalFormat("##.0#");

    public static void update(Packet packet) {
        if (!(packet instanceof S03PacketTimeUpdate)) {
            return;
        }

        long currentTime = System.currentTimeMillis();

        if (lastUpdate == -1) {
            lastUpdate = currentTime;
            return;
        }
        long timeDiff = currentTime - lastUpdate;
        float tickTime = timeDiff / 20;
        if (tickTime == 0) {
            tickTime = 50;
        }
        float tps = 1000 / tickTime;
        if (tps > 20.0f) {
            tps = 20.0f;
        }
        System.arraycopy(tpsCounts, 0, tpsCounts, 1, tpsCounts.length - 1);
        tpsCounts[0] = tps;

        double total = 0.0;
        for (float f : tpsCounts) {
            total += f;
        }
        total /= tpsCounts.length;

        if (total > 20.0) {
            total = 20.0;
        }

        TPS = Float.parseFloat(format.format(total));
        lastUpdate = currentTime;
    }

    public static void reset() {
        for (int i = 0; i < tpsCounts.length; i++) {
            tpsCounts[i] = 20.0f;
        }
        TPS = 20.0f;
    }
}
