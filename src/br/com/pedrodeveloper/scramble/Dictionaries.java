package br.com.pedrodeveloper.scramble;

public enum Dictionaries {

	MU_ITEMS("mu-items.txt", "Mu Item Names"),
	MU_MONSTERS("mu-monsters.txt", "Mu Monster Names"),
	MU_NPCS("mu-npcs.txt", "Mu NPC Names"),
	OTHER(null, "Outros");

	private String fileName;
	private String menuDescription;
	
	private Dictionaries(String fileName, String menuDescription) {
		this.fileName = fileName;
		this.menuDescription = menuDescription;
	}

	public String getFileName() {
		return fileName;
	}

	public String getMenuDescription() {
		return menuDescription;
	}
	
}
