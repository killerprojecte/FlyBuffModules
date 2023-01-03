package flybuff.enchantment;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.fastmcmirror.flybuff.api.BuffCallback;
import org.fastmcmirror.flybuff.api.BuffInstallHandler;

import java.util.Arrays;

public class InstallEnchantHandler implements BuffInstallHandler {
    @Override
    public BuffCallback checkCondition(Player player, ItemStack target, ItemStack gem, String buff_name, String param) {
        return new BuffCallback();
    }

    @Override
    public ItemStack handle(Player player, ItemStack target, ItemStack gem, String buff_name, String param) {
        String[] args = param.split(" ");
        String enchant_name = args[1];
        if (Arrays.stream(Enchantment.values()).noneMatch(enchantment -> enchantment.getName().equalsIgnoreCase(enchant_name))){
            throw new IllegalArgumentException("未知的附魔类型: " + enchant_name + " 请检查当前版本是否含有此附魔 或 是否输入错误");
        }
        ItemMeta meta = target.getItemMeta();
        if (args[0].equals("add")){
            int level = Integer.parseInt(args[2]);
            meta.addEnchant(Enchantment.getByName(enchant_name), level, true);
        } else if (args[0].equals("remove")){
            meta.removeEnchant(Enchantment.getByName(enchant_name));
        }
        target.setItemMeta(meta);
        return target;
    }

    @Override
    public String getIdentifier() {
        return "enchant";
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
        return "FlyBuffEnchantment";
    }
}
