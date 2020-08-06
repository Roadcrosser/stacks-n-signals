package stacksnsignals.registry;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import stacksnsignals.handler.SolderingStationHandler;

import static stacksnsignals.Stacks_n_Signals.SOLDERING_STATION_ID;

public class HandlerRegistry {

    public static ScreenHandlerType<SolderingStationHandler> SOLDERING_STATION_HANDLER;

    public HandlerRegistry(){

    }

    public static void initialize(){
        SOLDERING_STATION_HANDLER = ScreenHandlerRegistry.registerExtended(SOLDERING_STATION_ID, ((synchronizationID, inventory, buffer) -> {
            return new SolderingStationHandler(synchronizationID, inventory, buffer.readBlockPos());
        }));
    }
}
