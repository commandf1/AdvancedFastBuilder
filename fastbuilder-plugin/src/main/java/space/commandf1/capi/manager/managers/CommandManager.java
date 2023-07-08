package space.commandf1.capi.manager.managers;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import space.commandf1.capi.manager.Manager;
import space.commandf1.capi.command.CommandInfo;
import space.commandf1.capi.command.ICommandMap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager implements Manager {
    private static final CommandManager commandManager;

    /**
     * register command
     *
     * @author commandf1
     * @since 1.0
     * */
    @SuppressWarnings("unused")
    public <T extends ICommandMap> void registerCommandMap(T commandMap, Plugin plugin) {
        this.registerCommandMap(commandMap, plugin.getDescription().getName());
    }

    public void registerCommandMap(ICommandMap commandMap, String fallbackPrefix) {
        for (Method method : commandMap.getClass().getDeclaredMethods()) {
            CommandInfo commandInfo = method.getAnnotation(CommandInfo.class);
            if (commandInfo == null) {
                continue;
            }

            boolean returnVoidValue = method.getReturnType().getName().equalsIgnoreCase("void");

            Command command = new Command(commandInfo.name(), commandInfo.description(), "/" + commandInfo.name(), new ArrayList<>(Arrays.asList(commandInfo.aliases()))) {
                @Override
                public boolean execute(CommandSender commandSender, String s, String[] strings) {
                    if (commandInfo.needPermission()) {
                        if (!commandSender.hasPermission(commandInfo.permission())) {
                            if (!commandInfo.noPermissionMessage().equalsIgnoreCase("")) {
                                commandSender.sendMessage(commandInfo.noPermissionMessage());
                            }
                            return true;
                        }
                    }

                    if (!(commandSender instanceof Player) && commandInfo.playerOnly()) {
                        if (!commandInfo.notPlayerMessage().equalsIgnoreCase("")) {
                            commandSender.sendMessage(commandInfo.notPlayerMessage());
                        }
                        return true;
                    }

                    if (returnVoidValue) {
                        try {
                            method.invoke(commandMap, commandSender, s, strings);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return true;
                    } else {
                        try {
                            return (boolean) method.invoke(commandMap, commandSender, s, strings);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                }
            };

            registerCommand(fallbackPrefix, command);
        }
    }

    public void registerCommand(String fallbackPrefix, Command command) {
        try {
            SimplePluginManager pm = ((SimplePluginManager) Bukkit.getPluginManager());
            Class<?> clazz = pm.getClass();

            Field commandMapField = clazz.getDeclaredField("commandMap");
            commandMapField.setAccessible(true);

            CommandMap commandMap = (CommandMap) commandMapField.get(pm);
            commandMap.register(fallbackPrefix, command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CommandManager getInstance() {
        return commandManager;
    }

    static {
        commandManager = new CommandManager();
    }
}
