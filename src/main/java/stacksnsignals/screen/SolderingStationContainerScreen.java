package stacksnsignals.screen;

import net.minecraft.text.LiteralText;
import spinnery.client.screen.BaseContainerScreen;
import spinnery.widget.*;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;
import stacksnsignals.handler.SolderingStationContainer;

public class SolderingStationContainerScreen extends BaseContainerScreen<SolderingStationContainer> {
    public SolderingStationContainerScreen(SolderingStationContainer container){
        // Text name, ShestContainer linkedContainer, PlayerEntity player, int x, int y, int m
        super(container.name, container, container.player);

        int x = container.x;
        int y = container.y;
        int m = container.m;

        WInterface mainInterface = getInterface();

        WPanel mainpanel = mainInterface.createChild(WPanel::new, Position.of(0, 0, 0), Size.of(80, 80)).setParent(mainInterface);
        WToggle toggle_switch = mainInterface.createChild(WToggle::new, Position.of(0, 0, 0), Size.of(15, 15)).setParent(mainpanel);

        mainpanel.setOnAlign(WAbstractWidget::center);
        mainpanel.center();

        toggle_switch.setOnAlign(WAbstractWidget::center);
        toggle_switch.center();

        toggle_switch.setOnMouseClicked( (widget, mouseX, mouseY, mouseButton) ->
        {
            container.player.sendMessage(new LiteralText("did thing " + (toggle_switch.getToggleState() ? "1" : "0")), false);
        });




        mainInterface.add(mainpanel);
    }
}
