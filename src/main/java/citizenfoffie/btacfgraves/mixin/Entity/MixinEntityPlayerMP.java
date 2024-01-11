package citizenfoffie.btacfgraves.mixin.Entity;

import citizenfoffie.btacfgraves.BTAGraves;
import citizenfoffie.btacfgraves.MixinInterfaces.IEntityPlayer;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.net.packet.Packet100OpenWindow;
import net.minecraft.core.player.inventory.ContainerChest;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.world.World;
import net.minecraft.server.entity.player.EntityPlayerMP;
import net.minecraft.server.net.handler.NetServerHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityPlayerMP.class)
public abstract class MixinEntityPlayerMP extends EntityPlayer implements IEntityPlayer {
	@Shadow
	protected abstract void getNextWindowId();

	@Shadow
	public NetServerHandler playerNetServerHandler;

	@Shadow
	private int currentWindowId;

	public MixinEntityPlayerMP(World world) {
		super(world);
	}
	public void BTAGraves$displayGUIGrave(IInventory iinventory){
		this.getNextWindowId();
		BTAGraves.logNetwork(this.username + " interacted with iron chest at (" + x + ", " + y + ", " + z + ")");
		this.playerNetServerHandler.sendPacket(new Packet100OpenWindow(this.currentWindowId, 0, iinventory.getInvName(), iinventory.getSizeInventory()));
		this.craftingInventory = new ContainerChest(this.inventory, iinventory);
		this.craftingInventory.windowId = this.currentWindowId;
		this.craftingInventory.onContainerInit(((EntityPlayerMP)(Object)this));
	}
}
