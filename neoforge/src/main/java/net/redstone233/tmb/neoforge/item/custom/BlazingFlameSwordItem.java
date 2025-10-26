package net.redstone233.tmb.neoforge.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import net.redstone233.tmb.neoforge.keys.ModKeys;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BlazingFlameSwordItem extends SwordItem {
    public BlazingFlameSwordItem(Tier tier, Properties properties) {
        super(tier, properties
                .attributes(SwordItem.createAttributes(
                        Tiers.NETHERITE,
                        500,
                        -4.7f
                ))
        );
    }


    /**
     * 伤害敌人的方法，当使用该物品攻击敌人时触发
     * @param stack 当前使用的物品堆栈
     * @param target 被攻击的目标实体
     * @param attacker 发起攻击的实体
     * @return 如果成功造成伤害则返回true，否则返回false
     */
    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        // 以下是注释掉的代码，用于在主手或副手使用时造成物品耐久度损失
//        stack.isDamaged(1,attacker, EquipmentSlot.MAINHAND);
//        stack.isDamaged(1,attacker, EquipmentSlot.OFFHAND);

        // 检查目标是否是生物实体，攻击者是否是玩家
        if (target instanceof LivingEntity livingEntity && attacker instanceof Player player && !target.fireImmune()) {
            // 获取目标实体的燃烧状态
            livingEntity.isOnFire();
            // 设置目标实体的剩余燃烧刻度（20刻 = 1秒，共2400刻 = 120秒）
            livingEntity.setRemainingFireTicks(2400);
            // 点燃目标实体，持续120秒
            livingEntity.igniteForSeconds(120.0f);
            // 为玩家添加速度提升效果，持续1200刻（60秒），等级为4
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,1200,4,false,false,false));
            // 为玩家添加火焰抗性效果，持续1200刻（60秒），等级为4
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,1200,4,false,false,false));
            // 以下是注释掉的代码，功能与上面类似但使用了不同的API
//            livingEntity.setOnFire(true);
//            livingEntity.setOnFireForTicks(2400);
//            livingEntity.setOnFireFor(120.0f);
//            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,1200,4,false,false,false));
//            player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,1200,4,false,false,false));
            return true;
        } else {
            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 1200,4));
            return false;
        }
    }

    @Override
    public int getEnchantmentValue() {
        return 15;
    }

    /**
 * 为物品添加悬停文本信息
 * 这个方法会在鼠标悬停在物品上时显示额外的描述信息
 *
 * @param stack 物品堆栈实例，提供物品相关信息
 * @param context 悬停上下文，提供悬停时的环境信息
 * @param tooltipComponents 悬停文本组件列表，用于添加文本内容
 * @param tooltipFlag 悬停标志，控制悬停文本的显示方式
 */
    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
    // 添加物品名称"火焰之剑"，使用红色和黄色加粗显示
        tooltipComponents.add(Component.literal("§c§l火焰之剑").withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD));
    // 添加使用说明，包含按键提示
        tooltipComponents.add(Component.translatable("tooltip.ability_sword.display1").withStyle(ChatFormatting.WHITE)
                .append(Component.translatable("key.use_ability.item",Component.translatable(ModKeys.USE_ABILITY_KEY.getDisplayName().getString())
                                .withStyle(ChatFormatting.GOLD))
                        .append(Component.translatable("tooltip.ability_sword.display2").withStyle(ChatFormatting.WHITE))
                ));
    // 添加物品描述第一部分
        tooltipComponents.add(Component.literal("§7§l火焰之剑，拥有火焰之威，").withStyle(ChatFormatting.GRAY));
    // 添加物品描述第二部分
        tooltipComponents.add(Component.literal("专属定制武器").withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.BOLD));
    // 添加物品功能描述，包含换行符
        tooltipComponents.add(Component.literal("§7§l能够点燃敌人，并给予使用者速度和防火效果。\n\n").withStyle(ChatFormatting.GRAY));
    // 添加物品稀有度信息
        tooltipComponents.add(Component.literal("[稀有度]").append(Component.literal("传说").withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD)));
    // 调用父类的appendHoverText方法，确保其他可能的悬停文本也能显示
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

/**
 * 重写use方法，处理玩家使用物品时的交互逻辑
 * @param level 当前游戏世界
 * @param player 使用物品的玩家
 * @param usedHand 使用的手（主手或副手）
 * @return 交互结果持有者，包含交互结果和物品堆栈
 */
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
    // 检查玩家是否为客户端玩家
        if (player instanceof Player playerEntity && level.isClientSide) {
        // 添加抗火效果，持续1200 ticks（60秒），等级4
            playerEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,1200,4,false,false,false));
        // 添加饱和度效果，持续300 ticks（15秒），等级4，显示图标和粒子
            playerEntity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 300,4,false,true,true));
        // 返回成功交互结果，并返回主手物品
            return InteractionResultHolder.success(playerEntity.getMainHandItem());
        } else {
        // 如果不是客户端或玩家不正确，显示错误消息
            player.displayClientMessage(Component.literal("似乎没有正常运行。").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
        // 返回失败交互结果，并返回当前物品
            return InteractionResultHolder.fail(player.getMainHandItem());
        }
    }

/**
 * 重写交互方法，用于处理玩家与生物实体之间的交互行为
 * @param stack 玩家使用的物品堆
 * @param player 进行交互的玩家
 * @param interactionTarget 被交互的目标生物实体
 * @param usedHand 交互使用的手（主手或副手）
 * @return 交互结果，成功或失败
 */
    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity interactionTarget, @NotNull InteractionHand usedHand) {
        // 检查玩家和目标实体是否有效
        if (player instanceof Player playerEntity && interactionTarget instanceof LivingEntity target) {
            // 检查是否按下了能力使用键
            if (ModKeys.isUseAbilityKeyPressed()) {
                // 获取目标实体的燃烧状态
                target.isOnFire();
                // 检查目标实体是否对火焰免疫
                target.fireImmune();
                // 设置目标实体的剩余燃烧刻度（20刻 = 1秒，共2400刻 = 120秒）
                target.setRemainingFireTicks(3600);
                // 点燃目标实体，持续120秒
                target.igniteForSeconds(180.0f);
            }
            playerEntity.addEffect(new MobEffectInstance(MobEffects.JUMP,3600,4));
            playerEntity.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST,1800,6));
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.FAIL;
        }
    }

    /**
 * 重写postHurtEnemy方法，用于处理武器造成伤害后的行为
 * @param stack 受到伤害的物品堆栈
 * @param target 被攻击的目标生物
 * @param attacker 发起攻击的生物
 */
    @Override
    public void postHurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
    // 使物品在主手受到1点伤害
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    // 使物品在副手受到1点伤害
        stack.hurtAndBreak(1, attacker, EquipmentSlot.OFFHAND);
        // 调用父类的postHurtEnemy方法，执行默认的伤害后处理逻辑
        super.postHurtEnemy(stack, target, attacker);
    }



}
