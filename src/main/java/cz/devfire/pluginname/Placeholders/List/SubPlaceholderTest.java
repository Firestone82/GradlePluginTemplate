package cz.devfire.pluginname.Placeholders.List;

import cz.devfire.firelibs.Spigot.FireLibs;
import cz.devfire.firelibs.Spigot.Placeholders.Objects.IPlaceholder;
import cz.devfire.firelibs.Spigot.Placeholders.Objects.ISubPlaceholder;
import cz.devfire.pluginname.PluginCore;
import org.bukkit.entity.Player;

public final class SubPlaceholderTest implements ISubPlaceholder {
    private final PluginCore pluginCore;

    public SubPlaceholderTest(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    public String getLabel() {
        return "test";
    }

    public int getMinArgs() {
        return 1;
    }

    public int getMaxArgs() {
        return 1;
    }

    public String parse(IPlaceholder placeholder, Player player, String[] args) {
        return this.pluginCore.getDescription().getVersion();
    }
}