# Shopping Checkout

Submission for a coding exercise, some notes:

Step 1 - very simple initial checkout system. 

 assumes that the inputs are a list of strings, currently assumes any provided input with no pricing data has a price of zero rather than adding a validation/error (I would usually use scalaz Validation for this, but could also be achieved with just an Either and hand coding the reduce method to allow for it) 
 pricing data is not hard coded to the totalCalculator but can be parameterised for per instance of the Checkout object
 
 Step 2 - special offer pricing extension 
 
  adds the ability to provide buy-one-get one free and three-for-two rules (in addition to the original simple unit pricing) pricing rules are extensible if any more are ever needed then they just need to implement the base "Pricer" trait


Other commits - Validation 
  added a quick illustration of how Scalaz Validation could be used to report validation errors rather than just skipping items with no price data.


unit tests use scalatest
