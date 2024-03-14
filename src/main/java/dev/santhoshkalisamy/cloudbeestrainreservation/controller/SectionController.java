package dev.santhoshkalisamy.cloudbeestrainreservation.controller;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Section;
import dev.santhoshkalisamy.cloudbeestrainreservation.exception.SectionNotFoundException;
import dev.santhoshkalisamy.cloudbeestrainreservation.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the controller class for the Section entity.
 * It handles HTTP requests related to sections.
 */
@RestController
@RequestMapping("/section")
public class SectionController {
    private SectionService sectionService;

    /**
     * This method is used to inject the SectionService dependency.
     * @param sectionService The service class for Section entity.
     */
    @Autowired
    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    /**
     * This method handles the GET request to fetch a Section by its name.
     * It optionally accepts a parameter to filter seats based on their allocation status.
     * @param sectionName The name of the section to be fetched.
     * @param allocated The allocation status of the seats. If true, only allocated seats are fetched. If false, only unallocated seats are fetched. If null, all seats are fetched.
     * @return A ResponseEntity containing the Section entity and the HTTP status.
     * @throws SectionNotFoundException If the section with the given name is not found.
     */
    @GetMapping("/{sectionName}")
    public ResponseEntity<Section> getSection(@PathVariable("sectionName") String sectionName, @RequestParam(required = false) Boolean allocated) throws SectionNotFoundException {
        return ResponseEntity.status(200).body(sectionService.getSectionByName(sectionName, allocated));
    }

}
