package net.redstone233.tmb.fabric.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.redstone233.tmb.annotation.Fabric;
import net.redstone233.tmb.fabric.item.ModItems;

import java.util.concurrent.CompletableFuture;

@Fabric
public class ModEnglishLanguageProvider extends FabricLanguageProvider {
    public ModEnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("key.tmb.use_ability", "Use Ability For Sword");
        translationBuilder.add("category.tmb", "Test Mod");
        translationBuilder.add("itemGroup.tmb.mod_items", "Test Mod | Customization Items");
        translationBuilder.add("itemGroup.tmb.mod_weapons", "Test Mod | Customization Weapons");

        translationBuilder.add("tooltip.ability_sword.display1","Hold [");
        translationBuilder.add("key.use_ability.item","%s");
        translationBuilder.add("tooltip.ability_sword.display2"," ] for Ability to use");

        translationBuilder.add(ModItems.BLAZING_FLAME_SWORD, "Blazing Flame Sword");
        translationBuilder.add(ModItems.ICE_FREEZE_SWORD, "Ice Freeze Sword");
    }
}
