package a.koronkiewicz;

import a.koronkiewicz.services.MenuService;

public class Main {
	public static void main(String[] args) {

		MenuService menuService = new MenuService();
		menuService.showMainMenu();
	}
}
