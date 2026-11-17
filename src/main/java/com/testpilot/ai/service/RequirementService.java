package com.testpilot.ai.service;

import com.testpilot.ai.dto.RequirementRequest;
import com.testpilot.ai.entity.Requirement;
import com.testpilot.ai.repository.RequirementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementService {

    private final RequirementRepository requirementRepository;

    public RequirementService(RequirementRepository requirementRepository){
        this.requirementRepository = requirementRepository;
    }

    public Requirement saveRequirement(RequirementRequest request){

        Requirement requirement = convertToEntity(request);
        return requirementRepository.save(requirement);
    }

    public List<Requirement> getAllRequirements(){
        return requirementRepository.findAll();
    }

    public Requirement updateRequirement(Long id, Requirement updatedRequirement){
        Requirement existingRequirement = requirementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Requirement not found"));
        existingRequirement.setDescription(updatedRequirement.getDescription());
        existingRequirement.setTitle(updatedRequirement.getTitle());
        existingRequirement.setStatus(updatedRequirement.getStatus());
        return requirementRepository.save(existingRequirement);
    }

    public String deleteRequirement(Long id){
        Requirement existingRequirement = requirementRepository.findById(id).orElseThrow(() -> new RuntimeException("Requirement not found"));
        requirementRepository.deleteById(id);
        return "Delete successfully";
    }

    public Requirement convertToEntity(RequirementRequest request) {
        Requirement requirement = new Requirement();
        requirement.setTitle(request.getTitle());
        requirement.setDescription(request.getDescription());
        return requirement;
    }
}
