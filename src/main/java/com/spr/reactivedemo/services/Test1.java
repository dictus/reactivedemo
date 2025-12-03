/*
package com.spr.reactivedemo.services;

public class Test1 {

    public ListNode reverseList(ListNode head) {


        if(head ==null)
            return ;

            while(head.next!=null){
                ListNode temp = head.next;
                current.next = prev;          // Reverse
                prev = current;               // Move prev
                current = nextNode;

            }

    }

    public static void main(String[] args) {
        Test1 list = new Test1();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode reversedHead = list.reverseList(head);
        while (reversedHead != null) {
            System.out.print(reversedHead.val + " ");
            reversedHead = reversedHead.next;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
*/
