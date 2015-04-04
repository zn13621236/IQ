package linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class CopyRandomPointer {



    @Test
    public void testTry () {
        
        RandomListNode a= new RandomListNode(-1);

        RandomListNode b= new RandomListNode(-1);
        a.random=b;
        b.random=a;
//        ListNode c= new ListNode(3);
//        ListNode d= new ListNode(4);
        a.next=b;
//        b.next=c;
//        c.next=d;
 //      ListNode e= swapPairs(a);
        RandomListNode d=a;
        System.out.println ("the old one: ");
        while(a!=null){
      System.out.println (a.label);
      System.out.println (a);
      System.out.println (a.random.label);
      System.out.println (a.random);
      a=a.next;
      
      String s="asdf";
      s=s.replaceAll("\\W","");
      
  }  
        
        RandomListNode c=   copyRandomList(d);
        System.out.println ("the new one: ");
        while(c!=null){
      System.out.println (c.label);
      System.out.println (c);
      System.out.println (c.random.label);
      System.out.println (c.random);
      c=c.next;
      
  }
    }

    
    
    
    
    
     class RandomListNode {
             int label;
             RandomListNode next, random;
             RandomListNode(int x) { this.label = x; }
         };
         
         
         
         public RandomListNode copyRandomList(RandomListNode head) {
             if(head==null) return head;
             if(head.next==null) return new RandomListNode(head.label);
             RandomListNode cur=head;
             
             while(cur!=null){
                 RandomListNode a= new RandomListNode(cur.label);
                 a.next=cur.next;
                 cur.next=a;
                 cur=a.next;
             }
             cur=head;
             while(cur!=null&&cur.next!=null){
                RandomListNode next=cur.next;
               if(next.random==null&&cur.random!=null){
                   next.random=cur.random.next;
               }
               cur=cur.next.next;
             }
             
             RandomListNode result=head.next;
             
             while(result!=null){
                 if(result.next!=null){
                 result.next=result.next.next;}
                 result=result.next;
             }
             return head.next;
         }     
         
         
    // Definition for singly-linked list.
    static class ListNode {

        int      val;
        ListNode next;

        ListNode (int x) {
            val = x;
            next = null;
        }
    }
    
    
    
    
    
    public List<String> transform(String[] strs){
        if(strs==null) return null;
        List<String> aList= new ArrayList<>();
        
        Map<String,List<String>> aMap= new HashMap<> ();
        
        for(String a1:strs){
            char[] c=a1.toCharArray ();
            Arrays.sort (c);
            if(aMap.containsKey (new String(c))){
                aMap.get (new String(c)).add (a1);                
            }else{
                List<String> tempList= new ArrayList<>();
                tempList.add (a1);
                aMap.put (new String(c), tempList);
            }
            
        }
        
        for(String key: aMap.keySet ()){
            if(aMap.get (key).size ()>1){
                aList.addAll (aMap.get (key));                
            }                        
        }
        return aList;
    }
    
    
}
