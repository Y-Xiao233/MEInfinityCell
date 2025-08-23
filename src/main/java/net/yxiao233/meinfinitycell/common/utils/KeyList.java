package net.yxiao233.meinfinitycell.common.utils;

import appeng.api.stacks.AEKey;
import appeng.api.stacks.GenericStack;
import appeng.api.stacks.KeyCounter;
import appeng.api.storage.cells.ISaveProvider;
import appeng.api.storage.cells.StorageCell;
import appeng.core.AEConfig;
import appeng.items.storage.StorageCellTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.yxiao233.meinfinitycell.Meinfinitycell;
import net.yxiao233.meinfinitycell.common.inventory.InfinitiesCellInventory;
import net.yxiao233.meinfinitycell.common.items.InfinitiesCell;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class KeyList {
    private final ArrayList<Supplier<AEKey>> keys;
    public KeyList(){
        this.keys = new ArrayList<>();
    }
    public KeyList(ArrayList<Supplier<AEKey>> keys){
        this.keys = keys;
    }
    public static KeyList of(){
        return new KeyList();
    }

    public KeyList add(Supplier<AEKey> key){
        this.keys.add(key);
        return this;
    }

    public KeyList adds(Consumer<ArrayList<Supplier<AEKey>>> keys){
        keys.accept(this.keys);
        return this;
    }

    public boolean contains(AEKey what){
        for(Supplier<AEKey> key : keys) {
            if (key.get().equals(what)) {
                return true;
            }
        }
        return false;
    }

    public long insert(AEKey what, long amount){
        if(contains(what)){
            return amount;
        }
        return 0;
    }

    public long extract(AEKey what, long amount){
        if(contains(what)){
            return amount;
        }
        return 0;
    }

    public void getAvailableStacks(KeyCounter out){
        this.keys.forEach(keySupplier -> {
            out.add(keySupplier.get(), Meinfinitycell.getMax(keySupplier.get()));
        });
    }

    public boolean isEmpty(){
        return this.keys.isEmpty();
    }

    public ArrayList<Supplier<AEKey>> getList(){
        return this.keys;
    }

    public StorageCell getCellInventory(ItemStack is, ISaveProvider container) {
        return !is.isEmpty() && is.getItem() instanceof InfinitiesCell ? new InfinitiesCellInventory(is) : null;
    }
    public Optional<TooltipComponent> getTooltipImage(ItemStack is) {
        StorageCell handler = this.getCellInventory(is, (ISaveProvider)null);
        if (handler == null) {
            return Optional.empty();
        } else {
            boolean hasMoreContent;
            Object content;
            if (AEConfig.instance().isTooltipShowCellContent()) {
                content = new ArrayList<AEKey>();
                int maxCountShown = AEConfig.instance().getTooltipMaxCellContentShown();

                keys.forEach(keySupplier -> {
                    ((List)content).add(new GenericStack(keySupplier.get(),Meinfinitycell.getMax(keySupplier.get())));
                });

                hasMoreContent = ((List)content).size() > maxCountShown;
                if (((List)content).size() > maxCountShown) {
                    ((List)content).subList(maxCountShown, ((List)content).size()).clear();
                }
            } else {
                hasMoreContent = false;
                content = Collections.emptyList();
            }

            return Optional.of(new StorageCellTooltipComponent(List.of(), (List)content, hasMoreContent, false));
        }
    }
}
