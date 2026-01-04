package com.claypot.habitservice.controller;

import com.claypot.habitservice.dto.HabitCheckInRequest;
import com.claypot.habitservice.entity.Habit;
import com.claypot.habitservice.entity.HabitLog;
import com.claypot.habitservice.service.HabitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/habits")
public class HabitController {
    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<Habit> createHabit(
            @RequestHeader("X-user-ID") Long userId,
            @RequestBody Map<String,String> body) {
        Habit habit = habitService.createHabit(userId, body.get("name"));
        return ResponseEntity.ok(habit);
    }

    @GetMapping
    public ResponseEntity<Iterable<Habit>> getHabits(
            @RequestHeader("X-user-ID") Long userId) {
        return ResponseEntity.ok(habitService.getHabits(userId));
    }

    @PostMapping("/{habitId}/checkin")
    public ResponseEntity<HabitLog> checkIn(
            @RequestHeader("X-User-ID") Long userId,
            @PathVariable Long habitId,
            @RequestBody HabitCheckInRequest request) {

        HabitLog habitog = habitService.checkIn(
                userId,
                habitId,
                request.getCompleted()
        );

        return ResponseEntity.ok(habitog);
    }



}
