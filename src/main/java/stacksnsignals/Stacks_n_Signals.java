package stacksnsignals;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.impl.screenhandler.ExtendedScreenHandlerType;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stacksnsignals.block.SolderingStation;
import stacksnsignals.entity.SolderingStationEntity;
import stacksnsignals.handler.SolderingStationHandler;
import stacksnsignals.registry.BlockRegistry;
import stacksnsignals.registry.EntityRegistry;
import stacksnsignals.registry.HandlerRegistry;
import stacksnsignals.registry.ItemRegistry;
import stacksnsignals.screen.SolderingStationScreen;


public class Stacks_n_Signals implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "stacks-n-signals";
    public static final String MOD_NAME = "Stacks n' Signals";

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "general"),
            () -> new ItemStack(ItemRegistry.BREADBOARD_ITEM));

    public static final Identifier SOLDERING_STATION_ID = new Identifier(MOD_ID, "soldering_station");



    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");



        BlockRegistry.initialize();
        EntityRegistry.initialize();
        HandlerRegistry.initialize();
        ItemRegistry.initialize();
        }




    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}