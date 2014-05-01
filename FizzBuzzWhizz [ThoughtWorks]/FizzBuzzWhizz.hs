module FizzBuzzWhizz where

import           Data.List

main :: IO ()
main = mapM_ (print . fizzBuzzWhizz (3,5,7)) [1 .. 100]

fizzBuzzWhizz :: (Int, Int, Int) -> Int -> String
fizzBuzzWhizz (a, b, c) i = snd $ fizz' a . to (a, "Fizz") . to (b, "Buzz") . to (c, "Whizz") $ (i, [])
  where fizz' d (i', r)
          | show d `isInfixOf` show i'  = (i', "Fizz")
          | null r                      = (i', show i')
          | otherwise                   = (i', r)
        to (d, p) (i', r) | d == 0          = (i', r)
                          | i' `mod` d == 0 = (i', p ++ r)
                          | otherwise       = (i', r)
