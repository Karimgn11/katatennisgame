package fr.karim.tennisgamekata.service;

import org.junit.jupiter.api.Test;

import com.adobe.cq.social.scoring.api.ScoringEngine;

import fr.karim.service.TennisScoreResult;
import fr.karim.service.TennisScoringEngineImpl;
import com.adobe.cq.social.scoring.api.ScoringEngine;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TennisScoringEngineImplTest {

    private final TennisScoringEngineImpl scoringEngine = new TennisScoringEngineImpl();

    @Test
    void computeScore_simpleGame_PlayerAWins() {
        // GIVEN
        String sequence = "AAAA"; // A gagne directement

        // WHEN
        TennisScoreResult result = scoringEngine.computeScore(sequence);

        // THEN
        assertThat(result.getWinner()).isEqualTo("A");
        assertThat(result.getScoreTimeline()).isNotEmpty();
        assertThat(result.getScoreTimeline().get(result.getScoreTimeline().size() - 1))
                .contains("Player A wins the game");
    }

    @Test
    void computeScore_deuceAndAdvantageScenario() {
        // GIVEN : A et B vont jusqu'à Deuce, puis A gagne
        String sequence = "ABABABAA";

        // WHEN
        TennisScoreResult result = scoringEngine.computeScore(sequence);

        // THEN
        assertThat(result.getWinner()).isEqualTo("A");
        assertThat(result.getScoreTimeline())
                .anyMatch(s -> s.equals("Deuce"))
                .anyMatch(s -> s.contains("Advantage Player A"))
                .anyMatch(s -> s.contains("wins the game"));
    }

    @Test
    void computeScore_shouldThrowExceptionOnInvalidCharacter() {
        // GIVEN
        String sequence = "ABX";

        // WHEN + THEN
      assertThatThrownBy(() -> scoringEngine.computeScore(sequence))

                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid char");
    }
}
