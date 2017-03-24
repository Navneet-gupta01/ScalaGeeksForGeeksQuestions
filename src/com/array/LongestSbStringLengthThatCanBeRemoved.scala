package com.array

import scala.annotation.tailrec

/**
 * @author: Navneet Gupta
 * @createdOn: 24-Mar-2017
 */

object LongestSbStringLengthThatCanBeRemoved {
  /*
   * http://www.geeksforgeeks.org/length-longest-sub-string-can-make-removed/ 
   * Given a binary string (consists of only 0 and 1). If there is “100” as a sub-string in the string, then we can delete this 
   * sub-string. The task is to find the length of longest sub-string which can be make removed?
   */
  
  /*
   * We can solve this problem using a container like vector in c++ or ArrayList in Java. Below is the algorithm to solve this problem :

      Take a vector arr of pair type. Each element in arr stores two values character and it’s respective index in string.
      Store pair(‘@’,-1) as a base in arr. Take variable maxlen = 0 which stores the final result.
      Now one by one iterate for all characters in string, make pair of characters and it’s respective index and store it in arr. 
      	In parallel also check the condition if after inserting i’th character last three elements of ‘arr’ are making sub-string “100”.
      If sub-string exist then delete it from ‘arr’. Repeat this loop by number of times till you are getting sub-string “100” in arr 
      	and make it null by deleting continuously.
      The difference of indexs of i’th character and index of last element currently present in arr after deletion gives the length 
      	of sub-string that can be make null by continuous deletion of sub-string “100”, update maxlen.
   */
  
  def longestSubstring(str: String) :Int = {
    val list = List(('@',-1))
    val a = Vector[(Char,Int)]()
    str.foldLeft((0,list,0))((a,b) => {
      val newList = (b,a._3)::a._2
      val uList = processList(newList)
      val temp = uList.head._2
      (a._1 max (a._3-temp),uList,a._3+1)
    })._1
  }
  
  @tailrec
  def processList(xs: List[(Char,Int)]): List[(Char,Int)] = {
   // println(xs)
    if(xs.length >=3) {
      xs match {
        case ('0',_)::('0',_)::('1',_)::as => processList(as)
        case _ => xs
      }
    } else xs
  }
}