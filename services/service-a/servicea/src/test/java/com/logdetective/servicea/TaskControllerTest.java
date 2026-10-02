package com.logdetective.servicea;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskControllerTest {

    @Test
    void createTask_assignsIdAndStoresTask() {
        TaskController controller = new TaskController();
        Task task = new Task(null, "Test task", "To Do");

        Task created = controller.createTask(task);

        assertNotNull(created.getId());
        assertEquals("Test task", created.getTitle());
    }

    @Test
    void getTask_returnsNullForMissingId() {
        TaskController controller = new TaskController();

        Task result = controller.getTask(999L);

        assertNull(result);
    }

    @Test
    void getAllTasks_returnsEmptyWhenNoneCreated() {
        TaskController controller = new TaskController();

        var tasks = controller.getAllTasks();

        assertTrue(tasks.isEmpty());
    }
}