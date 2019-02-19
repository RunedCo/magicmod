package co.runed.magicmod.client.gui;

import co.runed.magicmod.client.gui.widget.NodeWidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;

import java.awt.Color;

@Environment(EnvType.CLIENT)
public class TestSpellScreen extends Screen {
    private NodeWidget test;

    private final Color nodeBackgroundColor = new Color(10, 10, 10,255);
    private final Color nodeBorderColor = new Color(0, 255, 121, 80);

    public TestSpellScreen() {
        this.test = new NodeWidget(nodeBackgroundColor, nodeBorderColor);
    }

    @Override
    public void draw(int int_1, int int_2, float float_1) {
        this.drawVerticalLine(100, 100 , 100 , 100);

        //this.test.addText("test");

        this.test.render(12, 100, 100, 50);
        this.test.render(10, 90, 104, 10);
        this.test.render(10, 220, 100, 50);


        this.drawTooltip("World Spell", 0, 20);

        this.drawTooltip("Vein I", 0, 40);

        this.drawTooltip("Break I", 0, 60);

        this.drawTooltip("Inventory I", 0, 80);
    }
}
