package citizenfoffie.btacfgraves.mixin;

import citizenfoffie.btacfgraves.BTAGraves;
import citizenfoffie.btacfgraves.tileEntities.TitleEntityGraveChest;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
@Debug(export = true)
abstract class DeathMixin extends EntityLiving {
	@Shadow
	public InventoryPlayer inventory;
	public DeathMixin(World world) {
		super(world);
	}

	@Inject(method = "onDeath", at = @At("HEAD"),remap = false)
	private void onDeath(Entity deathEntity, CallbackInfo ci) {
		world.setBlockWithNotify((int) (x-1), (int) (y-1), (int) z, BTAGraves.grave.id); // Place Grave
		TitleEntityGraveChest tileEntityChest = (TitleEntityGraveChest)world.getBlockTileEntity((int) (x-1), (int) (y-1), (int) z);
		BTAGraves.LOGGER.info(Integer.toString(this.inventory.getSizeInventory()));

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
