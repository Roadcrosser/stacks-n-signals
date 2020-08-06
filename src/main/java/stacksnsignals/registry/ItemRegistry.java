package stacksnsignals.registry;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import stacksnsignals.Stacks_n_Signals;

import static stacksnsignals.Stacks_n_Signals.MOD_ID;
import static stacksnsignals.Stacks_n_Signals.SOLDERING_STATION_ID;
import static stacksnsignals.registry.BlockRegistry.SOLDERING_STATION;

public class ItemRegistry {

    public static final Item BREADBOARD_ITEM = new Item(new Item.Settings().group(Stacks_n_Signals.ITEM_GROUP));

    public ItemRegistry(){
        // NO-OP
    }

    public static void initialize() {

        Registry.register(Registry.ITEM, SOLDERING_STATION_ID, new BlockItem(SOLDERING_STATION, new Item.Settings().group(Stacks_n_Signals.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "breadboard"), BREADBOARD_ITEM);
    }


}
