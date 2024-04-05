package fr.uga.l3miage.spring.tp3.component;


import fr.uga.l3miage.spring.tp3.components.CandidateComponent;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.repositories.CandidateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


// Pour la Récupération de la moyenne d'un candidat.
// Dans component on a la logique (le métier)
// puis le service qui utilise le component et c'est là où on le truc de moyenne
// le Controller implémente le end point et utilise le service

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CandidateComponentTest {
@Autowired
private CandidateComponent candidateComponent;

@MockBean
private CandidateRepository candidateRepository;

@Test
void getCandidateByIdNotFound(){
        CandidateEntity candidateEntity = CandidateEntity.builder()
        .id(1L)
        .firstname("Manal")
        .lastname("Ifegh")
        .build();
        when(candidateRepository.findById(anyLong())).thenReturn(Optional.of(candidateEntity));
        // When - Then
        assertDoesNotThrow(() -> candidateComponent.getCandidatById(candidateEntity.getId()));

}



}
