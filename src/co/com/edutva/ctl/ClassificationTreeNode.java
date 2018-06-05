package co.com.edutva.ctl;

import java.util.LinkedList;

import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.TreeNode;

import co.com.edutva.bd.Classification;

public class ClassificationTreeNode extends DefaultTreeNode<Classification>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7589308529660066563L;

	public ClassificationTreeNode(Classification classification) {
		super(classification, new LinkedList<TreeNode<Classification>>());
	}
	
//	public ClassificationTreeNode(Classification classification, TreeNode<Classification>[] children) {
//		super(classification, children);
//	}
 
    public ClassificationTreeNode(Classification data, boolean nullAsMax) {
		super(data, nullAsMax);
	}

	public boolean isLeaf() {
        return getData() != null && getData().getClassifications()!=null && getData().getClassifications().isEmpty();
    }

}
