package io.gloe.gloe.controller;

import io.gloe.gloe.entity.Source;
import io.gloe.gloe.service.SourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sources")
public class SourceController {
    private final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @PostMapping
    public ResponseEntity<Source> createSource(@RequestBody Source source) {
        Source createdSource = sourceService.createSource(source);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSource);
    }
}
