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

  val PricingData = Map(
    "Apple" -> UnitPricer(60),
    "Orange" -> UnitPricer(25)
  )

  "Checkout" should "return expected result provided basic pricing information and single Apple" in {
    val Items = "Apple" :: Nil

    Checkout(PricingData).calculateTotal(Items) shouldBe 60.success
  }

  "Checkout" should "return expected result provided basic pricing information and single Orange" in {
    val Items = "Orange" :: Nil

    Checkout(PricingData).calculateTotal(Items) shouldBe 25.success
  }

  "Checkout" should "return expected result provided basic pricing information and a list of items" in {
    val Items = "Apple" :: "Apple" :: "Orange" :: "Apple" :: Nil

    Checkout(PricingData).calculateTotal(Items) shouldBe 205.success
  }

  val SpecialOfferPricingData = Map(
    "Apple" -> BuyOneGetOneFree(60),
    "Orange" -> ThreeForTwo(25)
  )

  "Checkout" should "return expected result provided special offer pricing information and single Apple" in {
    val Items = "Apple" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 60.success
  }

  "Checkout" should "return expected result provided special offer pricing information and two Apples" in {
    val Items = "Apple" :: "Apple" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 60.success
  }

  "Checkout" should "return expected result provided special offer pricing information and three Apples" in {
    val Items = "Apple" :: "Apple" :: "Apple" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 120.success
  }

  "Checkout" should "return expected result provided special offer pricing information and single Orange" in {
    val Items = "Orange" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 25.success
  }

  "Checkout" should "return expected result provided special offer pricing information and two Oranges" in {
    val Items = "Orange" :: "Orange" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 50.success
  }

  "Checkout" should "return expected result provided special offer pricing information and three Oranges" in {
    val Items = "Orange" :: "Orange" :: "Orange" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 50.success
  }

  "Checkout" should "return expected result provided special offer pricing information and four Oranges" in {
    val Items = "Orange" :: "Orange" :: "Orange" :: "Orange" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 75.success
  }

  "Checkout" should "return expected result provided special offer pricing information and a list of items" in {
    val Items = "Apple" :: "Apple" :: "Orange" :: "Apple" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 145.success
  }

  "Checkout" should "return a list of errors if passed bad data in the list" in {
    val Items = "Banana" :: "Peach" :: "Pear" :: Nil

    Checkout(Map.empty).calculateTotal(Items) should (haveFailure("no price for Banana") and haveFailure("no price for Peach") and haveFailure("no price for Pear"))
  }
}
