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
  * @param prices A map containing the price of each item type
  */
case class Checkout(prices: Map[String, Int]) extends TotalCalculator {
  val calculateTotal = (items: List[String]) => {

    // create a map containing the frequency that each item type occurs in the list
    val itemFrequency: Map[String, Int] = items.groupBy(identity).mapValues(_.size)

    // use the pricing data to find the total spent on each item type
    // Note, have made the simplifying assumption that if an item is provided that is not in the price map then
    // its price is considered 0, in reality I would use a Validation Either and return an applicative error functionally
    val subTotals: Iterable[Int] = itemFrequency map { case (item, count) => prices.getOrElse(item, 0) * count }

    subTotals.sum
  }
}
