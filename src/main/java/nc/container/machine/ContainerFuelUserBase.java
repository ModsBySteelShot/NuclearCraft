package nc.container.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import nc.tile.machine.TileFuelUser;
import nc.util.BurnTime;

public abstract class ContainerFuelUserBase<T extends TileFuelUser> extends Container {

    private final T entity;
    public int lastBurnTime;
    public int lastItemBurnTime;
    public int lastCookTime;

    public ContainerFuelUserBase(InventoryPlayer inventory, T tileEntity) {
        this.entity = tileEntity;

        this.addSlotToContainer(new Slot(tileEntity, 0, 56, 17));
        this.addSlotToContainer(new Slot(tileEntity, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnace(inventory.player, tileEntity, 2, 116, 35));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    protected abstract boolean validateItemStack(ItemStack itemStack);

    public void addCraftingToCrafters(ICrafting icrafting) {
        super.addCraftingToCrafters(icrafting);

        icrafting.sendProgressBarUpdate(this, 0, this.entity.cookTime);
        icrafting.sendProgressBarUpdate(this, 1, this.entity.burnTime);
        icrafting.sendProgressBarUpdate(this, 2, this.entity.currentItemBurnTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (Object crafter : this.crafters) {
            ICrafting icrafting = (ICrafting) crafter;

            if (this.lastCookTime != this.entity.cookTime) {
                icrafting.sendProgressBarUpdate(this, 0, this.entity.cookTime);
            }

            if (this.lastBurnTime != this.entity.burnTime) {
                icrafting.sendProgressBarUpdate(this, 1, this.entity.burnTime);
            }

            if (this.lastItemBurnTime != this.entity.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate(this, 2, this.entity.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.entity.cookTime;
        this.lastBurnTime = this.entity.burnTime;
        this.lastItemBurnTime = this.entity.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int newValue) {
        switch (slot) {
            case 0:
                this.entity.cookTime = newValue;
            case 1:
                this.entity.burnTime = newValue;
            case 2:
                this.entity.currentItemBurnTime = newValue;
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int clickedSlotNumber) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(clickedSlotNumber);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (clickedSlotNumber == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (clickedSlotNumber != 1 && clickedSlotNumber != 0) {
                if (validateItemStack(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (BurnTime.getItemBurnTime(itemstack1) > 0) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (clickedSlotNumber < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return null;
                    }
                } else if (clickedSlotNumber < 39) {
                    if (!this.mergeItemStack(itemstack1, 3, 30, false)) {
                        return null;
                    }
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.entity.isUseableByPlayer(entityPlayer);
    }
}
