package co.runed.magicmod.client.gui.widget;

import co.runed.brace.gui.AdvDrawable;
import co.runed.brace.util.ColorUtil;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.render.*;
import org.lwjgl.opengl.GL11;

import java.awt.Color;

import static org.lwjgl.opengl.GL11.GL_LINES;

public class NodeWidget extends AdvDrawable {
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
        this.drawBox(x, y, width, height, this.backgroundColor, this.borderColor);

        GuiLighting.disable();
        this.drawStringCentered(textRenderer,"o", x + (width / 2), y + height - 2, -1);
        this.drawStringCentered(textRenderer,"o", x + (width / 2), y + - 7, -1);

        GlStateManager.pushMatrix();
        GlStateManager.pushTextureAttributes();

        BufferBuilder bufferBuilder = Tessellator.getInstance().getBufferBuilder();

        //renderer..(GL11.GL_LINES);
//Your code here e.e renderer.addVertex(x,y,z);

        bufferBuilder.begin(GL_LINES, VertexFormats.POSITION);

        bufferBuilder.vertex(0, 0, 0).next();
        bufferBuilder.vertex(100, 100, 0).next();

        Tessellator.getInstance().draw();

        GlStateManager.popMatrix();
        GlStateManager.popAttributes();

        this.drawStringCentered(textRenderer, "Vein I", x + (width / 2), y + 2, -1);
        GuiLighting.disable();
    }
}
