package com.herojoon.jpaproject.service;

import com.herojoon.jpaproject.entity.Member;
import com.herojoon.jpaproject.repository.MemberRepository;
import com.herojoon.jpaproject.repository.PostsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberServiceTest {
    @Autowired
    private MemberRepository memberRepository;
    @Test
    void createMember() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2011-12-03");
        Member member = Member.builder()
                .name("herojoon")
                .email("herojoon432@gmail.com")
                .nickname("heroble")
                .age(10)
                .birthday(date)
                .build();

        memberRepository.save(member);
    }

    @Test
    void updateMember() {
    }

    @Test
    void getMembers() {
    }

    @Test
    void getMember() {
    }

    @Test
    void deleteMember() {
    }
}