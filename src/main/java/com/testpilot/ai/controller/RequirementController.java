package com.testpilot.ai.controller;

import com.testpilot.ai.dto.RequirementRequest;
import com.testpilot.ai.dto.RequirementResponse;
import com.testpilot.ai.service.RequirementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class RequirementController {

    private RequirementService requirementService;

    public RequirementController(RequirementService requirementService){
        this.requirementService = requirementService;
    }

    @PostMapping("/api/requirements")
    public ResponseEntity<RequirementResponse> createRequirement(@Valid @RequestBody RequirementRequest request) {
        RequirementResponse response = requirementService.saveRequirement(request);
        return ResponseEntity
                .created(URI.create("http://localhost:8080/api/requirements/" + response.getId()))
                .body(response);
    }

    @GetMapping("/api/requirements")
    public List<RequirementResponse> getAllRequirement(){
        return requirementService.getAllRequirements();
    }

    @PutMapping("/api/requirements/{id}")
    public RequirementResponse updateRequirement(@PathVariable Long id, @Valid @RequestBody RequirementRequest request){
        return requirementService.updateRequirement(id,request);
    }
    
    @DeleteMapping("/api/requirements/{id}")
    public ResponseEntity<Void> deleteRequirement(@PathVariable Long id){
        requirementService.deleteRequirement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/requirements/{id}")
    public RequirementResponse getSingleRequirement(@PathVariable Long id){
        return requirementService.getSingleRequirement(id);
    }
}
