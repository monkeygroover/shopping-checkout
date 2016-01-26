/**
  * Created by monkeygroover on 26/01/16.
  */

import org.scalatest.{FlatSpec, Matchers}

class CheckoutSpec
  extends FlatSpec
    with Matchers {

  val PricingData = Map(
    "Apple" -> UnitPricer(60),
    "Orange" -> UnitPricer(25)
  )

  "Checkout" should "return expected result provided basic pricing information and single Apple" in {
    val Items = "Apple" :: Nil

    Checkout(PricingData).calculateTotal(Items) shouldBe 60
  }

  "Checkout" should "return expected result provided basic pricing information and single Orange" in {
    val Items = "Orange" :: Nil

    Checkout(PricingData).calculateTotal(Items) shouldBe 25
  }

  "Checkout" should "return expected result provided basic pricing information and a list of items" in {
    val Items = "Apple" :: "Apple" :: "Orange" :: "Apple" :: Nil

    Checkout(PricingData).calculateTotal(Items) shouldBe 205
  }

  val SpecialOfferPricingData = Map(
    "Apple" -> BuyOneGetOneFree(60),
    "Orange" -> ThreeForTwo(25)
  )

  "Checkout" should "return expected result provided special offer pricing information and single Apple" in {
    val Items = "Apple" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 60
  }

  "Checkout" should "return expected result provided special offer pricing information and two Apples" in {
    val Items = "Apple" :: "Apple" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 60
  }

  "Checkout" should "return expected result provided special offer pricing information and three Apples" in {
    val Items = "Apple" :: "Apple" :: "Apple" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 120
  }

  "Checkout" should "return expected result provided special offer pricing information and single Orange" in {
    val Items = "Orange" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 25
  }

  "Checkout" should "return expected result provided special offer pricing information and two Oranges" in {
    val Items = "Orange" :: "Orange" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 50
  }

  "Checkout" should "return expected result provided special offer pricing information and three Oranges" in {
    val Items = "Orange" :: "Orange" :: "Orange" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 50
  }

  "Checkout" should "return expected result provided special offer pricing information and four Oranges" in {
    val Items = "Orange" :: "Orange" :: "Orange" :: "Orange" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 75
  }

  "Checkout" should "return expected result provided special offer pricing information and a list of items" in {
    val Items = "Apple" :: "Apple" :: "Orange" :: "Apple" :: Nil

    Checkout(SpecialOfferPricingData).calculateTotal(Items) shouldBe 145
  }

}
