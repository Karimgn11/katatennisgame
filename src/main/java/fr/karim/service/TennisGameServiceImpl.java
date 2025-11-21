package fr.karim.service;

import fr.karim.dto.TennisGameRequestDto;
import fr.karim.dto.TennisGameResponseDto;
import fr.karim.entity.TennisGameEntity;
import fr.karim.mapper.TennisGameMapper;
import fr.karim.repository.TennisGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class TennisGameServiceImpl implements TennisGameService {

    private final TennisGameRepository repository;
    
    private final TennisScoringEngineImpl scoringEngine;

    @Override
    public TennisGameResponseDto createGameAndComputeScore(TennisGameRequestDto requestDto) {
       
        TennisScoreResult result = scoringEngine.computeScore(requestDto.getSequence());

        TennisGameEntity entity = TennisGameMapper.toEntity(
                requestDto,
                result.getWinner(),
                result.getScoreTimeline()
        );

        TennisGameEntity saved = repository.save(entity);
        return TennisGameMapper.toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TennisGameResponseDto getGameById(Long id) {
        TennisGameEntity entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Game not found with id " + id));

        return TennisGameMapper.toResponseDto(entity);
    }
}
