package info.aoisensi.padlock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockLockedChest;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
 
//@Mod(modid="aoisensi_padlock", name="PadLock", version="0.0.2")

@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class Padlock {
	
	public static CreativeTabs tabPadlock = new CreativeTabs("tabPadlock") {
		public ItemStack getIconItemStack() {
			return new ItemStack(itemPadlock, 1, 0);
		}
	};
	
	public final static Item itemPadlock = new ItemPadlock(5000);
	public final static Item itemPadlockKey = new ItemPadlockKey(5001);
	
	public final static Block blockPadlockedChest = new BlockPadlockedChest(501, 1, Block.chest.blockID)
	.setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("chestPadlocked");
	public final static Block blockPadlockedTrapChest = new BlockPadlockedChest(502, 0, Block.chestTrapped.blockID)
	.setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("chestTrapPadlocked");

	@Instance("Padlock")
	public static Padlock instance;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Stub Method
	}

	@Init
	public void load(FMLInitializationEvent event) {
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabPadlock", "en_US", "Padlock");
		initRecipe();
		initAddItems();
		initAddBlock();
		initOverwriteBlock();
		initAblePadlockList();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
	
	private void initOverwriteBlock() {
		int chestBlockId = Block.chest.blockID;
		Block.blocksList[chestBlockId] = null;
		BlockChest newChest = (BlockChest)new OverwriteBlockChest(chestBlockId, 0, Padlock.blockPadlockedChest.blockID)
		.setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("chest");
		
		int chestTrappedBlockId = Block.chestTrapped.blockID;
		Block.blocksList[chestTrappedBlockId] = null;
		Block newChestTrapped = new OverwriteBlockChest(chestTrappedBlockId, 1, Padlock.blockPadlockedTrapChest.blockID)
		.setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("chestTrap");
	}
	
	private void initRecipe() {
		for(int i = 0;i<4096;++i) {
			//レシピ
			int key = ((i*163)%4096+73)%4096*37%4096;
			
			//南京錠
			ItemStack itemStackPadlock = new ItemStack(itemPadlock,1,key);
			GameRegistry.addRecipe(
				itemStackPadlock, 
				"123", ".#.", "...",
				'1', new ItemStack(Item.dyePowder,1,i%16),
				'2', new ItemStack(Item.dyePowder,1,i/16%16),
				'3', new ItemStack(Item.dyePowder,1,i/256%16),
				'.',Item.goldNugget,
				'#',Item.ingotGold
			);
			
			//南京錠の鍵
			ItemStack itemStackPadlockKey = new ItemStack(itemPadlockKey,1,key);
			GameRegistry.addRecipe(
				itemStackPadlockKey,
				"123", " # ", " . ",
				'1', new ItemStack(Item.dyePowder,1,i%16),
				'2', new ItemStack(Item.dyePowder,1,i/16%16),
				'3', new ItemStack(Item.dyePowder,1,i/256%16),
				'.',Item.goldNugget,
				'#',Item.ingotGold
			);
		}
	}
	
	private void initAddItems() {
		LanguageRegistry.addName(itemPadlock, "Padlock");
		LanguageRegistry.addName(itemPadlockKey, "Padlock Key");
	}
	
	private void initAddBlock() {
		GameRegistry.registerBlock(blockPadlockedChest, "chestPadlocked");
		LanguageRegistry.addName(blockPadlockedChest, "Padlocked Chest");
		
		GameRegistry.registerBlock(blockPadlockedTrapChest, "chestTrapPadlocked");
		LanguageRegistry.addName(blockPadlockedTrapChest, "Padlocked Trapped Chest");
	}
	
	private void initAblePadlockList() {
		PadlockAbleList.addItem(blockPadlockedChest.blockID, Block.chest.blockID, true);
		PadlockAbleList.addItem(blockPadlockedTrapChest.blockID, Block.chestTrapped.blockID, true);
	}
}