package fr.karim.service; 

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class TennisScoreResult {
    String winner;
    List<String> scoreTimeline;
}
