import model.Epic;
import model.Task.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {
    @Test
    public void shouldBeEqualIfIdsAreEqual() {
        Epic epic1 = new Epic("Epic1", "Description1", Status.NEW);
        Epic epic2 = new Epic("Epic2", "Description2", Status.DONE);
        epic1.setId(1);
        epic2.setId(1);
        assertEquals(epic1, epic2);
    }
}