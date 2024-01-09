package citizenfoffie.btacfgraves;

import citizenfoffie.btacfgraves.tileEntities.TitleEntityGraveChest;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

import java.util.Properties;

public class BTAGraves implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "btacfgraves";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ConfigHandler config;
	static {
		// Config
		Properties prop = new Properties();
		prop.setProperty("ids.graveID", "2000");

		config = new ConfigHandler(MOD_ID, prop);
	}

    @Override
    public void onInitialize() {
		GraveBlocks.init();

    }
	@Override
	public void beforeGameStart() {
		GraveBlocks.init();
	}

	@Override
	public void afterGameStart() {
		EntityHelper.createTileEntity(TitleEntityGraveChest.class, "Grave");
	}

	@Override
	public void onRecipesReady() {

	}
}
