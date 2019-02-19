package co.runed.magicmod.client.gui.widget;

import co.runed.brace.util.ColorUtil;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.render.GuiLighting;

import java.awt.Color;
import java.util.Iterator;

public class NodeWidget extends Drawable {
    private TextRenderer textRenderer;

    private int backgroundColor;
    private int borderColor;

    public NodeWidget(Color backgroundColor, Color borderColor) {
        this(ColorUtil.convertColorToInt(backgroundColor), ColorUtil.convertColorToInt(borderColor));
    }

    public NodeWidget(int backgroundColor, int borderColor) {
        this.textRenderer = MinecraftClient.getInstance().textRenderer;

        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    public void render(int x, int y, int width, int height) {
        GlStateManager.disableRescaleNormal();
        GuiLighting.disable();
        GlStateManager.disableLighting();
        GlStateManager.disableDepthTest();

        this.zOffset = 300.0F;
        this.drawGradientRect(x - 3, y - 4, x + width + 3, y - 3, this.backgroundColor, this.backgroundColor);
        this.drawGradientRect(x - 3, y + height + 3, x + width + 3, y + height + 4, this.backgroundColor, this.backgroundColor);
        this.drawGradientRect(x - 3, y - 3, x + width + 3, y + height + 3, this.backgroundColor, this.backgroundColor);
        this.drawGradientRect(x - 4, y - 3, x - 3, y + height + 3, this.backgroundColor, this.backgroundColor);
        this.drawGradientRect(x + width + 3, y - 3, x + width + 4, y + height + 3, this.backgroundColor, this.backgroundColor);
        this.drawGradientRect(x - 3, y - 3 + 1, x - 3 + 1, y + height + 3 - 1, this.borderColor, this.borderColor);
        this.drawGradientRect(x + width + 2, y - 3 + 1, x + width + 3, y + height + 3 - 1, this.borderColor, this.borderColor);
        this.drawGradientRect(x - 3, y - 3, x + width + 3, y - 3 + 1, this.borderColor, this.borderColor);
        this.drawGradientRect(x - 3, y + height + 2, x + width + 3, y + height + 3, this.borderColor, this.borderColor);
        this.zOffset = 0.0F;

        this.drawStringCentered(textRenderer, "Vein I", x + (width / 2), y + 1, -1);

        GlStateManager.enableLighting();
        GlStateManager.enableDepthTest();
        GuiLighting.enable();
        GlStateManager.enableRescaleNormal();
    }
}
