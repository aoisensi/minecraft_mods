package info.aoisensi.padlock;

import sun.font.TrueTypeFont;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockPadlockedChest extends OverwriteBlockChest {

	
	protected BlockPadlockedChest(int par1, int par2, int par3) {
		super(par1, par2, par3);
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		return false;
	}
	
}