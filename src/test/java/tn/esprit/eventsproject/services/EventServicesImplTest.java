package tn.esprit.eventsproject.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Use this if it's a unit test with Mockito
//@SpringBootTest // Uncomment this if it's an integration test
class EventServicesImplTest {

    @Mock
    EventRepository eventRepository;

    @Mock
    ParticipantRepository participantRepository;

    @Mock
    LogisticsRepository logisticsRepository;

    @InjectMocks
    EventServicesImpl eventService;



    @Test
    void addParticipantTest() {
        Participant participant = new Participant();
        participant.setNom("John");
        participant.setPrenom("Doe");

        when(participantRepository.save(participant)).thenReturn(participant);

        Participant savedParticipant = eventService.addParticipant(participant);

        assertNotNull(savedParticipant);
        assertEquals("John", savedParticipant.getNom());
        assertEquals("Doe", savedParticipant.getPrenom());
        verify(participantRepository, times(1)).save(participant);
    }

    // Implement these methods or remove them if not needed
    @Test
    void addAffectEvenParticipant() {
    }

    @Test
    void testAddAffectEvenParticipant() {
    }

    @Test
    void testAddAffectLog() {
        // Mock data
        String descriptionEvent = "Sample Event Description";
        Logistics logistics = new Logistics();
        logistics.setIdLog(1); // Example ID, adjust as per your actual implementation

        // Mock behavior for eventRepository.findByDescription
        Event mockEvent = new Event();
        when(eventRepository.findByDescription(descriptionEvent)).thenReturn(mockEvent);

        // Call the method under test
        Logistics savedLogistics = eventService.addAffectLog(logistics, descriptionEvent);

        // Verify that eventRepository.save was called with the correct event object
        verify(eventRepository, times(1)).findByDescription(descriptionEvent);
        verify(eventRepository, times(1)).save(mockEvent);

        // Verify that logisticsRepository.save was called with the correct logistics object
        verify(logisticsRepository, times(1)).save(logistics);

        // Assert that logistics are correctly associated with the event
        Set<Logistics> logisticsSet = mockEvent.getLogistics();
        assertEquals(1, logisticsSet.size());
        assertTrue(logisticsSet.contains(logistics));
    }

    @Test
    void getLogisticsDates() {
    }

    @Test
    void calculCout() {
    }
}
