package stacksnsignals.solder_grid;

import spinnery.widget.WPanel;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;
import stacksnsignals.Utils;

import java.util.ArrayList;

import static stacksnsignals.Constants.*;

public class SolderGrid {
    private int z = 1;

    private ArrayList<ArrayList<ArrayList<SolderGridCell>>> grid = new ArrayList<>();

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
                    // drawn first so cell background does not render over other elements (that do not respect z axis)
                    if (i == 0) {
                        parent.createChild(WSolderGridCellPlate::new,
                                cellpos.add(cell_size * v, cell_size * u, 1),
                                Size.of(cell_size, cell_size)).setParent(parent);
                    }

                    grid.get(i).get(u).add(new SolderGridCell(v, u, i, parent));

                }

            }
        }
    }

    public void showlayer(int layer){
        for (int i=0; i<this.z; i++){
            for (int u=0; u<grid_size; u++){
                for (int v=0; v<grid_size; v++){
                    grid.get(i).get(u).get(v).set_visibility(layer == this.z);
                }
            }
        }
    }

    private void draw_cell(WPanel parent){


    }
}
