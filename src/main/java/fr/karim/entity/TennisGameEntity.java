package fr.karim.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tennis_game")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TennisGameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_a_name", nullable = false)
    private String playerAName;

    @Column(name = "player_b_name", nullable = false)
    private String playerBName;

    @Column(name = "sequence", nullable = false, length = 2000)
    private String sequence;

    @Column(name = "winner", length = 1)
    private String winner;

    @Column(name = "score_timeline", columnDefinition = "TEXT")
    private String scoreTimeline;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
