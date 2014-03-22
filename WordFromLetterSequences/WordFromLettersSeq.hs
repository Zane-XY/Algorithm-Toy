{-
- Generate possible word from "xdfajrboekfv"
- -}
module WordFromLettersSeq where
import Data.List
import qualified Data.ByteString.Lazy.Char8 as LC
import Control.Applicative
-- |
-- (n k) = (n-1 k-1) + (n-1 k), for 0 < k < n
-- Choose k elements from n elements equals choose k - 1 elements from n - 1 elements plus choose k elements from n - 1 elements.
combinations :: Int -> [a] -> [[a]]
combinations k xs = combinations' (length xs) k xs
  where combinations' n k' l@(y:ys)
          | k' == 0   = [[]]
          | k' >= n   = [l]
          | null l    = []
          | otherwise = map (y :) (combinations' (n - 1) (k' - 1) ys) ++ combinations' (n - 1) k' ys

source :: String
source = "xdfajrboekfv"

consonants :: String
consonants = "xdfjbkfv"

hasTwoVowels :: String -> Bool
hasTwoVowels x = any (not . null . (`intersect` x)) ["ae", "ao", "eo"]

-- remove a,e,o,r from original input
nonWordSeq :: [String]
nonWordSeq = concatMap permutations (combinations 2 consonants)

impossibleWord :: String -> Bool
impossibleWord x = any (`isInfixOf` x) nonWordSeq

possibleCombs :: [String]
possibleCombs = filter hasTwoVowels $ combinations 5 source

possiblePerms :: [LC.ByteString]
possiblePerms = map LC.pack $ filter (not . impossibleWord) (concatMap permutations possibleCombs)

loadDict :: IO [LC.ByteString]
loadDict = do
             c <- LC.readFile "5letterwords.txt"
             return (concat $ LC.words <$> LC.lines c)

main :: IO ()
main = intersect possiblePerms <$> loadDict >>= print
