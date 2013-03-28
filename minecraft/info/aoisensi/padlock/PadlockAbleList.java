package info.aoisensi.padlock;

import java.util.ArrayList;

public class PadlockAbleList {
	private static ArrayList<Integer> padlockedBlocksId = new ArrayList<Integer>();
	private static ArrayList<Integer> unpadlockedBlocksId = new ArrayList<Integer>();
	private static ArrayList<Boolean> isLargeChestList = new ArrayList<Boolean>();
	
	private static int tmpIndex = -1;
	
	public static boolean addItem(int padlockedBlockId, int unpadlockedBlockId, boolean isLargeChest) {
		if(padlockedBlocksId.contains(padlockedBlockId)) {
			return false;
		}
		if(unpadlockedBlocksId.contains(padlockedBlockId)) {
			return false;
		}
		padlockedBlocksId.add(padlockedBlockId);
		unpadlockedBlocksId.add(unpadlockedBlockId);
		isLargeChestList.add(isLargeChest);
		return true;
	}
	
	public static int findFromPadlocked(int padlockedBlockId) {
		tmpIndex = padlockedBlocksId.indexOf(padlockedBlockId);
		return unpadlockedBlocksId.get(tmpIndex);
	}
	
	public static boolean containsPadlocked(int padlockedBlockId) {
		return padlockedBlocksId.contains(padlockedBlockId);
	}
	
	public static int findFromUnpadlocked(int unpadlockedBlockId) {
		tmpIndex = unpadlockedBlocksId.indexOf(unpadlockedBlockId);
		return padlockedBlocksId.get(tmpIndex);
	}
	
	public static boolean containsUnpadlocked(int unpadlockedBlockId) {
		return unpadlockedBlocksId.contains(unpadlockedBlockId);
	}
	
	public static boolean gotCanLarge() {
		return isLargeChestList.get(tmpIndex);
	}
}
