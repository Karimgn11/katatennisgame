package fr.karim.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TennisScoringEngineImpl implements TennisScoringEngine {

    @Override
    public TennisScoreResult computeScore(String sequence) {
        int scoreA = 0;
        int scoreB = 0;
        List<String> timeline = new ArrayList<>();

        for (char c : sequence.toCharArray()) {
            if (c == 'A') {
                scoreA++;
            } else if (c == 'B') {
                scoreB++;
            } else {
                throw new IllegalArgumentException("Invalid char: " + c);
            }

            timeline.add(currentScoreAsString(scoreA, scoreB));

            if (isGameWon(scoreA, scoreB)) {
                String winner = scoreA > scoreB ? "A" : "B";
                timeline.add("Player " + winner + " wins the game");

                return TennisScoreResult.builder()
                        .winner(winner)
                        .scoreTimeline(timeline)
                        .build();
            }
        }

        return TennisScoreResult.builder()
                .winner(null)
                .scoreTimeline(timeline)
                .build();
    }

    private String currentScoreAsString(int a, int b) {
        if (a >= 3 && b >= 3) {
            if (a == b) {
                return "Deuce";
            }
            if (a == b + 1) {
                return "Advantage Player A";
            }
            if (b == a + 1) {
                return "Advantage Player B";
            }
        }
        return "Player A : " + toTennisScore(a) + " / Player B : " + toTennisScore(b);
    }

    private String toTennisScore(int score) {
        return switch (score) {
            case 0 -> "0";
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            default -> "40";
        };
    }

    private boolean isGameWon(int a, int b) {
        return (a >= 4 || b >= 4) && Math.abs(a - b) >= 2;
    }
}
