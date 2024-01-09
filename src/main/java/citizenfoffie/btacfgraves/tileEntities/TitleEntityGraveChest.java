package citizenfoffie.btacfgraves.tileEntities;
import net.minecraft.core.item.ItemStack;
public class TitleEntityGraveChest extends TileEntityBigChest  {
	public TitleEntityGraveChest(){
		contents = new ItemStack[45];
	}
	@Override
	public String getInvName() {
		return "Grave";
	}
}
