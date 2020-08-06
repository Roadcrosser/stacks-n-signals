package stacksnsignals.registry;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import stacksnsignals.entity.SolderingStationEntity;

import static stacksnsignals.Stacks_n_Signals.SOLDERING_STATION_ID;
import static stacksnsignals.registry.BlockRegistry.SOLDERING_STATION;

public class EntityRegistry {
    public static BlockEntityType<SolderingStationEntity> SOLDERING_STATION_ENTITY;

    public EntityRegistry() {
        // NO-OP
    }

    public static void initialize() {
        SOLDERING_STATION_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, SOLDERING_STATION_ID, BlockEntityType.Builder.create(SolderingStationEntity::new, SOLDERING_STATION).build(null));
    }

}
