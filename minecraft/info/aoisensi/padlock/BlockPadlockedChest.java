package info.aoisensi.padlock;

import sun.font.TrueTypeFont;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

public class BlockPadlockedChest extends OverwriteBlockChest {

	
	protected BlockPadlockedChest(int par1, int par2, int par3) {
		super(par1, par2, par3);
	}
	
	@Override
	public IInventory func_94442_h_(World par1World, int par2, int par3, int par4) {
		return null;
	}
	
}