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
@Table(name = "GENE_MEET_STOC_PUBL_NOTI_DISC_INFO")
public class GeneMeetStocPublNotiDiscInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BAS_DT")
    private String basDt;

    @Column(name = "CORP_NM")
    private String corpNm;

    @Column(name = "CRNO")
    private String crno;

    @Column(name = "RPT_FILE_CTT")
    private String rptFileCtt;

}
