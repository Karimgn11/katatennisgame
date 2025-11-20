package fr.karim.mapper;

import fr.karim.dto.TennisGameRequestDto;
import fr.karim.dto.TennisGameResponseDto;
import fr.karim.entity.TennisGameEntity;
import java.util.List;

public class TennisGameMapper {

    private TennisGameMapper() {
    }

    public static TennisGameEntity toEntity(
            TennisGameRequestDto requestDto,
            String winner,
            List<String> timeline
    ) {
        return TennisGameEntity.builder()
                .playerAName(requestDto.getPlayerAName())
                .playerBName(requestDto.getPlayerBName())
                .sequence(requestDto.getSequence())
                .winner(winner)
                .scoreTimeline(String.join("\n", timeline))
                .build();
    }

    public static TennisGameResponseDto toResponseDto(
            TennisGameEntity entity
    ) {
        List<String> timeline = entity.getScoreTimeline() != null
                ? List.of(entity.getScoreTimeline().split("\\n"))
                : List.of();

        return TennisGameResponseDto.builder()
                .id(entity.getId())
                .playerAName(entity.getPlayerAName())
                .playerBName(entity.getPlayerBName())
                .sequence(entity.getSequence())
                .winner(entity.getWinner())
                .scoreTimeline(timeline)
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
