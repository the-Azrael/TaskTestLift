import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static final int FLOORS_COUNT = 25;
    public static final int SPEED = 5;
    public static final int DOOR_SPEED = 10;
    private static Lift lift;
    public static final Integer[] ALL_FLOORS_WAY = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {
        lift = new Lift(FLOORS_COUNT, SPEED, DOOR_SPEED);
        lift.fillFloors(new LinkedList<>(Arrays.asList(ALL_FLOORS_WAY)));
        lift.run();
        System.out.println(lift.getFloors());
        System.out.println("Время в пути: " + lift.getTotalSeconds() + " сек.");

        //Ручное заполнение
        /*lift = new Lift(FLOORS_COUNT, SPEED, DOOR_SPEED);
        Scanner scanner  = new Scanner(System.in);
        while (true) {
            System.out.println("Ожидаю ввода этажа (для завершения введите 0): ");
            int floorNumber = scanner.nextInt();
            if (floorNumber == 0) {
                System.out.println(lift.getFloors());
                System.out.println("Время в пути: " + lift.getTotalSeconds() + " сек.");
                return;
            } else {
                lift.addFloor(floorNumber);
            }
        }*/
    }
}
