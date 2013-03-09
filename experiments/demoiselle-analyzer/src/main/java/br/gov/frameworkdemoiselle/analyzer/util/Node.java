package br.gov.frameworkdemoiselle.analyzer.util;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private final String name;

	private final Class<?> type;

	private final Node parent;

	private final List<Node> children = new ArrayList<Node>();

	private long size;

	protected Node(String name, Class<?> type, Node parent) {
		this.name = name;
		this.type = type;
		this.parent = parent;

		if (this.parent != null) {
			this.parent.addChild(this);
		}
	}

	public Node getParent() {
		return parent;
	}

	public List<Node> getChildren() {
		return children;
	}

	public String getName() {
		return name;
	}

	public long getSize() {
		long result = this.size;

		if (!isEnd()) {
			for (Node child : getChildren()) {
				result += child.getSize();
			}
		}

		return result;
	}

	public int getLevel() {
		int result = 0;

		if (getParent() != null) {
			result = getParent().getLevel() + 1;
		}

		return result;
	}

	Class<?> getType() {
		return type;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void addChild(Node node) {
		children.add(node);
	}

	public boolean isEnd() {
		return this.children.isEmpty() && this.size == 0;
	}
}
