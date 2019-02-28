package co.runed.magicmod.client.gui;

import co.runed.brace.gui.Widget;
import co.runed.magicmod.client.gui.widget.NodeWidget;
import co.runed.magicmod.container.TestSpellContainer;
import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.ContainerScreen;
import net.minecraft.text.StringTextComponent;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class TestSpellScreen extends ContainerScreen<TestSpellContainer> {
    public static final Identifier BACKGROUND = new Identifier("magicmod:textures/gui/spell_screen.png");

    private List<Widget> widgets = new ArrayList<>();

    private final Color nodeBackgroundColor = new Color(10, 10, 10);
    private final Color nodeBorderColor = new Color(7, 87, 45);
    //borders: blue: (7, 71, 87) red: (87, 7, 7) orange: (87, 37, 7) purple(47, 7, 87)

    public TestSpellScreen(TestSpellContainer container_1) {
        super(container_1, container_1.playerInventory, new StringTextComponent("Test"));

        this.widgets.add(new NodeWidget(nodeBackgroundColor.getRGB(), nodeBorderColor.getRGB(), Color.RED.getRGB(), nodeBorderColor.getRGB()));
        this.widgets.add(new NodeWidget(nodeBackgroundColor.getRGB(), nodeBorderColor.getRGB(), Color.RED.getRGB(), nodeBorderColor.getRGB()));
    }

    @Override
    public void draw(int mouseX, int mouseY, float float_1) {
        super.draw(mouseX, mouseY, float_1);

        for (int i = 0; i < this.widgets.size(); i++) {
            Widget widget = this.widgets.get(i);

            if(widget instanceof NodeWidget) {
                NodeWidget node = (NodeWidget) widget;
                node.setPosition(310 + (10 * i), (i + 1) * 60);

                node.update(mouseX, mouseY, float_1);
                node.render(mouseX, mouseY, float_1);

                if(i == this.widgets.size() - 1) {
                    NodeWidget next = (NodeWidget)this.widgets.get(i - 1);

                    Widget.drawLine(next.getBottomConnection(), node.getTopConnection(), 5, -1);
                }
            }
        }
    }

    @Override
    protected void drawBackground(float v, int i, int i1) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.client.getTextureManager().bindTexture(BACKGROUND);

        int x = (this.screenWidth - 238) / 2;
        int y = (this.screenHeight - 256) / 2;

        this.drawTexturedRect(x, y, 0, 0, 238, 256);
    }
}
