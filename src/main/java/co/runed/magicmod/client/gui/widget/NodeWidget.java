package co.runed.magicmod.client.gui.widget;

import co.runed.brace.gui.AdvDrawable;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.Vector3f;

public class NodeWidget extends AdvDrawable {
    private final TextRenderer textRenderer;

    private Vector3f position;

    public int x;
    public int y;

    public int width = 70;
    public int height = 12;

    private final int backgroundColor;
    private final int borderColor;

    public NodeWidget(int backgroundColor, int borderColor) {
        this.textRenderer = MinecraftClient.getInstance().textRenderer;

        this.x = 0;
        this.y = 0;

        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
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

    public void render() {
        this.drawBox(x, y, width, height, this.backgroundColor, this.borderColor);

        GuiLighting.disable();
        this.drawPlainStringCentered(textRenderer,"o", x + (width / 2), y + height - 2, -1);
        this.drawPlainStringCentered(textRenderer,"o", x + (width / 2), y + - 7, -1);
        this.drawPlainStringCentered(textRenderer,"x", x + width + 3, y + - 7, -1);

        this.drawStringCentered(textRenderer, "Inventory I", x + (width / 2), y + 2, -1);
        GuiLighting.disable();
    }

    public Vector3f getTopConnection() {
        return new Vector3f(x + (width / 2f) - 0.5f, y - 2, 0);
    }

    public Vector3f getBottomConnection() {
        return new Vector3f(x + (width / 2f) - 0.5f, y + height + 2, 0);
    }
}
