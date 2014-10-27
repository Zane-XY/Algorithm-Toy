module FizzBuzzWhizzTest where

import           Control.Monad.State
import           Data.List
import           FizzBuzzWhizz
import           Test.QuickCheck
import           Test.Tasty
import           Test.Tasty.QuickCheck

fizzBuzzWhizzProps :: TestTree
fizzBuzzWhizzProps = testGroup "Properties" [qcProps]

qcProps :: TestTree
qcProps = testGroup "(checked by QuickCheck)"
  [testProperty "random random [1..9] should follow FizzBuzzWhizz rules" $ verbose prop_fbw]

intTuple4EvenDistribution :: Gen (Int, Int, Int, Int)
intTuple4EvenDistribution
  = do a <- choose (1, 3)
       b <- choose (4, 6)
       c <- choose (7, 9)
       d <- choose (1, 100)
       return (a, b, c, d)

intTuple4DupsDistribution :: Gen (Int, Int, Int, Int)
intTuple4DupsDistribution
  = do a <- choose (1, 9)
       b <- choose (1, 9)
       c <- choose (1, 9)
       d <- choose (1, 100)
       return (a, b, c, d)

intTuple4 :: Gen (Int, Int, Int, Int)
intTuple4
  = oneof [intTuple4EvenDistribution, intTuple4DupsDistribution]

fizzBuzzWhizz' :: (Integral a, Show a) => (a, a, a) -> a -> String
fizzBuzzWhizz' (x, y, z) n
  = execState
      (do when (n `mod` z == 0) $ modify ("Whizz" ++)
          when (n `mod` y == 0) $ modify ("Buzz" ++)
          when (n `mod` x == 0) $ modify ("Fizz" ++)
          when (show x `isInfixOf` show n) $ modify (const "Fizz")
          modify (\ s -> if null s then show n else s))
      []

prop_fbw :: Property
prop_fbw
  = forAll intTuple4 $
      \(x,y,z,n) -> fizzBuzzWhizz' (x,y,z) n  == fizzBuzzWhizz (x, y, z) n
