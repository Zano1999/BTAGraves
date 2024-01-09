package citizenfoffie.btacfgraves.mixin.Entity;



import citizenfoffie.btacfgraves.MixinInterfaces.IEntityPlayer;
import citizenfoffie.btacfgraves.gui.GuiGraveChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.core.player.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = EntityPlayerSP.class, remap = false)
public class MixinEntityPlayerSP implements IEntityPlayer {
	@Unique
	private final EntityPlayerSP thisAs = (EntityPlayerSP)(Object)this;
	@Shadow
	protected Minecraft mc;
	public void displayGUIIronChest(IInventory iinventory) {
		this.mc.displayGuiScreen(new GuiGraveChest(thisAs.inventory, iinventory));
	}

	@Override
	public void displayGUIDiamondChest(IInventory iinventory) {

	}


}
