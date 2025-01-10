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
@Table(name = "REVI_PROC_DISC_INFO")
public class ReviProcDiscInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "CORV_APLPN_FNM")
    private String corvAplpnFnm;

    @Column(name = "CORV_CTPN_CTT", length=4000)
    private String corvCtpnCtt;

    @Column(name = "CORV_JURD_CURT_NM")
    private String corvJurdCurtNm;

    @Column(name = "CORV_PROP_DT")
    private String corvPropDt;

    @Column(name = "CORV_PROP_RSN_CTT", length=4000)
    private String corvPropRsnCtt;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "IVS_REF_CTT", length=4000)
    private String ivsRefCtt;

    @Column(name = "RPT_FILE_CTT", length=4000)
    private String rptFileCtt;

}
