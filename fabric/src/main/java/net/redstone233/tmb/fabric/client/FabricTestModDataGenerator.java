package net.redstone233.tmb.fabric.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.redstone233.tmb.annotation.Fabric;
import net.redstone233.tmb.fabric.data.ModEnglishLanguageProvider;
import net.redstone233.tmb.fabric.data.ModModelsProvider;

@Fabric
public class FabricTestModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelsProvider::new);
        pack.addProvider(ModEnglishLanguageProvider::new);
    }
}
