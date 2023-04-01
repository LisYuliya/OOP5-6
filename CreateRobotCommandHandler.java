
public class CreateRobotCommandHandler implements CommandHandler {

    @Override
    public String commandName() {
        return "create-robot";
    }

    @Override
    public void handleCommand(RobotMap map, String[] args) {
        try {
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);

            RobotMap.Robot robot = map.createRobot(new Point(x, y));
            System.out.println("Робот успешно создан: " + robot);
        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат аргументов команды. Используйте числа.");
        } catch (PositionException e) {
            System.out.println("Ошибка при создании робота: " + e.getMessage());
        }
    }
}
