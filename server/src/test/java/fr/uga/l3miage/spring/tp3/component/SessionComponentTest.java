package fr.uga.l3miage.spring.tp3.component;

import fr.uga.l3miage.spring.tp3.components.SessionComponent;
import fr.uga.l3miage.spring.tp3.enums.SessionStatus;
import fr.uga.l3miage.spring.tp3.models.EcosSessionEntity;
import fr.uga.l3miage.spring.tp3.models.EcosSessionProgrammationEntity;
import fr.uga.l3miage.spring.tp3.models.EcosSessionProgrammationStepEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SessionComponentTest {

    @Autowired
    private SessionComponent sessionComponent;


    @Test
    void createSessionTest() {
        //GIVEN
            EcosSessionProgrammationStepEntity ecosSessionProgrammationStepEntity1 =EcosSessionProgrammationStepEntity
                    .builder()
                    .code("F1")
                    .description("description 1")
                    .build();

        EcosSessionProgrammationStepEntity ecosSessionProgrammationStepEntity2 =EcosSessionProgrammationStepEntity
                .builder()
                .code("F2")
                .description("description 2")
                .build();


        EcosSessionProgrammationStepEntity ecosSessionProgrammationStepEntity3 =EcosSessionProgrammationStepEntity
                .builder()
                .code("F3")
                .description("description 3")
                .build();

        EcosSessionProgrammationEntity  ecosSessionProgrammationEntity = EcosSessionProgrammationEntity.builder()
                .label("S1")
                .ecosSessionProgrammationStepEntities(Set.of(ecosSessionProgrammationStepEntity1,ecosSessionProgrammationStepEntity2,ecosSessionProgrammationStepEntity3))
                .build();

        EcosSessionEntity ecosSessionEntity= EcosSessionEntity.builder()
                .name("Session Hiver")
                .id(12L)
                .startDate(LocalDateTime.of(2024, 12, 20, 0, 0))
                .endDate(LocalDateTime.of(2025,1,20,0,0))
                .status(SessionStatus.CREATED)
                .ecosSessionProgrammationEntity(ecosSessionProgrammationEntity)
                .build();

    //WHEN
    EcosSessionEntity ecosSessionEntityResponse = sessionComponent.createSession((ecosSessionEntity));

    //THEN
    //assertThat(ecosSessionEntityResponse).usingRecursiveComparison().isEqualTo(ecosSessionEntity);

   assertThat(ecosSessionEntityResponse).isEqualTo(ecosSessionEntityResponse);
    }

}
