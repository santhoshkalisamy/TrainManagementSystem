package dev.santhoshkalisamy.cloudbeestrainreservation.service.impl;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Seat;
import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Section;
import dev.santhoshkalisamy.cloudbeestrainreservation.exception.SectionNotFoundException;
import dev.santhoshkalisamy.cloudbeestrainreservation.repository.SectionRepository;
import dev.santhoshkalisamy.cloudbeestrainreservation.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl implements SectionService {
    private SectionRepository sectionRepository;
    @Autowired
    public void setSectionRepository(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Section getSectionByName(String name, Boolean allocated) throws SectionNotFoundException {
        Section section = null;
        if(allocated == null) {
            section = sectionRepository.findSectionByName(name);
        } else if (allocated) {
            section = sectionRepository.findSectionAndAllocatedSeats(name);
        } else {
            section = sectionRepository.findSectionAndUnAllocatedSeats(name);
        }
        if(section == null) {
            throw new SectionNotFoundException();
        }
        return section;
    }
}
