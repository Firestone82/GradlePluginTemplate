package cz.devfire.pluginname.Commands;

import cz.devfire.firelibs.Shared.Utils.StringUtils;
import cz.devfire.firelibs.Spigot.Commands.Objects.BaseCommand;
import cz.devfire.firelibs.Spigot.Commands.Objects.ICommand;
import cz.devfire.pluginname.Commands.List.SubCmdInfo;
import cz.devfire.pluginname.Commands.List.SubCmdReload;
import cz.devfire.pluginname.PluginCore;
import org.bukkit.command.CommandSender;

public final class CoreCommand extends BaseCommand implements ICommand {
    private final PluginCore pluginCore;

    public CoreCommand(PluginCore pluginCore) {
        super(pluginCore,"pluginName",null);
        this.pluginCore = pluginCore;

        subCommands.add(new SubCmdInfo(pluginCore));
        subCommands.add(new SubCmdReload(pluginCore));
    }

    @Override
    public void perform(CommandSender commandSender, String[] strings) {
        commandSender.sendMessage(StringUtils.cc(getNoCommandMessage()));
    }

    public String getNoPermissionMessage() {
        return pluginCore.getLanguage().get("Command.NoPermissions");
    }

    public String getNoCommandMessage() {
        return pluginCore.getLanguage().get("Command.NoCommand");
    }

    public String getUsageCommand() {
        return pluginCore.getLanguage().get("Command.Usage");
    }

}
