/**
  * Created by monkeygroover on 26/01/16.
  */

import scala.collection.immutable.Iterable
import scalaz._
import Scalaz._

/***
  * base trait for all implementations that can calculate a total from an input list
  */
trait TotalCalculator {
  val calculateTotal: List[String] => ValidationNel[String, Int]
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
    val subTotals: Iterable[ValidationNel[String, Int]] = itemFrequency map { case (item, count) => {
      val pricer = prices.get(item)

      pricer.map { _.priceForItems(count).success } getOrElse s"no price for $item".failureNel
    }}

    subTotals.reduce(_ |+| _)
  }
}


