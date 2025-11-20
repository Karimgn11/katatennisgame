package fr.karim.tennisgamekata.service;

import fr.karim.repository.TennisGameRepository;
import fr.karim.service.TennisGameServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

class TennisGameServiceImplTest {

    @Test
    void getGameById_shouldCallRepository() {
        // Mock simple
        TennisGameRepository repository = mock(TennisGameRepository.class);

        // Service testé (constructeur simplifié)
        TennisGameServiceImpl service = new TennisGameServiceImpl(repository, null);

        // GIVEN
        Long id = 99L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // WHEN
        try {
            service.getGameById(id);
        } catch (Exception ignored) {
            // OK : on ne teste pas le comportement ici
        }

        // THEN : vérifier que le repo a bien été appelé
        verify(repository).findById(id);
        verifyNoMoreInteractions(repository);
    }
}
