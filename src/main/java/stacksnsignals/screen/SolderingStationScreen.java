package stacksnsignals.screen;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.apache.logging.log4j.Level;
import spinnery.client.screen.BaseHandledScreen;
import spinnery.widget.*;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;
import spinnery.widget.api.WInputFilter;
import stacksnsignals.Stacks_n_Signals;
import stacksnsignals.entity.SolderingStationEntity;
import stacksnsignals.handler.SolderingStationHandler;
import stacksnsignals.item.BreadboardItem;
import stacksnsignals.registry.ItemRegistry;

import static stacksnsignals.Stacks_n_Signals.log;

public class SolderingStationScreen extends BaseHandledScreen<SolderingStationHandler> {

    WTextField textfield = null;

    public void update_text_field(ItemStack bread) {
        String bread_text = null;

        if (bread.getItem() == ItemRegistry.BREADBOARD_ITEM) {
            CompoundTag bread_tag = bread.getTag();
            if (bread_tag != null) {
                bread_text = bread_tag.getString("aaa");
            }
            textfield.setText("aaa");

        }
    }

    public SolderingStationScreen(Text name, SolderingStationHandler container, PlayerEntity player){
        super(name, container, player);

        SolderingStationEntity blockentity = (SolderingStationEntity) player.world.getBlockEntity(container.pos);

        ItemStack bread = blockentity.getStack(0);
        String bread_text = null;

        // TODO (Like right now): Unloading and Reloading chunk will print false negative on the first check, but be correct in subsequent checks. Plz fix me.
        if (bread.getItem() == ItemRegistry.BREADBOARD_ITEM){
            log(Level.INFO, "Bread is in slot 0");
            CompoundTag bread_tag = bread.getTag();
            if (bread_tag != null){
                bread_text = bread_tag.getString("aaa");
            }
        } else {
            log(Level.INFO, "Bread is not in slot 0");
        }






        WInterface mainInterface = getInterface();

        WPanel mainpanel = mainInterface.createChild(WPanel::new, Position.of(0, 0, 0), Size.of(300, 100)).setParent(mainInterface);
//        WToggle toggle_switch = mainInterface.createChild(WToggle::new, Position.of(0, 0, 0), Size.of(15, 15)).setParent(mainpanel);


        WSlot.addArray(Position.of(mainpanel).add(0, 18, 1), Size.of(18, 18), mainpanel, 0, SolderingStationHandler.SOLDERING_STATION_INVENTORY, 4, 1);
        WSlot.addPlayerInventory(Position.of(mainpanel).add(0, 85, 1), Size.of(18, 18), mainpanel);
        textfield = mainpanel.createChild(WTextField::new, Position.of(80, 10, 2), Size.of(100, 20)).setParent(mainpanel);
        if (bread_text != null){
            textfield.setText(bread_text);
        }

        WButton button = mainpanel.createChild(WButton::new, Position.of(200, 10, 1), Size.of(10, 10)).setParent(mainpanel);


        mainpanel.setOnAlign(WAbstractWidget::center);
        mainpanel.center();

        button.setOnMouseClicked((widget, mouseX, mouseY, mouseButton) ->
        {
            container.player.sendMessage(new LiteralText("text: " + textfield.getText()), false);

            PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
            passedData.writeBlockPos(container.pos);
            passedData.writeString(textfield.getText());

            ClientSidePacketRegistry.INSTANCE.sendToServer(Stacks_n_Signals.SOLDERING_UPDATE_PACKET_ID, passedData);

        });

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
