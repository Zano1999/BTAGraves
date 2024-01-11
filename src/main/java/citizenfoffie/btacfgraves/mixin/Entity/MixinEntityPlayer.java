package citizenfoffie.btacfgraves.mixin.Entity;

import citizenfoffie.btacfgraves.MixinInterfaces.IEntityPlayer;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = EntityPlayer.class, remap = false)
public class MixinEntityPlayer implements IEntityPlayer {
	@Override
	public void BTAGraves$displayGUIGrave(IInventory iinventory) {}
}
