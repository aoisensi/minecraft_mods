package info.aoisensi.padlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.world.World;

public class OverwriteBlockChest extends BlockChest {

	public int likeBlock;
	
	protected OverwriteBlockChest(int par1, int par2, int par3) {
		super(par1, par2);
		likeBlock = par3;
	}
	
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		int l = 0;
		
		if (isChest(world, x + 1, y, z)) {
			if(isSideChest(world, x + 1, y, z)) {
				return false;
			}
			++l;
		}
		
		if (isChest(world, x - 1, y, z)) {
			if(isSideChest(world, x - 1, y, z)) {
				return false;
			}
			++l;
		}
		
		if (isChest(world, x, y, z + 1)) {
			if(isSideChest(world, x, y, z + 1)) {
				return false;
			}
			++l;
		}
		
		if (isChest(world, x, y, z - 1)) {
			if(isSideChest(world, x, y, z - 1)) {
				return false;
			}
			++l;
		}
		return l < 2;
	}
	
	private boolean isSideChest(World world, int x, int y, int z) {
		return isChest(world, x + 1, y, z) || isChest(world, x - 1, y, z) || isChest(world, x, y, z + 1) || isChest(world, x, y, z - 1);
	}
	
	private boolean isChest(World world, int x, int y, int z) {
		int blockId2 = world.getBlockId(x, y, z);
		return blockId2 == this.blockID ? true : blockId2 == this.likeBlock;
	}
}
