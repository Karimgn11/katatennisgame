package fr.karim.controller;

import fr.karim.dto.TennisGameRequestDto;
import fr.karim.dto.TennisGameResponseDto;
import fr.karim.service.TennisGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
@Tag(name = "Tennis Games", description = "API de gestion et de scoring tennis")
public class TennisGameController {

    private final TennisGameService tennisGameService;

    @PostMapping
    @Operation(summary = "Créer un jeu et calculer le score")
    public ResponseEntity<TennisGameResponseDto> createGame(@RequestBody TennisGameRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tennisGameService.createGameAndComputeScore(requestDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un jeu par ID")
    public ResponseEntity<TennisGameResponseDto> getGame(@PathVariable Long id) {
        return ResponseEntity.ok(tennisGameService.getGameById(id));
    }
}
