/**
 * 
 */
package ancestor.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PAULA5
 * 
 */
public class MyFindCommonAncestor implements FindCommonAncestor {

	/**
	 * Method to trigger the call - starting point
	 * @param args
	 */
	public static void main(String[] args) {
		String[] commitHashes = { "G", "F", "E", "D", "C", "B", "A" };
		String[][] parentHashes = { { "F", "D" }, { "E" }, { "B" }, { "C" },
				{ "B" }, { "A" }, null };
		String commitHash1 = "D";
		String commitHash2 = "F";
		MyFindCommonAncestor m = new MyFindCommonAncestor();
		m.findCommmonAncestor(commitHashes, parentHashes, commitHash1,
				commitHash2);
	}

	/**
	 * Method to find all the ancesstors of the given element
	 * @param child
	 * @param commitHashes
	 * @param parentHashes
	 * @return
	 */
	private List<String> findALLAncesstors(String child, String[] commitHashes,
			String[][] parentHashes) {
		int childPosition = 0;
		String rootElement = null;

		List<String> ancesotrList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		sb.append(child);
		ancesotrList.add(child);
		for (int i = 0; i < commitHashes.length;) {
			System.out.println("i--> " + i + " child--> " + child + " SB--> "
					+ sb.toString());
			boolean root = true;
			if (child.equals(commitHashes[i])
					&& (childPosition != commitHashes.length - 1)) {
				childPosition = i;
				System.out.println("Child Position--> " + childPosition);
				String[] localArray = parentHashes[childPosition];
				if (localArray != null) {
					root = false;
					if (localArray.length > 1) {

					} else {
						child = localArray[0];
					}
				}
				if (root) {
					rootElement = child;
					System.out.println("Root Element--> " + rootElement);
					break;
				} else {
					sb.append(",");
					sb.append(child);
					ancesotrList.add(child);
				}
				i = 0;
			} else {
				i++;
			}
		}
		return removeRoot(ancesotrList, rootElement);
	}

	/**
	 * Once the list of all ancestors are found, the root element is removed from the list
	 * @param ancesotrList
	 * @param rootElement
	 * @return
	 */
	private List<String> removeRoot(List<String> ancesotrList,
			String rootElement) {
		ancesotrList.remove(rootElement);
		return ancesotrList;
	}

	@Override
	public String findCommmonAncestor(String[] commitHashes,
			String[][] parentHashes, String commitHash1, String commitHash2) {
		// TODO Auto-generated method stub
		String child = commitHash1;
		List<String> ancesotrList1 = findALLAncesstors(child, commitHashes,
				parentHashes);
		child = commitHash2;
		List<String> ancesotrList2 = findALLAncesstors(child, commitHashes,
				parentHashes);
		System.out.println(ancesotrList1.toString());
		System.out.println(ancesotrList2.toString());

		List<String> commonList = new ArrayList<String>(ancesotrList1);
		commonList.retainAll(ancesotrList2);

		System.out.println("Final List--> " + commonList.toString());
		return commonList.toString();
	}
}
