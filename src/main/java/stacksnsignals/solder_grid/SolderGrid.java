package stacksnsignals.solder_grid;

import org.apache.logging.log4j.Level;
import spinnery.widget.WPanel;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;
import stacksnsignals.Stacks_n_Signals;
import stacksnsignals.Utils;

import java.util.ArrayList;

import static stacksnsignals.Constants.*;

public class SolderGrid {
    boolean wire_mode = false;

    private int z = 1;

    final private ArrayList<ArrayList<ArrayList<SolderGridCell>>> grid = new ArrayList<>();

    // TODO: add/handle reset & expand methods to avoid destroying classes
    public SolderGrid(int height, WPanel parent) {

        this.z = height;

        // grid display coord calculation
        Position cellpos = Utils.calculate_grid_coordinates(parent);

        for (int i=0; i<z; i++){
            grid.add(new ArrayList<>());
            for (int u=0; u<grid_size; u++){
                grid.get(i).add(new ArrayList<>());
                for (int v=0; v<grid_size; v++) {
                    // draw display grid on first layer
                    // drawn first so cell background does not render over other elements (that do not respect z axis)'
                    Position curr_cell_pos = cellpos.add(cell_size * v, cell_size * u, 1);
                    if (i == 0) {
                        parent.createChild(WSolderGridCellPlate::new,
                                curr_cell_pos,
                                Size.of(cell_size, cell_size)).setParent(parent);
                    }

                    grid.get(i).get(u).add(new SolderGridCell(v, u, i, parent, this, curr_cell_pos));

                }

            }
        }
    }

    public boolean wire_mode(){
        return wire_mode;
    }

    public void show_layer(int layer){
        grid.forEach((row) -> row.forEach((line) -> line.forEach((cell) -> cell.set_visibility(layer == cell.get_z()))));
    }

    public void set_locked(boolean lock_state){
        grid.forEach((row) -> row.forEach((line) -> line.forEach((cell) -> cell.set_locked(lock_state))));
    }

}
