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

    private final int backgroundColor;
    private final int borderColor;
    private final int hoverBackgroundColor;
    private final int hoverBorderColor;

    public boolean dragging = false;

    private int dragOffsetX;
    private int dragOffsetY;

    public NodeWidget(int backgroundColor, int borderColor, int hoverBackgroundColor, int hoverBorderColor) {
        this.textRenderer = MinecraftClient.getInstance().textRenderer;

        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;

        this.hoverBackgroundColor = new Color(this.backgroundColor).brighter().getRGB();
        this.hoverBorderColor = hoverBorderColor;

        this.width = 70;
        this.height = 12;
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
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
    public void update(int mouseX, int mouseY, float partialTicks) {
        super.update(mouseX, mouseY, partialTicks);

        if(this.dragging) {
            this.setPosition(mouseX + this.dragOffsetX, mouseY + this.dragOffsetY);
        }
    }

    @Override
    public void onMouseOver(int mouseX, int mouseY) {
        //boolean mouse = this.mouseClicked(mouseX, mouseY, 1);

        /* if(mouseClicked(mouseX, mouseY, 0)) {
            System.out.println("clicked");
        } */
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        System.out.println(String.format("%s %s %s", mouseX, mouseY, mouseButton));

        if(mouseButton == 0 && this.hovering) {
            this.dragging = true;

            this.dragOffsetX = this.x -(int)mouseX;
            this.dragOffsetY = this.y - (int)mouseY;

            this.setHasFocus(true);
        }

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean mouseReleased(double double_1, double double_2, int int_1) {
        if(this.dragging && int_1 == 0) {
            this.dragging = false;

            this.setHasFocus(false);
        }

        System.out.println(String.format("r %s %s %s", double_1, double_2, int_1));

        return super.mouseReleased(double_1, double_2, int_1);
    }


    @Override
    public boolean mouseDragged(double double_1, double double_2, int int_1, double double_3, double double_4) {
        System.out.println(String.format("%s %s %s %s %s", double_1, double_2, int_1, double_3, double_4));

        return super.mouseDragged(double_1, double_2, int_1, double_3, double_4);
    }

    public Vector3f getTopConnection() {
        return new Vector3f(x + (width / 2f) - 0.5f, y - 2, 0);
    }

    public Vector3f getBottomConnection() {
        return new Vector3f(x + (width / 2f) - 0.5f, y + height + 2, 0);
    }
}
