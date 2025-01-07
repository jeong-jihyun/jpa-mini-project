package com.herojoon.jpaproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "LOCK_UP_RETU_INFO")
public class LockUpRetuInfoJPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "afrsRsqtCnt")
    private String afrsRsqtCnt;

    @Column(name = "basDt")
    private String basDt;

    @Column(name = "crno")
    private String crno;

    @Column(name = "isinCd")
    private String isinCd;

    @Column(name = "isinCdNm")
    private String isinCdNm;

    @Column(name = "itmsShrtnCd")
    private String itmsShrtnCd;

    @Column(name = "lblProtTsumIssuStckCnt")
    private String lblProtTsumIssuStckCnt;

    @Column(name = "lkupRegDt")
    private String lkupRegDt;

    @Column(name = "lkupRegStckCnt")
    private Integer lkupRegStckCnt;

    @Column(name = "lstgDcd")
    private String lstgDcd;

    @Column(name = "lstgDcdNm")
    private String lstgDcdNm;

    @Column(name = "rsrnDt")
    private String rsrnDt;

    @Column(name = "rsrnStckCnt")
    private String rsrnStckCnt;

    @Column(name = "scrsItmsKcd")
    private String scrsItmsKcd;

    @Column(name = "scrsItmsKcdNm")
    private String scrsItmsKcdNm;

    @Column(name = "stckIssuCmpyNm")
    private String stckIssuCmpyNm;

    @Column(name = "stckLblHoldRcd")
    private Integer stckLblHoldRcd;

    @Column(name = "stckLblHoldRcdNm")
    private String stckLblHoldRcdNm;
}
