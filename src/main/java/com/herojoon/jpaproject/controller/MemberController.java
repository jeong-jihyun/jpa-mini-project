package com.herojoon.jpaproject.controller;

import com.herojoon.jpaproject.constraint.Gender;
import com.herojoon.jpaproject.entity.MemberEntity;
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
    public ResponseEntity<MemberEntity> createMember() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2011-12-03");
        MemberEntity memberJPO = MemberEntity.builder()
                .name("herojoon")
                .email("herojoon432@gmail.com")
                .nickname("heroble")
                .age(10)
                .birthday(date)
                .sex(Gender.MAN)
                .build();
        MemberEntity savedMemberJPO = memberService.createMember(memberJPO);
        return new ResponseEntity<>(savedMemberJPO, HttpStatus.OK);
    }

    /**
     * Member 수정
     *
     * @return
     * @throws ParseException
     */
    @PutMapping("update")
    public ResponseEntity<MemberEntity> updateMember() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2011-12-03");
        MemberEntity memberJPO = MemberEntity.builder()
                .id(1l)
                .name("herojoon2")
                .email("herojoon432@gmail.com")
                .nickname("heroble2")
                .age(10)
                .birthday(date)
                .build();
        MemberEntity updatedMemberJPO = memberService.updateMember(memberJPO);
        if (!ObjectUtils.isEmpty(updatedMemberJPO)) {
            return new ResponseEntity<>(updatedMemberJPO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(memberJPO, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Member List 조회
     *
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<MemberEntity>> getMembers() {
        List<MemberEntity> memberJPOS = memberService.getMembers();
        return new ResponseEntity<>(memberJPOS, HttpStatus.OK);
    }

    /**
     * Id에 해당하는 Member 조회
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<MemberEntity> getMember(
            @PathVariable("id") Long id) {
        MemberEntity memberJPO = memberService.getMember(id);
        return new ResponseEntity<>(memberJPO, HttpStatus.OK);
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
