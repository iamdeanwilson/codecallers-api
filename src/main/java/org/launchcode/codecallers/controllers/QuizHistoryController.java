package org.launchcode.codecallers.controllers;

import org.launchcode.codecallers.models.QuizHistory;
import org.launchcode.codecallers.models.data.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/quiz")
public class QuizHistoryController {

    @Autowired
    QuizRepository quizRepository;

    @PostMapping("/{topic}/{difficulty}")
    public String addQuizData(@RequestBody QuizHistory newQuizHistory, @PathVariable String topic, @PathVariable String difficulty) {

        QuizHistory quizHistory = new QuizHistory(
                newQuizHistory.getUserID(),
                newQuizHistory.getDate(),
                newQuizHistory.getTopic(),
                newQuizHistory.getDifficulty(),
                newQuizHistory.getScore());


        quizRepository.save(quizHistory);

        return "";
    }



}
