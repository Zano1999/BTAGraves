package citizenfoffie.btacfgraves;

import citizenfoffie.btacfgraves.block.BlockGrave;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.sound.block.BlockSounds;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.BlockBuilder;
import useless.dragonfly.helper.ModelHelper;
import useless.dragonfly.model.block.BlockModelDragonFly;

import static citizenfoffie.btacfgraves.BTAGraves.MOD_ID;
import static citizenfoffie.btacfgraves.BTAGraves.config;

public class GraveBlocks implements ModInitializer {
	public static final Block grave = new BlockBuilder(MOD_ID)
			.setSideTextures("steelchestfront.png")
			.setNorthTexture("steelchestfront.png")
			.setTopTexture("steelchestfront.png")
			.setBottomTexture("steelchestfront.png")
			.setBlockSound(BlockSounds.STONE)
			.setHardness(2.5f)
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setBlockModel(new BlockModelDragonFly(ModelHelper.getOrCreateBlockModel(MOD_ID, "Grave.json")))
			.build(new BlockGrave("grave",config.getInt("ids.graveID"), Material.stone));
	public static void init() {
		if (Item.seat == null) {
			throw new RuntimeException();
		}
	}
	@Override
	public void onInitialize() {
		// DO NOT TOUCH THIS! It's an error prevention method. Thanks Useless!
		try {
			Class.forName("net.minecraft.core.block.Block");
			Class.forName("net.minecraft.core.item.Item");
		} catch (ClassNotFoundException ignored) {}

	}

}
