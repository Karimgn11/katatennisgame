package fr.karim.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TennisGameResponseDto {
    private Long id;
    private String playerAName;
    private String playerBName;
     private String sequence;
    private String winner;
    private List<String> scoreTimeline;
     private LocalDateTime createdAt;
}
