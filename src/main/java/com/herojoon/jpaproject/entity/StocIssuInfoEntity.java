package com.herojoon.jpaproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 *주식발행내역조회 Entity
 */
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "STOC_ISSU_INFO")
public class StocIssuInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "basDt")
    private String basDt;

    @Column(name = "crno")
    private String crno;
    /*보통주신주수*/
    @Column(name = "isinCd")
    private String isinCd;

    @Column(name = "isinCdNm")
    private String isinCdNm;

    @Column(name = "issuStckCnt")
    private String issuStckCnt;

    @Column(name = "lstgDt")
    private String lstgDt;

    @Column(name = "scrsDcd")
    private String scrsDcd;

    @Column(name = "scrsItmsKcd")
    private String scrsItmsKcd;

    @Column(name = "scrsItmsKcdNm")
    private String scrsItmsKcdNm;

    @Column(name = "stckIssuCmpyNm")
    private String stckIssuCmpyNm;

    @Column(name = "stckIssuDcnt")
    private Integer stckIssuDcnt;

    @Column(name = "stckIssuDt")
    private String stckIssuDt;

    @Column(name = "stckIssuRcd")
    private String stckIssuRcd;

    @Column(name = "stckIssuRcdNm")
    private String stckIssuRcdNm;

    @Column(name = "stckIssuSqno")
    private Integer stckIssuSqno;
}
