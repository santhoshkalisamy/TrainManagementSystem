package dev.santhoshkalisamy.cloudbeestrainreservation.service;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Section;
import dev.santhoshkalisamy.cloudbeestrainreservation.exception.SectionNotFoundException;

/**
 * This is the service interface for the Section entity.
 * It declares methods for section-related operations.
 */
public interface SectionService {

    /**
     * This method is used to get a Section by its name and allocation status.
     * @param name The name of the Section.
     * @param allocated The allocation status of the Section.
     * @return The Section entity that matches the given name and allocation status.
     * @throws SectionNotFoundException If a Section with the given name and allocation status is not found.
     */
    public Section getSectionByName(String name, Boolean allocated) throws SectionNotFoundException;
}
