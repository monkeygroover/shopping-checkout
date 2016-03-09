/**
  * Created by monkeygroover on 26/01/16.
  */

import org.scalatest.{FlatSpec, Matchers}
import org.typelevel.scalatest.ValidationMatchers
import scalaz._
import Scalaz._

class CheckoutSpec
  extends FlatSpec
    with Matchers
    with ValidationMatchers {

  import Pricers._

  val PricingData = Map(
    "Apple" -> unitPricer(60),
    "Orange" -> unitPricer(25)
  )

  "Checkout" should "return expected result provided basic pricing information and single Apple" in {
    val Items = List("Apple")

    Checkout(PricingData).calculateTotal(Items) shouldBe 60.success
  }

  "Checkout" should "return expected result provided basic pricing information and single Orange" in {
    val Items = List("Orange")

    Checkout(PricingData).calculateTotal(Items) shouldBe 25.success
  }

  "Checkout" should "return expected result provided basic pricing information and a list of items" in {
    val Items = List("Apple", "Apple", "Orange", "Apple")

    Checkout(PricingData).calculateTotal(Items) shouldBe 205.success
  }

  val SpecialOfferPricingData = Map(
    "Apple" -> buyOneGetOneFree(60),
    "Orange" -> threeForTwo(25)
  )

  "Checkout" should "return expected result provided special offer pricing information and single Apple" in {
    val Items = List("Apple")

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 60.success
  }

  "Checkout" should "return expected result provided special offer pricing information and two Apples" in {
    val Items = List("Apple", "Apple")

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 60.success
  }

  "Checkout" should "return expected result provided special offer pricing information and three Apples" in {
    val Items = List("Apple", "Apple", "Apple")

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 120.success
  }

  "Checkout" should "return expected result provided special offer pricing information and single Orange" in {
    val Items = List("Orange")

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 25.success
  }

  "Checkout" should "return expected result provided special offer pricing information and two Oranges" in {
    val Items = List("Orange", "Orange")

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 50.success
  }

  "Checkout" should "return expected result provided special offer pricing information and three Oranges" in {
    val Items = List("Orange", "Orange", "Orange")

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 50.success
  }

  "Checkout" should "return expected result provided special offer pricing information and four Oranges" in {
    val Items = List("Orange", "Orange", "Orange", "Orange" )

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 75.success
  }

  "Checkout" should "return expected result provided special offer pricing information and a list of items" in {
    val Items = List("Apple", "Apple", "Orange", "Apple")

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 145.success
  }

  "Checkout" should "return a list of errors if passed bad data in the list" in {
    val Items = List("Banana", "Peach", "Pear")

    Checkout(Map.empty).calculateTotal(Items) should (haveFailure("no pricer for Banana") and haveFailure("no pricer for Peach") and haveFailure("no pricer for Pear"))
  }
}
