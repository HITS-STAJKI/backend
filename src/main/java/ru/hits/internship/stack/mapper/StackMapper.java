package ru.hits.internship.stack.mapper;

import org.mapstruct.*;
import ru.hits.internship.stack.entity.StackEntity;
import ru.hits.internship.stack.models.CreateStackDto;
import ru.hits.internship.stack.models.StackDto;
import ru.hits.internship.stack.models.UpdateStackDto;

@Mapper(componentModel = "spring")
public interface StackMapper {

    @Mapping(target = "id", ignore = true)
    StackEntity map(CreateStackDto createStackDto);

    StackDto map(StackEntity stackEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void update(@MappingTarget StackEntity stackEntity, UpdateStackDto updateStackDto);
}
