package coins;
/**
* DollarCoin Coin Subclass
* @author Lisa Miller
* @since 9/9/2017
*/
public class DollarCoin extends Coin {

  public DollarCoin(){
    super(1.0,"dollar", "gold");
  }
  
    //required by Coin abstract class
  public String getBack(){
    return "eagle";
  }
}
