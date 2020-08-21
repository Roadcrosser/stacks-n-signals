package stacksnsignals.solder_grid;

import net.minecraft.text.LiteralText;
import org.apache.logging.log4j.Level;
import spinnery.widget.WAbstractWidget;
import spinnery.widget.WPanel;
import spinnery.widget.WStaticText;
import spinnery.widget.WToggle;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;
import stacksnsignals.Stacks_n_Signals;
import stacksnsignals.Utils;

import java.util.ArrayList;
import java.util.List;

import static stacksnsignals.Constants.*;

public class SolderGridCell {

    SolderGrid grid;

    List<WSolderGridCellWireSlot> wire_slots = new ArrayList<>();
    WSolderGridCellWireBridge wire_bridge;

    private int x;
    private int y;
    private int z;

    public SolderGridCell(int x, int y, int z, WPanel parent, SolderGrid parent_grid){

        grid = parent_grid;

        this.x = x;
        this.y = y;
        this.z = z;

        Position cellpos = Utils.calculate_grid_coordinates(parent);
        cellpos = cellpos.add((cell_size * x), (cell_size * y), 0);

        int slot_l1 = 4;
        int slot_l2 = 6;
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
                wire_slots.add(parent.createChild(WSolderGridCellWireSlot::new).set_cell_position(i).setPosition(cellpos.add(slot_x, slot_y, 5)).setSize(Size.of(slot_width, slot_height)).setParent(parent));
            } else {
                wire_bridge = parent.createChild(WSolderGridCellWireBridge::new).set_slots(wire_slots).setPosition(cellpos.add(slot_x, slot_y, 5)).setSize(Size.of(slot_l1, slot_l1)).setParent(parent);
            }
        }


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
