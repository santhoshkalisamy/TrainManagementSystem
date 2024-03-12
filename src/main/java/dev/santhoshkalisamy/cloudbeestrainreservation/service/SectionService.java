package dev.santhoshkalisamy.cloudbeestrainreservation.service;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Section;
import dev.santhoshkalisamy.cloudbeestrainreservation.exception.SectionNotFoundException;

public interface SectionService {
    public Section getSectionByName(String name, Boolean allocated) throws SectionNotFoundException;
}
