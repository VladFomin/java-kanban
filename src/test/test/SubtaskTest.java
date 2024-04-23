package test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import model.Subtask;
import model.Task.Status;
import org.junit.jupiter.api.Test;

class SubtaskTest {
    @Test
    public void shouldBeEqualIfIdsAreEqual() {
        Subtask subtask1 = new Subtask("Subtask1", "Description1", Status.NEW, 1);
        Subtask subtask2 = new Subtask("Subtask2", "Description2", Status.DONE, 1);
        subtask1.setId(1);
        subtask2.setId(1);
        assertEquals(subtask1, subtask2);
    }

}