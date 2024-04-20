/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "thongtinsd")
public class thongtinsd implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTT")
    private int maTT;

    @ManyToOne
    @JoinColumn(name = "maTV")
    private int maTV;

    @ManyToOne
    @JoinColumn(name = "maTB")
    private int maTB;

    @Column(name = "TGVao")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp tgVao;

    @Column(name = "TGMuon")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp tgMuon;

    @Column(name = "TGTra")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp tgTra;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "tgDatCho")
    private Timestamp tgDatCho;

    public thongtinsd() {
    }

    public thongtinsd(int maTT, int maTV, int maTB, Timestamp tgVao, Timestamp tgMuon, Timestamp tgTra, Timestamp tgDatCho) {
        this.maTT = maTT;
        this.maTV = maTV;
        this.maTB = maTB;
        this.tgVao = tgVao;
        this.tgMuon = tgMuon;
        this.tgTra = tgTra;
        this.tgDatCho = tgDatCho;
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaTB() {
        return maTB;
    }

    public void setMaTB(int maTB) {
        this.maTB = maTB;
    }

    public Timestamp getTgVao() {
        return tgVao;
    }

    public void setTgVao(Timestamp tgVao) {
        this.tgVao = tgVao;
    }

    public Timestamp getTgMuon() {
        return tgMuon;
    }

    public void setTgMuon(Timestamp tgMuon) {
        this.tgMuon = tgMuon;
    }

    public Timestamp getTgTra() {
        return tgTra;
    }

    public void setTgTra(Timestamp tgTra) {
        this.tgTra = tgTra;
    }

    public Timestamp getTgDatCho() {
        return tgDatCho;
    }

    public void setTgDatCho(Timestamp tgDatCho) {
        this.tgDatCho = tgDatCho;
    }

}
