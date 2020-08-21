package stacksnsignals.solder_grid;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import spinnery.client.render.BaseRenderer;
import spinnery.client.render.TextRenderer;
import spinnery.widget.WAbstractToggle;
import spinnery.widget.api.Color;
import stacksnsignals.Stacks_n_Signals;

import static stacksnsignals.Stacks_n_Signals.MOD_ID;

@Environment(EnvType.CLIENT)
public class WSolderGridCellWireSlot extends WAbstractToggle {

    protected boolean locked = false;
    protected int cell_position = 0;
    protected Identifier texture;
    protected Identifier texture_hover;

    public WSolderGridCellWireSlot set_cell_position(int cell_position){
        this.cell_position = cell_position;
        texture = new Identifier(MOD_ID, String.format("textures/ui/wires/%d.png", cell_position));
        texture_hover = new Identifier(MOD_ID, String.format("textures/ui/wires/%db.png", cell_position));
        return this;
    }

    @Override
    public void onMouseClicked(float mouseX, float mouseY, int mouseButton) {
        if (!locked){
            super.onMouseClicked(mouseX, mouseY, mouseButton);
        }
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

        if (getToggleState() || isFocused()){
            Identifier to_draw = texture;

            if (getToggleState() && isFocused()){
                to_draw = texture_hover;
            }
            BaseRenderer.drawTexturedQuad(matrices, provider, x, y, z, sX, sY, Color.of(getToggleState() ? 0xffffffff : 0x99ffffff), to_draw);
        }

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
