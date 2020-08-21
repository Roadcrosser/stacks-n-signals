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

import static stacksnsignals.Constants.*;

public class SolderGridCell {

    SolderGrid grid;

    WSolderGridCellWireSlot s1;
    WSolderGridCellWireSlot s2;

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
//
//
        s1 = parent.createChild(WSolderGridCellWireSlot::new, cellpos.add(1, 1, 5), Size.of(10, 10)).setParent(parent);
        s2 = parent.createChild(WSolderGridCellWireSlot::new, cellpos.add(11, 9, 5), Size.of(10, 10)).setParent(parent);
    }
    public int get_z(){
        return this.z;
    }

    public void set_locked(boolean lock_state){
        s1.set_locked(lock_state);
        s2.set_locked(lock_state);
    }
    public void set_visibility(boolean visibility){
//        num1.setHidden(visibility);
//        num2.setHidden(visibility);
    }
}
