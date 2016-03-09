/**
  * Created by monkeygroover on 26/01/16.
  */

import Pricers.PriceFn

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
  * @param pricers A map containing a price rule for each item type
  */
case class Checkout(pricers: Map[String, PriceFn]) extends TotalCalculator {
  val calculateTotal = (items: List[String]) => {

    val subTotals: Iterable[ValidationNel[String, Int]] = for {
      (item, count) <- items.foldMap(item => Map(item -> 1)) // creates a map containing the count for each item in the list
    } yield pricers.get(item).fold(ifEmpty = s"no pricer for $item".failureNel[Int]) { _(count).success }

    subTotals.reduce(_ |+| _)
  }
}


