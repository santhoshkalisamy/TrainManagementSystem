package dev.santhoshkalisamy.cloudbeestrainreservation.controller;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Seat;
import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Section;
import dev.santhoshkalisamy.cloudbeestrainreservation.exception.SectionNotFoundException;
import dev.santhoshkalisamy.cloudbeestrainreservation.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping("/{sectionName}")
    public ResponseEntity<Section> getSection(@PathVariable("sectionName") String sectionName, @RequestParam(required = false) Boolean allocated) throws SectionNotFoundException {
        return ResponseEntity.status(200).body(sectionService.getSectionByName(sectionName, allocated));
    }

}
