
public class HelpCommandHandler implements CommandHandler {
    @Override
    public String commandName() {
        return "help";
    }
    
    @Override
    public void handleCommand(RobotMap map, String[] args) {
        System.out.println("Список команд:");
        System.out.println("create-map n m - создать карту размера n на m");
        System.out.println("create-robot x y - создать робота с начальной позицией (x,y)");
        System.out.println("move-robot id - переместить робота с идентификатором id на одну клетку вперед в текущем направлении");
        System.out.println("change-direction id direction - изменить направление робота с идентификатором id на direction (LEFT или RIGHT)");
        System.out.println("help - отобразить этот список команд");
    }
    
}
