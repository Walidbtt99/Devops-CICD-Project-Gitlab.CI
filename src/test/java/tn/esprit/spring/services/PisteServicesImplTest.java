package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.IPisteRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PisteServicesImplTest {

    @Mock
    private IPisteRepository pisteRepository;

    @InjectMocks
    private PisteServicesImpl pisteServices;

    private Piste piste1;
    private Piste piste2;

    @BeforeEach
    void setUp() {
        Set<Skier> skiers = new HashSet<>();
        piste1 = new Piste(1L, "Piste 1", null, 1000, 30, skiers);
        piste2 = new Piste(2L, "Piste 2", null, 800, 25, skiers);
    }

    @Test
    void testRetrieveAllPistesFound() {
        try {
            when(pisteRepository.findAll()).thenReturn(Arrays.asList(piste1, piste2));

            List<Piste> pistes = pisteServices.retrieveAllPistes();

            assertNotNull(pistes);
            assertEquals(2, pistes.size());
            assertEquals("Piste 1", pistes.get(0).getNamePiste());
            System.out.println("testRetrieveAllPistesFound: Test passed");
        } catch (AssertionError | Exception e) {
            System.out.println("testRetrieveAllPistesFound: Test failed - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testRetrieveAllPistesNotFound() {
        try {
            when(pisteRepository.findAll()).thenReturn(Collections.emptyList());

            List<Piste> pistes = pisteServices.retrieveAllPistes();

            assertNotNull(pistes);
            assertTrue(pistes.isEmpty());
            System.out.println("testRetrieveAllPistesNotFound: Test passed");
        } catch (AssertionError | Exception e) {
            System.out.println("testRetrieveAllPistesNotFound: Test failed - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testRetrieveAllPistesError() {
        try {
            when(pisteRepository.findAll()).thenThrow(new RuntimeException("Database error"));

            assertThrows(RuntimeException.class, () -> pisteServices.retrieveAllPistes());

            System.out.println("testRetrieveAllPistesError: Test passed");
        } catch (AssertionError | Exception e) {
            System.out.println("testRetrieveAllPistesError: Test failed - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testAddPisteSuccess() {
        try {
            when(pisteRepository.save(piste1)).thenReturn(piste1);

            Piste savedPiste = pisteServices.addPiste(piste1);

            assertNotNull(savedPiste);
            assertEquals("Piste 1", savedPiste.getNamePiste());
            verify(pisteRepository).save(piste1);
            System.out.println("testAddPisteSuccess: Test passed");
        } catch (AssertionError | Exception e) {
            System.out.println("testAddPisteSuccess: Test failed - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testAddPisteFailure() {
        try {
            when(pisteRepository.save(piste1)).thenReturn(null);

            Piste savedPiste = pisteServices.addPiste(piste1);

            assertNull(savedPiste);
            verify(pisteRepository).save(piste1);
            System.out.println("testAddPisteFailure: Test passed");
        } catch (AssertionError | Exception e) {
            System.out.println("testAddPisteFailure: Test failed - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testAddPisteError() {
        try {
            when(pisteRepository.save(piste1)).thenThrow(new RuntimeException("Save failed"));

            assertThrows(RuntimeException.class, () -> pisteServices.addPiste(piste1));

            System.out.println("testAddPisteError: Test passed");
        } catch (AssertionError | Exception e) {
            System.out.println("testAddPisteError: Test failed - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testRemovePisteSuccess() {
        try {
            Long pisteId = 1L;

            pisteServices.removePiste(pisteId);

            verify(pisteRepository).deleteById(pisteId);
            System.out.println("testRemovePisteSuccess: Test passed");
        } catch (AssertionError | Exception e) {
            System.out.println("testRemovePisteSuccess: Test failed - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testRemovePisteFailure() {
        try {
            Long pisteId = 1L;
            doNothing().when(pisteRepository).deleteById(pisteId);

            pisteServices.removePiste(pisteId);

            verify(pisteRepository).deleteById(pisteId);
            System.out.println("testRemovePisteFailure: Test passed");
        } catch (AssertionError | Exception e) {
            System.out.println("testRemovePisteFailure: Test failed - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testRemovePisteError() {
        try {
            Long pisteId = 1L;
            doThrow(new RuntimeException("Deletion failed")).when(pisteRepository).deleteById(pisteId);

            assertThrows(RuntimeException.class, () -> pisteServices.removePiste(pisteId));

            System.out.println("testRemovePisteError: Test passed");
        } catch (AssertionError | Exception e) {
            System.out.println("testRemovePisteError: Test failed - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testRetrievePisteFound() {
        try {
            Long pisteId = 1L;
            when(pisteRepository.findById(pisteId)).thenReturn(Optional.of(piste1));

            Piste foundPiste = pisteServices.retrievePiste(pisteId);

            assertNotNull(foundPiste);
            assertEquals("Piste 1", foundPiste.getNamePiste());
            System.out.println("testRetrievePisteFound: Test passed");
        } catch (AssertionError | Exception e) {
            System.out.println("testRetrievePisteFound: Test failed - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testRetrievePisteNotFound() {
        try {
            Long pisteId = 1L;
            when(pisteRepository.findById(pisteId)).thenReturn(Optional.empty());

            Piste foundPiste = pisteServices.retrievePiste(pisteId);

            assertNull(foundPiste);
            System.out.println("testRetrievePisteNotFound: Test passed");
        } catch (AssertionError | Exception e) {
            System.out.println("testRetrievePisteNotFound: Test failed - " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testRetrievePisteError() {
        try {
            Long pisteId = 1L;
            when(pisteRepository.findById(pisteId)).thenThrow(new RuntimeException("Database error"));

            assertThrows(RuntimeException.class, () -> pisteServices.retrievePiste(pisteId));

            System.out.println("testRetrievePisteError: Test passed");
        } catch (AssertionError | Exception e) {
            System.out.println("testRetrievePisteError: Test failed - " + e.getMessage());
            throw e;
        }
    }
}
