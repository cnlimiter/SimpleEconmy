package cn.evolvefield.mods.simpleeco.utils;

import cn.evolvefield.mods.simpleeco.SimpleEco;
import net.minecraft.util.text.*;

public class TextUtils {

    public static String getTranslationKey(String beforeModid, String afterModid) {
        beforeModid = beforeModid.endsWith(".") ? beforeModid : beforeModid + ".";
        afterModid = afterModid.startsWith(".") ? afterModid : "." + afterModid;
        return beforeModid + SimpleEco.MOD_ID + afterModid;
    }
    public static ITextComponent getColoredTextFromI18n(Color color, boolean bold, boolean underline, boolean italic, String translationKey, Object... parameters) {
        return new TranslationTextComponent(translationKey, parameters)
                .setStyle(Style.EMPTY
                        .withColor(color)
                        .withBold(bold)
                        .setUnderlined(underline)
                        .withItalic(italic));
    }

    public static ITextComponent getYellowTextFromI18n(boolean bold, boolean underline, boolean italic, String translationKey, Object... parameters) {
        return getColoredTextFromI18n(Color.fromLegacyFormat(TextFormatting.YELLOW), bold, underline, italic, translationKey, parameters);
    }

    public static ITextComponent getGreenTextFromI18n(boolean bold, boolean underline, boolean italic, String translationKey, Object... parameters) {
        return getColoredTextFromI18n(Color.fromLegacyFormat(TextFormatting.GREEN), bold, underline, italic, translationKey, parameters);
    }
}
