package optifine;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import shadersmod.client.Shaders;

public class CrashReporter
{
    public static void onCrashReport(CrashReport p_onCrashReport_0_, CrashReportCategory p_onCrashReport_1_)
    {
        try
        {
            GameSettings gamesettings = Config.getGameSettings();

            if (gamesettings == null)
            {
                return;
            }

            return;
        }
        catch (Exception exception)
        {
            Config.dbg(exception.getClass().getName() + ": " + exception.getMessage());
        }
    }

    private static String makeReport(CrashReport p_makeReport_0_)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("OptiFineVersion: " + Config.getVersion() + "\n");
        stringbuffer.append("Summary: " + makeSummary(p_makeReport_0_) + "\n");
        stringbuffer.append("\n");
        stringbuffer.append(p_makeReport_0_.getCompleteReport());
        stringbuffer.append("\n");
        return stringbuffer.toString();
    }

    private static String makeSummary(CrashReport p_makeSummary_0_)
    {
        Throwable throwable = p_makeSummary_0_.getCrashCause();

        if (throwable == null)
        {
            return "Unknown";
        }
        else
        {
            StackTraceElement[] astacktraceelement = throwable.getStackTrace();
            String s = "unknown";

            if (astacktraceelement.length > 0)
            {
                s = astacktraceelement[0].toString().trim();
            }

            String s1 = throwable.getClass().getName() + ": " + throwable.getMessage() + " (" + p_makeSummary_0_.getDescription() + ")" + " [" + s + "]";
            return s1;
        }
    }

    public static void extendCrashReport(CrashReportCategory p_extendCrashReport_0_)
    {
        p_extendCrashReport_0_.addCrashSection("OptiFine Version", Config.getVersion());

        if (Config.getGameSettings() != null)
        {
            p_extendCrashReport_0_.addCrashSection("Render Distance Chunks", "" + Config.getChunkViewDistance());
            p_extendCrashReport_0_.addCrashSection("Mipmaps", "" + Config.getMipmapLevels());
            p_extendCrashReport_0_.addCrashSection("Anisotropic Filtering", "" + Config.getAnisotropicFilterLevel());
            p_extendCrashReport_0_.addCrashSection("Antialiasing", "" + Config.getAntialiasingLevel());
            p_extendCrashReport_0_.addCrashSection("Multitexture", "" + Config.isMultiTexture());
        }

        p_extendCrashReport_0_.addCrashSection("Shaders", "" + Shaders.getShaderPackName());
        p_extendCrashReport_0_.addCrashSection("OpenGlVersion", "" + Config.openGlVersion);
        p_extendCrashReport_0_.addCrashSection("OpenGlRenderer", "" + Config.openGlRenderer);
        p_extendCrashReport_0_.addCrashSection("OpenGlVendor", "" + Config.openGlVendor);
        p_extendCrashReport_0_.addCrashSection("CpuCount", "" + Config.getAvailableProcessors());
    }
}
