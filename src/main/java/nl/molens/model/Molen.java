package nl.molens.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.Field;

import org.hibernate.search.annotations.Indexed;

@Entity
@NamedQuery(name = "Molens.scrollMolens", query = "from Molen m order by m.molenId desc")
@Indexed
@DynamicUpdate
@Table(name = "molens")
public class Molen {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "molenid")
  private int molenId;

  @Field
  @Column
  @Size(min = 4, max = 200)
  private String naam;
  @Field
  @Column
  private Integer bouwjaar;
  @Field
  @Column
  @NotNull
  private String type;
  @Field
  @Column
  @NotNull
  private String kenmerken;
  @Field
  @Column
  private String functie;
  @Field
  @Column
  private String adres;
  @Field
  @Column
  private String molenaar;
  @Field
  @Column
  private String eigenaar;
  @Field
  @Column
  @Size(min = 4, max = 200)
  private String plaats;
  @Field
  @Column
  private String website;
  @Column
  private Blob foto;
  @Column(name = "fotocontenttype")
  private String fotoContentType;
  @Column(name = "fotowidth")
  private Integer fotoWidth;
  @Column(name = "fotoheight")
  private Integer fotoHeight;

  public int getMolenId() {
    return molenId;
  }

  public void setMolenId(int molenId) {
    this.molenId = molenId;
  }

  public String getNaam() {
    return naam;
  }

  public void setNaam(String naam) {
    this.naam = naam;
  }

  public Integer getBouwjaar() {
    return bouwjaar;
  }

  public void setBouwjaar(Integer bouwjaar) {
    this.bouwjaar = bouwjaar;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getKenmerken() {
    return kenmerken;
  }

  public void setKenmerken(String kenmerken) {
    this.kenmerken = kenmerken;
  }

  public String getFunctie() {
    return functie;
  }

  public void setFunctie(String functie) {
    this.functie = functie;
  }

  public String getAdres() {
    return adres;
  }

  public void setAdres(String adres) {
    this.adres = adres;
  }

  public String getMolenaar() {
    return molenaar;
  }

  public void setMolenaar(String molenaar) {
    this.molenaar = molenaar;
  }

  public String getEigenaar() {
    return eigenaar;
  }

  public void setEigenaar(String eigenaar) {
    this.eigenaar = eigenaar;
  }

  public String getPlaats() {
    return plaats;
  }

  public void setPlaats(String plaats) {
    this.plaats = plaats;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public Blob getFoto() {
    return foto;
  }

  public void setFoto(Blob foto) {
    this.foto = foto;
  }

  public String getFotoContentType() {
    return fotoContentType;
  }

  public void setFotoContentType(String fotoContentType) {
    this.fotoContentType = fotoContentType;
  }

  public Integer getFotoWidth() {
    return fotoWidth;
  }

  public void setFotoWidth(Integer fotoWidth) {
    this.fotoWidth = fotoWidth;
  }

  public Integer getFotoHeight() {
    return fotoHeight;
  }

  public void setFotoHeight(Integer fotoHeight) {
    this.fotoHeight = fotoHeight;
  }

}
