package com.herojoon.jpaproject.service;

import com.herojoon.jpaproject.entity.MemberEntity;
import com.herojoon.jpaproject.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    private MemberRepository memberRepository;
    @Test
    void createMember() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2011-12-03");
        MemberEntity memberJPO = MemberEntity.builder()
                .name("herojoon")
                .email("herojoon432@gmail.com")
                .nickname("heroble")
                .age(10)
                .birthday(date)
                .build();

        memberRepository.save(memberJPO);
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