package citizenfoffie.btacfgraves.mixin;

import citizenfoffie.btacfgraves.GraveBlocks;
import citizenfoffie.btacfgraves.tileEntities.TitleEntityGraveChest;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntityChest;
import net.minecraft.core.data.gamerule.GameRules;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;

import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.packet.Packet;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.InventoryBasic;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.world.chunk.ChunkCoordinates;
import net.minecraft.server.world.WorldServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.core.world.World;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.InventoryLargeChest;

@Mixin(EntityPlayer.class)
@Debug(export = true)
abstract class DeathMixin extends EntityLiving {
	@Shadow
	private ChunkCoordinates lastDeathCoordinate;
	@Shadow
	public InventoryPlayer inventory;
	private static final String MOD_ID = "btacfgraves";
	private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public DeathMixin(World world) {
		super(world);
	}

	@Inject(method = "onDeath", at = @At("HEAD"),remap = false)
	private void onDeath(Entity deathEntity, CallbackInfo ci) {
		EntityLiving thisAs = (EntityLiving) (Object) this;

		world.setBlockWithNotify((int) (thisAs.x-1), (int) (thisAs.y-1), (int) thisAs.z, GraveBlocks.grave.id); // Place Grave
		TitleEntityGraveChest tileEntityChest = (TitleEntityGraveChest)world.getBlockTileEntity((int) (thisAs.x-1), (int) (thisAs.y-1), (int) thisAs.z);
		LOGGER.info(Integer.toString(this.inventory.getSizeInventory()));

		for(int i = 0; i < 36; ++i) {
			ItemStack itemStack = this.inventory.getStackInSlot(i);
			if (itemStack != null) {
				tileEntityChest.setInventorySlotContents(i,itemStack);
				this.inventory.mainInventory[i] = null;
			}
		}
		for(int i = 0; i < this.inventory.armorInventory.length; ++i) {
			ItemStack itemStack = this.inventory.armorItemInSlot(i);
			if (itemStack != null) {
				tileEntityChest.setInventorySlotContents(i+36,itemStack);
				this.inventory.armorInventory[i] = null;
			}
		}
	}
}
