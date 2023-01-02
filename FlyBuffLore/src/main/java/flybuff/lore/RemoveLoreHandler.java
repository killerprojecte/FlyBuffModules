package flybuff.lore;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.fastmcmirror.flybuff.api.BuffCallback;
import org.fastmcmirror.flybuff.api.BuffRemoveHandler;
import org.fastmcmirror.flybuff.utils.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveLoreHandler implements BuffRemoveHandler {
    @Override
    public BuffCallback checkCondition(Player player, ItemStack target, ItemStack gem, String buff_name, String param) {
        return new BuffCallback();
    }

    @Override
    public ItemStack handle(Player player, ItemStack target, ItemStack gem, String buff_name, String param) {
        String[] args = param.split(" ");
        String lore = param.substring((args[0] + " ").length());
        ItemMeta meta = target.getItemMeta();
        List<String> list = new ArrayList<>();
        if (meta.hasLore() && meta.getLore().size() != 0) {
            list.addAll(meta.getLore());
        }
        if (args[0].equals("remove")) {
            list.removeIf(it -> it.startsWith(Color.color(PlaceholderAPI.setPlaceholders(player, lore))));
        } else if (args[0].equals("add")) {
            list.add(Color.color(PlaceholderAPI.setPlaceholders(player, lore)));
        } else if (args[0].equals("insert")) {
            list.add(Integer.parseInt(args[1]), Color.color(PlaceholderAPI.setPlaceholders(player, param.substring((args[0] + " " + args[1] + " ").length()))));
        } else if (args[0].equals("replace")) {
            list = list.stream().map(it -> it.replace(Color.color(PlaceholderAPI.setPlaceholders(player, args[1].replace("%space%", " "))), Color.color(PlaceholderAPI.setPlaceholders(player, args[2].replace("%space%", " "))))).collect(Collectors.toList());
        }
        meta.setLore(list);
        target.setItemMeta(meta);
        return target;
    }

    @Override
    public String getIdentifier() {
        return "lore";
    }

    @Override
    public String getAuthor() {
        return "FlyProject";
    }

    @Override
    public List<String> getDependencies() {
        return Arrays.asList("PlaceholderAPI");
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String getName() {
        return "FlyBuffLore";
    }
}
