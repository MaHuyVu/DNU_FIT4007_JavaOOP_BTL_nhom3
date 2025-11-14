package service;

import model.MenuItem;
import util.CsvUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MenuService {

    private List<MenuItem> menuItems = new ArrayList<>();

    public void loadMenu(String filePath) {
        try {
            this.menuItems = CsvUtil.readMenu(filePath);
            System.out.println("Tải menu thành công (" + menuItems.size() + " món).");
        } catch (Exception e) {
            System.out.println("Lỗi khi tải menu: " + e.getMessage());
        }
    }

    public void showMenu() {
        System.out.println("\n========= DANH SÁCH MÓN ĂN =========");

        if (menuItems.isEmpty()) {
            System.out.println("Menu rỗng!");
            return;
        }

        for (MenuItem item : menuItems) {
            System.out.println(item);
        }
    }


    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }


    public boolean updateMenuItem(String id, String name, double price, double discount) {
        for (MenuItem m : menuItems) {
            if (m.getId().equals(id)) {
                m.setName(name);
                m.setPrice(price);
                m.setDiscount(discount);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMenuItem(String id) {
        return menuItems.removeIf(m -> m.getId().equals(id));
    }


    public List<MenuItem> sortByPrice(boolean ascending) {
        List<MenuItem> sorted = new ArrayList<>(menuItems);

        sorted.sort(ascending ?
                Comparator.comparingDouble(MenuItem::getPrice) :
                Comparator.comparingDouble(MenuItem::getPrice).reversed()
        );

        return sorted;
    }


    public List<MenuItem> sortByDiscount(boolean ascending) {
        List<MenuItem> sorted = new ArrayList<>(menuItems);

        sorted.sort(ascending ?
                Comparator.comparingDouble(MenuItem::getDiscount) :
                Comparator.comparingDouble(MenuItem::getDiscount).reversed()
        );

        return sorted;
    }


    public MenuItem findById(String itemId) {
        for (MenuItem m : menuItems) {
            if (m.getId().equals(itemId)) {
                return m;
            }
        }
        return null;
    }


    public List<MenuItem> getAll() {
        return menuItems;
    }


    public List<MenuItem> searchByPriceRange(double min, double max) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem m : menuItems) {
            if (m.getPrice() >= min && m.getPrice() <= max) {
                result.add(m);
            }
        }
        return result;
    }

    public List<MenuItem> searchByMinDiscount(double discount) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem m : menuItems) {
            if (m.getDiscount() >= discount) {
                result.add(m);
            }
        }
        return result;
    }

    public List<MenuItem> searchByKeywordAndType(String keyword, String type) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem m : menuItems) {
            if (m.getName().toLowerCase().contains(keyword.toLowerCase())
                    && m.getType().equalsIgnoreCase(type)) {
                result.add(m);
            }
        }
        return result;
    }

    public List<MenuItem> searchByName(String keyword) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem m : menuItems) {
            if (m.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(m);
            }
        }
        return result;
    }

    // TÌM THEO LOẠI (FOOD / DRINK)
    public List<MenuItem> searchByType(String type) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem m : menuItems) {
            if (m.getType().equalsIgnoreCase(type)) {
                result.add(m);
            }
        }
        return result;

    }
}
