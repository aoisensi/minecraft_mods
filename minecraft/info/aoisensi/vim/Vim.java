package info.aoisensi.vim;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import sun.net.www.content.text.Generic;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "Vim", name = "Vim", version = "0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class Vim {

	public final Block blockVim = new BlockVim(521)
			.setHardness(0.5F).setStepSound(Block.soundStoneFootstep)
			.setCreativeTab(CreativeTabs.tabBlock);

	@Instance("Generic")
	public static Vim instance;

	@Init
	public void load(FMLInitializationEvent event) {
		GameRegistry.registerBlock(blockVim, "vim");
		MinecraftForge.setBlockHarvestLevel(blockVim, "pickaxe", 2);
	}
}
