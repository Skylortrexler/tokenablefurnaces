package website.skylorbeck.minecraft.tokenablefurnaces.chests;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.block.ChestAnimationProgress;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;
import website.skylorbeck.minecraft.tokenablefurnaces.chests.trapped.*;

@Environment(EnvType.CLIENT)
public class ExtraChestEntityRenderer<T extends BlockEntity & ChestAnimationProgress> implements BlockEntityRenderer<T> {
    private static final String BASE = "bottom";
    private static final String LID = "lid";
    private static final String LATCH = "lock";
    private final ModelPart singleChestLid;
    private final ModelPart singleChestBase;
    private final ModelPart singleChestLatch;
    private final ModelPart doubleChestRightLid;
    private final ModelPart doubleChestRightBase;
    private final ModelPart doubleChestRightLatch;
    private final ModelPart doubleChestLeftLid;
    private final ModelPart doubleChestLeftBase;
    private final ModelPart doubleChestLeftLatch;
    static SpriteIdentifier pumpkin;
    static SpriteIdentifier pumpkinLeft;
    static SpriteIdentifier pumpkinRight;
    static SpriteIdentifier christmas;
    static SpriteIdentifier christmasLeft;
    static SpriteIdentifier christmasRight;
    static SpriteIdentifier iron;
    static SpriteIdentifier ironLeft;
    static SpriteIdentifier ironRight;
    static SpriteIdentifier gold;
    static SpriteIdentifier goldLeft;
    static SpriteIdentifier goldRight;
    static SpriteIdentifier diamond;
    static SpriteIdentifier diamondLeft;
    static SpriteIdentifier diamondRight;
    static SpriteIdentifier netherite;
    static SpriteIdentifier netheriteLeft;
    static SpriteIdentifier netheriteRight;
    static SpriteIdentifier amethyst;
    static SpriteIdentifier amethystLeft;
    static SpriteIdentifier amethystRight;
    static SpriteIdentifier ironT;
    static SpriteIdentifier ironTLeft;
    static SpriteIdentifier ironTRight;
    static SpriteIdentifier goldT;
    static SpriteIdentifier goldTLeft;
    static SpriteIdentifier goldTRight;
    static SpriteIdentifier diamondT;
    static SpriteIdentifier diamondTLeft;
    static SpriteIdentifier diamondTRight;
    static SpriteIdentifier netheriteT;
    static SpriteIdentifier netheriteTLeft;
    static SpriteIdentifier netheriteTRight;
    static SpriteIdentifier amethystT;
    static SpriteIdentifier amethystTLeft;
    static SpriteIdentifier amethystTRight;


    public ExtraChestEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        ModelPart modelPart = ctx.getLayerModelPart(EntityModelLayers.CHEST);
        this.singleChestBase = modelPart.getChild("bottom");
        this.singleChestLid = modelPart.getChild("lid");
        this.singleChestLatch = modelPart.getChild("lock");
        ModelPart modelPart2 = ctx.getLayerModelPart(EntityModelLayers.DOUBLE_CHEST_LEFT);
        this.doubleChestRightBase = modelPart2.getChild("bottom");
        this.doubleChestRightLid = modelPart2.getChild("lid");
        this.doubleChestRightLatch = modelPart2.getChild("lock");
        ModelPart modelPart3 = ctx.getLayerModelPart(EntityModelLayers.DOUBLE_CHEST_RIGHT);
        this.doubleChestLeftBase = modelPart3.getChild("bottom");
        this.doubleChestLeftLid = modelPart3.getChild("lid");
        this.doubleChestLeftLatch = modelPart3.getChild("lock");
        Identifier identifier = new Identifier("textures/atlas/chest.png");
        pumpkin =  new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/pumpkin"));
        pumpkinLeft =  new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/pumpkin_left"));
        pumpkinRight =  new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/pumpkin_right"));
        
        christmas =  new SpriteIdentifier(identifier, new Identifier("minecraft:entity/chest/christmas"));
        christmasLeft =  new SpriteIdentifier(identifier, new Identifier("minecraft:entity/chest/christmas_left"));
        christmasRight =  new SpriteIdentifier(identifier, new Identifier("minecraft:entity/chest/christmas_right"));
        
        iron =  new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/iron"));
        ironLeft =  new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/iron_left"));
        ironRight =  new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/iron_right"));
        
        gold = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/gold"));
        goldLeft = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/gold_left"));
        goldRight = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/gold_right"));
        
        diamond = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/diamond"));
        diamondLeft = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/diamond_left"));
        diamondRight = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/diamond_right"));
        
        netherite = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/netherite"));
        netheriteLeft = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/netherite_left"));
        netheriteRight = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/netherite_right"));
        
        amethyst = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/amethyst"));
        amethystLeft = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/amethyst_left"));
        amethystRight = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/amethyst_right"));
        
        ironT =  new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/trapped/iron"));
        ironTLeft =  new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/trapped/iron_left"));
        ironTRight =  new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/trapped/iron_right"));
        
        goldT = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/trapped/gold"));
        goldTLeft = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/trapped/gold_left"));
        goldTRight = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/trapped/gold_right"));
        
        diamondT = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/trapped/diamond"));
        diamondTLeft = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/trapped/diamond_left"));
        diamondTRight = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/trapped/diamond_right"));
        
        netheriteT = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/trapped/netherite"));
        netheriteTLeft = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/trapped/netherite_left"));
        netheriteTRight = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/trapped/netherite_right"));
        
        amethystT = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/trapped/amethyst"));
        amethystTLeft = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/trapped/amethyst_left"));
        amethystTRight = new SpriteIdentifier(identifier, new Identifier("tokenablefurnaces:entity/chest/trapped/amethyst_right"));
        
    }

    public static TexturedModelData getSingleTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("bottom", ModelPartBuilder.create().uv(0, 19).cuboid(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F), ModelTransform.NONE);
        modelPartData.addChild("lid", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F), ModelTransform.pivot(0.0F, 9.0F, 1.0F));
        modelPartData.addChild("lock", ModelPartBuilder.create().uv(0, 0).cuboid(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F), ModelTransform.pivot(0.0F, 8.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    public static TexturedModelData getRightDoubleTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("bottom", ModelPartBuilder.create().uv(0, 19).cuboid(1.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F), ModelTransform.NONE);
        modelPartData.addChild("lid", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F), ModelTransform.pivot(0.0F, 9.0F, 1.0F));
        modelPartData.addChild("lock", ModelPartBuilder.create().uv(0, 0).cuboid(15.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F), ModelTransform.pivot(0.0F, 8.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    public static TexturedModelData getLeftDoubleTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("bottom", ModelPartBuilder.create().uv(0, 19).cuboid(0.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F), ModelTransform.NONE);
        modelPartData.addChild("lid", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F), ModelTransform.pivot(0.0F, 9.0F, 1.0F));
        modelPartData.addChild("lock", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F), ModelTransform.pivot(0.0F, 8.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        World world = entity.getWorld();
        boolean bl = world != null;
        BlockState blockState = bl ? entity.getCachedState() : (BlockState) Blocks.CHEST.getDefaultState().with(ExtraChestBlock.FACING, Direction.SOUTH);
        ChestType chestType = blockState.contains(ExtraChestBlock.CHEST_TYPE) ? (ChestType) blockState.get(ExtraChestBlock.CHEST_TYPE) : ChestType.SINGLE;
        Block block = blockState.getBlock();
        if (block instanceof AbstractChestBlock abstractChestBlock) {
            boolean bl2 = chestType != ChestType.SINGLE;
            matrices.push();
            float f = ((Direction) blockState.get(ExtraChestBlock.FACING)).asRotation();
            matrices.translate(0.5D, 0.5D, 0.5D);
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-f));
            matrices.translate(-0.5D, -0.5D, -0.5D);
            DoubleBlockProperties.PropertySource propertySource2;
            if (bl) {
                propertySource2 = abstractChestBlock.getBlockEntitySource(blockState, world, entity.getPos(), true);
            } else {
                propertySource2 = DoubleBlockProperties.PropertyRetriever::getFallback;
            }
//            float g = ((Float2FloatFunction) propertySource2.apply(ExtraChestBlock.getAnimationProgressRetriever(entity))).get(tickDelta);
            float g = ((ExtraChestEntity)entity).getAnimationProgress(tickDelta);
            g = 1.0F - g;
            g = 1.0F - g * g * g;
            int i = ((Int2IntFunction) propertySource2.apply(new LightmapCoordinatesRetriever())).applyAsInt(light);
            SpriteIdentifier spriteIdentifier = iron;
            if (bl2) {
                if (chestType == ChestType.LEFT) {
                    spriteIdentifier =
                            entity instanceof GoldChestEntity ? goldLeft :
                                    entity instanceof DiamondChestEntity ? diamondLeft :
                                            entity instanceof NetheriteChestEntity ? netheriteLeft :
                                                    entity instanceof AmethystChestEntity ? amethystLeft :
                                                            entity instanceof IronTrappedChestEntity ? ironTLeft :
                                                                    entity instanceof GoldTrappedChestEntity ? goldTLeft :
                                                                            entity instanceof DiamondTrappedChestEntity ? diamondTLeft :
                                                                                    entity instanceof NetheriteTrappedChestEntity ? netheriteTLeft :
                                                                                            entity instanceof AmethystTrappedChestEntity ? amethystTLeft :
                                                                                                    entity instanceof PumpkinChestEntity ? pumpkinLeft :
                                                                                                            entity instanceof ChristmasChestEntity ? christmasLeft :
                                                                                                                    ironLeft;
                    VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
                    if (entity instanceof PumpkinChestEntity) {
                        this.render(matrices, vertexConsumer, this.doubleChestRightLid, this.singleChestLatch, this.doubleChestRightBase, g, i, overlay);
                    } else {
                        this.render(matrices, vertexConsumer, this.doubleChestRightLid, this.doubleChestRightLatch, this.doubleChestRightBase, g, i, overlay);
                    }

                } else {
                    spriteIdentifier =
                            entity instanceof GoldChestEntity ? goldRight :
                                    entity instanceof DiamondChestEntity ? diamondRight :
                                            entity instanceof NetheriteChestEntity ? netheriteRight :
                                                    entity instanceof AmethystChestEntity ? amethystRight :
                                                            entity instanceof IronTrappedChestEntity ? ironTRight :
                                                                    entity instanceof GoldTrappedChestEntity ? goldTRight :
                                                                            entity instanceof DiamondTrappedChestEntity ? diamondTRight :
                                                                                    entity instanceof NetheriteTrappedChestEntity ? netheriteTRight :
                                                                                            entity instanceof AmethystTrappedChestEntity ? amethystTRight :
                                                                                                    entity instanceof PumpkinChestEntity ? pumpkinRight :
                                                                                                            entity instanceof ChristmasChestEntity ? christmasRight :
                                                                                                                    ironRight;
                    VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
                    if (entity instanceof PumpkinChestEntity) {
                        this.render(matrices, vertexConsumer, this.doubleChestLeftLid, this.singleChestLatch, this.doubleChestLeftBase, g, i, overlay);
                    } else {
                        this.render(matrices, vertexConsumer, this.doubleChestLeftLid, this.doubleChestLeftLatch, this.doubleChestLeftBase, g, i, overlay);
                    }
                }
            } else {
                spriteIdentifier =
                        entity instanceof GoldChestEntity ? gold :
                                entity instanceof DiamondChestEntity ? diamond :
                                        entity instanceof NetheriteChestEntity ? netherite :
                                                entity instanceof AmethystChestEntity ? amethyst :
                                                        entity instanceof IronTrappedChestEntity ? ironT :
                                                                entity instanceof GoldTrappedChestEntity ? goldT :
                                                                        entity instanceof DiamondTrappedChestEntity ? diamondT :
                                                                                entity instanceof NetheriteTrappedChestEntity ? netheriteT :
                                                                                        entity instanceof AmethystTrappedChestEntity ? amethystT :
                                                                                                entity instanceof PumpkinChestEntity ? pumpkin :
                                                                                                        entity instanceof ChristmasChestEntity ? christmas :
                                                                                                                iron;
                VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
                this.render(matrices, vertexConsumer, this.singleChestLid, this.singleChestLatch, this.singleChestBase, g, i, overlay);
            }
            matrices.pop();
        }
    }

    private void render(MatrixStack matrices, VertexConsumer vertices, ModelPart lid, ModelPart latch, ModelPart base, float openFactor, int light, int overlay) {
        lid.pitch = -(openFactor * 1.5707964F);
        latch.pitch = lid.pitch;
        lid.render(matrices, vertices, light, overlay);
        latch.render(matrices, vertices, light, overlay);
        base.render(matrices, vertices, light, overlay);
    }

}
