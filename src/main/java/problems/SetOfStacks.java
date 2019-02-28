/**
 * Distributed Stacks
 *
 * - SetOfStacks has
 *   - upper bound (limit)
 *   - list of stacks (stacks)
 *   - active stack (activeStackIndex = 0)
 *   - active peek (activeStackLength = 0)
 *
 *   Approach:
 *   - push: if value (limit > activeStackLength) redistribute
 *           push value on to stack of activeStackIndex and increment
 * 		 	activeStackLength
 *  - pop: if activeStackLength == 1 && activeStackIndex > 0 then
 *           					 stacks.delete(stacks.length - 1)
 *							 set activeStackLength = limit
 *	     pop stacks[activeStackIndex] and decrement activeStackLength
 *
 */

import java.util.ArrayList;
import java.util.Stack;

public class SetOfStacks {
	ArrayList<Stack<Integer>> stacks;
	int limit;
	int activeStackIndex;
	int activeStackLength;

	public SetOfStacks(int limit) {
		this.limit = limit;
    stacks = new ArrayList<>();
    assert stacks.isEmpty() == true : "Should be empty";
    stacks.add(new Stack<Integer>());
		activeStackIndex = 0;
		activeStackLength = 0;
	}
	private void redistribute() {
		// create a new stack in stacks
		// push value onto new stack
		// set activeStackLength to 0
		Stack<Integer> stack = new Stack<>();
		stacks.add(stack);
		activeStackLength = 0;
		activeStackIndex++;
	}

	private void shrink() {
		stacks.remove(stacks.size() - 1);
		activeStackIndex--;
    activeStackLength = limit;
	}

	public int activeStackLength() {
		return activeStackLength;
	}

	public int activeStackIndex() {
		return activeStackIndex;
  }

	public Integer pop() {
		if (activeStackLength == 0 && activeStackIndex == 0) {
			throw new RuntimeException("Cannot pop() from empty");
		}
		Stack<Integer> activeStack = stacks.get(activeStackIndex);
		Integer val = activeStack.pop();
    activeStackLength--;
		if (activeStack.empty() && activeStackLength == 0) shrink();
    return val;
	}

	public void push(Integer val) {
		if (limit <= activeStackLength) redistribute();
		stacks.get(activeStackIndex).push(val);
		activeStackLength++;
	}

  //TODO: redistribute if needed
	public Integer popAt(int index) {
		return stacks.get(index).pop(); // breaks invariant contract
	}

	// test client
	public static void main(String[] args) {
		int limit = 3;
    SetOfStacks setStacks = new SetOfStacks(limit);
    setStacks.push(Integer.valueOf(3));
    setStacks.push(Integer.valueOf(4));
    setStacks.push(Integer.valueOf(4));
    setStacks.push(Integer.valueOf(1));
    setStacks.push(Integer.valueOf(2));
    setStacks.push(Integer.valueOf(5));
    // 5 -> 2 -> 1 -> 4 -> 4 -> 3

    assert setStacks.activeStackIndex() == 1 : "Wrong index";
    assert setStacks.activeStackLength() == 3 : "Wrong length (" + setStacks.activeStackLength() + ")";

    Integer popped = setStacks.pop();
    // 2 -> 1 -> 4 -> 4 -> 3
    assert popped == Integer.valueOf(5) : "Wrong item popped";
    assert setStacks.activeStackLength() == 2 : "Wrong length";
    setStacks.pop(); // 1 -> 4 -> 4 -> 3
    setStacks.pop(); // 4 -> 4 -> 3
    assert setStacks.activeStackIndex() == 0 : "Wrong index";
    assert setStacks.activeStackLength() == 3 : "Wrong length (" + setStacks.activeStackLength() + ")";
  }
}

