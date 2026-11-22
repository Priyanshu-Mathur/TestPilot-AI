package com.testpilot.ai.mapper;

import com.testpilot.ai.dto.RequirementRequest;
import com.testpilot.ai.dto.RequirementResponse;
import com.testpilot.ai.entity.Requirement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequirementMapper {
    RequirementResponse toResponse(Requirement requirement);

    Requirement toEntity(RequirementRequest request);
}
