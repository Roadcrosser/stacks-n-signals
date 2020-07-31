package stacksnsignals.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import spinnery.common.container.BaseContainer;
import spinnery.widget.WAbstractWidget;
import spinnery.widget.WInterface;
import spinnery.widget.WSlot;

import java.util.Collection;

public class SolderingStationContainer extends BaseContainer {
    public static final int SOLDERING_STATION_INVENTORY = 1;
    public Text name;
    public PlayerEntity player;
    public int x;
    public int y;
    public int m;

    public SolderingStationContainer(int synchronizationID, Text name, PlayerInventory playerInventory, BlockPos pos, int x, int y, int m) {
        super(synchronizationID, playerInventory);

        this.name = name;
        this.player = playerInventory.player;
        this.x = x;
        this.y = y;
        this.m = m;

    }
}
