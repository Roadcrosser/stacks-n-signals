package stacksnsignals.solder_grid;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import spinnery.client.render.BaseRenderer;
import spinnery.widget.WAbstractWidget;
import spinnery.widget.api.Color;

@Environment(EnvType.CLIENT)
public class WSolderGridCellPlate extends WAbstractWidget {


    @Override
    public void draw(MatrixStack matrices, VertexConsumerProvider provider){
        matrices.push();

        BaseRenderer.drawBeveledPanel(matrices, provider, getX(), getY(), getZ(), getWidth(), getHeight(), Color.of(0xff373737), Color.of(0xffc6c6c6), Color.of(0xffffffff));
        matrices.pop();

        super.draw(matrices, provider);
    }
}
