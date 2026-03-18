
import java.util.*;

public class ProductService implements IProductService {

    // Danh sách lưu sản phẩm
    private List<Product> list = new ArrayList<>();

    // Stack lưu sản phẩm đã xóa (để undo)
    private Stack<Product> stackDeleted = new Stack<>();

    // Queue lưu sản phẩm chờ xử lý
    private Queue<Product> queue = new LinkedList<>();

    private Scanner sc = new Scanner(System.in);

    // ================== THÊM ==================
    @Override
    public void addProduct() {

        System.out.print("Nhap ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nhap Name: ");
        String name = sc.nextLine();

        System.out.print("Nhap Price: ");
        double price = Double.parseDouble(sc.nextLine());

        Product p = new Product(id, name, price);

        list.add(p);

        System.out.println("Them thanh cong!");
    }

    // ================== HIỂN THỊ ==================
    @Override
    public void displayAll() {

        if (list.isEmpty()) {
            System.out.println("Danh sach rong!");
            return;
        }

        for (Product p : list) {
            System.out.println(p);
        }
    }

    // ================== XÓA ==================
    @Override
    public void deleteProduct() {

        System.out.print("Nhap ID can xoa: ");
        int id = Integer.parseInt(sc.nextLine());

        Iterator<Product> iterator = list.iterator();

        while (iterator.hasNext()) {
            Product p = iterator.next();

            if (p.getId() == id) {
                stackDeleted.push(p); // Lưu vào stack để undo
                iterator.remove();
                System.out.println("Da xoa!");
                return;
            }
        }

        System.out.println("Khong tim thay!");
    }

    // ================== UNDO ==================
    @Override
    public void undoDelete() {

        if (stackDeleted.isEmpty()) {
            System.out.println("Khong co gi de undo!");
            return;
        }

        Product p = stackDeleted.pop();
        list.add(p);

        System.out.println("Undo thanh cong!");
    }

    // ================== TÌM KIẾM ==================
    @Override
    public void searchByName() {

        System.out.print("Nhap ten can tim: ");
        String keyword = sc.nextLine();

        boolean found = false;

        for (Product p : list) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(p);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay!");
        }
    }

    // ================== SẮP XẾP ==================
    @Override
    public void sortByPrice() {

        Collections.sort(list, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });

        System.out.println("Da sap xep tang dan theo gia!");
    }

    // ================== QUEUE ==================
    @Override
    public void addToQueue() {

        System.out.print("Nhap ID san pham dua vao hang doi: ");
        int id = Integer.parseInt(sc.nextLine());

        for (Product p : list) {
            if (p.getId() == id) {
                queue.offer(p); // Thêm vào queue
                System.out.println("Da dua vao hang doi!");
                return;
            }
        }

        System.out.println("Khong tim thay!");
    }

    @Override
    public void processQueue() {

        if (queue.isEmpty()) {
            System.out.println("Hang doi rong!");
            return;
        }

        Product p = queue.poll(); // Lấy ra và xóa khỏi queue
        System.out.println("Dang xu ly: " + p);
    }
}