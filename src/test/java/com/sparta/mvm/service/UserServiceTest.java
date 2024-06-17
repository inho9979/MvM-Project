package com.sparta.mvm.service;

import com.sparta.mvm.dto.ResignDto;
import com.sparta.mvm.dto.SignupRequestDto;
import com.sparta.mvm.dto.SignupResponseDto;
import com.sparta.mvm.entity.User;
import com.sparta.mvm.entity.UserStatusEnum;
import com.sparta.mvm.exception.CustomException;
import com.sparta.mvm.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;


    User user;
    @BeforeEach
    void setUp(){

        user = new User("username","password","name","email","", UserStatusEnum.USER_NORMAL);
    }

    @Test
    @DisplayName("회원가입 테스트 : 중복회원")
    void signup() {
        //given
        SignupRequestDto requestDto = new SignupRequestDto();
        requestDto.setUsername("username");
        requestDto.setPassword("password");
        requestDto.setName("name");
        requestDto.setEmail("email");
        String username = "username";
        UserService userService = new UserService(userRepository, passwordEncoder);
        given(userRepository.findByUsername(username)).willReturn(Optional.of(user));

        //when - then
        CustomException exception = assertThrows(CustomException.class, () -> userService.signup(requestDto));
        assertEquals("중복되거나 탈퇴한 사용자가 존재합니다.",exception.getStatusEnum().getMsg());
    }
    @Test
    @DisplayName("회원가입 테스트 : 정상")
    void signup2() {
        //given
        SignupRequestDto requestDto = new SignupRequestDto();
        requestDto.setUsername("username");
        requestDto.setPassword("password");
        requestDto.setName("name");
        requestDto.setEmail("email");
        String username = "username";
        UserService userService = new UserService(userRepository, passwordEncoder);
        given(userRepository.findByUsername(username)).willReturn(Optional.empty());

        //when - then
        SignupResponseDto resultDto = userService.signup(requestDto);

        assertEquals(resultDto.getUsername(), requestDto.getUsername());
    }

    @Test
    @DisplayName("회원탈퇴 테스트 : 실패")
    void resign() {
        //given
        String username = "username";
        ResignDto resingDto = new ResignDto();
        resingDto.setPassword("password");
        given(userRepository.findByUsername(username)).willReturn(Optional.of(user));
        given(passwordEncoder.matches(resingDto.getPassword(), user.getPassword())).willReturn(false);
        UserService userService = new UserService(userRepository, passwordEncoder);

        //when
        CustomException exception = assertThrows(CustomException.class, () -> userService.resign(user, resingDto));

        //then
        assertEquals("비밀번호를 확인해주세요", exception.getStatusEnum().getMsg());
    }
}