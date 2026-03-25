package com.example.interview.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskServiceImplTest {

    // Testing the SLA logic only
    @Test
    void addSLAtoDueDate() {

        TaskServiceImpl taskService = new TaskServiceImpl();

        // March 24, 2025 is a Monday
        LocalDate startDate = LocalDate.of(2025, 3, 24);

        // 5 business days later should be Monday, March 31, 2025
        // because Saturday and Sunday are skipped
        LocalDate expectedDueDate = LocalDate.of(2025, 3, 31);

        LocalDate actualDueDate = taskService.addSLAtoDueDate(startDate);

        assertEquals(expectedDueDate, actualDueDate);
    }
}