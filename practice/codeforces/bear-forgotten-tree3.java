import java.io.*;
import java.util.*;

class Main {
	static class State {
		int srcNode;
		int destNode;
		int depth;

		int resetSrcNode;
		int resetMaxDepth;

		State(int srcNode, int destNode, int depth) {
			this.srcNode = srcNode;
			this.destNode = destNode;
			this.depth = depth;
		}
		void resetDepth() {
			this.depth = 0;
			this.srcNode = resetSrcNode;
		}
		void nextNode() {
			this.srcNode = destNode;
			this.destNode++;
			this.depth++;
		}
		void printNodes() {
			System.out.println(this.srcNode + " " + this.destNode + " [" + this.depth + "]");
		}
		void setResetOptions(int resetSrcNode, int resetMaxDepth) {
			this.resetSrcNode = resetSrcNode;
			this.resetMaxDepth = resetMaxDepth;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test = 0;
		while (sc.hasNext()) {
			int nodes = sc.nextInt();
			int maxDepth = sc.nextInt();
			int maxDepthFromFirstNode = sc.nextInt();
			System.out.printf("[%d] Nodes=%d,maxDepth=%d,maxDepthFromFirstNode=%d\n", test++, nodes, maxDepth, maxDepthFromFirstNode);

			boolean maxDepthLimitNotExceed = maxDepth <= 2 * maxDepthFromFirstNode;
			boolean edgeCaseCondition = ! (maxDepth == maxDepthFromFirstNode && maxDepth == 1 && nodes > 2);
			if (maxDepthLimitNotExceed && edgeCaseCondition) {
				State state = new State(1, 2, 0);
				while (state.depth < maxDepthFromFirstNode) {
					System.out.println("\tDepth=" + state.depth + ",maxDepthFromFirstNode=" + maxDepthFromFirstNode);
					state.printNodes();
					state.nextNode();
				}

				int correspondingMaxDepth = maxDepth - maxDepthFromFirstNode;
				if (correspondingMaxDepth == 0) {
					if (state.destNode >= nodes) {
						return;
					}
					state.setResetOptions(2, 1);
				} else {
					state.setResetOptions(1, correspondingMaxDepth);
				}

				state.resetDepth();
				while(state.srcNode != nodes) {
					if (state.depth == state.resetMaxDepth) {
						state.resetDepth();
					}
					state.printNodes();
					state.nextNode();
				}
			} else {
				System.out.println(-1);
			}
			System.out.println();
		}
	}
}