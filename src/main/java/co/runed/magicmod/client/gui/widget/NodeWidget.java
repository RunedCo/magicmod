package co.runed.magicmod.client.gui.widget;

import co.runed.brace.gui.Widget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.InputListener;
import net.minecraft.client.render.GuiLighting;
import net.minecraft.client.util.math.Vector3f;

import java.awt.*;

public class NodeWidget extends Widget {
    private final TextRenderer textRenderer;

    private Vector3f position;

    public int width = 70;
    public int height = 12;

    private final int backgroundColor;
    private final int borderColor;
    private final int hoverBackgroundColor;
    private final int hoverBorderColor;

    public NodeWidget(int backgroundColor, int borderColor, int hoverBackgroundColor, int hoverBorderColor) {
        this.textRenderer = MinecraftClient.getInstance().textRenderer;

        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;

        this.hoverBackgroundColor = new Color(this.backgroundColor).brighter().brighter().brighter().getRGB();
        this.hoverBorderColor = hoverBorderColor;

        this.width = 70;
        this.height = 12;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        boolean hover = this.isMouseOver(mouseX, mouseY);

        this.drawBox(x, y, width, height, hover ? this.hoverBackgroundColor : this.backgroundColor, this.borderColor);

        GuiLighting.disable();
        this.drawPlainStringCentered(textRenderer,"o", x + (width / 2), y + height - 2, -1);
        this.drawPlainStringCentered(textRenderer,"o", x + (width / 2), y + - 7, -1);
        this.drawPlainStringCentered(textRenderer,"x", x + width + 3, y + - 7, -1);

        this.drawStringCentered(textRenderer, "Inventory I", x + (width / 2), y + 2, -1);
        GuiLighting.disable();
    }

    @Override
    public void onMouseOver(int mouseX, int mouseY) {

    }

    public Vector3f getTopConnection() {
        return new Vector3f(x + (width / 2f) - 0.5f, y - 2, 0);
    }

    public Vector3f getBottomConnection() {
        return new Vector3f(x + (width / 2f) - 0.5f, y + height + 2, 0);
    }
}
