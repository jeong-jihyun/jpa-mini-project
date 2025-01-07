package com.herojoon.jpaproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * 유상증자결정공시정보조회 Entity
 */
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "CAPI_INCR_WITH_CONS_DISC_INFO")
public class CapiIncrWithConsDiscInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "acthInvtYn", length = 300)
    private String acthInvtYn;

    @Column(name = "arasBsisCtt", length = 4000)
    private String arasBsisCtt;

    @Column(name = "audpnAtndYn", length = 300)
    private String audpnAtndYn;

    @Column(name = "basDt", length = 8)
    private String basDt;

    @Column(name = "basStprDcXchrRt")
    private String basStprDcXchrRt;

    @Column(name = "bfSthdSbscSchEdDt", length = 8)
    private String bfSthdSbscSchEdDt;

    @Column(name = "bfSthdSbscSchSttgDt", length = 8)
    private String bfSthdSbscSchSttgDt;

    @Column(name = "bfciOnskIssuStckCnt")
    private String bfciOnskIssuStckCnt;

    @Column(name = "bfciPfstIssuStckCnt")
    private String bfciPfstIssuStckCnt;

    @Column(name = "bodRsolDt", length = 8)
    private String bodRsolDt;

    @Column(name = "capiMthoNm", length = 4000)
    private String capiMthoNm;

    @Column(name = "cnvrIssuStckCnt")
    private String cnvrIssuStckCnt;

    @Column(name = "cnvrStckKindNm", length = 4000)
    private String cnvrStckKindNm;

    @Column(name = "corRsnCtt", length = 4000)
    private String corRsnCtt;

    @Column(name = "crno", length = 4000)
    private String crno;

    @Column(name = "dturLstgPsblYn", length = 300)
    private String dturLstgPsblYn;

    @Column(name = "dturLstgYn", length = 300)
    private String dturLstgYn;

    @Column(name = "emstowUnnFoalAlctRto")
    private String emstowUnnFoalAlctRto;

    @Column(name = "emstowUnnSbscSchEdDt", length = 8)
    private String emstowUnnSbscSchEdDt;

    @Column(name = "emstowUnnSbscSchSttgDt", length = 8)
    private String emstowUnnSbscSchSttgDt;

    @Column(name = "etcAgrmCtt", length = 4000)
    private String etcAgrmCtt;

    @Column(name = "etcCptlAmt")
    private String etcCptlAmt;

    @Column(name = "etcCtt", length = 4000)
    private String etcCtt;

    @Column(name = "fctfndAt")
    private String fctfndAt;

    @Column(name = "frstPcPlanCtt", length = 4000)
    private String frstPcPlanCtt;

    @Column(name = "hcfhPlanCtt", length = 4000)
    private String hcfhPlanCtt;

    @Column(name = "imarTrCmteDclTrgtYn", length = 300)
    private String imarTrCmteDclTrgtYn;

    @Column(name = "issuPricCmpuMthCtt", length = 4000)
    private String issuPricCmpuMthCtt;

    @Column(name = "ivsRefCtt", length = 4000)
    private String ivsRefCtt;

    @Column(name = "mgmCptAt")
    private String mgmCptAt;

    @Column(name = "nrfsHndvSchDt", length = 8)
    private String nrfsHndvSchDt;

    @Column(name = "nstAlctBasDt", length = 8)
    private String nstAlctBasDt;

    @Column(name = "nstAlctStckCnt")
    private String nstAlctStckCnt;

    @Column(name = "nstDvdnRckDt", length = 8)
    private String nstDvdnRckDt;

    @Column(name = "nstLstgSchDt", length = 8)
    private String nstLstgSchDt;

    @Column(name = "onskFrmIssuPricSchDt", length = 8)
    private String onskFrmIssuPricSchDt;

    @Column(name = "onskIssuFrmPric")
    private String onskIssuFrmPric;

    @Column(name = "onskIssuSchPric")
    private String onskIssuSchPric;

    @Column(name = "onskNstCnt")
    private String onskNstCnt;

    @Column(name = "otcoScrtAcqAmt", length = 4000)
    private String otcoScrtAcqAmt;

    @Column(name = "otdrAbncNopeCnt")
    private String otdrAbncNopeCnt;

    @Column(name = "otdrAtndNopeCnt")
    private String otdrAtndNopeCnt;

    @Column(name = "otshFrmIssuPricSchDt", length = 8)
    private String otshFrmIssuPricSchDt;

    @Column(name = "otshIssuFrmPric")
    private String otshIssuFrmPric;

    @Column(name = "otshIssuSchPric")
    private String otshIssuSchPric;

    @Column(name = "pbsbSbscSchEdDt", length = 8)
    private String pbsbSbscSchEdDt;

    @Column(name = "pbsbSbscSchSttgDt", length = 8)
    private String pbsbSbscSchSttgDt;

    @Column(name = "pfstNstCnt")
    private String pfstNstCnt;

    @Column(name = "pftDvdnCtt", length = 4000)
    private String pftDvdnCtt;

    @Column(name = "prmrCnvYn", length = 300)
    private String prmrCnvYn;

    @Column(name = "prmrCrtfLstgYn", length = 300)
    private String prmrCrtfLstgYn;

    @Column(name = "pymtSchActhInvtAmt")
    private String pymtSchActhInvtAmt;

    @Column(name = "pymtSchActhInvtRto")
    private String pymtSchActhInvtRto;

    @Column(name = "pymtSchStckCnt")
    private String pymtSchStckCnt;

    @Column(name = "rd3ptAlctArasBsisCtt", length = 4000)
    private String rd3ptAlctArasBsisCtt;

    @Column(name = "rgscUnltStckYn", length = 300)
    private String rgscUnltStckYn;

    @Column(name = "rprsSptdCmpyNm", length = 4000)
    private String rprsSptdCmpyNm;

    @Column(name = "rptFileCtt", length = 4000)
    private String rptFileCtt;

    @Column(name = "sbmsExemRsnCtt", length = 4000)
    private String sbmsExemRsnCtt;

    @Column(name = "scrtDclrptSbmsTrgtYn", length = 300)
    private String scrtDclrptSbmsTrgtYn;

    @Column(name = "shcpPymtDt", length = 8)
    private String shcpPymtDt;

    @Column(name = "stckCnvrClmTermMnthCnt")
    private String stckCnvrClmTermMnthCnt;

    @Column(name = "stckCnvrCondCtt", length = 4000)
    private String stckCnvrCondCtt;

    @Column(name = "stckCtt", length = 4000)
    private String stckCtt;

    @Column(name = "stckParPrc")
    private String stckParPrc;

    @Column(name = "stckRdptAmt")
    private String stckRdptAmt;

    @Column(name = "stckRdptCondNm", length = 4000)
    private String stckRdptCondNm;

    @Column(name = "stckRdptMthNm", length = 4000)
    private String stckRdptMthNm;

    @Column(name = "stckRdptTermMnthCnt")
    private String stckRdptTermMnthCnt;

    @Column(name = "stckVtrgCtt", length = 4000)
    private String stckVtrgCtt;

    @Column(name = "trdMdatFinInvsNm", length = 4000)
    private String trdMdatFinInvsNm;

    @Column(name = "wt1yRdptSchYn", length = 300)
    private String wt1yRdptSchYn;
}