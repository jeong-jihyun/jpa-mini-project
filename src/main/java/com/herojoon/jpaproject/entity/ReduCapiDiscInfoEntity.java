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
@Table(name = "REDU_CAPI_DISC_INFO")
public class ReduCapiDiscInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AFRC_CPTL_AMT")
    private String afrcCptlAmt;

    @Column(name = "AFRC_ISSU_ONSK_CNT")
    private int afrcIssuOnskCnt;

    @Column(name = "AFRC_ISSU_OTSH_CNT")
    private int afrcIssuOtshCnt;

    @Column(name = "ARAS_BSIS_CTT", length = 4000)
    private String arasBsisCtt;

    @Column(name = "AUDT_CMBR_ATND_YN")
    private String audtCmbrAtndYn;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "BFRC_CPTL_AMT")
    private String bfrcCptlAmt;

    @Column(name = "BFRC_ISSU_ONSK_CNT")
    private int bfrcIssuOnskCnt;

    @Column(name = "BFRC_ISSU_OTSH_CNT")
    private int bfrcIssuOtshCnt;

    @Column(name = "BOD_RSOL_DT")
    private String bodRsolDt;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "DSCE_SBMS_ED_DT")
    private String dsceSbmsEdDt;

    @Column(name = "DSCE_SBMS_STTG_DT")
    private String dsceSbmsSttgDt;

    @Column(name = "ETC_CTT", length = 4000)
    private String etcCtt;

    @Column(name = "GMSH_SCH_DT")
    private String gmshSchDt;

    @Column(name = "IMAR_TR_CMTE_DCL_TRGT_YN")
    private String imarTrCmteDclTrgtYn;

    @Column(name = "IVS_REF_CTT", length = 4000)
    private String ivsRefCtt;

    @Column(name = "NRFS_HNDV_PLC_NM")
    private String nrfsHndvPlcNm;

    @Column(name = "NRFS_HNDV_SCH_DT")
    private String nrfsHndvSchDt;

    @Column(name = "NST_LSTG_SCH_DT")
    private String nstLstgSchDt;

    @Column(name = "ONSK_RDCP_RTO")
    private Double onskRdcpRto;

    @Column(name = "ORFS_SBMS_TERM_MNTH_CNT")
    private String orfsSbmsTermMnthCnt;

    @Column(name = "OTDR_ABNC_NOPE_CNT")
    private int otdrAbncNopeCnt;

    @Column(name = "OTDR_ATND_NOPE_CNT")
    private int otdrAtndNopeCnt;

    @Column(name = "OTSH_RDCP_RTO")
    private Double otshRdcpRto;

    @Column(name = "RDCP_BAS_DT")
    private String rdcpBasDt;

    @Column(name = "RDCP_MTH_NM", length = 4000)
    private String rdcpMthNm;

    @Column(name = "RDCP_ONSK_CNT")
    private String rdcpOnskCnt;

    @Column(name = "RDCP_OTSH_CNT")
    private String rdcpOtshCnt;

    @Column(name = "RDCP_RSN_CTT", length = 4000)
    private String rdcpRsnCtt;

    @Column(name = "RPT_FILE_CTT", length = 4000)
    private String rptFileCtt;

    @Column(name = "STCK_CTT", length = 4000)
    private String stckCtt;

    @Column(name = "STCK_PAR_PRC")
    private String stckParPrc;

    @Column(name = "TR_STOP_SCH_TERM_MNTH_CNT")
    private String trStopSchTermMnthCnt;

    @Column(name = "TRSNM_STOP_TERM_MNTH_CNT")
    private String trsnmStopTermMnthCnt;

}
