package stacksnsignals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import stacksnsignals.handler.SolderingStationHandler;
import stacksnsignals.registry.HandlerRegistry;
import stacksnsignals.screen.SolderingStationScreen;

public class Stacks_n_Signals_client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(HandlerRegistry.SOLDERING_STATION_HANDLER, (ScreenRegistry.Factory<SolderingStationHandler, SolderingStationScreen>) ((handler, inventory, title) -> {
            return new SolderingStationScreen(title, handler, inventory.player);
        }));
    }
}
