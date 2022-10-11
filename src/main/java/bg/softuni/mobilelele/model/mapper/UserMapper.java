package bg.softuni.mobilelele.model.mapper;

import bg.softuni.mobilelele.model.dto.UserRegisterDto;
import bg.softuni.mobilelele.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "active", constant = "true")
    User userDtoToUserEntity(UserRegisterDto registerDTO);
}