package com.liveclips.soccer.model;

public class LiveClipsContentListItem {
	private String categoryType;
	private String leftSideImage;
	private String rightSideImage;
	private String entityId;
	private String rowText;
	private boolean showLeftSideDefaultIcon;
	private boolean updateInSharedPreference;
	private boolean isUsersFavourite;
	private boolean isTopicMenu;

	public String getRowText() {
		return rowText;
	}

	public void setRowText(String rowText) {
		this.rowText = rowText;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getLeftSideImage() {
		return leftSideImage;
	}

	public void setLeftSideImage(String leftSideImage) {
		this.leftSideImage = leftSideImage;
	}

	public String getRightSideImage() {
		return rightSideImage;
	}

	public void setRightSideImage(String rightSideImage) {
		this.rightSideImage = rightSideImage;
	}

	public boolean isShowLeftSideDefaultIcon() {
		return showLeftSideDefaultIcon;
	}

	public void setShowLeftSideDefaultIcon(boolean showLeftSideDefaultIcon) {
		this.showLeftSideDefaultIcon = showLeftSideDefaultIcon;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public boolean isUpdateInSharedPreference() {
		return updateInSharedPreference;
	}

	public void setUpdateInSharedPreference(boolean updateInSharedPreference) {
		this.updateInSharedPreference = updateInSharedPreference;
	}

	public boolean isUsersFavourite() {
		return isUsersFavourite;
	}

	public void setUsersFavourite(boolean isUsersFavourite) {
		this.isUsersFavourite = isUsersFavourite;
	}

	public boolean isTopicMenu() {
		return isTopicMenu;
	}

	public void setTopicMenu(boolean isTopicMenu) {
		this.isTopicMenu = isTopicMenu;
	}

}
