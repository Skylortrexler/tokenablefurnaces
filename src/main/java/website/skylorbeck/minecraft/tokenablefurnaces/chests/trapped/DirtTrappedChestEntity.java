package website.skylorbeck.minecraft.tokenablefurnaces.chests.trapped;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import website.skylorbeck.minecraft.skylorlib.storage.ExtraChestEntity;
import website.skylorbeck.minecraft.tokenablefurnaces.Declarer;
import website.skylorbeck.minecraft.tokenablefurnaces.Ref;
import website.skylorbeck.minecraft.tokenablefurnaces.Screenhandlers.DirtDoubleScreenHandler;
import website.skylorbeck.minecraft.tokenablefurnaces.Screenhandlers.DirtScreenHandler;

public class DirtTrappedChestEntity extends ExtraChestEntity {
    protected DirtTrappedChestEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState,1,"dirt",true, Ref.MODID);
    }

    public DirtTrappedChestEntity(BlockPos pos, BlockState state) {
        super(Declarer.DIRTTRAPPEDCHESTENTITY,pos, state,1,"dirt",true, Ref.MODID);
    }

    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new DirtScreenHandler(syncId, playerInventory, this,1,1);
    }

    protected Text getContainerName() {
        return new TranslatableText("container.dirtchest");
    }
    protected Text getDoubleContainerName(){
        return new TranslatableText("container.dirtchestdouble");
    }
    protected ScreenHandler createDoubleScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory){
        return new DirtDoubleScreenHandler(syncId, playerInventory, inventory,1,2);
    }
}