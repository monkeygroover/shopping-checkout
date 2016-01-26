import scala.collection.immutable.Iterable

/**
  * Created by monkeygroover on 26/01/16.
  */

/***
  * base trait for all implementations that can calculate a total from an input list
  */
trait TotalCalculator {
  val calculateTotal: List[String] => Int
}

/***
  * represents a checkout seeded with particular pricing data
  *
  * @param prices A map containing a price rule for each item type
  */
case class Checkout(prices: Map[String, Pricer]) extends TotalCalculator {
  val calculateTotal = (items: List[String]) => {

    // create a map containing the frequency that each item type occurs in the list
    val itemFrequency: Map[String, Int] = items.groupBy(identity).mapValues(_.size)

    // use the pricing data to find the total spent on each item type
    val subTotals: Iterable[Int] = itemFrequency map { case (item, count) => {
      val pricer = prices.get(item)

      // Note, again I have made the simplifying assumption that if an item is provided that is not in the price map then
      // its price is considered 0, in reality I would use a Validation Either and return an applicative error functionally
      pricer.map { _.priceForItems(count) } getOrElse 0
    }}

    subTotals.sum
  }
}


