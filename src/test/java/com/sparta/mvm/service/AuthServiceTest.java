//package com.sparta.mvm.service;
//
//import com.sparta.mvm.entity.User;
//import com.sparta.mvm.entity.UserStatusEnum;
//import com.sparta.mvm.jwt.JwtUtil;
//import com.sparta.mvm.repository.UserRepository;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.mockito.Mock;
//import org.mockito.BDDMockito;
//
//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class AuthServiceTest {
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Autowired
//    JwtUtil jwtUtil;
//
//    @Autowired
//    UserRepository userRepository;
//
//    User user;
//    String refreshToken;
//
//    @Test
//    @DisplayName("Auth서비스 login테스트")
//    void login() {
//        User testUser = new User("user","1234","name","email","lineIntro", UserStatusEnum.USER_NORMAL);
//        given()
//    }
//
//    @Test
//    void tokenReissuance() {
//    }
//
//    @Test
//    void invalidateTokens() {
//    }
//}