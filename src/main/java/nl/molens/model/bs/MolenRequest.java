package nl.molens.model.bs;

import nl.molens.model.Molen;

public class MolenRequest {

  private Molen molen ;
  private String requestId;

  public Molen getMolen() {
    return molen;
  }
  public void setMolen(Molen molen) {
    this.molen = molen;
  }
  public String getRequestId() {
    return requestId;
  }
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

}
