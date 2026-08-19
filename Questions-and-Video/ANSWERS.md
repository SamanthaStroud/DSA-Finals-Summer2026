1. Binary Search Tree:
    Why does an inorder traversal of a BST return sorted results?
    - because at every node everything thats smaller is in the left subtree and everything bigger is in the right.
      Inorder visits left then the node then right so it always comes out smallest to largest.
    What happens to the tree if you insert values in order (1,2,3,4,5)? How does this affect performance?
    - every new value is bigger than the last, so it always goes right. The tree turns into a straight line
      instead of branching out. Height goes from O(log n) to O(n) so it gets slower.
    What is the difference between average and worst-case time complexity for a BST?
    - noramlly the tree is fairly balanced so height is about O(log n) and operations are O(log n). Worst
      case the tree is not balanced (like the 1-5 example) height becomes O(n) so then operations are O(n) too.
    Where would you place duplicate priority values in your tree?
    - I send them right same as anything greater. Keeps the insert logic simple and duplicates end up next to each other in order.

2. Sorting Algorithm:
   	Explain how your sorting algorithm works step-by-step using a small example
   	- insertion sort [5, 2, 4, 1]:
        - 2 vs 5 → 5 shifts right, 2 goes first → [2, 5, 4, 1]
        - 4 vs 5 → shifts, vs 2 → stops, inserts → [2, 4, 5, 1]
        - 1 vs 5, 4, 2 → all shift, 1 goes first → [1, 2, 4, 5]
      it builds up a sorted section one item at a time moving the bigger items back to make room.
   	What is the time complexity of your algorithm?
   	- O(n²) average/worst case. O
   	- (n) best case if the list is already sorted.
    When would your sorting algorithm perform well?
    - on small lists or lists that are already close to sorted.
    Why is your sorting algorithm ideal or not ideal for very large datasets?
    - not ideal — really slow as the list grows a large dataset would need something like merge sort
      instead.

3. System Desgin:
    Why might you choose to sort data in your application instead of the database?
    - because this requires writing the sorting by hand. In a real app I'd just use ORDER BY in SQL since it's faster.
    What is one advantage of using a BST in this system?
    - Inorder traversal gives you sorted priorities without a separate sort step, and highest/lowest are quick
      lookups instead of scanning every order.
    What is one limitation of your current design?
    - the tree isn't balanced so if orders come in already sorted by priority it turns into a linked list and
      slows downs.


Extra Questuons:
When you used AI to help with your development work.:
 - i used Ai:
    -  to create a check list of everything that i need to do for this sprint
    -  to check BST fix (insert, inorder, and highest/lowest) was correct
    -  to help me with help debuging as i was doing the 3 unit tests.
    -  to help me debug the errors i was getting while coding this sprint
    -  to explain concepts to me that i needed a little more clarity in that i was a little confused with
    -  to check that i have met all the reqiurements for the sprint before submission

VIDEO SENT IN SEPARATELY TO BIG TO ADD TO GITHUB :)