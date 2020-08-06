package stacksnsignals.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.registry.Registry;
import stacksnsignals.Stacks_n_Signals;
import stacksnsignals.block.SolderingStation;

import static stacksnsignals.Stacks_n_Signals.SOLDERING_STATION_ID;

public class BlockRegistry {
    public static final Block SOLDERING_STATION = new SolderingStation(FabricBlockSettings.of(Material.METAL).hardness(4.0f));

    public BlockRegistry() {
        // NO-OP
    }

    public static void initialize() {
        Registry.register(Registry.BLOCK, SOLDERING_STATION_ID, SOLDERING_STATION);
    }




}
