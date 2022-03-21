package com.jpa.jpaup1.service;

import com.jpa.jpaup1.domain.Member;
import com.jpa.jpaup1.repository.MemberJpaRepository;
import com.jpa.jpaup1.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceUnitTest {

    @InjectMocks
    MemberService2Impl memberService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    MemberJpaRepository memberJpaRepository;

    @Nested
    @DisplayName("멤버 저장 테스트")
    class MemberSaveTest {
        @Test
        @DisplayName("mock 정상으로 동작하는가?")
        public void exceptTest() throws Exception {
            //given
            doReturn(Optional.of(Member.builder().id(2L).build())).when(memberJpaRepository).findById(any());
            //when
            Member one = memberService.findOne(2L);

            //then
            Assertions.assertThat(one.getId()).isEqualTo(2L);

        }

        @Test
        @DisplayName("중복된 이름이면 에러가 발생하는가?")
        public void isDuplicated_throwError() throws Exception {
            //given
            ArrayList<Member> members = new ArrayList<>(Arrays.asList(Member.builder().build()));
            doReturn(members).when(memberRepository).findByName(any());
            //doReturn(new ArrayList<>()).when(memberRepository).findByName(any());
            //when

            //then
            Assertions.assertThatExceptionOfType(IllegalStateException.class)
                    .isThrownBy(() -> memberService.join(Member.builder().build()));
        }

        @Test
        @DisplayName("저장 후 아이디를 반환하는가?")
        public void afterSave_returnId() throws Exception {
            //given
            doReturn(new ArrayList<>()).when(memberRepository).findByName(any());

            //when
            Long saveId = memberService.join(Member.builder().id(1L).build());

            //then
            Assertions.assertThat(saveId).isEqualTo(1L);
        }
    }

    @Nested
    @DisplayName("멤버수정 테스트")
    class MemberPutTest {

        @Test
        @DisplayName("정상작동하는가?")
        public void isOk() throws Exception {
            //given

            //when

            //then
        }

        @Test
        @DisplayName("실패하는가?")
        public void isfail() throws Exception {
            //given

            //when

            //then
        }
    }
}
