/**
  * Created by monkeygroover on 26/01/16.
  */

object Pricers {

  type PriceFn = (Int) => Int

  def unitPricer(unitPrice: Int) = (itemCount: Int) => itemCount * unitPrice

  def buyOneGetOneFree(unitPrice: Int) = (itemCount: Int) => (itemCount / 2 + itemCount % 2) * unitPrice

  def threeForTwo(unitPrice: Int) = (itemCount: Int) => ((itemCount / 3) * 2 * unitPrice) + (itemCount % 3 * unitPrice)
}