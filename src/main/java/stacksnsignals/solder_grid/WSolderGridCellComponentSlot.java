package stacksnsignals.solder_grid;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import spinnery.client.render.BaseRenderer;
import spinnery.widget.WAbstractButton;
import spinnery.widget.api.Color;

import java.util.ArrayList;
import java.util.List;

import static stacksnsignals.Constants.angles;
import static stacksnsignals.Stacks_n_Signals.MOD_ID;

public class WSolderGridCellComponentSlot extends WAbstractButton {
    List<WSolderGridCellDirectionalSlot> directional_slots = new ArrayList<>();

    public WSolderGridCellComponentSlot set_directional_slots(List<WSolderGridCellDirectionalSlot> slots){
        directional_slots.addAll(slots);

        return this;
    }

    @Override
    public void draw(MatrixStack matrices, VertexConsumerProvider provider){
        if (isHidden() || !isFocused()){
            return;
        }

        float x = getX();
        float y = getY();
        float z = getZ();

        float sX = getWidth();
        float sY = getHeight();

        String tex_path = "textures/components/diode.png";
        Identifier texture = new Identifier(MOD_ID, tex_path);

        int direction = 0;

        for (int i=0; i<directional_slots.size(); i++){
            WSolderGridCellDirectionalSlot curr_slot = directional_slots.get(i);

            if (curr_slot.isFocused()){
                direction = i;
                break;
            }
        }

        int angle = angles[direction];

        matrices.push();

        float dX = 0, dY = 0;

        switch (direction){
            case 0:
                dX = x;
                dY = y;
                break;
            case 1:
                dX = x+sX;
                dY = y;
                break;
            case 2:
                dX = x+sX;
                dY = y+sY;
                break;
            case 3:
                dX = x;
                dY = y+sY;
        }

        matrices.translate(dX, dY, z);
        matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(angle));
        BaseRenderer.drawTexturedQuad(matrices, provider, 0, 0, 0, sX, sY, Color.of(0x99ffffff), texture);

        matrices.pop();



    }
}
