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
@Table(name = "CAPI_INCR_WITH_CONS_BONU_ISSU_DISC_INFO")
public class CapiIncrWithConsBonuIssuDiscInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ACTH_INVT_YN")
    private String acthInvtYn;

    @Column(name = "ARAS_BSIS_CTT")
    private String arasBsisCtt;

    @Column(name = "AUDPN_ATND_YN")
    private String audpnAtndYn;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "BAS_STPR_DC_XCHR_RT")
    private String basStprDcXchrRt;

    @Column(name = "BF_STHD_SBSC_SCH_ED_DT")
    private String bfSthdSbscSchEdDt;

    @Column(name = "BF_STHD_SBSC_SCH_STTG_DT")
    private String bfSthdSbscSchSttgDt;

    @Column(name = "BFCI_ONSK_TISU_STCK_CNT")
    private String bfciOnskTisuStckCnt;

    @Column(name = "BFCI_OTSH_TISU_STCK_CNT",length = 4000)
    private String bfciOtshTisuStckCnt;

    @Column(name = "BOD_RSOL_DT")
    private String bodRsolDt;

    @Column(name = "CAPI_MTHO_NM")
    private String capiMthoNm;

    @Column(name = "CNVR_ISSU_STCK_CNT")
    private int cnvrIssuStckCnt;

    @Column(name = "CNVR_STCK_KIND_NM")
    private String cnvrStckKindNm;

    @Column(name = "COR_RSN_CTT")
    private String corRsnCtt;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "DTUR_LSTG_PSBL_YN")
    private String dturLstgPsblYn;

    @Column(name = "DTUR_LSTG_YN")
    private String dturLstgYn;

    @Column(name = "EMSTOW_UNN_FOAL_ALCT_RTO")
    private Double emstowUnnFoalAlctRto;

    @Column(name = "EMSTOW_UNN_SBSC_SCH_ED_DT")
    private String emstowUnnSbscSchEdDt;

    @Column(name = "EMSTOW_UNN_SBSC_SCH_STTG_DT")
    private String emstowUnnSbscSchSttgDt;

    @Column(name = "ENP_CORP_NM")
    private String enpCorpNm;

    @Column(name = "ETC_AGRM_CTT")
    private String etcAgrmCtt;

    @Column(name = "ETC_CPTL_AMT")
    private String etcCptlAmt;

    @Column(name = "ETC_CTT")
    private String etcCtt;

    @Column(name = "FCTFND_AT")
    private String fctfndAt;

    @Column(name = "FRST_PC_PLAN_CTT", length = 4000)
    private String frstPcPlanCtt;

    @Column(name = "HCFH_PLAN_CTT", length = 4000)
    private String hcfhPlanCtt;

    @Column(name = "IMAR_TR_CMTE_DCL_TRGT_YN")
    private String imarTrCmteDclTrgtYn;

    @Column(name = "ISSU_PRIC_CMPU_MTH_CTT", length = 4000)
    private String issuPricCmpuMthCtt;

    @Column(name = "IVS_REF_CTT")
    private String ivsRefCtt;

    @Column(name = "MGM_CPT_AT")
    private String mgmCptAt;

    @Column(name = "NRFS_HNDV_SCH_DT")
    private String nrfsHndvSchDt;

    @Column(name = "NST_ALCT_BAS_DT")
    private String nstAlctBasDt;

    @Column(name = "NST_DVDN_RCK_DT")
    private String nstDvdnRckDt;

    @Column(name = "NST_LSTG_SCH_DT")
    private String nstLstgSchDt;

    @Column(name = "ONSK_FRM_ISSU_PRIC_SCH_DT")
    private String onskFrmIssuPricSchDt;

    @Column(name = "ONSK_ISSU_FRM_PRIC")
    private int onskIssuFrmPric;

    @Column(name = "ONSK_ISSU_SCH_PRIC")
    private int onskIssuSchPric;

    @Column(name = "ONSK_NST_ALCT_STCK_CNT")
    private Double onskNstAlctStckCnt;

    @Column(name = "ONSK_NST_CNT")
    private String onskNstCnt;

    @Column(name = "OTCO_SCRT_ACQ_AMT")
    private String otcoScrtAcqAmt;

    @Column(name = "OTDR_ABNC_NOPE_CNT")
    private String otdrAbncNopeCnt;

    @Column(name = "OTDR_ATND_NOPE_CNT")
    private String otdrAtndNopeCnt;

    @Column(name = "OTSH_FRM_ISSU_PRIC_SCH_DT")
    private String otshFrmIssuPricSchDt;

    @Column(name = "OTSH_ISSU_FRM_PRIC")
    private int otshIssuFrmPric;

    @Column(name = "OTSH_ISSU_SCH_PRIC")
    private int otshIssuSchPric;

    @Column(name = "OTSH_NST_ALCT_STCK_CNT")
    private Double otshNstAlctStckCnt;

    @Column(name = "OTSH_NST_CNT", length = 4000)
    private String otshNstCnt;

    @Column(name = "PBSB_SBSC_SCH_ED_DT")
    private String pbsbSbscSchEdDt;

    @Column(name = "PBSB_SBSC_SCH_STTG_DT")
    private String pbsbSbscSchSttgDt;

    @Column(name = "PFT_DVDN_CTT")
    private String pftDvdnCtt;

    @Column(name = "PRMR_CNV_YN")
    private String prmrCnvYn;

    @Column(name = "PRMR_CRTF_LSTG_YN")
    private String prmrCrtfLstgYn;

    @Column(name = "PYMT_SCH_ACTH_INVT_AMT")
    private int pymtSchActhInvtAmt;

    @Column(name = "PYMT_SCH_ACTH_INVT_RTO")
    private String pymtSchActhInvtRto;

    @Column(name = "PYMT_SCH_STCK_CNT")
    private int pymtSchStckCnt;

    @Column(name = "RD3PT_ALCT_ARAS_BSIS_CTT")
    private String rd3ptAlctArasBsisCtt;

    @Column(name = "RGSC_UNLT_STCK_YN")
    private String rgscUnltStckYn;

    @Column(name = "RPRS_SPTD_CMPY_NM")
    private String rprsSptdCmpyNm;

    @Column(name = "RPT_FILE_CTT")
    private String rptFileCtt;

    @Column(name = "SBMS_EXEM_RSN_CTT")
    private String sbmsExemRsnCtt;

    @Column(name = "SCRT_DCLRPT_SBMS_TRGT_YN")
    private String scrtDclrptSbmsTrgtYn;

    @Column(name = "SHCP_PYMT_DT")
    private String shcpPymtDt;

    @Column(name = "STCK_CNVR_CLM_TERM_MNTH_CNT")
    private String stckCnvrClmTermMnthCnt;

    @Column(name = "STCK_CNVR_COND_CTT", length = 4000)
    private String stckCnvrCondCtt;

    @Column(name = "STCK_CTT", length = 4000)
    private String stckCtt;

    @Column(name = "STCK_PAR_PRC")
    private String stckParPrc;

    @Column(name = "STCK_RDPT_AMT")
    private int stckRdptAmt;

    @Column(name = "STCK_RDPT_COND_NM")
    private String stckRdptCondNm;

    @Column(name = "STCK_RDPT_MTH_NM")
    private String stckRdptMthNm;

    @Column(name = "STCK_RDPT_TERM_MNTH_CNT",length = 4000)
    private String stckRdptTermMnthCnt;

    @Column(name = "STCK_VTRG_CTT")
    private String stckVtrgCtt;

    @Column(name = "TRD_MDAT_FIN_INVS_NM")
    private String trdMdatFinInvsNm;

    @Column(name = "WT1Y_RDPT_SCH_YN")
    private String wt1yRdptSchYn;
}
