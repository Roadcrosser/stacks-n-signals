package stacksnsignals.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import spinnery.common.container.BaseContainer;
import spinnery.common.handler.BaseScreenHandler;
import spinnery.widget.WAbstractWidget;
import spinnery.widget.WInterface;
import spinnery.widget.WSlot;
import stacksnsignals.Stacks_n_Signals;

import java.util.Collection;

public class SolderingStationHandler extends BaseScreenHandler {
    public static final int SOLDERING_STATION_INVENTORY = 1;
    public Text name;
    public PlayerEntity player;
    public int x;
    public int y;
    public int m;

    public ScreenHandlerType<?> getType() {
        return Stacks_n_Signals.SOLDERING_STATION_HANDLER;
    }

    public SolderingStationHandler(int synchronizationID, PlayerInventory playerInventory, BlockPos pos) {
        super(synchronizationID, playerInventory);

        this.player = playerInventory.player;

    }
}
