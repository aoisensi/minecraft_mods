package info.aoisensi.padlock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;

public class ItemPadlockKey extends ItemPadlock {

	public ItemPadlockKey(int id) {
		super(id);
		setUnlocalizedName("padlockkey");
	}
	
	public ItemPadlockKey(int id, int maxStackSize, CreativeTabs tab, int texture, String name) {
		super(id);
		setMaxStackSize(maxStackSize);
		setCreativeTab(tab);
		setUnlocalizedName(name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister iconRegister)
	{
		iconIndex = iconRegister.registerIcon("padlock:padlockkey");
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		player.sendChatToPlayer(String.format("metadeta:%d blockid:%d", world.getBlockMetadata(x, y, z), world.getBlockId(x, y, z)));
		int blockId = world.getBlockId(x, y, z);
		if(PadlockAbleList.containsPadlocked(blockId)) {
			int metadeta = world.getBlockMetadata(x, y, z);
			if(side == metadeta) {
				unpadlockChest(world, x, y, z, PadlockAbleList.findFromPadlocked(blockId), metadeta);
				if(PadlockAbleList.gotCanLarge()) {
					if(willAblepadlock(world, x + 1, y, z, blockId, metadeta)) {
						return true;
					}
					if(willAblepadlock(world, x - 1, y, z, blockId, metadeta)) {
						return true;
					}
					if(willAblepadlock(world, x, y, z + 1, blockId, metadeta)) {
						return true;
					}
					if(willAblepadlock(world, x, y, z - 1, blockId, metadeta)) {
						return true;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	private boolean willAblepadlock(World world, int x, int y, int z, int blockId, int metadeta) {
		if(world.getBlockId(x, y, z) == blockId) {
			unpadlockChest(world, x, y, z, PadlockAbleList.findFromPadlocked(blockId), metadeta);
			return true;
		}
		return false;
	}
	
	private void unpadlockChest(World world, int x, int y, int z, int unpadlockedBlock, int metadeta) {
		TileEntityChest tileEntityChest = (TileEntityChest)world.getBlockTileEntity(x, y, z);
		TileEntityChest newTileEntityChest = new TileEntityChest();
		for(int i=0;i<tileEntityChest.getSizeInventory();++i) {
			newTileEntityChest.setInventorySlotContents(i, tileEntityChest.getStackInSlot(i));
			tileEntityChest.setInventorySlotContents(i, null);
		}
		newTileEntityChest.adjacentChestChecked = tileEntityChest.adjacentChestChecked;
		newTileEntityChest.adjacentChestZNeg = tileEntityChest.adjacentChestZNeg;
		newTileEntityChest.adjacentChestXPos = tileEntityChest.adjacentChestXPos;
		newTileEntityChest.adjacentChestXNeg = tileEntityChest.adjacentChestXNeg;
		newTileEntityChest.adjacentChestZPosition = tileEntityChest.adjacentChestZPosition;
		newTileEntityChest.lidAngle = tileEntityChest.lidAngle;
		newTileEntityChest.prevLidAngle = tileEntityChest.prevLidAngle;
		newTileEntityChest.numUsingPlayers = tileEntityChest.numUsingPlayers;
		
		world.setBlock(x, y, z, unpadlockedBlock, metadeta, 3);
		world.setBlockTileEntity(x, y, z, newTileEntityChest);
	}
}
