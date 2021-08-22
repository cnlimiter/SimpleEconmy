package cn.evolvefield.mods.simpleeco.client.gui.base;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.text.StringTextComponent;

public class TextFieldRect extends TextFieldWidget {
    /**
     * Used as the basic text field for anything in the mod.
     * @param defaultString The initial text rendered on the button.
     */
    public TextFieldRect(FontRenderer fontRenderer, int x, int y, int width, int stringLimit, String defaultString) {

        super(fontRenderer, x + 1, y + 2, width, 12, new StringTextComponent(defaultString));
        setTextColor(-1);
        setTextColorUneditable(-1);
        setMaxLength(stringLimit);
        setEditable(true);
        //setEnableBackgroundDrawing(true);
        insertText(defaultString);
        //setText(defaultString);
        setCanLoseFocus(true);
    }
}
