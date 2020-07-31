package stacksnsignals;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Stacks_n_Signals implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "stacks-n-signals";
    public static final String MOD_NAME = "Stacks n' Signals";

    public static final Block SOLDERING_STATION = new SolderingStation(FabricBlockSettings.of(Material.METAL).hardness(4.0f));

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "soldering_station"), SOLDERING_STATION);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "soldering_station"), new BlockItem(SOLDERING_STATION, new Item.Settings().group(ItemGroup.MISC)));
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}