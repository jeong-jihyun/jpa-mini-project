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
@Table(name = "BW_RIGH_ISSU_DISC_INFO")
public class BwRighIssuDiscInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AUDPN_ATND_YN")
    private String audpnAtndYn;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "BAS_EXRT")
    private Float basExrt;

    @Column(name = "BOD_RSOL_DT")
    private String bodRsolDt;

    @Column(name = "BOND_ISSU_AREA_NM")
    private String bondIssuAreaNm;

    @Column(name = "BOND_ISSU_DCNT")
    private String bondIssuDcnt;

    @Column(name = "BW_ISSU_MTH_CTT", length = 4000)
    private String bwIssuMthCtt;

    @Column(name = "BWR_EXERT_PRC")
    private Double bwrExertPrc;

    @Column(name = "BWR_EXERT_RTO")
    private String bwrExertRto;

    @Column(name = "BWR_FCVL_AMT")
    private String bwrFcvlAmt;

    @Column(name = "BWR_ISSU_DECS_RMK_CTT", length = 4000)
    private String bwrIssuDecsRmkCtt;

    @Column(name = "BWR_KIND_NM")
    private String bwrKindNm;

    @Column(name = "BWR_SBSC_DT")
    private String bwrSbscDt;

    @Column(name = "BWR_THRY_PRIC")
    private Double bwrThryPric;

    @Column(name = "BWR_THRY_PRIC_MDEL_NM")
    private String bwrThryPricMdelNm;

    @Column(name = "COR_RSN_CTT", length = 4000)
    private String corRsnCtt;

    @Column(name = "CPBD_EXPR_DT")
    private String cpbdExprDt;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "DSSL_CNPT_MAX_STHD_RLT_NM",length = 4000)
    private String dsslCnptMaxSthdRltNm;

    @Column(name = "DSSL_SCH_DT")
    private String dsslSchDt;

    @Column(name = "ETC_CPTL_AMT")
    private String etcCptlAmt;

    @Column(name = "EXERT_PRIC_ADJ_CTT", length = 4000)
    private String exertPricAdjCtt;

    @Column(name = "EXERT_PRIC_DECS_MTH_NM", length = 4000)
    private String exertPricDecsMthNm;

    @Column(name = "EXPR_INRT")
    private double exprInrt;

    @Column(name = "FCTFND_AT")
    private String fctfndAt;

    @Column(name = "GRN_INST_NM")
    private String grnInstNm;

    @Column(name = "HCFH_PLAN_CTT", length = 4000)
    private String hcfhPlanCtt;

    @Column(name = "IMAR_TR_CMTE_DCL_TRGT_YN")
    private String imarTrCmteDclTrgtYn;

    @Column(name = "INT_PAY_MTH_NM", length = 4000)
    private String intPayMthNm;

    @Column(name = "ISSU_STCK_KIND_NM")
    private String issuStckKindNm;

    @Column(name = "ISSU_STCK_RTO")
    private String issuStckRto;

    @Column(name = "IVS_REF_CTT", length = 4000)
    private String ivsRefCtt;

    @Column(name = "MAX_STHD_RLT_NM", length = 4000)
    private String maxSthdRltNm;

    @Column(name = "MGM_CPT_AT")
    private String mgmCptAt;

    @Column(name = "MRAC_CTT", length = 4000)
    private String mracCtt;

    @Column(name = "NST_UNDT_SCRT_DSSL_AMT")
    private String nstUndtScrtDsslAmt;

    @Column(name = "OPTN_CTT", length = 4000)
    private String optnCtt;

    @Column(name = "OTCO_SCRT_ACQ_AMT")
    private String otcoScrtAcqAmt;

    @Column(name = "OTDR_ABNC_NOPE_CNT")
    private Long otdrAbncNopeCnt;

    @Column(name = "OTDR_ATND_NOPE_CNT")
    private Long otdrAtndNopeCnt;

    @Column(name = "OVSE_CPBD_TFCVL_AMT")
    private String ovseCpbdTfcvlAmt;

    @Column(name = "OVSE_ISSU_LNB_TR_CTT", length = 4000)
    private String ovseIssuLnbTrCtt;

    @Column(name = "OVSE_LSTG_MRKT_NM")
    private String ovseLstgMrktNm;

    @Column(name = "PAMT_RDPT_MTH_NM", length = 4000)
    private String pamtRdptMthNm;

    @Column(name = "PRMR_DSSL_CNPT_NM", length = 4000)
    private String prmrDsslCnptNm;

    @Column(name = "PRMR_ISSU_STCK_CNT")
    private int prmrIssuStckCnt;

    @Column(name = "PRMR_SCRT_TDSL_AMT")
    private String prmrScrtTdslAmt;

    @Column(name = "PRMR_SPRT_YN")
    private String prmrSprtYn;

    @Column(name = "PRMR_VALU_CTT", length = 4000)
    private String prmrValuCtt;

    @Column(name = "RGT_EXERT_ED_DT")
    private String rgtExertEdDt;

    @Column(name = "RGT_EXERT_STTG_DT")
    private String rgtExertSttgDt;

    @Column(name = "RPRS_SPTD_CMPY_NM")
    private String rprsSptdCmpyNm;

    @Column(name = "RPT_FILE_CTT", length = 4000)
    private String rptFileCtt;

    @Column(name = "SBMS_EXEM_RSN_CTT", length = 4000)
    private String sbmsExemRsnCtt;

    @Column(name = "SCRT_DCLRPT_SBMS_TRGT_YN")
    private String scrtDclrptSbmsTrgtYn;

    @Column(name = "SHCP_PYMT_DT")
    private String shcpPymtDt;

    @Column(name = "SHCP_PYMT_MTH_NM", length = 4000)
    private String shcpPymtMthNm;

    @Column(name = "SPCF_ISSU_TRPR_NM", length = 4000)
    private String spcfIssuTrprNm;

    @Column(name = "SRFC_INRT")
    private double srfcInrt;

    @Column(name = "TCPB_FCVL_AMT")
    private String tcpbFcvlAmt;

    @Column(name = "TISU_FCVL_AMT", length = 4000)
    private String tisuFcvlAmt;

}
