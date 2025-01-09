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
@Table(name = "ASSE_TRAN_PUT_BACK_OPTI_DISC_INFO")
public class AsseTranPutBackOptiDiscInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "ENP_CORP_NM")
    private String enpCorpNm;

    @Column(name = "RPT_FILE_CTT")
    private String rptFileCtt;

}
