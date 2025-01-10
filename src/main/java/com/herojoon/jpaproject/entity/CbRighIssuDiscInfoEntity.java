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
@Table(name = "CB_RIGH_ISSU_DISC_INFO")
public class CbRighIssuDiscInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ACTN_AUDPN_NM")
    private String actnAudpnNm;

    @Column(name = "ACTN_YEAR")
    private String actnYear;

    @Column(name = "AUDPN_ATND_YN")
    private String audpnAtndYn;

    @Column(name = "AUDT_OPNN_CTT", length=4000)
    private String audtOpnnCtt;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "BAS_EXRT")
    private String basExrt;

    @Column(name = "BOD_RSOL_DT")
    private String bodRsolDt;

    @Column(name = "BOND_ISSU_AREA_NM")
    private String bondIssuAreaNm;

    @Column(name = "BOND_ISSU_DCNT")
    private String bondIssuDcnt;

    @Column(name = "BOND_KIND_NM")
    private String bondKindNm;

    @Column(name = "BZWR_EXOR_FNM")
    private String bzwrExorFnm;

    @Column(name = "BZWR_EXOR_SHR_RAT")
    private String bzwrExorShrRat;

    @Column(name = "CB_CLM_ED_DT")
    private String cbClmEdDt;

    @Column(name = "CB_CLM_STTG_DT")
    private String cbClmSttgDt;

    @Column(name = "CB_CNVR_PRC")
    private int cbCnvrPrc;

    @Column(name = "CB_ISSU_MTH_CTT", length=4000)
    private String cbIssuMthCtt;

    @Column(name = "CB_PRIC_DECS_MTH_CTT", length=4000)
    private String cbPricDecsMthCtt;

    @Column(name = "CBR_SBSC_DT")
    private String cbrSbscDt;

    @Column(name = "CEO_FNM")
    private String ceoFnm;

    @Column(name = "CEO_SHR_RAT")
    private String ceoShrRat;

    @Column(name = "COR_RSN_CTT", length=4000)
    private String corRsnCtt;

    @Column(name = "CORP_TAST_AMT")
    private String corpTastAmt;

    @Column(name = "CORP_TDBT_AMT")
    private String corpTdbtAmt;

    @Column(name = "CPBD_CNVR_RTO")
    private String cpbdCnvrRto;

    @Column(name = "CPBD_CNVR_STCK_CNT")
    private String cpbdCnvrStckCnt;

    @Column(name = "CPBD_CNVR_STCK_RTO")
    private String cpbdCnvrStckRto;

    @Column(name = "CPBD_EXPR_DT")
    private String cpbdExprDt;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "CRTM_NPAL")
    private String crtmNpal;

    @Column(name = "CVPRC_ADJ_CTT")
    @Lob
    private String cvprcAdjCtt;

    @Column(name = "DBET_CNVR_STCK_KIND_NM")
    private String dbetCnvrStckKindNm;

    @Column(name = "ENP_SALE_AMT")
    private String enpSaleAmt;

    @Column(name = "ETC_CPTL_AMT")
    private String etcCptlAmt;

    @Column(name = "EXPR_INRT")
    private String exprInrt;

    @Column(name = "FCTFND_AT")
    private String fctfndAt;

    @Column(name = "FNCO_CPTL_AMT")
    private String fncoCptlAmt;

    @Column(name = "FNCO_TCPT_AMT")
    private String fncoTcptAmt;

    @Column(name = "FNPN_CNT")
    private String fnpnCnt;

    @Column(name = "GRN_INST_NM")
    private String grnInstNm;

    @Column(name = "HCFH_PLAN_CTT", length=4000)
    private String hcfhPlanCtt;

    @Column(name = "IMAR_TR_CMTE_DCL_TRGT_YN")
    private String imarTrCmteDclTrgtYn;

    @Column(name = "INT_PAY_MTH_NM")
    private String intPayMthNm;

    @Column(name = "IVS_REF_CTT", length=4000)
    private String ivsRefCtt;

    @Column(name = "MAX_STHD_FNM")
    private String maxSthdFnm;

    @Column(name = "MAX_STHD_RLT_NM")
    private String maxSthdRltNm;

    @Column(name = "MAX_STHD_SHR_RAT")
    private String maxSthdShrRat;

    @Column(name = "MGM_CPT_AT")
    private String mgmCptAt;

    @Column(name = "MRAC_CTT", length=4000)
    private String mracCtt;

    @Column(name = "OPTN_CTT", length=4000)
    private String optnCtt;

    @Column(name = "OTCO_SCRT_ACQ_AMT")
    private Double otcoScrtAcqAmt;

    @Column(name = "OTDR_ABNC_NOPE_CNT")
    private Double otdrAbncNopeCnt;

    @Column(name = "OTDR_ATND_NOPE_CNT")
    private Double otdrAtndNopeCnt;

    @Column(name = "OVSE_CPBD_TFCVL_AMT")
    private Double ovseCpbdTfcvlAmt;

    @Column(name = "OVSE_ISSU_LNB_TR_CTT", length=4000)
    private String ovseIssuLnbTrCtt;

    @Column(name = "OVSE_LSTG_MRKT_NM")
    private String ovseLstgMrktNm;

    @Column(name = "PAMT_RDPT_MTH_NM")
    private String pamtRdptMthNm;

    @Column(name = "RGT_EXERT_CORP_NM")
    private String rgtExertCorpNm;

    @Column(name = "RPRS_SPTD_CMPY_NM")
    private String rprsSptdCmpyNm;

    @Column(name = "RPT_FILE_CTT", length=4000)
    private String rptFileCtt;

    @Column(name = "SBMS_EXEM_RSN_CTT", length=4000)
    private String sbmsExemRsnCtt;

    @Column(name = "SCRT_DCLRPT_SBMS_TRGT_YN")
    private String scrtDclrptSbmsTrgtYn;

    @Column(name = "SHCP_PYMT_DT")
    private String shcpPymtDt;

    @Column(name = "SPCF_ISSU_TRPR_NM")
    private String spcfIssuTrprNm;

    @Column(name = "SRFC_INRT")
    private String srfcInrt;

    @Column(name = "STAC_TERM_MNTH_CNT")
    private String stacTermMnthCnt;

    @Column(name = "TCPB_FCVL_AMT")
    private String tcpbFcvlAmt;

    @Column(name = "TISU_FCVL_AMT")
    private String tisuFcvlAmt;

}
