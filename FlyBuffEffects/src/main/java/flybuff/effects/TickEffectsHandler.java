package flybuff.effects;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.fastmcmirror.flybuff.FlyBuff;
import org.fastmcmirror.flybuff.api.BuffTickHandler;
import org.fastmcmirror.flybuff.api.ItemSlot;

import java.util.Arrays;
import java.util.List;

public class TickEffectsHandler implements BuffTickHandler {
    @Override
    public ItemStack handle(Player player, ItemStack item, String buff_name, ItemSlot slot, String param) {
        //[effect] POTION_TYPE LEVEL TIME
        String[] args = param.split(" ");
        if (args.length != 3) {
            throw new IllegalArgumentException("[FlyBuffEffects] 错误的格式调用格式 请检查官方介绍");
        }
        if (Arrays.stream(PotionEffectType.values()).noneMatch(type -> type.getName().equalsIgnoreCase(args[0]))) {
            throw new IllegalArgumentException("[FlyBuffEffects] 未知的药水类型: " + args[0] + " 原因: 输入错误或当前版本尚不支持");
        }
        PotionEffect effect = new PotionEffect(PotionEffectType.getByName(args[0].toUpperCase()),
                (int) Double.parseDouble(MathEngine.format(PlaceholderAPI.setPlaceholders(player, args[2]))),
                (int) Double.parseDouble(MathEngine.format(PlaceholderAPI.setPlaceholders(player, args[1]))));
        for (PotionEffect active : player.getActivePotionEffects()) {
            if (effect.getType().equals(active.getType())) {
                if (active.getAmplifier() > effect.getAmplifier()) return item;
                if (active.getAmplifier() == effect.getAmplifier()) {
                    if (active.getDuration() > 10) return item;
                }
            }
        }
        Bukkit.getScheduler().runTask(FlyBuff.instance, () -> player.addPotionEffect(effect));
        return item;
    }

    @Override
    public String getIdentifier() {
        return "effect";
    }

    @Override
    public String getAuthor() {
        return "FlyProject";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String getName() {
        return "FlyBuffEffects";
    }

    @Override
    public List<String> getDependencies() {
        return Arrays.asList("PlaceholderAPI");
    }
}
