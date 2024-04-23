package test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import model.Epic;
import model.Task.TaskStatus;
import org.junit.jupiter.api.Test;

class EpicTest {
    @Test
    public void shouldBeEqualIfIdsAreEqual() {
        Epic epic1 = new Epic("Epic1", "Description1", TaskStatus.NEW);
        Epic epic2 = new Epic("Epic2", "Description2", TaskStatus.DONE);
        epic1.setId(1);
        epic2.setId(1);
        assertEquals(epic1, epic2);
    }

}