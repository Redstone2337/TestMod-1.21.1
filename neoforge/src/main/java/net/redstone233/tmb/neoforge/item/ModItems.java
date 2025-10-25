package net.redstone233.tmb.neoforge.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redstone233.tmb.annotation.NeoForged;
import net.redstone233.tmb.core.TestMod;
import net.redstone233.tmb.neoforge.item.custom.BlazingFlameSwordItem;
import net.redstone233.tmb.neoforge.item.custom.IceFreezeSwordItem;


@NeoForged
public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TestMod.MOD_ID);

    public static final DeferredItem<IceFreezeSwordItem> ICE_FREEZE_SWORD = ITEMS.register("ice_freeze_sword",
            () -> new IceFreezeSwordItem(
            Tiers.NETHERITE,
            new Item.Properties()
                    .durability(3000000)
    ));

    public static final DeferredItem<BlazingFlameSwordItem> BLAZING_FLAME_SWORD = ITEMS.register("blazing_flame_sword",
            () -> new BlazingFlameSwordItem(
                    Tiers.NETHERITE,
                    new Item.Properties()
                            .durability(3000000)
            ));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
