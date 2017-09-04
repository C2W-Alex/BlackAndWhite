package blackandwhite;

import blackandwhite.proxy.CommonProxy;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = BlackAndWhite.MODID, name = BlackAndWhite.MODNAME, version = BlackAndWhite.VERSION, useMetadata = true)
public class BlackAndWhite {

    public static final String MODID = "blackandwhite";
    public static final String MODNAME = "blackandwhite";
    public static final String VERSION = "0.0.1";

    @SidedProxy(clientSide = "blackandwhite.proxy.ClientProxy", serverSide = "blackandwhite.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static BlackAndWhite instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    @Config(modid = MODID)
    public static class ConfigHandler {

        @Config.Name("Testing")
        @Config.Comment("Testing Config")
        public static boolean testConfigOption = true;

        @Mod.EventBusSubscriber(modid = MODID)
        public static class ConfigChanged {

            @SubscribeEvent
            public static void onConfigChanged(ConfigChangedEvent event) {
                if (event.getModID().equals(MODID)) {
                    ConfigManager.sync(MODID, Config.Type.INSTANCE);
                }
            }

        }

    }

}
