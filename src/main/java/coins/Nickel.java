package coins;

/**
* Nickel Coin Subclass
* @author Lisa Miller
* @since 9/9/2017
*/
public class Nickel extends Coin {

  public Nickel(){
    super(.05,"nickel", "Silver");
  }
  //required by Coin abstract class
  public String getBack(){
    return "Monticello";
  }
}
