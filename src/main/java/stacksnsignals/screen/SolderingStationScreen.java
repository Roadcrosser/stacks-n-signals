package stacksnsignals.screen;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableInt;
import spinnery.client.screen.BaseHandledScreen;
import spinnery.widget.*;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;
import stacksnsignals.Stacks_n_Signals;
import stacksnsignals.entity.SolderingStationEntity;
import stacksnsignals.handler.SolderingStationHandler;
import stacksnsignals.registry.ItemRegistry;
import stacksnsignals.solder_grid.SolderGrid;
import stacksnsignals.solder_grid.WSolderGridCellPlate;

import static stacksnsignals.Constants.cell_size;

public class SolderingStationScreen extends BaseHandledScreen<SolderingStationHandler> {

    WTextField textfield = null;
    SolderGrid soldergrid;

    boolean bool = false;

    public void update_text_field(ItemStack bread) {
        String bread_text = null;

        if (bread.getItem() == ItemRegistry.BREADBOARD_ITEM) {
            CompoundTag bread_tag = bread.getTag();
            if (bread_tag != null) {
                bread_text = bread_tag.getString("aaa");
            }
            textfield.setText(bread_text);

        }
    }

    public SolderingStationScreen(Text name, SolderingStationHandler container, PlayerEntity player){
        super(name, container, player);

        SolderingStationEntity blockentity = (SolderingStationEntity) player.world.getBlockEntity(container.pos);

        ItemStack bread = blockentity.getStack(0);
        String bread_text = null;

        if (bread.getItem() == ItemRegistry.BREADBOARD_ITEM){
            CompoundTag bread_tag = bread.getTag();
            if (bread_tag != null){
                bread_text = bread_tag.getString("aaa");
            }
        }






        WInterface mainInterface = getInterface();

        WPanel panel = mainInterface.createChild(WPanel::new, Position.of(0, 0, 0), Size.of(380, 200)).setParent(mainInterface);

        Position panel_pos = Position.of(panel);
//        WToggle toggle_switch = mainInterface.createChild(WToggle::new, Position.of(0, 0, 0), Size.of(15, 15)).setParent(mainpanel);

        int padding = 10;

        int scrollbar_padding = 9;
        WVerticalScrollableContainer component_holder = panel.createChild(WVerticalScrollableContainer::new,
                panel_pos.add((panel.getWidth()/2)-(cell_size)-padding-scrollbar_padding, panel.getY() + padding, 0),
                Size.of(cell_size + scrollbar_padding, cell_size * 5));

        component_holder.setHasFade(false);
        component_holder.setHasArrows(false);
        component_holder.setScrollbarWidth(7);

        for (int i=0; i<8; i++){
            component_holder.addRow(new WToggle().setSize(Size.of(15, 15)));
        }

        panel.createChild(WSlot::new, panel_pos.add((panel.getWidth()/2)-(cell_size * 3)-padding, (panel.getHeight()/2) - cell_size, 1),
                Size.of(cell_size, cell_size))
                .setSlotNumber(0)
                .setInventoryNumber(SolderingStationHandler.SOLDERING_STATION_INVENTORY);

        int cache_padding = 150;

        for (int i=0; i<3; i++){
            panel.createChild(WSlot::new, panel_pos.add((panel.getWidth()/2)-cache_padding+(i*(cell_size + 2)), (panel.getHeight()/2) - cell_size, 1),
                    Size.of(cell_size, cell_size))
                    .setSlotNumber(i+1)
                    .setInventoryNumber(SolderingStationHandler.SOLDERING_STATION_INVENTORY);

            panel.createChild(WVerticalBar::new, panel_pos.add((panel.getWidth()/2)-cache_padding+(cell_size + (i*10)), (panel.getHeight()/2 - (cell_size+padding+23)) - cell_size, 1),
                    Size.of(4, 50))
                    .setLimit(new MutableInt(50))
                    .setProgress(new MutableInt(25));
        }

        WSlot.addPlayerInventory(panel_pos.add((panel.getWidth()/2)-(cell_size * 9)-padding, (panel.getHeight()/2) + padding, 1), Size.of(18, 18), panel);

        textfield = panel.createChild(WTextField::new, Position.of(80, 10, 2), Size.of(100, 20)).setParent(panel);
        if (bread_text != null){
            textfield.setText(bread_text);
        }

        textfield.setHidden(true);
        WButton button = panel.createChild(WButton::new, Position.of(200, 10, 1), Size.of(10, 10)).setParent(panel);
//        button.setHidden(true);



        soldergrid = new SolderGrid(1, panel);


        panel.setOnAlign(WAbstractWidget::center);
        panel.center();

        button.setOnMouseClicked((widget, mouseX, mouseY, mouseButton) ->
        {
            bool = !bool;
            soldergrid.set_locked(bool);
//            container.player.sendMessage(new LiteralText("text: " + textfield.getText()), false);
//
//            PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
//            passedData.writeBlockPos(container.pos);
//            passedData.writeString(textfield.getText());
//
//            ClientSidePacketRegistry.INSTANCE.sendToServer(Stacks_n_Signals.SOLDERING_UPDATE_PACKET_ID, passedData);

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




        mainInterface.add(panel);
    }
}
