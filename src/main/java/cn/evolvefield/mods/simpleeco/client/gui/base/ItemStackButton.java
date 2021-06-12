package cn.evolvefield.mods.simpleeco.client.gui.base;

import cn.evolvefield.mods.simpleeco.utils.ScreenUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public abstract class ItemStackButton extends Button {

    protected ScreenRect rect;
    protected final ItemRenderer itemRenderer;

    /**
     * A base button used to render a given ItemStack.
     * @param pressable Called when the button is pressed.
     */
    public ItemStackButton(int x, int y, ItemRenderer itemRender, IPressable pressable) {
        super(x, y, 16, 16, new StringTextComponent(""), pressable);
        rect = new ScreenRect(this.x, this.y, width, height);
        this.itemRenderer = itemRender;
    }

    public abstract ItemStack getRenderedStack();
    public abstract String[] getTooltip();

    public void setRect(ScreenRect rect) {
        this.rect = rect;
        this.x = rect.x;
        this.y = rect.y;
        this.width = rect.width;
        this.height = rect.height;
    }

    @Override
    public void renderButton (MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

        if (this.visible && this.active) {

            isHovered = rect.contains(mouseX, mouseY);

            ScreenUtil.drawItemStack(itemRenderer, getRenderedStack(), rect.x, rect.y);
            ScreenUtil.drawHoveringTextBox(matrixStack, mouseX, mouseY, 150, rect, getTooltip());

            GL11.glColor4f(1, 1, 1, 1);
        }
    }
}
