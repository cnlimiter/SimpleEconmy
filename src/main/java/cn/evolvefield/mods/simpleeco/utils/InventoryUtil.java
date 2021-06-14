package cn.evolvefield.mods.simpleeco.utils;


import cn.evolvefield.mods.simpleeco.inventory.SEItemHandler;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Objects;

public class InventoryUtil {
    private static int getSlotAmount(Object obj) {

        if (obj instanceof IInventory) {
            return ((IInventory)obj).getSizeInventory();
        }

        if (obj instanceof SEItemHandler) {
            return ((SEItemHandler)obj).getSlots();
        }

        return 0;
    }

    private static boolean isStackValidForSlot(Object obj, ItemStack stack, int slotId) {

        if (obj instanceof IInventory) {
            return ((IInventory)obj).isItemValidForSlot(slotId, stack);
        }

        if (obj instanceof SEItemHandler) {
            return ((SEItemHandler)obj).isItemValid(slotId, stack);
        }

        return false;
    }

    private static ItemStack getStackInSlot(Object obj, int slotId) {

        if (obj instanceof IInventory) {
            return ((IInventory)obj).getStackInSlot(slotId);
        }

        if (obj instanceof SEItemHandler) {
            return ((SEItemHandler)obj).getStackInSlot(slotId);
        }

        return ItemStack.EMPTY;
    }

    private static void setStackInSlot(Object obj, ItemStack stack, int slotId) {

        if (obj instanceof IInventory) {
            ((IInventory)obj).setInventorySlotContents(slotId, stack);
        }

        if (obj instanceof SEItemHandler) {
            ((SEItemHandler)obj).setStackInSlot(slotId, stack);
        }
    }

    private static void decreaseCountFromSlot(Object obj, int slot, int decreaseAmount) {

        if (obj instanceof IInventory) {
            ((IInventory)obj).decrStackSize(slot, decreaseAmount);
        }

        if (obj instanceof SEItemHandler) {
            ((SEItemHandler)obj).decrStackSize(slot, decreaseAmount);
        }
    }

    public static boolean canInsertStack(ItemStack stack, Object obj) {

        int amountLeft = stack.getCount();

        for (int slotId = 0; slotId < getSlotAmount(obj); slotId++) {

            ItemStack stackInSlot = getStackInSlot(obj, slotId);

            if (isStackValidForSlot(obj, stack, slotId)) {

                if (stackInSlot.isEmpty()) {
                    amountLeft -= stack.getMaxStackSize();
                }

                else if (ItemStack.areItemsEqual(stackInSlot, stack)) {

                    if (stack.hasTag()) {

                        if (!stackInSlot.hasTag() || ItemStack.areItemStackTagsEqual(stack, stackInSlot)) {
                            continue;
                        }
                    }

                    int spaceLeftInStack = stack.getMaxStackSize() - stackInSlot.getCount();
                    amountLeft -= spaceLeftInStack;
                }
            }
        }

        return amountLeft <= 0;
    }

    public static void insertOverflowingStack(Object obj, ItemStack stack) {

        if (stack.getCount() >= stack.getMaxStackSize()) {

            int amountLeft = stack.getCount();

            while (amountLeft > 0) {

                ItemStack partialStack = stack.copy();
                int stackSize = Math.min(amountLeft, stack.getMaxStackSize());
                partialStack.setCount(stackSize);
                amountLeft -= stackSize;

                InventoryUtil.insertStack(obj, partialStack);
            }
        }

        else InventoryUtil.insertStack(obj, stack);
    }

    public static void insertStack(Object obj, ItemStack stack) {
        insertStackWithOffset(0, obj, stack);
    }

    public static void insertStackWithOffset(int slotOffset, Object obj, ItemStack stack) {

        for (int slotId = slotOffset; slotId < getSlotAmount(obj); slotId++) {

            ItemStack stackInSlot = getStackInSlot(obj, slotId);

            if (ItemStack.areItemsEqual(stackInSlot, stack) && (stackInSlot.getCount() + stack.getCount() <= stack.getMaxStackSize())) {

                if (stack.hasTag()) {

                    if (!ItemStack.areItemStackTagsEqual(stackInSlot, stack)) {
                        continue;
                    }
                }

                ItemStack stack2 = stack.copy();
                stack2.setCount(stackInSlot.getCount() + stack.getCount());

                setStackInSlot(obj, stack2, slotId);
                break;
            }

            else if (stackInSlot.isEmpty()) {
                setStackInSlot(obj, stack, slotId);
                break;
            }
        }
    }

    public static boolean insertHeldStackIntoSlot(ItemStack stack, Object obj, int slotId, boolean removeStack) {

        if (getSlotAmount(obj) > slotId) {

            if (!stack.isEmpty() && getStackInSlot(obj, slotId).isEmpty()) {

                setStackInSlot(obj, stack.copy(), slotId);

                if (removeStack) {
                    stack.setCount(0);
                }

                return true;
            }
        }

        return false;
    }

    public static void breakInventory (World world, SEItemHandler inventory, Location location) {

        for (int slotId = 0; slotId < inventory.getSlots(); slotId++) {

            ItemStack stackInSlot = inventory.getStackInSlot(slotId);

            if (!stackInSlot.isEmpty()) {

                ItemEntity dropEntity = ItemsUtil.spawnStackAtLocation(world, location, stackInSlot);

                if (stackInSlot.hasTag()) {
                    dropEntity.getItem().setTag(stackInSlot.getTag());
                }
            }
        }
    }

    public static int countItems (Object obj, boolean useNBT, ItemStack stack) {

        if (stack.getCount() > stack.getMaxStackSize()) {

            for (int i = 0; i < stack.getCount(); i++) {
                stack.setCount(1);
                countItems(obj, useNBT, stack);
            }
        }

        int count = 0;

        for (int slotId = 0; slotId < getSlotAmount(obj); slotId++) {

            ItemStack stackInSlot = getStackInSlot(obj, slotId);

            if (stackInSlot.isItemEqual(stack)) {

                if (useNBT && stack.hasTag()) {

                    if (stackInSlot.hasTag() && Objects.equals(stackInSlot.getTag(), stack.getTag())) {
                        count += stackInSlot.getCount();
                    }
                }

                else count += stackInSlot.getCount();
            }
        }

        return count;
    }

    public static void consumeStack(Object obj, int amount, boolean useNBT, ItemStack stack) {
        consumeStackWithOffset(obj, amount, useNBT, 0, stack);
    }

    public static void consumeStackWithOffset(Object obj, int amount, boolean useNBT, int slotOffset, ItemStack stack) {

        int amountLeft = amount;

        if (countItems(obj, useNBT, stack) >= amount) {

            for (int slotId = slotOffset; slotId < getSlotAmount(obj); slotId++) {

                if (amountLeft <= 0) {
                    break;
                }

                ItemStack stackInSlot = getStackInSlot(obj, slotId);

                if (!stackInSlot.isEmpty()) {

                    if (stackInSlot.isItemEqual(stack)) {

                        if (useNBT && stack.hasTag()) {

                            if (!stackInSlot.hasTag() || !Objects.equals(stackInSlot.getTag(), stack.getTag())) {
                                continue;
                            }
                        }

                        if (amountLeft >= stackInSlot.getCount()) {
                            amountLeft -= stackInSlot.getCount();
                            setStackInSlot(obj, ItemStack.EMPTY, slotId);
                        }

                        else {
                            ItemStack copy = stackInSlot.copy();
                            decreaseCountFromSlot(obj, slotId, amountLeft);
                            amountLeft -= copy.getCount();
                        }
                    }
                }
            }
        }
    }
}
