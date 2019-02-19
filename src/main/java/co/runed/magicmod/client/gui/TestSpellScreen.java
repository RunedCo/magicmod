package co.runed.magicmod.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Screen;

@Environment(EnvType.CLIENT)
public class TestSpellScreen extends Screen {
    @Override
    public void draw(int int_1, int int_2, float float_1) {
        this.drawTooltip("test", 0, 0);
    }
}
