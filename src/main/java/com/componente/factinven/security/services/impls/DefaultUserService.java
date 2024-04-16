package com.componente.factinven.security.services.impls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.componente.factinven.security.dtos.UserDTO;
import com.componente.factinven.security.entities.UserEntity;
import com.componente.factinven.security.enums.AccountStatus;
import com.componente.factinven.security.enums.RoleAuthority;
import com.componente.factinven.security.repositories.UserRepository;
import com.componente.factinven.security.services.IUserService;
import com.componente.factinven.security.utils.DataMapperUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultUserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        log.info("Creating User with User Details: {} ", userDTO);
        UserEntity userEntity = DataMapperUtil.convertTo(userDTO, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//        userEntity.setCreatedBy(SecurityContext.getCurrentUser());
        // If it is a public call than the value will be -999L {Default}
        // and if it is call by ADMIN or someone then it will have their respective mysql ids
        userEntity.setRole(RoleAuthority.ROLE_MOD);
        userEntity.setCreatedBy(-999);
        userEntity.setStatus(AccountStatus.ACTIVE);
        userEntity.setCreatedDate(new Date());

        userEntity = userRepository.save(userEntity);
        log.info("Saved User Entity in Serive layer: {} ", userEntity);
        return DataMapperUtil.convertTo(userEntity, UserDTO.class);
    }

    @Override
    public Optional<UserDTO> getByUsername(String username) {
        log.info("Finding user By username in service layer: {} ", username);
        final Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        if(userEntityOptional.isEmpty()) {
            log.warn("No user Found by username: {} ", username);
            return Optional.empty();
        }
        final UserEntity userEntity = userEntityOptional.get();
        log.info("Retrieved User Entity in SE: {} ", userEntity);
        return Optional.of(DataMapperUtil.convertTo(userEntity, UserDTO.class));
    }

    @Override
    public List<UserDTO> getAll() {
        log.info("Getting List of user DTOs");
        final List<UserDTO> userDTOS = new ArrayList<>();
        userRepository.findAll().stream().filter(e->{return e.getRole()!=RoleAuthority.ROLE_ADMIN;}).forEach((userEntity -> {
            userDTOS.add(DataMapperUtil.convertTo(userEntity, UserDTO.class));
        }));
        return userDTOS;
    }
    
    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        log.info("Creating User with User Details: {} ", userDTO);
        UserEntity userEntity = DataMapperUtil.convertTo(getByUsername(userDTO.getUsername()).get(), UserEntity.class);
        //userEntity.setPassword(passwordEncoder.de(userDTO.getPassword()));
//        userEntity.setCreatedBy(SecurityContext.getCurrentUser());
        // If it is a public call than the value will be -999L {Default}
        // and if it is call by ADMIN or someone then it will have their respective mysql ids
        //userEntity.setRole(RoleAuthority.ROLE_MOD);
        //userEntity.setCreatedBy(-999L);
        //userEntity.setStatus(AccountStatus.ACTIVE);
        //userEntity.setCreatedOn(LocalDateTime.now());
        userEntity = userRepository.save(userEntity);
        log.info("Saved User Entity in Serive layer: {} ", userEntity);
        return DataMapperUtil.convertTo(userEntity, UserDTO.class);
    }
    
    
    @Override
    public boolean inactivateUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setStatus(AccountStatus.INACTIVE);
        userEntity = userRepository.save(userEntity);
        log.info("Inactivated User Entity in Serive layer: {} ", userEntity);
		return true;
    }

	@Override
	public boolean activateUser(Long id) {
	      UserEntity userEntity = userRepository.findById(id).get();
	      userEntity.setStatus(AccountStatus.ACTIVE);
	      userEntity = userRepository.save(userEntity);
	      log.info("Inactivated User Entity in Serive layer: {} ", userEntity);
	      return true;
	}
}
