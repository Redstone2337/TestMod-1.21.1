package net.redstone233.tmb.neoforge;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.redstone233.tmb.annotation.NeoForged;
import net.redstone233.tmb.core.TestMod;
import net.redstone233.tmb.neoforge.data.ModEnglishLanguageProvider;
import net.redstone233.tmb.neoforge.data.ModItemModelsProvider;

import java.util.concurrent.CompletableFuture;


@NeoForged
@EventBusSubscriber(modid = TestMod.MOD_ID)
public class NeoforgeTestModDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new ModItemModelsProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModEnglishLanguageProvider(packOutput));

    }
}
