package dev.santhoshkalisamy.cloudbeestrainreservation.service.impl;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Seat;
import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Section;
import dev.santhoshkalisamy.cloudbeestrainreservation.exception.SectionNotFoundException;
import dev.santhoshkalisamy.cloudbeestrainreservation.repository.SectionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class SectionServiceImplTest {

    @InjectMocks
    SectionServiceImpl sectionService;

    @Mock
    SectionRepository sectionRepository;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSectionDetails() throws SectionNotFoundException {
        initializeMocks("A");
        Section section = sectionService.getSectionByName("A", null);
        Section allocatedSection = sectionService.getSectionByName("A", true);
        Section unAllocatedSection = sectionService.getSectionByName("A", false);
        Assert.assertNotNull(section);
        Assert.assertNotNull(allocatedSection);
        Assert.assertNotNull(unAllocatedSection);

        Assert.assertTrue(allocatedSection.getSeats().get(0).isAllocated());
        Assert.assertFalse(unAllocatedSection.getSeats().get(0).isAllocated());
    }

    @Test(expected = SectionNotFoundException.class)
    public void testGetSectionNotFoundError() throws SectionNotFoundException {
        initializeMocks("A");
        Mockito.when(sectionRepository.findSectionByName("A")).thenReturn(null);
        sectionService.getSectionByName("A", null);
    }

    private void initializeMocks(String name) {
        Section section = new Section();
        section.setSeats(new ArrayList<>());
        Seat seat = new Seat();
        Seat seat1 = new Seat();
        seat1.setAllocated(true);
        section.getSeats().add(seat);
        section.getSeats().add(seat1);

        Section onlyAllocated = new Section();
        onlyAllocated.setSeats(new ArrayList<>());

        onlyAllocated.getSeats().add(seat1);

        Section onlyUnallocated = new Section();
        onlyUnallocated.setSeats(new ArrayList<>());

        onlyUnallocated.getSeats().add(seat);
        Mockito.when(sectionRepository.findSectionByName(name)).thenReturn(section);
        Mockito.when(sectionRepository.findSectionAndAllocatedSeats(name)).thenReturn(onlyAllocated);
        Mockito.when(sectionRepository.findSectionAndUnAllocatedSeats(name)).thenReturn(onlyUnallocated);
    }
}
