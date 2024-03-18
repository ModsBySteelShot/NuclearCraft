package nc.util;

import java.util.List;

import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

public class InfoNC {

    public static final String shiftString = "Hold Shift for more info";

    public static final String[] nul = { "" };

    public static void shiftInfo(List<String> list) {
        list.add(EnumChatFormatting.ITALIC + InfoNC.shiftString);
    }

    public static void info(List<String> list, String line) {
        list.add(EnumChatFormatting.AQUA + line);
    }

    public static boolean shift() {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    public static void infoList(List<String> list, String... lines) {
        for (String line : lines) {
            list.add(EnumChatFormatting.AQUA + line);
        }
    }

    public static void infoFull(List<String> list, String... lines) {
        if (lines.length > 0) {
            if (InfoNC.shift()) {
                InfoNC.infoList(list, lines);
            } else {
                InfoNC.shiftInfo(list);
            }
        }
    }
}
