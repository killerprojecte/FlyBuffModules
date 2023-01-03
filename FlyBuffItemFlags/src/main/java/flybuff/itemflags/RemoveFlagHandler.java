package flybuff.itemflags;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.fastmcmirror.flybuff.api.BuffCallback;
import org.fastmcmirror.flybuff.api.BuffRemoveHandler;

import java.util.Arrays;

public class RemoveFlagHandler implements BuffRemoveHandler {
    @Override
    public BuffCallback checkCondition(Player player, ItemStack target, ItemStack gem, String buff_name, String param) {
        return new BuffCallback();
    }

    @Override
    public ItemStack handle(Player player, ItemStack target, ItemStack gem, String buff_name, String param) {
        String[] args = param.split(" ");
        ItemMeta meta = target.getItemMeta();
        String flag = args[1];
        if (!flag.equalsIgnoreCase("UNBREAKABLE") && Arrays.stream(ItemFlag.values()).noneMatch(temp -> temp.name().equalsIgnoreCase(flag))){
            throw new IllegalArgumentException("未知的物品标识: " + flag + " 请检查当前服务器版本是否支持此标识 或 是否输入错误");
        }
        if (args[0].equals("add")){
            if (flag.equalsIgnoreCase("UNBREAKABLE")) {
                meta.setUnbreakable(true);
            } else {
                meta.addItemFlags(ItemFlag.valueOf(flag.toUpperCase()));
            }
        } else if (args[0].equals("remove")){
            if (flag.equalsIgnoreCase("UNBREAKABLE")) {
                meta.setUnbreakable(false);
            } else {
                meta.removeItemFlags(ItemFlag.valueOf(flag.toUpperCase()));
            }
        }
        target.setItemMeta(meta);
        return target;
    }

    @Override
    public String getIdentifier() {
        return "itemflag";
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
        return "FlyBuffItemFlags";
    }
}
