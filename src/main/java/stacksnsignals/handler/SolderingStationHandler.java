package stacksnsignals.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import spinnery.common.handler.BaseScreenHandler;
import spinnery.widget.WInterface;
import spinnery.widget.WSlot;
import stacksnsignals.entity.SolderingStationEntity;
import stacksnsignals.registry.HandlerRegistry;

public class SolderingStationHandler extends BaseScreenHandler {
    public static final int SOLDERING_STATION_INVENTORY = 1;
    public Text name;
    public PlayerEntity player;
    public BlockPos pos;

    @Override
    public ScreenHandlerType<?> getType() {
        return HandlerRegistry.SOLDERING_STATION_HANDLER;
    }

    public SolderingStationHandler(int synchronizationID, PlayerInventory playerInventory, BlockPos pos) {
        super(synchronizationID, playerInventory);

        this.player = playerInventory.player;
        this.pos = pos;

        WInterface mainInterface = getInterface();
        addInventory(SOLDERING_STATION_INVENTORY, (SolderingStationEntity) player.world.getBlockEntity(pos));
        WSlot.addHeadlessArray(mainInterface, 0, SOLDERING_STATION_INVENTORY, 4, 1);
        WSlot.addHeadlessPlayerInventory(mainInterface);

    }
}
