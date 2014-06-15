package vazkii.botania.common.block.decor.slabs.living;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.decor.slabs.BlockLivingSlab;

public class BlockLivingrockSlab extends BlockLivingSlab {

	public BlockLivingrockSlab(boolean full) {
		super(full, ModBlocks.livingrock, 0);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
	}

	@Override
	public BlockSlab getFullBlock() {
		return (BlockSlab) ModBlocks.livingrockBrickSlabFull;
	}

	@Override
	public BlockSlab getSingleBlock() {
		return (BlockSlab) ModBlocks.livingrockBrickSlab;
	}

}
