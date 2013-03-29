package info.aoisensi.padlock;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;

public class ItemPadlock extends Item {
	
	public ItemPadlock(int id) {
		super(id);
		setMaxStackSize(1);
		setCreativeTab(Padlock.tabPadlock);
		setUnlocalizedName("padlock");
	}
	
	public ItemPadlock(int id, int maxStackSize, CreativeTabs tab, String name) {
		super(id);
		setMaxStackSize(maxStackSize);
		setCreativeTab(tab);
		setUnlocalizedName(name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister iconRegister) {
		iconIndex = iconRegister.registerIcon("padlock:padlock");
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		String response = "\u00a7e..";
		int key = par1ItemStack.getItemDamage();
		for(int i=0;i<6;++i) {
			switch((key>>(i*2))%4) {
			case 0:
				response += ".";
				break;
			case 1:
				response += "!";
				break;
			case 2:
				response += "i";
				break;
			case 3:
				response += "|";
				break;
			}
		}
		response += ".O";
		par3List.add(response);
//		par3List.add(Integer.toString(key));
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		par2EntityPlayer.sendChatToPlayer("onItemUse");
		return false;
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		player.sendChatToPlayer(String.format("metadeta:%d blockid:%d", world.getBlockMetadata(x, y, z), world.getBlockId(x, y, z)));
		int blockId = world.getBlockId(x, y, z);
		if(PadlockAbleList.containsUnpadlocked(blockId)) {
			int metadeta = world.getBlockMetadata(x, y, z);
			if(side == metadeta) {
				padlockChest(world, x, y, z, PadlockAbleList.findFromUnpadlocked(blockId), side);
				if(PadlockAbleList.gotCanLarge()) {
					if(willAbleunpadlock(world, x + 1, y, z, blockId, metadeta)) {
						return true;
					}
					if(willAbleunpadlock(world, x - 1, y, z, blockId, metadeta)) {
						return true;
					}
					if(willAbleunpadlock(world, x, y, z + 1, blockId, metadeta)) {
						return true;
					}
					if(willAbleunpadlock(world, x, y, z - 1, blockId, metadeta)) {
						return true;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	private boolean willAbleunpadlock(World world, int x, int y, int z, int blockId, int metadeta) {
		if(world.getBlockId(x, y, z) == blockId) {
			padlockChest(world, x, y, z, PadlockAbleList.findFromUnpadlocked(blockId), metadeta);
			return true;
		}
		return false;
	}
	
	private void padlockChest(World world, int x, int y, int z, int padlockedBlock, int metadeta) {
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
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
		
		world.setBlock(x, y, z, padlockedBlock, metadeta, 3);
		world.setBlockTileEntity(x, y, z, newTileEntityChest);
	}
}
