package stacksnsignals.solder_grid;

import net.minecraft.text.LiteralText;
import org.apache.logging.log4j.Level;
import spinnery.widget.WAbstractWidget;
import spinnery.widget.WPanel;
import spinnery.widget.WStaticText;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;
import stacksnsignals.Stacks_n_Signals;
import stacksnsignals.Utils;

import static stacksnsignals.Constants.*;

public class SolderGridCell {

    private WStaticText num1;
    private WStaticText num2;

    private int x;
    private int y;
    private int z;

    public SolderGridCell(int x, int y, int z, WPanel parent){

        this.x = x;
        this.y = y;
        this.z = z;


        Position cellpos = Utils.calculate_grid_coordinates(parent);
        cellpos = cellpos.add((cell_size * x), (cell_size * y), 0);
//
//
        num1 = parent.createChild(WStaticText::new, cellpos.add(1, 1, 5), Size.of(50, 50)).setText(new LiteralText(Integer.toString(x))).setParent(parent);
        num2 = parent.createChild(WStaticText::new, cellpos.add(11, 9, 5), Size.of(50, 50)).setText(new LiteralText(Integer.toString(y))).setParent(parent);
    }

    public void set_visibility(boolean visibility){
        num1.setHidden(visibility);
        num2.setHidden(visibility);
    }
}
