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
@Table(name = "BONU_ISSU_DISC_INFO")
public class BonuIssuDiscInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AUDT_CMBR_ATND_YN")
    private String audtCmbrAtndYn;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "BFCI_ONSK_TISU_STCK_CNT")
    private float bfciOnskTisuStckCnt;

    @Column(name = "BFCI_OTSH_TISU_STCK_CNT")
    private float bfciOtshTisuStckCnt;

    @Column(name = "BOD_RSOL_DT")
    private String bodRsolDt;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "IVS_REF_CTT")
    private String ivsRefCtt;

    @Column(name = "NRFS_HNDV_SCH_DT")
    private String nrfsHndvSchDt;

    @Column(name = "NST_ALCT_BAS_DT")
    private String nstAlctBasDt;

    @Column(name = "NST_DVDN_RCK_DT")
    private String nstDvdnRckDt;

    @Column(name = "NST_LSTG_SCH_DT")
    private String nstLstgSchDt;

    @Column(name = "ONSK_NST_ALCT_STCK_CNT")
    private float onskNstAlctStckCnt;

    @Column(name = "ONSK_NST_CNT")
    private int onskNstCnt;

    @Column(name = "OTDR_ABNC_NOPE_CNT")
    private int otdrAbncNopeCnt;

    @Column(name = "OTDR_ATND_NOPE_CNT")
    private int otdrAtndNopeCnt;

    @Column(name = "OTSH_NST_ALCT_STCK_CNT")
    private float otshNstAlctStckCnt;

    @Column(name = "OTSH_NST_CNT")
    private int otshNstCnt;

    @Column(name = "RPT_FILE_CTT")
    private String rptFileCtt;

    @Column(name = "STCK_PAR_PRC")
    private int stckParPrc;
}
