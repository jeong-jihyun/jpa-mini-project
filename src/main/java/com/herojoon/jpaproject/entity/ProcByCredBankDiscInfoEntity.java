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
@Table(name = "PROC_BY_CRED_BANK_DISC_INFO")
public class ProcByCredBankDiscInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "BOND_MNG_INST_NM", length=4000)
    private String bondMngInstNm;

    @Column(name = "CRBK_MNG_CTT", length=4000)
    private String crbkMngCtt;

    @Column(name = "CRBK_MNG_RSN_CTT", length=4000)
    private String crbkMngRsnCtt;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "IVS_REF_CTT", length=4000)
    private String ivsRefCtt;

    @Column(name = "LWST_AFRM_DT")
    private String lwstAfrmDt;

    @Column(name = "MNG_OPNG_DECS_DT")
    private String mngOpngDecsDt;

    @Column(name = "MNG_TERM_MNTH_CNT")
    private String mngTermMnthCnt;

    @Column(name = "RPT_FILE_CTT", length=4000)
    private String rptFileCtt;

}
