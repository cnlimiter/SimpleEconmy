package cn.evolvefield.mods.simpleeco.client.gui.base;


import cn.evolvefield.mods.simpleeco.utils.ScreenUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;



public abstract class GuiScreenBase extends Screen {
    protected final PlayerEntity player;
    protected final Hand hand;

    protected GuiScreenBase(PlayerEntity player, Hand hand) {

        super(new StringTextComponent("Help"));
        this.player = player;
        this.hand = hand;
    }



    /**
     * Used to render anything in the background layer.
     */
    protected abstract void drawGuiBackground (MatrixStack matrixStack, int mouseX, int mouseY);

    /**
     * Used to render anything in the foreground layer.
     */
    protected abstract void drawGuiForeground (MatrixStack matrixStack, int mouseX, int mouseY);

    /**
     * Used to determine the width of the GUI.
     */
    protected abstract int getGuiSizeX ();

    /**
     * Used to determine the height of the GUI.
     */
    protected abstract int getGuiSizeY ();

    /**
     * Used to determine the left of the GUI.
     */
    protected int getScreenX () {
        return (this.width - getGuiSizeX()) / 2;
    }

    /**
     * Used to determine the top of the GUI.
     */
    protected int getScreenY () {
        return (this.height - getGuiSizeY()) / 2;
    }

    protected abstract boolean canCloseWithInvKey ();

    /**
     * The base render method. Handles ALL rendering.
     */
    @Override
    public void render (MatrixStack matrixStack, int mouseX, int mouseY, float f1) {

        renderBackground(matrixStack);

        drawGuiBackground(matrixStack, mouseX, mouseY);
        super.render(matrixStack, mouseX, mouseY, f1);
        drawGuiForeground(matrixStack, mouseX, mouseY);
    }

    /**
     * Handles closing the GUI when the inventory key is pressed.
     */
    @Override
    public boolean keyPressed (int i1, int i2, int i3) {
        super.keyPressed(i1, i2, i3);

        if (minecraft != null) {
            if (canCloseWithInvKey() && i1 == minecraft.gameSettings.keyBindInventory.getKey().getKeyCode()) {
                player.closeScreen();
                return true;
            }
        }
        return false;
    }
}
