package cz.devfire.pluginname.Commands.List;

import com.google.common.collect.Lists;
import cz.devfire.firelibs.Shared.Utils.StringUtils;
import cz.devfire.firelibs.Spigot.Commands.Objects.ICommand;
import cz.devfire.firelibs.Spigot.Commands.Objects.ISubCommand;
import cz.devfire.pluginname.PluginCore;
import org.bukkit.command.CommandSender;

import java.util.List;

public final class SubCmdInfo implements ISubCommand {
    private final PluginCore pluginCore;

    public SubCmdInfo(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @Override
    public String getLabel() {
        return "info";
    }

    @Override
    public String getUsage() {
        return "pluginCommand info";
    }

    @Override
    public String getPermission() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public int getMinArgs() {
        return 1;
    }

    @Override
    public int getMaxArgs() {
        return 1;
    }

    @Override
    public void perform(ICommand command, CommandSender sender, String[] args) {
        sender.sendMessage(StringUtils.cc("&c&lServer &8&l» &7PluginName plugin made by &eFirestone82"));
    }

    @Override
    public List<String> tabComplete(ICommand command, CommandSender sender, String[] args) {
        return Lists.newArrayList();
    }
}
