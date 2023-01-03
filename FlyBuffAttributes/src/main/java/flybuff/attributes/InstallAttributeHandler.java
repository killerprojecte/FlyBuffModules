package flybuff.attributes;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.fastmcmirror.flybuff.api.BuffCallback;
import org.fastmcmirror.flybuff.api.BuffInstallHandler;

import java.util.Arrays;

public class InstallAttributeHandler implements BuffInstallHandler {
    @Override
    public BuffCallback checkCondition(Player player, ItemStack target, ItemStack gem, String buff_name, String param) {
        return new BuffCallback();
    }

    @Override
    public ItemStack handle(Player player, ItemStack target, ItemStack gem, String buff_name, String param) {
        String[] args = param.split(" ");
        ItemMeta meta = target.getItemMeta();
        String attribute_name = args[1];
        if (Arrays.stream(Attribute.values()).noneMatch(attribute -> attribute.name().equalsIgnoreCase(attribute_name))){
            throw new IllegalArgumentException("未知的物品属性: " + attribute_name + " 请检查当前服务器版本是否支持此属性 或 是否输入错误");
        }
        Attribute attribute = Attribute.valueOf(attribute_name.toUpperCase());
        AttributeModifier modifier = new AttributeModifier("FlyBuffAttributes", Double.parseDouble(args[2]) , AttributeModifier.Operation.ADD_NUMBER);
        if (args[0].equals("add")){
            meta.addAttributeModifier(attribute, modifier);
        } else if (args[0].equals("remove")){
            meta.removeAttributeModifier(attribute, modifier);
        }
        target.setItemMeta(meta);
        return target;
    }

    @Override
    public String getIdentifier() {
        return "attribute";
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
        return "FlyBuffAttributes";
    }
}
