package cn.evolvefield.mods.simpleeco.utils;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.extensions.IForgeBlockState;
import net.minecraftforge.common.util.FakePlayer;

import java.util.List;

public class Location {

    public final World world;
    public double x, y, z;
    private BlockPos blockPos;

    public Location(World world, BlockPos pos) {
        this(world, pos.getX(), pos.getY(), pos.getZ());
    }

    public Location(World world, double x, double y, double z) {

        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;

        blockPos = new BlockPos(x, y, z);
    }

    public Location(TileEntity tileEntity) {
        this(tileEntity.getLevel(), tileEntity.getBlockPos().getX(), tileEntity.getBlockPos().getY(), tileEntity.getBlockPos().getZ());
    }

    public Location(Entity entity) {
        this(entity.level, entity.getX(), entity.getY(), entity.getZ());
    }

    public Location(Location location, Direction dir) {
        this(location, dir, 1);
    }

    public Location(Location location, Direction dir, int distance) {

        this.world = location.world;
        this.x = location.x + (dir.getStepX() * distance);
        this.y = location.y + (dir.getStepY() * distance);
        this.z = location.z + (dir.getStepZ() * distance);

        blockPos = new BlockPos(x, y, z);
    }

    public Location translate(Direction dir, int distance) {

        this.x += (dir.getStepX() * distance);
        this.y += (dir.getStepY() * distance);
        this.z += (dir.getStepZ() * distance);
        blockPos = new BlockPos(x, y, z);

        return this;
    }

    public Location translate(Location location) {

        this.x += location.x;
        this.y += location.y;
        this.z += location.z;
        blockPos = new BlockPos(x, y, z);
        return this;
    }

    public Location copy() {
        return new Location(this.world, this.x, this.y, this.z);
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public IForgeBlockState getForgeBlockState() {

        if (getBlockPos() == null) {
            return null;
        }

        return world.getBlockState(getBlockPos());
    }

    public BlockState getBlockState() {

        if (getForgeBlockState() == null) {
            return null;
        }

        return getForgeBlockState().getBlockState();
    }

    public Block getBlock() {

        if (getBlockState() == null) {
            return null;
        }

        return getBlockState().getBlock();
    }

    public Material getBlockMaterial() {
        return getBlockState().getMaterial();
    }

    public List<ItemStack> getDrops(PlayerEntity player, ItemStack heldStack) {
        return Block.getDrops(getBlockState(), (ServerWorld) world, getBlockPos(), null, player, heldStack);
    }

    public int getLightValue() {
        return world.getLightEmission(getBlockPos());
    }

    public TileEntity getTileEntity() {
        return world.getBlockEntity(getBlockPos());
    }

    public double getDistance(Location location) {

        double dx = x - location.x;
        double dy = y - location.y;
        double dz = z - location.z;

        return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
    }

    public void setBlock(Block block) {
        world.setBlockAndUpdate(getBlockPos(), block.getBlock().defaultBlockState());
    }

    public void setBlock(BlockState state) {
        world.setBlockAndUpdate(getBlockPos(), state.getBlock().defaultBlockState());
        world.setBlockAndUpdate(getBlockPos(), state);
    }

    public void setBlock(BlockState state, PlayerEntity placer) {
        world.setBlock(getBlockPos(), state, 2);
        state.getBlock().setPlacedBy(world, getBlockPos(), state, placer, new ItemStack(state.getBlock()));
    }

    public void setBlockToAir() {
        setBlock(Blocks.AIR);
    }

    public void breakBlock(PlayerEntity player, ItemStack heldItem) {

        if (player instanceof FakePlayer) {
            return;
        }

        if (player instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) player).gameMode.destroyBlock(blockPos);
        }

        SoundUtil.playBlockPlaceSound(world, player, getBlockState(), this);
    }

    public boolean isZero() {
        return x == 0 && y == 0 && z == 0;
    }

    public boolean isAirBlock() {
        return getBlock() == Blocks.AIR;
    }

    public boolean isBlockValidForPlacing() {
        return getBlockMaterial().isReplaceable() || isAirBlock();
    }

    public boolean isFullCube() {
        return getBlockState().isCollisionShapeFullBlock(world, getBlockPos());
    }

    public boolean isEntityAtLocation(Entity entity) {

        double entityX = entity.getX();
        double entityY = entity.getY();
        double entityZ = entity.getZ();

        return entityX == x && entityZ == z && (entityY == y || entityY + 1 == y);
    }

    public boolean doesBlockHaveCollision() {
        return getBlock().getCollisionShape(getBlockState(), world, getBlockPos(), ISelectionContext.empty()) != VoxelShapes.empty();
    }

    public static Location readFromNBT(World world, CompoundNBT nbt) {

        double x = nbt.getDouble("locX");
        double y = nbt.getDouble("locY");
        double z = nbt.getDouble("locZ");

        Location loc = new Location(world, x, y, z);

        if (!loc.isZero()) {
            return loc;
        }

        return null;
    }

    public void writeToNBT(CompoundNBT nbt) {
        nbt.putDouble("locX", z);
        nbt.putDouble("locY", y);
        nbt.putDouble("locZ", z);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Location) {
            Location newLoc = (Location) obj;
            return world == newLoc.world && x == newLoc.x && y == newLoc.y && z == newLoc.z;
        }

        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + ", " + z + "]";
    }
}
