package stacksnsignals.registry;

import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import stacksnsignals.Stacks_n_Signals;
import stacksnsignals.entity.SolderingStationEntity;

public class PacketRegistry {

    public PacketRegistry() {
        // NO-OP
    }

    public static void initialize(){
        ServerSidePacketRegistry.INSTANCE.register(Stacks_n_Signals.SOLDERING_UPDATE_PACKET_ID, SolderingStationEntity::solder_update_packet);
    }
}
