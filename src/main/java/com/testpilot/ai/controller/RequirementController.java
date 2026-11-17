package com.testpilot.ai.controller;

import com.testpilot.ai.dto.RequirementRequest;
import com.testpilot.ai.entity.Requirement;
import com.testpilot.ai.service.RequirementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequirementController {

    private RequirementService requirementService;

    public RequirementController(RequirementService requirementService){
        this.requirementService = requirementService;
    }

    @PostMapping("/api/requirements")
    public Requirement createRequirement(@RequestBody RequirementRequest request){
        return requirementService.saveRequirement(request);
    }

    @GetMapping("/api/requirements")
    public List<Requirement> getAllRequirement(){
        return requirementService.getAllRequirements();
    }

    @PutMapping("/api/requirements/{id}")
    public Requirement updateRequirement(@PathVariable Long id, @RequestBody Requirement requirement){
        return requirementService.updateRequirement(id,requirement);
    }
    
    @DeleteMapping("/api/requirements/{id}")
    public String deleteRequirement(@PathVariable Long id){
        return requirementService.deleteRequirement(id);
    }
}
