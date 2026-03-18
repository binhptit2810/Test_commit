

import project.business.ProductService;
import project.model.Product;
import java.util.Scanner;

// Lớp giao diện (menu + nhập xuất)
public class ProductView {

    private ProductService service = new ProductService();
    private Scanner sc = new Scanner(System.in);

    // ===== Menu chính =====
    public void menu() {

        int choice;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Them san pham");
            System.out.println("2. Hien thi danh sach");
            System.out.println("3. Tim kiem theo ID");
            System.out.println("4. Cap nhat san pham");
            System.out.println("5. Xoa san pham");
            System.out.println("6. Sap xep theo gia");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    them();
                    break;
                case 2:
                    service.showAll();
                    break;
                case 3:
                    timKiem();
                    break;
                case 4:
                    capNhat();
                    break;
                case 5:
                    xoa();
                    break;
                case 6:
                    service.sortByPrice();
                    break;
                case 0:
                    System.out.println("Tam biet!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }

        } while (choice != 0);
    }

    // ===== Thêm =====
    private void them() {

        System.out.print("Nhap ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nhap ten: ");
        String name = sc.nextLine();

        System.out.print("Nhap gia: ");
        double price = Double.parseDouble(sc.nextLine());

        Product p = new Product(id, name, price);
        service.add(p);
    }

    // ===== Tìm kiếm =====
    private void timKiem() {

        System.out.print("Nhap ID can tim: ");
        int id = Integer.parseInt(sc.nextLine());

        service.findById(id);
    }

    // ===== Cập nhật =====
    private void capNhat() {

        System.out.print("Nhap ID can cap nhat: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nhap ten moi: ");
        String name = sc.nextLine();

        System.out.print("Nhap gia moi: ");
        double price = Double.parseDouble(sc.nextLine());

        service.update(id, name, price);
    }

    // ===== Xóa =====
    private void xoa() {

        System.out.print("Nhap ID can xoa: ");
        int id = Integer.parseInt(sc.nextLine());

        service.delete(id);
    }
}


