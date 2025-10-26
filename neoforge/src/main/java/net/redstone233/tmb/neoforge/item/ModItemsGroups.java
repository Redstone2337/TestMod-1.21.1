package net.redstone233.tmb.neoforge.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redstone233.tmb.core.TestMod;

import java.util.function.Supplier;

public class ModItemsGroups {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod.MOD_ID);

    public static final Supplier<CreativeModeTab> MOD_ITEMS =
            CREATIVE_MODE_TABS.register("mod_items", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ICE_FREEZE_SWORD.get()))
                    .title(Component.translatable("itemGroup.tmb.mod_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.ICE_FREEZE_SWORD);
                        output.accept(ModItems.BLAZING_FLAME_SWORD);
                    }).build());

    public static final Supplier<CreativeModeTab> MOD_WEAPONS =
            CREATIVE_MODE_TABS.register("mod_weapons", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.BLAZING_FLAME_SWORD.get()))
                    .title(Component.translatable("itemGroup.tmb.mod_weapons"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.BLAZING_FLAME_SWORD);
                    }).withTabsBefore(ResourceLocation.fromNamespaceAndPath(TestMod.MOD_ID, "mod_items"))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
