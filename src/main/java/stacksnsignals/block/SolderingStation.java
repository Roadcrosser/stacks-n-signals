package stacksnsignals.block;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stacksnsignals.Stacks_n_Signals;

public class SolderingStation extends Block {

    public SolderingStation(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            player.sendMessage(new LiteralText("do thing"), false);
            ContainerProviderRegistry.INSTANCE.openContainer(Stacks_n_Signals.SOLDERING_STATION_ID, player, (buffer) -> {
                buffer.writeText(new TranslatableText(this.getTranslationKey()));
                buffer.writeBlockPos(pos);
                buffer.writeInt(1);
                buffer.writeInt(1);
                buffer.writeInt(1);
            });
        }

        return ActionResult.SUCCESS;
    }

}
