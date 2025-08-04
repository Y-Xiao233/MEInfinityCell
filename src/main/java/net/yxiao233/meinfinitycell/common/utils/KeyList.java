package net.yxiao233.meinfinitycell.common.utils;

import appeng.api.stacks.AEKey;
import appeng.api.stacks.KeyCounter;
import net.yxiao233.meinfinitycell.Meinfinitycell;

import java.util.ArrayList;
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
}
