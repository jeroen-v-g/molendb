package nl.molens.model.bs;

import org.springframework.beans.BeanUtils;

import nl.molens.model.Molen;

public class MolenBS{

  private int molenId;
  private String naam;
  private Integer bouwjaar;
  private String type;
  private String kenmerken;
  private String functie;
  private String adres;
  private String molenaar;
  private String eigenaar;
  private String plaats;
  private String website;
  private Integer fotoWidth;
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
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
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
  public void cloneFromMolen(Molen molen)
  {
    BeanUtils.copyProperties(molen, this);
  }
}
