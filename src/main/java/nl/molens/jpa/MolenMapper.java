package nl.molens.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.BeanMapping;

import nl.molens.model.Molen;

@Mapper(componentModel = "spring")
public interface MolenMapper {

   @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMolenFromDto(Molen molenDto, @MappingTarget Molen molen);
}

