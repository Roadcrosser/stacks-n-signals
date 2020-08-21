package stacksnsignals.solder_grid;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.apache.logging.log4j.Level;
import spinnery.client.render.BaseRenderer;
import spinnery.client.render.TextRenderer;
import spinnery.widget.WAbstractToggle;
import spinnery.widget.api.Color;
import stacksnsignals.Stacks_n_Signals;

@Environment(EnvType.CLIENT)
public class WSolderGridCellWireSlot extends WAbstractToggle {

    protected boolean locked = false;

    @Override
    public void onMouseClicked(float mouseX, float mouseY, int mouseButton) {
        if (!locked){
            super.onMouseClicked(mouseX, mouseY, mouseButton);
        }

        Stacks_n_Signals.log(Level.INFO, getToggleState() ? "on" : "off");
    }

    @Override
    public void draw(MatrixStack matrices, VertexConsumerProvider provider) {
        if (isHidden()) {
            return;
        }

        float x = getX();
        float y = getY();
        float z = getZ();

        float sX = getWidth();
        float sY = getHeight();

        Color col = getToggleState() ? Color.of(0xff0000ff) : Color.of(0xffff0033);
//        Color col = isFocused() ? Color.of(0xff0000ff) : Color.of(0xffff0033);

        BaseRenderer.drawQuad(matrices, provider, x, y, z, sX, 1, col);
        BaseRenderer.drawQuad(matrices, provider, x, y, z, 1, sY, col);
//
        BaseRenderer.drawQuad(matrices, provider, x, y + sY, z, sX, 1, col);
        BaseRenderer.drawQuad(matrices, provider, x + sX, y, z, 1, sY + 1, col);
//
//        BaseRenderer.drawQuad(matrices, provider, x + 1, y + 1, z, sX - 1, sY - 1, getToggleState() ? Color.of(0xff00ff00) : Color.of(0xffff0000));
//
//        if (getToggleState()) {
//            BaseRenderer.drawBeveledPanel(matrices, provider, x + sX - 8, y - 1, z, 8, sY + 3, Color.of(0xff373737), Color.of(0xffc6c6c6), Color.of(0xffffffff));
//        } else {
//            BaseRenderer.drawBeveledPanel(matrices, provider, x + 1, y - 1, z, 8, sY + 3, Color.of(0xff373737), Color.of(0xffc6c6c6), Color.of(0xffffffff));
//        }

        super.draw(matrices, provider);
    }

    public void set_locked(boolean lock_state){
        locked = lock_state;
    }

    @Override
    public boolean isFocusedMouseListener() {
        return true;
    }

}
