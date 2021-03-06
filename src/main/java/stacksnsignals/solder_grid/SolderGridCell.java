package stacksnsignals.solder_grid;

import spinnery.widget.WPanel;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;

import java.util.ArrayList;
import java.util.List;

import static stacksnsignals.Constants.*;

public class SolderGridCell {

    SolderGrid grid;

    List<WSolderGridCellDirectionalSlot> wire_slots = new ArrayList<>();
    WSolderGridCellWireBridge wire_bridge;
    WSolderGridCellComponentSlot component_slot;

    private int x;
    private int y;
    private int z;

    public SolderGridCell(int x, int y, int z, WPanel parent, SolderGrid parent_grid, Position cell_position){

        grid = parent_grid;

        this.x = x;
        this.y = y;
        this.z = z;

        int slot_b = 4;
        int slot_bs = 7;

        int slot_l1 = 16;
        int slot_l2 = 8;
        for (int i=0; i<5; i++){
            int slot_width = slot_l1;
            int slot_height = slot_l2;

            float slot_x = (float)(cell_size - slot_width)/2;
            float slot_y = 1;

            if (i % 2 != 0 || i == 4){
                slot_width = slot_l2;
                slot_height = slot_l1;
                slot_y = (float)(cell_size - slot_height)/2;
            }

            switch (i){
//                case 0:
//                    break;
                case 1:
                    slot_x = cell_size - slot_width - 1;
                    break;
                case 2:
                    slot_y = cell_size - slot_height - 1;
                    break;
                case 3:
                    slot_x = 1;
                    break;
                default:
                    break;
            }

            if (i != 4){
                wire_slots.add(parent.createChild(WSolderGridCellDirectionalSlot::new)
                        .set_cell_location(i)
                        .setPosition(cell_position.add(slot_x, slot_y, 5))
                        .setSize(Size.of(slot_width, slot_height))
                        .setParent(parent));
            } else {
                wire_bridge = parent.createChild(WSolderGridCellWireBridge::new)
                        .set_slots(wire_slots)
                        .setPosition(cell_position.add(slot_bs, slot_bs, 5))
                        .setSize(Size.of(slot_b, slot_b))
                        .setParent(parent);
            }
        }

        wire_slots.forEach((slot) -> slot.set_slots(wire_slots));

        component_slot = parent.createChild(WSolderGridCellComponentSlot::new)
            .set_directional_slots(wire_slots)
            .setPosition(cell_position.add(1, 1, 6))
            .setSize(Size.of(cell_size-1, cell_size-1));


    }
    public int get_z(){
        return this.z;
    }

    public void set_locked(boolean lock_state){
        wire_slots.forEach((slot) -> slot.set_locked(lock_state));
    }
    public void set_visibility(boolean visibility){
        wire_slots.forEach((slot) -> slot.setHidden(visibility));
    }
}
