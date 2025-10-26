package net.redstone233.tmb.neoforge.data;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.redstone233.tmb.core.TestMod;
import net.redstone233.tmb.neoforge.item.ModItems;

public class ModEnglishLanguageProvider extends LanguageProvider {
    public ModEnglishLanguageProvider(PackOutput output) {
        super(output, TestMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("key.tmb.use_ability", "Use Ability For Sword");
        add("category.tmb", "Test Mod");
        add("itemGroup.tmb.mod_items", "Test Mod | Customization Items");
        add("itemGroup.tmb.mod_weapons", "Test Mod | Customization Weapons");

        add("tooltip.ability_sword.display1","Hold [");
        add("key.use_ability.item","%s");
        add("tooltip.ability_sword.display2"," ] for Ability to use");

        add(ModItems.BLAZING_FLAME_SWORD.asItem(), "Blazing Flame Sword");
        add(ModItems.ICE_FREEZE_SWORD.asItem(), "Ice Freeze Sword");
    }
}
