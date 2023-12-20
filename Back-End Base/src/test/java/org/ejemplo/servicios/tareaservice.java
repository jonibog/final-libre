import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class TareaServiceTest {

    @Test
    void testUpdateTarea() throws BalanceException {
        // Arrange
        TareaRepository tareaRepository = mock(TareaRepository.class);
        UserService userService = mock(UserService.class);

        TareaService tareaService = new TareaService(tareaRepository, userService);

        String userId = "userId";
        User user = new User();
        user.setRole("administrator");

        Tarea tarea = new Tarea();
        tarea.setId(""); // Invalid ID
        tarea.setName(""); // Invalid Name
        tarea.setDescription("Tarea description");


        tarea.setAssignee(new User());
        tarea.getAssignee().setUsername("assigneeUser");

        // Mocking
        when(userService.findByUser(anyString())).thenReturn(java.util.Optional.of(user));
        when(tareaRepository.existsById(anyString())).thenReturn(true);
        when(userService.findByUser("assigneeUser")).thenReturn(java.util.Optional.of(new User()));

        // Act
        Tarea result = tareaService.updateTarea(userId, tarea);

        // Assert
        assertNotNull(result);
        assertEquals(tarea.getId(), result.getId());
        assertEquals(tarea.getName(), result.getName());
        assertEquals(tarea.getDescription(), result.getDescription());
        assertEquals(tarea.getAssignee(), result.getAssignee());


    }
        @Test
        void testUpdateTareaWithInvalidData() {
            // Arrange
            TareaRepository tareaRepository = mock(TareaRepository.class);
            UserService userService = mock(UserService.class);

            TareaService tareaService = new TareaService(tareaRepository, userService);

            String userId = "userId";
            User user = new User();
            user.setRole("user"); // Assuming the user is not an administrator

            Tarea tarea = new Tarea();
            tarea.setId(""); // Invalid ID
            tarea.setName(""); // Invalid Name
            tarea.setDescription("Tarea description");

            // Mocking
            when(userService.findByUser(anyString())).thenReturn(java.util.Optional.of(user));
            when(TareaRepository.existsById(anyString())).thenReturn(false); // Simulating a non-existent task

            // Act and Assert
            BalanceException exception = assertThrows(BalanceException.class, () -> tareaService.updateTask(userId, tarea));


            // Assert exception details
            assertEquals(HttpStatus.PRECONDITION_FAILED, exception.getStatus());
            assertEquals("No se puede actualizar la tarea", exception.getMessage());
            // Add additional assertions for the error messages based on your actual implementation
        }
    }

    @Test
    void testCreateTareaWithValidData() throws BalanceException {
        // Arrange
        TareaRepository tareaRepository = mock(TareaRepository.class);
        UserService userService = mock(UserService.class);

        TareaService tareaService = new TareaService(tareaRepository, userService);

        String userId = "userId";
        User user = new User();
        user.setUsername(userId);

        Tarea tarea = new Tarea();
        tarea.setName("New Tarea");
        tarea.setDescription("Tarea description");

        // Mocking
        when(userService.findByUser(anyString())).thenReturn(java.util.Optional.of(user));
        when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);

        // Act
        Tarea result = tareaService.createTarea(userId, tarea);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("New Task", result.getName());
        assertEquals("Task description", result.getDescription());
        assertNotNull(result.getFechaCreacion());
        assertEquals(user, result.getAuthor());

}
    @Test
    void testCreateTareaWithInvalidData() {
        // Arrange
        TareaRepository tareaRepository = mock(TareaRepository.class);
        UserService userService = mock(UserService.class);

        TareaService tareaService = new TareaService(tareaRepository, userService);

        String userId = "userId";
        User user = new User();
        user.setUsername(userId);

        Tarea tarea = new Tarea();
        // Invalid data, empty name
        tarea.setName("");
        tarea.setDescription("Tarea description");

        // Mocking
        when(userService.findByUser(anyString())).thenReturn(java.util.Optional.of(user));

        // Act and Assert
        BalanceException exception = assertThrows(BalanceException.class, () -> tareaService.createTarea(userId, tarea));


        // Assert exception details
        assertEquals(HttpStatus.PRECONDITION_FAILED, exception.getStatus());
        assertEquals("No se puede crear la tarea", exception.getMessage());
        // Add additional assertions for the error messages based on your actual implementation
    }
}

    @Test
    void testCloseTareaWithInvalidData() {
        // Arrange
        TareaRepository tareaRepository = mock(TareaRepository.class);
        UserService userService = mock(UserService.class);

        TareaService tareaService = new TareaService(tareaRepository, userService);

        String userId = "userId";
        String tareaId = "invalidId"; // Invalid tarea ID

        // Mocking
        when(tareaRepository.existsById(anyString())).thenReturn(false); // Simulating a non-existent tarea
        when(userService.findByUser(anyString())).thenReturn(Optional.empty());

        // Act and Assert
        BalanceException exception = assertThrows(BalanceException.class, () -> tareaService.closeTarea(userId, tareaId));

        // Assert exception details
        assertEquals(HttpStatus.PRECONDITION_FAILED, exception.getStatus());
        assertEquals("No se puede finalizar la tarea", exception.getMessage());
        // Add additional assertions for the error messages based on your actual implementation
    }
}

    @Test
    void testCloseTareaWithValidData() throws BalanceException {
        // Arrange
        TareaRepository tareaRepository = mock(TareaRepository.class);
        UserService userService = mock(UserService.class);

        TareaService tareaService = new TareaService(tareaRepository, userService);

        String userId = "userId";
        String taskId = "validId";

        Tarea tarea = new Tarea();
        tarea.setId(taskId);
        tarea.setNombre("Valid Task");
        tarea.setEncargado(new Usuario());
        tarea.getEncargado().setUser("validUser");

        // Mocking
        when(tareaRepository.existsById(anyString())).thenReturn(true);
        when(tareaRepository.findById(anyString())).thenReturn(Optional.of(tarea));
        when(userService.findByUser(anyString())).thenReturn(Optional.of(new Usuario()));

        // Act
        Tarea result = tareaService.closeTask(userId, tareaId);

        // Assert
        assertNotNull(result);
        assertEquals(tarea, result);
        assertNotNull(result.getFechaRealizado());
    }
}
}
