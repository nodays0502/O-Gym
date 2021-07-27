package com.B305.ogym.controller;

import com.B305.ogym.controller.dto.SignupRequestDto;
import com.B305.ogym.controller.dto.SuccessResponseDto;
import com.B305.ogym.controller.dto.UpdateStudentRequestDto;
import com.B305.ogym.domain.users.common.UserBase;
import com.B305.ogym.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/signup")
    public ResponseEntity<SuccessResponseDto> signup(
        @RequestBody @Valid SignupRequestDto userDto
    ) {
        userService.signup(userDto);
        return ResponseEntity.ok(new SuccessResponseDto<Map>(
            200, "회원가입이 성공했습니다", new HashMap()
        ));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('PTTEACHER','ADMIN','USER')")
    public ResponseEntity<SuccessResponseDto> getMyUserInfo() {
        return ResponseEntity.ok(new SuccessResponseDto<UserBase>(
            200, "회원가입이 성공했습니다",userService.getMyUserWithAuthorities()
        ));
    }

    @DeleteMapping("/user")
    @PreAuthorize("hasAnyRole('PTTEACHER','ADMIN','USER')")
    public ResponseEntity<SuccessResponseDto> deleteMyUser() {
        userService.deleteUserBase();
        // Spring Context에서도 지워야한다.
        return ResponseEntity.ok(new SuccessResponseDto<Map>(
            200, "회원정보 삭제에 성공했습니다", new HashMap()
        ));
    }

    @PatchMapping("/user/student")
    @PreAuthorize("hasAnyRole('PTTEACHER','ADMIN','USER')")
    public ResponseEntity<SuccessResponseDto> updateStudent(
        @RequestBody @Valid UpdateStudentRequestDto studentRequestDto
    ) {
        userService.changeStudent(studentRequestDto);
        return ResponseEntity.ok(new SuccessResponseDto<Map>(
            200, "회원정보 수정에 성공했습니다.", new HashMap()
        ));
    }

    @PatchMapping("/user/teacher")
    @PreAuthorize("hasAnyRole('PTTEACHER','ADMIN','USER')")
    public ResponseEntity<SuccessResponseDto> updateTeacher() {

        return ResponseEntity.ok(new SuccessResponseDto<Map>(
            200, "회원정보 수정에 성공했습니다.", new HashMap()
        ));
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserBase> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username));
    }
}
