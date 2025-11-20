package fr.karim.service;

import fr.karim.dto.TennisGameRequestDto;
import fr.karim.dto.TennisGameResponseDto;

public interface TennisGameService {

    TennisGameResponseDto createGameAndComputeScore(TennisGameRequestDto requestDto);

    TennisGameResponseDto getGameById(Long id);
}
