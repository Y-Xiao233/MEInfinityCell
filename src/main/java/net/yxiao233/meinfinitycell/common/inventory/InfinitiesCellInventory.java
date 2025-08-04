package net.yxiao233.meinfinitycell.common.inventory;

import appeng.api.config.Actionable;
import appeng.api.networking.security.IActionSource;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.KeyCounter;
import appeng.api.storage.cells.CellState;
import appeng.api.storage.cells.ICellHandler;
import appeng.api.storage.cells.ISaveProvider;
import appeng.api.storage.cells.StorageCell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.yxiao233.meinfinitycell.common.items.InfinitiesCell;
import net.yxiao233.meinfinitycell.common.utils.KeyList;
import org.jetbrains.annotations.Nullable;

public class InfinitiesCellInventory implements StorageCell {
    private final ItemStack stack;
    private final AEKey icon;
    private final KeyList keys;
    public static final ICellHandler HANDLER = new Handler();
    public InfinitiesCellInventory(ItemStack stack) {
        if (!(stack.getItem() instanceof InfinitiesCell cell)) {
            throw new IllegalArgumentException("Cell isn't an infinity cell!");
        }
        this.stack = stack;
        this.icon = cell.getIcon().get();
        this.keys = cell.getKeys();
    }
    @Override
    public CellState getStatus() {
        return CellState.NOT_EMPTY;
    }

    @Override
    public double getIdleDrain() {
        return 1;
    }

    @Override
    public long insert(AEKey what, long amount, Actionable mode, IActionSource source) {
        return this.keys.insert(what,amount);
    }

    @Override
    public long extract(AEKey what, long amount, Actionable mode, IActionSource source) {
        return this.keys.extract(what,amount);
    }

    @Override
    public void persist() {

    }

    @Override
    public Component getDescription() {
        return this.stack.getHoverName();
    }

    @Override
    public void getAvailableStacks(KeyCounter out) {
        this.keys.getAvailableStacks(out);
    }

    @Override
    public boolean isPreferredStorageFor(AEKey what, IActionSource source) {
        return this.icon.equals(what);
    }

    private static class Handler implements ICellHandler {

        @Override
        public boolean isCell(ItemStack is) {
            return is != null && is.getItem() instanceof InfinitiesCell;
        }

        @Override
        public @Nullable StorageCell getCellInventory(ItemStack is, @Nullable ISaveProvider host) {
            return isCell(is) ? new InfinitiesCellInventory(is) : null;
        }
    }
}
