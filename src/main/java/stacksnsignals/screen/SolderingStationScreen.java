package stacksnsignals.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.apache.logging.log4j.Level;
import spinnery.client.screen.BaseHandledScreen;
import spinnery.widget.*;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;
import stacksnsignals.entity.SolderingStationEntity;
import stacksnsignals.handler.SolderingStationHandler;
import stacksnsignals.registry.ItemRegistry;

import static stacksnsignals.Stacks_n_Signals.log;

public class SolderingStationScreen extends BaseHandledScreen<SolderingStationHandler> {
    public SolderingStationScreen(Text name, SolderingStationHandler container, PlayerEntity player){
        super(name, container, player);

        SolderingStationEntity blockentity = (SolderingStationEntity) player.world.getBlockEntity(container.pos);

        ItemStack bread = blockentity.getStack(0);

        // TODO (Like right now): Unloading and Reloading chunk will print false negative on the first check, but be correct in subsequent checks. Plz fix me.
        if (bread.getItem() == ItemRegistry.BREADBOARD_ITEM){
            log(Level.INFO, "Bread is in slot 0");
        } else {
            log(Level.INFO, "Bread is not in slot 0");
        }
        
        
        WInterface mainInterface = getInterface();

        WPanel mainpanel = mainInterface.createChild(WPanel::new, Position.of(0, 0, 0), Size.of(80, 80)).setParent(mainInterface);
//        WToggle toggle_switch = mainInterface.createChild(WToggle::new, Position.of(0, 0, 0), Size.of(15, 15)).setParent(mainpanel);


        WSlot.addArray(Position.of(mainpanel).add(6, 18, 1), Size.of(18, 18), mainpanel, 0, SolderingStationHandler.SOLDERING_STATION_INVENTORY, 4, 1);
        WSlot.addPlayerInventory(Position.of(mainpanel).add(6, 85, 1), Size.of(18, 18), mainpanel);

        mainpanel.setOnAlign(WAbstractWidget::center);
        mainpanel.center();

//        toggle_switch.setOnAlign(WAbstractWidget::center);
//        toggle_switch.center();
//
//
//
//        toggle_switch.setOnMouseClicked( (widget, mouseX, mouseY, mouseButton) ->
//        {
//            container.player.sendMessage(new LiteralText("did thing " + (toggle_switch.getToggleState() ? "1" : "0")), false);
//
//        });




        mainInterface.add(mainpanel);
    }
}
