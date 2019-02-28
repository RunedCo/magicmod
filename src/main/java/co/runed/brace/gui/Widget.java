package co.runed.brace.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.InputListener;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.GuiLighting;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.Vector3f;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class Widget extends DrawableHelper implements InputListener {
    public int x;
    public int y;

    public int width;
    public int height;

    public void update(int mouseX, int mouseY, float float_1) {
        if(this.isMouseOver(mouseX, mouseY)) {
            this.onMouseOver(mouseX, mouseY);
        }
    }

    public void render(int mouseX, int mouseY, float float_1) {

    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void onMouseOver(int mouseX, int mouseY) {

    }

    public void onMouseOff() {

    }

    public boolean isMouseOver(int x, int y) {
        if(x >= this.x - 4 && x <= this.x + this.width + 4) {
            if(y >= this.y - 4 && y <= this.y + this.height + 4) {
                return true;
            }
        }

        return false;
    }

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

    public void drawPlainStringCentered(TextRenderer textRenderer_1, String string_1, int int_1, int int_2, int int_3) {
        textRenderer_1.draw(string_1, (float)(int_1 - textRenderer_1.getStringWidth(string_1) / 2), (float)int_2, int_3);
    }

    public static void drawLine(Vector3f startPos, Vector3f endPos, int lineWidth, int color) {
        Widget.drawLine(startPos, endPos, lineWidth, color, color);
    }

    public static void drawLine(Vector3f startPos, Vector3f endPos, int lineWidth, int color1, int color2) {
        Color color_1 = new Color(color1);
        Color color_2 = new Color(color2);

        GlStateManager.disableTexture();
        GlStateManager.enableBlend();
        GlStateManager.disableAlphaTest();
        GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        GlStateManager.lineWidth(lineWidth);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBufferBuilder();
        bufferBuilder.begin(GL11.GL_LINES, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(startPos.x(), startPos.y(), 1).color(color_1.getRed(), color_1.getGreen(), color_1.getBlue(), color_1.getAlpha()).next();
        bufferBuilder.vertex(endPos.x(), endPos.y(), 1).color(color_2.getRed(), color_2.getGreen(), color_2.getBlue(), color_2.getAlpha()).next();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlphaTest();
        GlStateManager.enableTexture();
    }
}
