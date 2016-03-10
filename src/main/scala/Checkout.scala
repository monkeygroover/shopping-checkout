/**
  * Created by monkeygroover on 26/01/16.
  */

import scalaz._,Scalaz._

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
case class Checkout(pricers: Map[String, (Int) => Int]) extends TotalCalculator {
  val calculateTotal = (items: List[String]) => {

    // create a map containing the count for each item in the list
    val itemFrequencies: Map[String, Int] = items.foldMap(item => Map(item -> 1))

    // calculate the subtotal for each item by applying the rule for that item
    val calculateSubtotal: ((String, Int)) => ValidationNel[String, Int] =
      (itemData: (String, Int)) => pricers.get(itemData._1).fold(ifEmpty = s"no pricer for ${itemData._1}".failureNel[Int]) {
        pricer => pricer(itemData._2).success
      }

    itemFrequencies map calculateSubtotal reduce {
      _ |+| _
    }
  }
}

object Pricers {
  val unitPricer = (unitPrice: Int) => (itemCount: Int) => itemCount * unitPrice

  val buyOneGetOneFree = (unitPrice: Int) => (itemCount: Int) => (itemCount / 2 + itemCount % 2) * unitPrice

  val threeForTwo = (unitPrice: Int) => (itemCount: Int) => ((itemCount / 3) * 2 * unitPrice) + (itemCount % 3 * unitPrice)
}


