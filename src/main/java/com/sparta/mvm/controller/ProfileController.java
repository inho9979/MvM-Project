package com.sparta.mvm.controller;

import com.sparta.mvm.dto.ProfileRequestDto;
import com.sparta.mvm.dto.ProfileResponseDto;
import com.sparta.mvm.exception.CommonResponse;
import com.sparta.mvm.exception.CustomException;
import com.sparta.mvm.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sparta.mvm.exception.ErrorEnum.NOT_VALID_ARGUMENTS;
import static com.sparta.mvm.exception.ErrorEnum.USER_NOT_FOUND;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/{userId}")
    public ResponseEntity<CommonResponse> getProfile(@PathVariable Long userId) {
        ProfileResponseDto profile = profileService.getProfile(userId);
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("프로필 조회 성공!")
                .data(profile)
                .build());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<CommonResponse> updateProfile(@PathVariable Long userId, @Valid @RequestBody ProfileRequestDto requestDto,
                                                        BindingResult bindingResult){
        if (userId == null) {
            throw new CustomException(USER_NOT_FOUND);
        }

        // 오류가 발생했다면 어느 필드에서 에러가 발생했는지 출력
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            throw new CustomException(NOT_VALID_ARGUMENTS);
        }
        ProfileResponseDto profile = profileService.updateSchedule(userId, requestDto);
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("스케줄이 수정되었습니다.")
                .data(profile)
                .build());
    }

}
