package stacksnsignals;

import spinnery.widget.WPanel;
import spinnery.widget.api.Position;

import static stacksnsignals.Constants.*;
import static stacksnsignals.Constants.grid_start_y_offset;

public class Utils {
    public static Position calculate_grid_coordinates(WPanel parent){
        Position cellpos = Position.of(parent);
        float start_y = cellpos.getY() + (parent.getHeight() - (float)(cell_size * grid_size))/2;
        cellpos = cellpos.add(parent.getWidth()/2, start_y, 0);
        cellpos = cellpos.add(grid_start_x_offset, grid_start_y_offset, 0);

        return cellpos;
    }
}
