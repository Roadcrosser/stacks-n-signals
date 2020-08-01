package stacksnsignals;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stacksnsignals.block.SolderingStation;
import stacksnsignals.entity.SolderingStationEntity;
import stacksnsignals.handler.SolderingStationHandler;
import stacksnsignals.screen.SolderingStationScreen;


public class Stacks_n_Signals implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "stacks-n-signals";
    public static final String MOD_NAME = "Stacks n' Signals";

    public static final Block SOLDERING_STATION = new SolderingStation(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
    public static final Identifier SOLDERING_STATION_ID = new Identifier(MOD_ID, "soldering_station");
    public static BlockEntityType<SolderingStationEntity> SOLDERING_STATION_ENTITY;
    public static ScreenHandlerType<SolderingStationHandler> SOLDERING_STATION_HANDLER;

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");

        Registry.register(Registry.BLOCK, SOLDERING_STATION_ID, SOLDERING_STATION);
        Registry.register(Registry.ITEM, SOLDERING_STATION_ID, new BlockItem(SOLDERING_STATION, new Item.Settings().group(ItemGroup.MISC)));
        ScreenHandlerType<SolderingStationHandler> SOLDERING_STATION_HANDLER = ScreenHandlerRegistry.registerExtended(SOLDERING_STATION_ID, ((synchronizationID, inventory, buffer) -> {
            return new SolderingStationHandler(synchronizationID, inventory, buffer.readBlockPos());
        }));
        ScreenRegistry.register(SOLDERING_STATION_HANDLER, (ScreenRegistry.Factory<SolderingStationHandler, SolderingStationScreen>) ((handler, inventory, title) -> {
            return new SolderingStationScreen(title, handler, inventory.player);
        }));

        SOLDERING_STATION_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, SOLDERING_STATION_ID, BlockEntityType.Builder.create(SolderingStationEntity::new, SOLDERING_STATION).build(null));
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}