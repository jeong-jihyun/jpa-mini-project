package com.herojoon.jpaproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "BUSI_SUSP_DISC_INFO")
public class BusiSuspDiscInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AUDPN_ATND_YN")
    private String audpnAtndYn;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "BOD_RSOL_DT")
    private String bodRsolDt;

    @Column(name = "BZOP_STOP_AMT")
    private String bzopStopAmt;

    @Column(name = "BZOP_STOP_AMT_RTO")
    private double bzopStopAmtRto;

    @Column(name = "BZOP_STOP_CTPN_CTT", length=4000)
    private String bzopStopCtpnCtt;

    @Column(name = "BZOP_STOP_CTT", length=4000)
    private String bzopStopCtt;

    @Column(name = "BZOP_STOP_DT")
    private String bzopStopDt;

    @Column(name = "BZOP_STOP_FILD_NM",length = 4000)
    private String bzopStopFildNm;

    @Column(name = "BZOP_STOP_INFC_CTT", length=4000)
    private String bzopStopInfcCtt;

    @Column(name = "BZOP_STOP_RSN_CTT", length=4000)
    private String bzopStopRsnCtt;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "IVS_REF_CTT", length=4000)
    private String ivsRefCtt;

    @Column(name = "LGSC_CORP_YN")
    private String lgscCorpYn;

    @Column(name = "LTST_TSLE_AMT")
    private String ltstTsleAmt;

    @Column(name = "OTDR_ABNC_NOPE_CNT")
    private int otdrAbncNopeCnt;

    @Column(name = "OTDR_ATND_NOPE_CNT")
    private int otdrAtndNopeCnt;

    @Column(name = "RPT_FILE_CTT", length=4000)
    private String rptFileCtt;

    @Column(name = "XCHG_LBL_PBAN_YN")
    private String xchgLblPbanYn;

}
