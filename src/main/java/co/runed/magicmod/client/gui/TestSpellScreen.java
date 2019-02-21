package co.runed.magicmod.client.gui;

import co.runed.brace.util.ColorUtil;
import co.runed.magicmod.client.gui.widget.NodeWidget;
import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.Vector3f;
import org.lwjgl.opengl.GL11;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class TestSpellScreen extends Screen {
    private NodeWidget test;

    List<NodeWidget> nodes;


    private final Color nodeBackgroundColor = new Color(10, 10, 10);
    private final Color nodeBorderColor = new Color(7, 87, 45);
    //borders: blue: (7, 71, 87) red: (87, 7, 7) orange: (87, 37, 7) purple(47, 7, 87)

    public TestSpellScreen() {
        this.nodes = new LinkedList<>();

        this.nodes.add(new NodeWidget(nodeBackgroundColor.getRGB(), nodeBorderColor.getRGB()));
        this.nodes.add(new NodeWidget(nodeBackgroundColor.getRGB(), nodeBorderColor.getRGB()));
    }

    @Override
    public void method_18326(int mouseX, int mouseY, float float_1) {

        //this.test.addText("test");

        GlStateManager.pushMatrix();
        //GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        for (int i = 0; i < this.nodes.size(); i++) {
            NodeWidget node = this.nodes.get(i);

            node.setPosition(50 + (10 * i), (i + 1) * 60);
            //if(i == 1) {
            //    node.setPosition(mouseX, mouseY);
            //}

            node.render();

            if(i == this.nodes.size() - 1) {
                this.drawLine(this.nodes.get(i - 1).getBottomConnection(), node.getTopConnection(), 5, -1);
            }
        }

        GlStateManager.popMatrix();

        this.drawString(MinecraftClient.getInstance().textRenderer, "x: " + mouseX + " y: " + mouseY, 10, 10, -1);

        //new OptionSliderWidget().render(int_1, int_2, float_1);

        //this.zOffset = 200;
        //this.drawLine(new Vector3f(45.5f, 132, 0), new Vector3f(45.5f, 220 - 2, 0), 5, -1);
        //this.zOffset = 0;
    }

    public void drawLine(Vector3f startPos, Vector3f endPos, int lineWidth, int color) {
        this.drawLine(startPos, endPos, lineWidth, color, color);
    }

    public void drawLine(Vector3f startPos, Vector3f endPos, int lineWidth, int color1, int color2) {
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
        bufferBuilder.vertex(startPos.x(), startPos.y(), (double)this.zOffset).color(color_1.getRed(), color_1.getGreen(), color_1.getBlue(), color_1.getAlpha()).next();
        bufferBuilder.vertex(endPos.x(), endPos.y(), (double)this.zOffset).color(color_2.getRed(), color_2.getGreen(), color_2.getBlue(), color_2.getAlpha()).next();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlphaTest();
        GlStateManager.enableTexture();
    }
}
