package net.yxiao233.meinfinitycell.common.compact.kubejs.helper;

import appeng.api.stacks.AEKey;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.latvian.mods.kubejs.fluid.FluidWrapper;
import dev.latvian.mods.rhino.util.HideFromJS;
import me.ramidzkh.mekae2.ae2.MekanismKey;
import mekanism.api.MekanismAPI;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalStack;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.function.Supplier;

@SuppressWarnings("removal")
public class MekanismKeyHelper {
    public static Supplier<AEKey> of(Object from){
        return () -> MekanismKey.of(stackOf(from));
    }

    @HideFromJS
    public static ChemicalStack stackOf(Object from) {
        return switch (from) {
            case null -> null;
            case ChemicalStack c -> c;
            case Chemical c -> new ChemicalStack(c.getAsHolder(), FluidType.BUCKET_VOLUME);
            case Holder<?> c -> new ChemicalStack((Holder) c, FluidType.BUCKET_VOLUME);
            default -> {
                var str = from.toString();

                if (str.isEmpty() || str.equals("mekanism:empty")) {
                    yield ChemicalStack.EMPTY;
                } else {
                    try {
                        yield readStack(new StringReader(str));
                    } catch (CommandSyntaxException ex) {
                        ex.printStackTrace();
                        yield ChemicalStack.EMPTY;
                    }
                }
            }
        };
    }

    @HideFromJS
    static Holder<Chemical> read(StringReader reader) throws CommandSyntaxException {
        reader.skipWhitespace();
        return MekanismAPI.CHEMICAL_REGISTRY.getHolder(ResourceLocation.read(reader)).get();
    }

    @HideFromJS
    static ChemicalStack readStack(StringReader reader) throws CommandSyntaxException {
        reader.skipWhitespace();
        var amount = FluidWrapper.readFluidAmount(reader);
        var chemical = read(reader);
        return new ChemicalStack(chemical, amount);
    }
}
