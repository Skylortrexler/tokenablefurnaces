package website.skylorbeck.minecraft.tokenablefurnaces.shulkers;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import website.skylorbeck.minecraft.skylorlib.storage.ExtraShulkerEntity;
import website.skylorbeck.minecraft.tokenablefurnaces.Declarer;
import website.skylorbeck.minecraft.tokenablefurnaces.Ref;
import website.skylorbeck.minecraft.tokenablefurnaces.Screenhandlers.DiamondScreenHandler;

import java.util.stream.IntStream;

public class DiamondShulkerEntity extends ExtraShulkerEntity {
    public DiamondShulkerEntity(BlockPos pos, BlockState state) {
        super(Declarer.DIAMONDSHULKERENTITY,pos, state,"diamond", Ref.MODID);
        this.inventory = DefaultedList.ofSize(24*9, ItemStack.EMPTY);
        this.AVAILABLE_SLOTS = IntStream.range(0, 24*9).toArray();
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("container.diamondshulker");
    }
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
//        return GenericContainerScreenHandler.createGeneric9x6(syncId, playerInventory, this);
        return new DiamondScreenHandler(syncId, playerInventory, this,24,9);
    }
}
