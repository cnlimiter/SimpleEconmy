package cn.evolvefield.mods.simpleeco.client.gui.base;


import cn.evolvefield.mods.simpleeco.utils.ScreenUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import org.lwjgl.opengl.GL11;

import static cn.evolvefield.mods.simpleeco.SimpleEco.MOD_ID;


public class ButtonRect extends Button {
    public final ScreenRect rect;

    public ButtonRect(int x, int y, int width, int height, String text, IPressable pressable) {
        super(x, y, width, height, new StringTextComponent(text), pressable);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        rect = new ScreenRect(x, y, width, height);
    }

    public void setPosition (int x, int y) {
        rect.x = x;
        this.x = x;
        rect.y = y;
        this.y = y;
    }


    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (visible) {

            if (rect.contains(mouseX, mouseY)) {
                GL11.glColor4f(1F, 1F, 1F, 1F);
                isHovered = true;
            } else {
                GL11.glColor4f(0.8F, 0.8F, 0.8F, 8F);
                isHovered = false;
            }

            if (!active) {
                GL11.glColor4f(0.5F, 0.5F, 0.5F, 1F);
            }

            Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(MOD_ID + ":textures/gui/gui_textures.png") );//textures in simpleeco
            ScreenUtil.drawCappedRect(rect.x, rect.y, 0, 240, 5, rect.width, rect.height, 256, 16);

            GL11.glColor4f(1, 1, 1, 1);

            ScreenUtil.drawCenteredString(matrixStack, getMessage().getString(), rect.x + (rect.width / 2), rect.y + (rect.height - 8) / 2, 100, 0xFFFFFF);

        }
    }
}
