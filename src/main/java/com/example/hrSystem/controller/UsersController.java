//package com.example.hrSystem.controller;
//
//import com.example.hrSystem.Dto.UserDto;
//import com.example.hrSystem.handler.UsersHandler;
//import com.example.hrSystem.validation.InsertValidation;
//import com.example.hrSystem.validation.UpdateValidation;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.AllArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/user")
//@AllArgsConstructor
//@Tag(name = "User", description = "REST API for User")
//@Log4j2
//public class UsersController {
//    private UsersHandler usersHandler;
//
//    @GetMapping
//    @Operation(summary = "Get All Users Paged")
//    public ResponseEntity<?> getAll(
//            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
//            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
//            @RequestParam(value = "username", required = false) String username,
//            @RequestParam(value = "arabicName", required = false) String arabicName,
//            @RequestParam(value = "roleCode", required = false) String roleCode
//
//    ) {
//        return usersHandler.getAll(username, arabicName, roleCode, page, size);
//    }
//
//    @GetMapping("/{id}")
//    @Operation(summary = "get User By Id")
//    public ResponseEntity<?> getById(@PathVariable Integer id) {
//        return usersHandler.getById(id);
//    }
//
//    @PostMapping
//    @Operation(summary = "add New User")
//    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody UserDto userDto) {
//        return usersHandler.save(userDto);
//    }
//
//    @PutMapping("/{id}")
//    @Operation(summary = "update User ")
//    public ResponseEntity<?> update(@PathVariable Integer id, @Validated(UpdateValidation.class) @RequestBody UserDto userDto) {
//        return usersHandler.update(id, userDto);
//    }
//
////    @PutMapping("/current/password")
////    public ResponseEntity<?> changePassword(@AuthenticationPrincipal User user,
////                                            @Validated(UpdateValidation.class) @RequestBody ChangePasswordRequestDto dto) {
////        return usersHandler.changePassword(user, dto);
////    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "delete User By Id")
//    public ResponseEntity<?> delete(@PathVariable Integer id) {
//        return usersHandler.delete(id);
//    }
//
//    //_________-- Role --___________
//
//    @GetMapping("/{id}/role")
//    @Operation(summary = "get roles for specified user")
//    public ResponseEntity<?> getRoles(@PathVariable Integer id) {
//
//        return usersHandler.getUserRoles(id);
//    }
//
//
//    @PostMapping("/{id}/role/{roleId}")
//    @Operation(summary = "add role to user")
//    public ResponseEntity<?> saveRole(@PathVariable(name = "id") Integer userId,
//                                      @PathVariable(name = "roleId") Integer roleId) {
//
//        return usersHandler.assignRole(userId, roleId);
//    }
//
//    @DeleteMapping("/{id}/role/{roleId}")
//    @Operation(summary = "add role to user")
//    public ResponseEntity<?> deleteRole(@PathVariable(name = "id") Integer userId,
//                                        @PathVariable Integer roleId) {
//
//        return usersHandler.deleteRole(userId, roleId);
//    }
//}
