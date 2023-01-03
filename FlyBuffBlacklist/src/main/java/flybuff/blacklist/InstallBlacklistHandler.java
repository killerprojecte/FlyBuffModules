package flybuff.blacklist;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.fastmcmirror.flybuff.api.BuffCallback;
import org.fastmcmirror.flybuff.api.BuffInstallHandler;

public class InstallBlacklistHandler implements BuffInstallHandler {
    @Override
    public BuffCallback checkCondition(Player player, ItemStack target, ItemStack gem, String buff_name, String param) {
        BuffCallback callback = new BuffCallback();
        if (target.getType().toString().equalsIgnoreCase(param)){
            callback.setHasReason(true);
            callback.setStatus(false);
            callback.setReason("&c&l此宝石无法镶嵌到此物品中!");
        }
        return callback;
    }

    @Override
    public ItemStack handle(Player player, ItemStack target, ItemStack gem, String buff_name, String param) {
        return target;
    }

    @Override
    public String getIdentifier() {
        return "blacklist";
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
        return "FlyBuffBlacklist";
    }
}
