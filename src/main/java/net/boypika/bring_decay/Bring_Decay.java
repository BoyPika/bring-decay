package net.boypika.bring_decay;

import io.github.timecubed.tulip.TulipConfigManager;
import net.boypika.bring_decay.util.Trades;
import net.fabricmc.api.ModInitializer;
import net.boypika.bring_decay.potion.ModPotions;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Bring_Decay implements ModInitializer {
    public static final String MOD_ID = "bring_decay";
    public static TulipConfigManager tulipInstance = new TulipConfigManager(MOD_ID, false);
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final Logger LOGGER1 = LogManager.getLogger("Tulip");
    private static final String ConfigFile = FabricLoader.getInstance().getConfigDir().toString() + "\\" + MOD_ID + ".properties";
    private boolean fileExists() {
        File file = new File(ConfigFile);

        return file.exists();
    }

    @Override
    public void onInitialize() {
        if (!fileExists()) {
            try {
                tulipInstance.createFileWithProperties();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        tulipInstance.saveProperty("witch_throw_wither", false);
        tulipInstance.saveProperty("min_health_for_decay_from_witch", 8.0F);
        tulipInstance.saveProperty("brewable_decay_potion", false);
        tulipInstance.load();
        ModPotions.registerPotions();
        Trades.registerTrades();
        LOGGER.info("[1.16.3 - 1.19.2] Bring Decay Init");
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            LOGGER.info(String.valueOf(tulipInstance.getBoolean("witch_throw_wither")));
            LOGGER.info(String.valueOf(tulipInstance.getFloat("min_health_for_decay_from_witch")));
            LOGGER.info(String.valueOf(tulipInstance.getBoolean("brewable_decay_potion")));
        }
    }
}
