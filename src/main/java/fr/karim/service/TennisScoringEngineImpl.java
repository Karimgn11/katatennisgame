package fr.karim.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TennisScoringEngineImpl implements TennisScoringEngine {

    /**
     * Calcule le score à partir d'une séquence
     * de points gagnés par le joueur A ou B. La méthode avance point par point,
     * construit la timeline et s'arrête dès qu'un joueur remporte le jeu 
     * selon les règles définis dans l kata
     */

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

             // A n'importe quel moment, Si les conditions de victoire sont réunies, 
             // le jeu est terminé

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
     // Cas normal : on tranmets à la méthodes les points convertiss en notation tennis (15/30/40)

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

      /**
     * Règle officielle : un joueur gagne le jeu s'il a au moins 4 points
     * et possède 2 points d'avance sur son adversaire.
     */

    private boolean isGameWon(int a, int b) {
        return (a >= 4 || b >= 4) && Math.abs(a - b) >= 2;
    }
}
