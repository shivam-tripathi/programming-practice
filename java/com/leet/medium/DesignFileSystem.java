package com.leet.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesignFileSystem {
	class FileNode implements Comparable<FileNode> {
		private String path;
		private int value;
		private List<FileNode> children;

		public FileNode() {
			this.children = new ArrayList<>();
		}

		public FileNode(String path, int value) {
			this.path = path;
			this.value = value;
			this.children = new ArrayList<>();
		}

		public FileNode(String path) {
			this.path = path;
		}

		public String getPath() {
			return this.path;
		}

		public int getValue() {
			return this.value;
		}

		public List<FileNode> getChildren() {
			return this.children;
		}

		public void addChild(FileNode child) {
			this.children.add(child);
			Collections.sort(this.getChildren());
		}

		public int compareTo(FileNode that) {
			return this.getPath().compareTo(that.getPath());
		}
	}

	FileNode root = new FileNode();

}
