/**
  * Created by monkeygroover on 26/01/16.
  */

import org.scalatest.{FlatSpec, Matchers}

class CheckoutSpec
  extends FlatSpec
    with Matchers {

  val PricingData = Map(
    "Apple" -> 60,
    "Orange" -> 25
  )

  "Checkout" should "return expected result provided pricing information and single Apple" in {
    val Items = "Apple" :: Nil

    Checkout(PricingData).calculateTotal(Items) shouldBe 60
  }

  "Checkout" should "return expected result provided pricing information and single Orange" in {
    val Items = "Orange" :: Nil

    Checkout(PricingData).calculateTotal(Items) shouldBe 25
  }

  "Checkout" should "return expected result provided pricing information and a list of items" in {
    val Items = "Apple" :: "Apple" :: "Orange" :: "Apple" :: Nil

    Checkout(PricingData).calculateTotal(Items) shouldBe 205
  }
}
