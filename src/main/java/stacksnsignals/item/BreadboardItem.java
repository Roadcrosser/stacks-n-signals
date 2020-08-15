package stacksnsignals.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import stacksnsignals.Stacks_n_Signals;

import java.util.List;

public class BreadboardItem extends Item {

    public BreadboardItem(Settings settings){
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        CompoundTag bread_tag = itemStack.getTag();
        if (bread_tag != null){
           String nbt_data = bread_tag.getString("aaa");
           if (nbt_data != null){
               tooltip.add(new LiteralText(nbt_data));
           }
        }
    }
}
