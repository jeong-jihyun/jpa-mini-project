package com.herojoon.jpaproject.controller;

import com.herojoon.jpaproject.entity.Member;
import com.herojoon.jpaproject.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(tags = "Member")
@RequiredArgsConstructor
@RequestMapping("api/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    /**
     * Member 생성
     *
     * @return
     * @throws ParseException
     */
    @PostMapping("create")
    @Operation(summary = "회원생성", description = "회원정보 등록 처리")
    public ResponseEntity<Member> createMember() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2011-12-03");
        Member member = Member.builder()
                .name("herojoon")
                .email("herojoon432@gmail.com")
                .nickname("heroble")
                .age(10)
                .birthday(date)
                .build();
        Member savedMember = memberService.createMember(member);
        return new ResponseEntity<>(savedMember, HttpStatus.OK);
    }

    /**
     * Member 수정
     *
     * @return
     * @throws ParseException
     */
    @PutMapping("update")
    public ResponseEntity<Member> updateMember() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2011-12-03");
        Member member = Member.builder()
                .id(1l)
                .name("herojoon2")
                .email("herojoon432@gmail.com")
                .nickname("heroble2")
                .age(10)
                .birthday(date)
                .build();
        Member updatedMember = memberService.updateMember(member);
        if (!ObjectUtils.isEmpty(updatedMember)) {
            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(member, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Member List 조회
     *
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Member>> getMembers() {
        List<Member> members = memberService.getMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    /**
     * Id에 해당하는 Member 조회
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<Member> getMember(
            @PathVariable("id") Long id) {
        Member member = memberService.getMember(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    /**
     * Id에 해당하는 Member 삭제
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteMember(
            @PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
