package fr.karim.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TennisGameRequestDto {
    private String playerAName;
    private String playerBName;
    private String sequence;
}
