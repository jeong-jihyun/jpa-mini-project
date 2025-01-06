package com.herojoon.jpaproject.service;

import com.herojoon.jpaproject.entity.MemberJPO;
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
     * @param memberJPO
     * @return
     */
    public MemberJPO createMember(MemberJPO memberJPO) {
        MemberJPO savedMemberJPO = memberRepository.save(memberJPO);  // JpaRepository에서 제공하는 save() 함수
        return savedMemberJPO;
    }

    /**
     * Member 수정
     * JPA Repository의 save Method를 사용하여 객체를 업데이트 할 수 있습니다.
     * Entity Member에 @Id로 설정한 키 값이 존재할 경우 해당하는 데이터를 업데이트 해줍니다.
     * 만약 수정하려는 Entity Member 객체에 @Id 값이 존재하지 않으면 Insert 되기 때문에
     * 아래와 같이 업데이트 하고자 하는 Member가 존재하는지 체크하는 로직을 추가하였습니다.
     *
     * @param memberJPO
     * @return
     */
    public MemberJPO updateMember(MemberJPO memberJPO) {
        MemberJPO updatedMemberJPO = null;
        try {
            MemberJPO existMemberJPO = getMember(memberJPO.getId());
            if (!ObjectUtils.isEmpty(existMemberJPO)) {
                updatedMemberJPO = memberRepository.save(memberJPO);  // JpaRepository에서 제공하는 save() 함수
            }
        } catch (Exception e) {
            log.info("[Fail] e: " + e.toString());
        } finally {
            return updatedMemberJPO;
        }
    }

    /**
     * Member List 조회
     * 
     * @return
     */
    public List<MemberJPO> getMembers() {
        return memberRepository.findAll();  // JpaRepository에서 제공하는 findAll() 함수
    }

    /**
     * Id에 해당하는 Member 조회
     * 
     * @param id
     * @return
     */
    public MemberJPO getMember(Long id) {
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
