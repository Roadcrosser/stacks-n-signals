package stacksnsignals.solder_grid;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import spinnery.client.render.BaseRenderer;
import spinnery.widget.WAbstractWidget;
import spinnery.widget.WStaticImage;
import spinnery.widget.api.Color;

import java.util.ArrayList;
import java.util.List;

import static stacksnsignals.Stacks_n_Signals.MOD_ID;

@Environment(EnvType.CLIENT)
public class WSolderGridCellWireBridge extends WAbstractWidget {
    ArrayList<WSolderGridCellWireSlot> slot_list = new ArrayList<>();

    public WSolderGridCellWireBridge set_slots(List<WSolderGridCellWireSlot> slots){
        slot_list.addAll(slots);
        return this;
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

        String tex_path = "textures/ui/wires/";

        StringBuilder current_bridge = new StringBuilder(tex_path);
        StringBuilder ghost_bridge = new StringBuilder(tex_path);

        String png = ".png";


        int curr_connections = 0;
        int ghost_connections = 0;

        for (int i=0; i<slot_list.size(); i++){
            WSolderGridCellWireSlot curr_slot = slot_list.get(i);

            if (curr_slot.getToggleState()){
                current_bridge.append(i);
                ghost_bridge.append(i);
                curr_connections++;
                ghost_connections++;
            } else if (curr_slot.isFocused()){
                ghost_bridge.append(i);
                ghost_connections++;
            }

        }

        current_bridge.append(png);
        ghost_bridge.append(png);

        if (curr_connections > 1){
            BaseRenderer.drawTexturedQuad(matrices, provider, x, y, z, sX, sY, new Identifier(MOD_ID, current_bridge.toString()));


        }
        if (ghost_connections > 1 && !current_bridge.toString().equals(ghost_bridge.toString())){
            BaseRenderer.drawTexturedQuad(matrices, provider, x, y, z, sX, sY,Color.of(0x99ffffff), new Identifier(MOD_ID, ghost_bridge.toString()));
        }

        super.draw(matrices, provider);
    }

}
