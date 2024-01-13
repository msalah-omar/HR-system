//package com.example.hrSystem.handler;
//
//import com.example.hrSystem.Dto.RoleDto;
//import com.example.hrSystem.Dto.UserDto;
//import com.example.hrSystem.Dto.commen.PaginatedResultDto;
//import com.example.hrSystem.Service.RoleService;
//import com.example.hrSystem.Service.UserService;
//import com.example.hrSystem.entity.Role;
//import com.example.hrSystem.entity.User;
//import com.example.hrSystem.exception.ErrorCodes;
//import com.example.hrSystem.exception.ResourceAlreadyExistsException;
//import com.example.hrSystem.exception.ResourceNotFoundException;
//import com.example.hrSystem.exception.ResourceRelatedException;
//import com.example.hrSystem.mapper.PaginationMapper;
//import com.example.hrSystem.mapper.UserMapper;
//import lombok.AllArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//@AllArgsConstructor
//public class UsersHandler {
//    private UserService userService;
//    private UserMapper userMapper;
//    private PaginationMapper paginationMapper;
//    private PasswordEncoder passwordEncoder;
//    private RoleService roleService;
//
//    public ResponseEntity<?> getAll(String username, String arabicName, String roleCode, Integer page, Integer size) {
//        Page<User> usersPage = userService.getAll(username, arabicName, roleCode, page, size);
//        List<UserDto> userDtoList = userMapper.toDto(usersPage.getContent());
//        PaginatedResultDto<UserDto> paginatedResult = new PaginatedResultDto<>();
//        paginatedResult.setData(userDtoList);
//        paginatedResult.setPagination(paginationMapper.toPaginationDto(usersPage));
//        return ResponseEntity.ok(paginatedResult);
//    }
//
//    public ResponseEntity<?> getById(Integer id) {
//        User user = userService.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
//        return ResponseEntity.ok(userMapper.toDto(user));
//    }
//
//    public ResponseEntity<?> save(UserDto dto) {
//        Optional<User> user = userService.findByUsername(dto.getUsername());
//        user.ifPresent(e -> {
//            throw new ResourceAlreadyExistsException(User.class.getSimpleName(), "Username", dto.getUsername(), ErrorCodes.DUPLICATE_RESOURCE.getCode());
//        });
//        User newUser = userMapper.toEntity(dto);
//        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
//        newUser.setIsTemporaryPassword(true);
//        userService.save(newUser);
//        UserDto userDto = userMapper.toDto(newUser);
//        return ResponseEntity.created(URI.create("/user/" + userDto.getId())).body(userDto);
//    }
//
//    public ResponseEntity<?> update(Integer id, UserDto userDto) {
//        User user = userService.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
//        Optional<User> existedUsername = userService.findByUsername(userDto.getUsername());
//        if (existedUsername.isPresent() && !existedUsername.get().getId().equals(id)) {
//            throw new ResourceAlreadyExistsException(User.class.getSimpleName(), "Username", userDto.getUsername(), ErrorCodes.DUPLICATE_RESOURCE.getCode());
//        }
//        User entity = userMapper.updateEntity(userDto, user);
//        if (userDto.getPassword() != null) {
//            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
//        }
//        userService.save(entity);
//        return ResponseEntity.ok().build();
//    }
//
//
//    public ResponseEntity<?> delete(Integer id) {
//        User user = userService.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
//        try {
//            userService.delete(user);
//        } catch (Exception exception) {
//            throw new ResourceRelatedException(User.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
//        }
//        return ResponseEntity.noContent().build();
//    }
//
//    public ResponseEntity<?> assignRole(Integer userId, Integer roleId) {
//        User user = userService
//                .findByIdWithRoles(userId).orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), userId));
//        Role role = roleService.findById(roleId)
//                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), roleId));
//        boolean added = user.getRoles().add(role);
//        if (!added) {
//            throw new ResourceAlreadyExistsException(Role.class.getSimpleName(), "Id", roleId.toString(), ErrorCodes.DUPLICATE_RESOURCE.getCode());
//        }
//        userService.save(user);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//
//    public ResponseEntity<?> deleteRole(Integer userId, Integer roleId) {
//        User user = userService
//                .findByIdWithRoles(userId).orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), userId));
//        Role role = roleService
//                .findById(roleId).orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), roleId));
//        user.getRoles().remove(role);
//        userService.save(user);
//        return ResponseEntity.noContent().build();
//    }
//
//    public ResponseEntity<?> getUserRoles(Integer userId) {
//        User user = userService
//                .findByIdWithRoles(userId).orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), userId));
//        UserDto dto = userMapper.toDto(user);
//        List<RoleDto> roleDtoList = new ArrayList<>(dto.getRoles());
//        Page<RoleDto> rolePage = new PageImpl<>(roleDtoList);
//        PaginatedResultDto<RoleDto> paginatedResult = new PaginatedResultDto<>();
//        paginatedResult.setData(roleDtoList);
//        paginatedResult.setPagination(paginationMapper.toPaginationDto(rolePage));
//        return ResponseEntity.ok(paginatedResult);
//    }
//}
//
