package cz.devfire.pluginname.Placeholders;

import cz.devfire.firelibs.Spigot.Placeholders.Objects.BasePlaceholder;
import cz.devfire.pluginname.Placeholders.List.SubPlaceholderTest;
import cz.devfire.pluginname.PluginCore;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CorePlaceholder extends BasePlaceholder {
    private final PluginCore pluginCore;

    public CorePlaceholder(PluginCore pluginCore) {
        super(pluginCore,"pluginName");
        this.pluginCore = pluginCore;

        subPlaceholders.add(new SubPlaceholderTest(pluginCore));
    }

    @Override
    public String parse(Plugin plugin, Player player, String[] strings) {
        return getUnknownPlaceholder();
    }
}
