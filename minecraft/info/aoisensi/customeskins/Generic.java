package info.aoisensi.customeskins;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
 
//@Mod(modid="aoisensi_customeskins", name="CustomeSkins", version="0.0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Generic {
 
    /**Forgeが使うあなたのMODのインスタンスです。**/
    @Instance("Generic")
    public static Generic instance;

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        // Stub Method
        }
 
    @Init
    public void load(FMLInitializationEvent event) {

        }
 
    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
        // Stub Method
        }
}