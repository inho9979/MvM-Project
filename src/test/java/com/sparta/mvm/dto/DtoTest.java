package com.sparta.mvm.dto;

import com.sparta.mvm.entity.Comment;
import com.sparta.mvm.entity.Post;
import com.sparta.mvm.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DtoTest {

    @Test
    @DisplayName("CommentResponseDto 내부 toDto 메서드 테스트")
    void test1() {
        //given
        Comment testComment = new Comment("테스트", new Post());
        User user = new User();
        testComment.setUser(user);
        CommentResponseDto testDto = new CommentResponseDto("테스트", 1, 0L,
                null, "테스트", null, null);

        //when - then
        assertEquals(CommentResponseDto.toDto("테스트", 1, testComment).getComments(), testComment.getComments());
    }

    @Test
    @DisplayName("CommentResponseDto 내부 toDeleteResponse 메서드 테스트")
    void test2() {
        //given
        CommentResponseDto testDto = CommentResponseDto.builder().msg("테스트메시지").statusCode(1).build();

        //when - then
        assertEquals(CommentResponseDto.toDeleteResponse("테스트메시지",1).getMsg(), testDto.getMsg());
    }

    @Test
    @DisplayName("PostResponseDto 내부 toDto 메서드 테스트")
    void test3() {
        Post testPost = new Post("테스트내용");
        testPost.setUser(new User());
        PostResponseDto testDto = PostResponseDto.builder().msg("테스트").statusCode(1).contents("테스트내용").build();

        assertEquals(PostResponseDto.toDto("테스트",1, testPost).getContents(), testDto.getContents());
    }
    @Test
    @DisplayName("PostResponseDto 내부 toDeleteResponse 메서드 테스트")
    void test4() {
        PostResponseDto testDto = PostResponseDto.builder().msg("테스트").statusCode(1).contents("테스트내용").build();
        assertEquals(PostResponseDto.toDeleteResponse("테스트",1).getMsg(), testDto.getMsg());
    }
}
