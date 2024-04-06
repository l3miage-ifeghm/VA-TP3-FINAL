package fr.uga.l3miage.spring.tp3.services;

import fr.uga.l3miage.spring.tp3.components.SessionComponent;
import fr.uga.l3miage.spring.tp3.enums.SessionStatus;
import fr.uga.l3miage.spring.tp3.exceptions.rest.CreationSessionRestException;
import fr.uga.l3miage.spring.tp3.models.EcosSessionEntity;
import fr.uga.l3miage.spring.tp3.models.EcosSessionProgrammationEntity;
import fr.uga.l3miage.spring.tp3.models.EcosSessionProgrammationStepEntity;
import fr.uga.l3miage.spring.tp3.request.SessionCreationRequest;
import fr.uga.l3miage.spring.tp3.request.SessionProgrammationCreationRequest;
import fr.uga.l3miage.spring.tp3.request.SessionProgrammationStepCreationRequest;
import fr.uga.l3miage.spring.tp3.responses.SessionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SessionServiceTest {
    @Autowired
    SessionService sessionService;
    @MockBean
    SessionComponent sessionComponent;


    @Test
    void testCreateSession() {

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


        //GIVEN
        SessionProgrammationStepCreationRequest sessionProgrammationStepCreationRequest1 = SessionProgrammationStepCreationRequest
                .builder()
                .id(1L)
                .code("F1")
                .description("description 1")
                .dateTime(LocalDateTime.of(2024, 12, 10, 0, 0))
                .build();

        SessionProgrammationStepCreationRequest sessionProgrammationStepCreationRequest2 = SessionProgrammationStepCreationRequest
                .builder()
                .code("F2")
                .description("description 2")
                .dateTime(LocalDateTime.of(2023, 12, 10, 0, 0))
                .build();


        SessionProgrammationCreationRequest ecosSessionProgrammation = SessionProgrammationCreationRequest.builder()
                .id(2L)
                .label("S1")
                .steps(Set.of(sessionProgrammationStepCreationRequest1, sessionProgrammationStepCreationRequest2))
                .build();

        SessionCreationRequest sessionCreationRequest = SessionCreationRequest
                .builder()
                .name("Session Hiver")
                .startDate(LocalDateTime.of(2024, 12, 20, 0, 0))
                .endDate(LocalDateTime.of(2025, 1, 20, 0, 0))
                .examsId(Set.of(2L, 4L))
                .ecosSessionProgrammation(ecosSessionProgrammation)
                .build();

        try {
            when(sessionComponent.createSession(any(EcosSessionEntity.class))).thenReturn(ecosSessionEntity);

            // WHEN
            SessionResponse sessionResponse = sessionService.createSession(sessionCreationRequest);

            // THEN
            assertThat(sessionResponse).isNotNull();
        } catch (CreationSessionRestException e) {
        }

    }

}

