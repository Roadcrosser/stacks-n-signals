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

    public static boolean point_in_triangle(float xt, float yt, float x0, float y0, float x1, float y1, float x2, float y2){
        // Adapted from https://stackoverflow.com/a/34093754

        float dX = xt - x2;
        float dY = yt - y2;

        float dX21 = x2 - x1;
        float dY12 = y1 - y2;

        float D = dY12 * (x0 - x2) + dX21 * (y0 - y2);
        float s = dY12 * dX + dX21 * dY;
        float t = (y2 - y0) * dX + (x0 - x2) * dY;

        if (D < 0){
            return s <= 0 && t <= 0 && s + t >= D;
        }
        return s >= 0 && t >= 0 && s + t <= D;
    }

}
