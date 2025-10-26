package net.redstone233.tmb.neoforge.data;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.redstone233.tmb.core.TestMod;
import net.redstone233.tmb.neoforge.item.ModItems;

public class ModItemModelsProvider extends ItemModelProvider {
    public ModItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TestMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheldItem(ModItems.ICE_FREEZE_SWORD.asItem());
        handheldItem(ModItems.BLAZING_FLAME_SWORD.asItem());
    }
}
