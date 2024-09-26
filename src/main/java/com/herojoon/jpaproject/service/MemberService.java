package com.herojoon.jpaproject.service;

import com.herojoon.jpaproject.entity.Member;
import com.herojoon.jpaproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    /**
     * Member 생성
     *
     * @param member
     * @return
     */
    public Member createMember(Member member) {
        Member savedMember = memberRepository.save(member);  // JpaRepository에서 제공하는 save() 함수
        return savedMember;
    }

    /**
     * Member 수정
     * JPA Repository의 save Method를 사용하여 객체를 업데이트 할 수 있습니다.
     * Entity Member에 @Id로 설정한 키 값이 존재할 경우 해당하는 데이터를 업데이트 해줍니다.
     * 만약 수정하려는 Entity Member 객체에 @Id 값이 존재하지 않으면 Insert 되기 때문에
     * 아래와 같이 업데이트 하고자 하는 Member가 존재하는지 체크하는 로직을 추가하였습니다.
     *
     * @param member
     * @return
     */
    public Member updateMember(Member member) {
        Member updatedMember = null;
        try {
            Member existMember = getMember(member.getId());
            if (!ObjectUtils.isEmpty(existMember)) {
                updatedMember = memberRepository.save(member);  // JpaRepository에서 제공하는 save() 함수
            }
        } catch (Exception e) {
            log.info("[Fail] e: " + e.toString());
        } finally {
            return updatedMember;
        }
    }

    /**
     * Member List 조회
     * 
     * @return
     */
    public List<Member> getMembers() {
        return memberRepository.findAll();  // JpaRepository에서 제공하는 findAll() 함수
    }

    /**
     * Id에 해당하는 Member 조회
     * 
     * @param id
     * @return
     */
    public Member getMember(Long id) {
        return memberRepository.getById(id);  // JpaRepository에서 제공하는 getById() 함수
    }

    /**
     * Id에 해당하는 Member 삭제
     * 
     * @param id
     */
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);  // JpaRepository에서 제공하는 deleteById() 함수
    }
}
