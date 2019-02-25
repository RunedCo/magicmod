package co.runed.magicmod.client.gui;

import co.runed.magicmod.client.gui.widget.NodeWidget;
import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.Vector3f;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class TestSpellScreen extends Screen {
    private NodeWidget test;

    List<NodeWidget> nodes;


    private final Color nodeBackgroundColor = new Color(10, 10, 10);
    private final Color nodeBorderColor = new Color(7, 87, 45);
    //borders: blue: (7, 71, 87) red: (87, 7, 7) orange: (87, 37, 7) purple(47, 7, 87)

    public TestSpellScreen() {
        this.nodes = new ArrayList<>();

        this.nodes.add(new NodeWidget(nodeBackgroundColor.getRGB(), nodeBorderColor.getRGB()));
        this.nodes.add(new NodeWidget(nodeBackgroundColor.getRGB(), nodeBorderColor.getRGB()));

        //int a = 0x07572D;
    }

    @Override
    public void method_18326(int mouseX, int mouseY, float float_1) {
        for (int i = 0; i < this.nodes.size(); i++) {
            NodeWidget node = this.nodes.get(i);

            node.setPosition(50 + (10 * i), (i + 1) * 60);

            node.render();

            if(i == this.nodes.size() - 1) {
                this.drawLine(this.nodes.get(i - 1).getBottomConnection(), node.getTopConnection(), 5, -1);
            }
        }
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
