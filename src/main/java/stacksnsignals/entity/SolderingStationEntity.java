package stacksnsignals.entity;

import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.Level;
import spinnery.common.inventory.BaseInventory;
import spinnery.common.utility.InventoryUtilities;
import stacksnsignals.Stacks_n_Signals;
import stacksnsignals.handler.SolderingStationHandler;
import stacksnsignals.registry.EntityRegistry;
import stacksnsignals.registry.ItemRegistry;
import stacksnsignals.screen.SolderingStationScreen;

import static stacksnsignals.Stacks_n_Signals.log;

public class SolderingStationEntity extends BlockEntity implements Inventory, ExtendedScreenHandlerFactory {

    final DefaultedList<ItemStack> stacks;

    public SolderingStationEntity() {
        super(EntityRegistry.SOLDERING_STATION_ENTITY);
        this.stacks = DefaultedList.ofSize(size(), ItemStack.EMPTY);
    }

    @Override
    public int size() {
        return 4;
    }

    @Override
    public boolean isEmpty() {
        return stacks.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStack(int slot) {
        return stacks.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack stack = stacks.get(slot).split(amount);
        markDirty();
        return stack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack stack = stacks.remove(slot);
        markDirty();
        return stack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        stacks.set(slot, stack);
        markDirty();

        // TODO fix this static method thingy
        if (world != null && world.isClient && slot == 0){
            SolderingStationScreen.update_text_field(stack);
        }

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        stacks.clear();
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        return true; // If you need to control what is valid for each slot, otherwise just don't even override
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(getPos());
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText("block.stacks-n-signals.soldering_station");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new SolderingStationHandler(syncId, inv, getPos());
    }


    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        if (tag.contains("inventory")) {
            BaseInventory inv = InventoryUtilities.read(tag); // Yep, you can cheap out here
            for (int i = 0; i < inv.size(); i++) {
                setStack(i, inv.getStack(i));
            }
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        InventoryUtilities.write(this, tag);
        super.toTag(tag);
        return tag;
    }

    public static void solder_update_packet(PacketContext packet_context, PacketByteBuf attached_data){
        // Get the BlockPos we put earlier in the IO thread
        BlockPos pos = attached_data.readBlockPos();
        String str = attached_data.readString();

        packet_context.getTaskQueue().execute(() -> {
            // Execute on the main thread

            // ALWAYS validate that the information received is valid in a C2S packet!
            if(packet_context.getPlayer().world.canSetBlock(pos)){

                SolderingStationEntity blockentity = (SolderingStationEntity) packet_context.getPlayer().world.getBlockEntity(pos);

                ItemStack bread = blockentity.getStack(0);
                String bread_text = null;

                if (bread.getItem() == ItemRegistry.BREADBOARD_ITEM){
                    CompoundTag bread_tag = new CompoundTag();
                    bread_tag.putString("aaa", str);

                    bread.setTag(bread_tag);
                }
            }

        });
    }
}
