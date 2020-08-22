package stacksnsignals.solder_grid;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import spinnery.client.render.BaseRenderer;
import spinnery.widget.WAbstractToggle;
import spinnery.widget.api.Color;
import stacksnsignals.Utils;

import java.util.ArrayList;
import java.util.List;

import static stacksnsignals.Stacks_n_Signals.MOD_ID;

@Environment(EnvType.CLIENT)
public class WSolderGridCellWireSlot extends WAbstractToggle {

    protected boolean locked = false;
    protected int cell_location = 0;
    protected Identifier texture;
    protected Identifier texture_hover;

    ArrayList<WSolderGridCellWireSlot> slot_list = new ArrayList<>();

    public WSolderGridCellWireSlot set_cell_location(int cell_location){
        this.cell_location = cell_location;
        texture = new Identifier(MOD_ID, String.format("textures/ui/wires/%d.png", cell_location));
        texture_hover = new Identifier(MOD_ID, String.format("textures/ui/wires/%db.png", cell_location));
        return this;
    }

    public WSolderGridCellWireSlot set_slots(List<WSolderGridCellWireSlot> slots){
        slot_list.addAll(slots);
        return this;
    }

    @Override
    public void onMouseClicked(float mouseX, float mouseY, int mouseButton) {
        if (!locked){
            super.onMouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    public boolean within_bounds_raw_check(float xt, float yt){
        float x0 = 0, y0 = 0, x1 = 0, y1 = 0, x2 = 0, y2 = 0;

        switch (cell_location){
            case 0:
                x0 = getX();
                x1 = getWideX();
                x2 = getX() + (getWidth()/2);

                y0 = getY();
                y1 = getY();
                y2 = getHighY();

                break;
            case 1:
                x0 = getWideX();
                x1 = getWideX();
                x2 = getX();

                y0 = getY();
                y1 = getHighY();
                y2 = getY() + (getHeight()/2);

                break;
            case 2:
                x0 = getWideX();
                x1 = getX();
                x2 = getX() + (getWidth()/2);

                y0 = getHighY();
                y1 = getHighY();
                y2 = getY();

                break;
            case 3:
                x0 = getX();
                x1 = getX();
                x2 = getWideX();

                y0 = getY();
                y1 = getHighY();
                y2 = getY() + (getHeight()/2);

                break;
        }

        return Utils.point_in_triangle(xt, yt, x0, y0, x1, y1, x2, y2);
    }

    @Override
    public boolean isWithinBounds(float positionX, float positionY){
        if (!within_bounds_raw_check(positionX, positionY) || locked){
            return false;
        }

        for (int i = 0; i < slot_list.size(); i++){
            if (i == cell_location){
                continue;
            }
            if (slot_list.get(i).within_bounds_raw_check(positionX, positionY)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void draw(MatrixStack matrices, VertexConsumerProvider provider) {
        if (isHidden() || (!getToggleState() && locked)) {
            return;
        }

        float x = getX();
        float y = getY();
        float z = getZ();

        float sX = getWidth();
        float sY = getHeight();

        switch (cell_location) {
            case 0:
                x += 6;
                sX -= 12;
                sY -= 2;
                break;
            case 1:
                x += 2;
                sX -= 2;
                y += 6;
                sY -= 12;
                break;
            case 2:
                x += 6;
                sX -= 12;
                y += 2;
                sY -= 2;
                break;
            case 3:
                sX -= 2;
                y += 6;
                sY -= 12;
                break;

        }

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
