import org.junit.jupiter.api.*;
import java.util.*;

public class LiftTest {
    public static final int TEST_FLOORS_COUNT = 10;
    public static final int TEST_SPEED_FAST = 2;
    public static final int TEST_DOOR_SPEED_FAST = 5;
    public static final int TEST_SPEED_SLOW = 5;
    public static final int TEST_DOOR_SPEED_SLOW = 10;
    public static final int TEST_SPEED_MIDDLE = 3;
    public static final int TEST_DOOR_SPEED_MIDDLE = 8;
    public static final Integer[] ALL_FLOORS_WAY = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public static Lift liftFast = new Lift(TEST_FLOORS_COUNT, TEST_SPEED_FAST, TEST_DOOR_SPEED_FAST);
    public static Lift liftSlow = new Lift(TEST_FLOORS_COUNT, TEST_SPEED_SLOW, TEST_DOOR_SPEED_SLOW);
    public static Lift liftMiddle = new Lift(TEST_FLOORS_COUNT, TEST_SPEED_MIDDLE, TEST_DOOR_SPEED_MIDDLE);
    public static HashSet<Lift> liftList =  new HashSet<>();

    @BeforeAll
    public static void testsStart() {
        System.out.println("Tests started");
        liftList.add(liftFast);
        liftList.add(liftSlow);
        liftList.add(liftMiddle);
    }

    @BeforeEach
    public void testStart() {
        System.out.println("Test started");
    }

    @AfterAll
    public static  void testsEnd() {
        System.out.println("Tests completed");
    }

    @AfterEach
    public void testEnd() {
        System.out.println("Test complete");
    }

    @Test
    public void setTestFloorsCount() {
        for (Lift lift : liftList) {
            lift.fillFloors(new LinkedList<>(Arrays.asList(ALL_FLOORS_WAY)));
            lift.run();
            Assertions.assertEquals(lift.getFloorsCount(), 10);
        }
    }
}
