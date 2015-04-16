package com.real.go.two;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MakeMine13 {
    
    /*
     * Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
     */
   
//   public List<List<String>> partition(String s) {
//        
//    }
    
    
    /*
     * Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
     */
    
//public int minCut(String s) {
//        
//    }
   
    /*
     * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
     */
   
//  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
//        
//    }
  class UndirectedGraphNode {
           int label;
           List<UndirectedGraphNode> neighbors;
           UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
       };  


/*
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
 */

//       public int canCompleteCircuit(int[] gas, int[] cost) {
//           
//       }
       
    /*
     * There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?   
     */

//       public int candy(int[] ratings) {
//           
//       }
       
 /*
  * Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?      
  */
   
//       public int singleNumber(int[] A) {
//           
//       }
 
       /*
        * Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
        */
       
 
//       public int singleNumber(int[] A) {
//           
//       }
       
       
      /*
       * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list. 
       */
//       public RandomListNode copyRandomList(RandomListNode head) {
//           
//       } 
       
       class RandomListNode {
                int label;
                RandomListNode next, random;
                RandomListNode(int x) { this.label = x; }
            };
            
            
   /*
    * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".         
    */
 
//            public boolean wordBreak(String s, Set<String> dict) {
//                
//            }         
            
 /*
  * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].           
  */

//            public List<String> wordBreak(String s, Set<String> dict) {
//                
//            }        
            
}
