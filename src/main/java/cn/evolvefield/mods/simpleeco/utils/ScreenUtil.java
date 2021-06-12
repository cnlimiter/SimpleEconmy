package cn.evolvefield.mods.simpleeco.utils;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static cn.evolvefield.mods.simpleeco.SimpleEco.MOD_ID;


public class ScreenUtil {
    private static final int TEXTURE_SIZE = 256;
    private static final Minecraft mc = Minecraft.getInstance();


    public static void bindTexture (String modID ,String name) {
        mc.getTextureManager().bind(new ResourceLocation(modID + ":textures/gui/" + name + ".png"));
    }

    public static void drawRect (int x, int y, int u, int v, int zLevel, int width, int height) {

        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, zLevel);

        int maxX = x + width;
        int maxY = y + height;

        int maxU = u + width;
        int maxV = v + height;

        float pixel = 1F / TEXTURE_SIZE;

        Tessellator tessellator = RenderSystem.renderThreadTesselator();
        BufferBuilder buffer = tessellator.getBuilder();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.vertex((float) x, (float) maxY, 50).uv(u * pixel, maxV * pixel).endVertex();
        buffer.vertex((float) maxX, (float) maxY, 50).uv(maxU * pixel, maxV * pixel).endVertex();
        buffer.vertex((float) maxX, (float) y, 50).uv(maxU * pixel, v * pixel).endVertex();
        buffer.vertex((float) x, (float) y, 50).uv(u * pixel, v * pixel).endVertex();
        tessellator.end();

        RenderSystem.disableBlend();

        GL11.glPopMatrix();
    }

    public static void drawCenteredString (MatrixStack matrixStack, String text, int x, int y, int zLevel, int color) {

        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, 50 + zLevel);
        mc.font.draw(matrixStack, text, x - (float) (mc.font.width(text) / 2), y, color);
        GL11.glPopMatrix();
    }

    public static void drawCappedRect (int x, int y, int u, int v, int zLevel, int width, int height, int maxWidth, int maxHeight) {

        //TOP LEFT
        drawRect(x, y, u, v, zLevel, Math.min(width - 2, maxWidth), Math.min(height - 2, maxHeight));

        //RIGHT
        if (width <= maxWidth) drawRect(x + width - 2, y, u + maxWidth - 2, v, zLevel, 2, Math.min(height - 2, maxHeight));

        //BOTTOM
        if (height <= maxHeight) drawRect(x, y + height - 2, u, v + maxHeight - 2, zLevel, Math.min(width - 2, maxWidth), 2);

        //BOTTOM RIGHT
        if (width <= maxWidth && height <= maxHeight) drawRect(x + width - 2, y + height - 2, u + maxWidth - 2, v + maxHeight - 2, zLevel, 2, 2);
    }
}
