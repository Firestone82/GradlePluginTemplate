package cz.devfire.pluginname.Commands.List;

import com.google.common.collect.Lists;
import cz.devfire.firelibs.Spigot.Commands.Objects.ICommand;
import cz.devfire.firelibs.Spigot.Commands.Objects.ISubCommand;
import cz.devfire.pluginname.PluginCore;
import org.bukkit.command.CommandSender;

import java.util.List;

public final class SubCmdReload implements ISubCommand {
    private final PluginCore pluginCore;

    public SubCmdReload(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @Override
    public String getLabel() {
        return "reload";
    }

    @Override
    public String getUsage() {
        return "pluginCommand reload";
    }

    @Override
    public String getPermission() {
        return "pluginName.command.reload";
    }

    @Override
    public String getDescription() {
        return "Reloads plugin";
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
        sender.sendMessage(pluginCore.getLanguage().get("Reloaded",pluginCore.reload() +""));
    }

    @Override
    public List<String> tabComplete(ICommand command, CommandSender sender, String[] args) {
        return Lists.newArrayList();
    }
}
