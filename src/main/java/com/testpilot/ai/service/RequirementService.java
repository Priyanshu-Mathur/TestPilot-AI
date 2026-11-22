package com.testpilot.ai.service;

import com.testpilot.ai.dto.RequirementRequest;
import com.testpilot.ai.dto.RequirementResponse;
import com.testpilot.ai.entity.Requirement;
import com.testpilot.ai.exception.ResourceNotFoundException;
import com.testpilot.ai.mapper.RequirementMapper;
import com.testpilot.ai.repository.RequirementRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequirementService {

    private final RequirementRepository requirementRepository;
    private final RequirementMapper requirementMapper;

    public RequirementService(RequirementRepository requirementRepository, RequirementMapper requirementMapper){
        this.requirementRepository = requirementRepository;
        this.requirementMapper = requirementMapper;
    }

    public RequirementResponse saveRequirement(RequirementRequest request) {
        Requirement requirement = requirementMapper.toEntity(request);
        Requirement savedRequirement = requirementRepository.save(requirement);
        return requirementMapper.toResponse(savedRequirement);
    }

    public List<RequirementResponse> getAllRequirements(){
        List<Requirement> requirements = requirementRepository.findAll();
        List<RequirementResponse> responses = new ArrayList<>();
        for (Requirement requirement : requirements){
            responses.add(requirementMapper.toResponse(requirement));
        }
        return responses;
    }

    public RequirementResponse updateRequirement(Long id, RequirementRequest request){
        Requirement existingRequirement = requirementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requirement not found"));
        existingRequirement.setDescription(request.getDescription());
        existingRequirement.setTitle(request.getTitle());
        Requirement updatedRequirement = requirementRepository.save(existingRequirement);
        return requirementMapper.toResponse(updatedRequirement);
    }

    public void deleteRequirement(Long id){
        requirementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requirement not found"));
        requirementRepository.deleteById(id);
    }

    public RequirementResponse getSingleRequirement(Long id){
        Requirement existingRequirement = requirementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requirement not found"));
        return requirementMapper.toResponse(existingRequirement);
    }

//We replace this with RequiremnetMapper interface it will do also same work what these methods are doing
//    public Requirement convertToEntity(RequirementRequest request) {
//        Requirement requirement = new Requirement();
//        requirement.setTitle(request.getTitle());
//        requirement.setDescription(request.getDescription());
//        return requirement;
//    }
//
//    public RequirementResponse convertToResponse(Requirement requirement) {
//        RequirementResponse response = new RequirementResponse();
//        response.setId(requirement.getId());
//        response.setTitle(requirement.getTitle());
//        response.setDescription(requirement.getDescription());
//        response.setStatus(requirement.getStatus());
//        return response;
//    }
}
