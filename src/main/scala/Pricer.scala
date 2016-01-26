/**
  * Created by monkeygroover on 26/01/16.
  */
trait Pricer {
  def priceForItems(itemCount: Int) : Int
}

case class UnitPricer(unitPrice: Int) extends Pricer {
  def priceForItems(itemCount: Int) = itemCount * unitPrice
}

case class BuyOneGetOneFree(unitPrice: Int) extends Pricer {
  def priceForItems(itemCount: Int) = (itemCount / 2 + itemCount % 2) * unitPrice
}

case class ThreeForTwo(unitPrice: Int) extends Pricer {
  def priceForItems(itemCount: Int) = ((itemCount / 3) * 2 * unitPrice) + (itemCount % 3 * unitPrice)
}
