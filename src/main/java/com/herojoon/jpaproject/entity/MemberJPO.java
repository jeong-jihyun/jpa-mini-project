package com.herojoon.jpaproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.sun.istack.NotNull;
import com.herojoon.jpaproject.constraint.Gender;
import com.herojoon.jpaproject.converter.GenderConvert;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity  // 객체와 테이블 매핑
@Table(name = "MEMBER")  // index 없음 (기본 테이블 생성)
@ToString(of = {"id", "name", "email", "nickname", "age", "birthday"})
public class MemberJPO {
    @Id  // Primary Key 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")  // 컬럼 지정
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "BIRTHDAY")
    private Date birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private TeamJPO team;

    @Column(name = "SEX")
    @Convert(converter = GenderConvert.class)
    private Gender sex;
}
