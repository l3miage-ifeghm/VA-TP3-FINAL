// Pour la Récupération de la moyenne d'un candidat.
// Dans component on a la logique (le métier)
// puis le service qui utilise le component et c'est là où on le truc de moyenne
// le Controller implémente le end point et utilise le service
package fr.uga.l3miage.spring.tp3.component;


import fr.uga.l3miage.spring.tp3.components.CandidateComponent;
import fr.uga.l3miage.spring.tp3.exceptions.technical.CandidateNotFoundException;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.repositories.CandidateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CandidateComponentTest {
        @Autowired
        private CandidateComponent candidateComponent;
        @MockBean
        private CandidateRepository candidateRepository;

        @Test
        void getCandidateByIdNotFound(){
                //GIVEN
                when(candidateRepository.findById(anyLong())).thenReturn(Optional.empty());

                //WHEN - THEN
                assertThrows(CandidateNotFoundException.class, ()->candidateComponent.getCandidatById(12L));
        }

        @Test
        void getCandidateByIdFound(){
                //GIVEN
                CandidateEntity candidateEntity = CandidateEntity
                        .builder()
                        .id(1L)
                        .email("manalifegh@gmail.com")
                        .build();
                when(candidateRepository.findById(1L)).thenReturn(Optional.of((candidateEntity)));

                //WHEN - THEN
                // pour tester à la fois que la méthode ne lève pas d'exception et qu'elle renvoie le bon résultat
                // on n'a pas trouvé d'autres méthodes à part faire un "try - catch"
                assertDoesNotThrow(()->{
                        assertThat(candidateComponent.getCandidatById(12L).getId()).isEqualTo(1L);
                });

        }
}