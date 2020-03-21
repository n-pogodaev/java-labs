package Calculator.StackCalculator.Commands;

import Calculator.Exception.ConfigErrorException;
import Calculator.Exception.WrongCommandNameException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final String configFileName = "commandClasses.csv";
    private static final Map<String, String> commandClassesNames = ReadClassesNames(configFileName);
    private static Map<String, String> ReadClassesNames(String fileName) {
        HashMap<String, String> result = new HashMap<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(CommandFactory.class.getResourceAsStream(fileName)));
            while (reader.ready()) {
                String[] commandAndClassName = reader.readLine().split(",");
                result.putIfAbsent(commandAndClassName[0], commandAndClassName[1]);
            }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return result;
    }
    public static Command CreateCommand(String commandName) throws WrongCommandNameException, ConfigErrorException {
        if (commandName == null) {
            throw new WrongCommandNameException("null pointer instead of command name");
        }
        Command result;
        String className = commandClassesNames.get(commandName);
        if (className == null) {
            throw new WrongCommandNameException("wrong command name: " + commandName);
        }
        try {
            Constructor<?> constructor = Class.forName(className).getConstructor();
            result = (Command) constructor.newInstance();
        }
        catch (ClassNotFoundException e) {
            throw new ConfigErrorException("class is absent: " + className);
        }
        catch (NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException | ClassCastException e) {
            throw new ConfigErrorException("not a command class name: " + className);
        }
        return result;
    }
}
