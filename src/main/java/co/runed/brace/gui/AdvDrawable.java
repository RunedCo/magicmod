package co.runed.brace.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.render.GuiLighting;

public class AdvDrawable extends Drawable {
    public void drawBox(int x, int y, int width, int height, int backgroundColor, int borderColor) {
        GlStateManager.disableRescaleNormal();
        GuiLighting.disable();
        GlStateManager.disableLighting();
        GlStateManager.disableDepthTest();

        this.drawGradientRect(x - 3, y - 4, x + width + 3, y - 3, backgroundColor, backgroundColor);
        this.drawGradientRect(x - 3, y + height + 3, x + width + 3, y + height + 4, backgroundColor, backgroundColor);
        this.drawGradientRect(x - 3, y - 3, x + width + 3, y + height + 3, backgroundColor, backgroundColor);
        this.drawGradientRect(x - 4, y - 3, x - 3, y + height + 3, backgroundColor, backgroundColor);
        this.drawGradientRect(x + width + 3, y - 3, x + width + 4, y + height + 3, backgroundColor, backgroundColor);

        this.drawGradientRect(x - 3, y - 3 + 1, x - 3 + 1, y + height + 3 - 1, borderColor, borderColor);
        this.drawGradientRect(x + width + 2, y - 3 + 1, x + width + 3, y + height + 3 - 1, borderColor, borderColor);
        this.drawGradientRect(x - 3, y - 3, x + width + 3, y - 3 + 1, borderColor, borderColor);
        this.drawGradientRect(x - 3, y + height + 2, x + width + 3, y + height + 3, borderColor, borderColor);

        GlStateManager.enableLighting();
        GlStateManager.enableDepthTest();
        GuiLighting.enable();
        GlStateManager.enableRescaleNormal();
    }    
}
