
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Введите команду для создания карты:");
        RobotMap map = null;
        while (true) {
            String command = sc.nextLine();
            if (command.startsWith("create-map")) {
                String[] split = command.split(" "); // [create-map 3 5]
                String[] arguments = Arrays.copyOfRange(split, 1, split.length); // [3 5]

                try {
                    map = new RobotMap(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1]));
                    System.out.println("Карта создана!");
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("При создании карты возникло исключение: " + e.getMessage() + "." +
                            " Попробуйте еще раз");
                }
            } else {
                System.out.println("Команда не найдена. Попробуйте еще раз");
            }
        }

        List<CommandHandler> handlers = List.of(
                new ChangeDirectionCommandHandler(),
                new CreateRobotCommandHandler(),
                new MoveRobotCommandHandler(),
                new HelpCommandHandler());
        CommandManager commandManager = new CommandManager(map, handlers);

        System.out.println("ИГРАЕМ...");
        System.out.println("Список команд:");
        System.out.println("create-map n m - создать карту размера n на m");
        System.out.println("create-robot x y - создать робота с начальной позицией (x,y)");
        System.out.println("move-robot id - переместить робота с идентификатором id на одну клетку вперед в текущем направлении");
        System.out.println("change-direction id direction - изменить направление робота с идентификатором id на direction (LEFT или RIGHT)");
        System.out.println("help - отобразить этот список команд");

        while (true) {
            String command = sc.nextLine();
            commandManager.handleCommand(command);
        }
    }
}
