package com.example.bt4.service;

import com.example.bt4.model.Category;
import com.example.bt4.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private List<Product> listProduct = new ArrayList<>();

    @Autowired
    public ProductService(CategoryService categoryService) {
        Category dienthoai = categoryService.get(1); // Điện thoại
        Category laptop    = categoryService.get(2); // Laptop

        // 10 điện thoại – giá trong khoảng 1 – 9,999,999
        Object[][] phones = {
            {"Samsung Galaxy S24",   9500000, "https://images.samsung.com/is/image/samsung/p6pim/global/sm-s921bzadeue/gallery/global-galaxy-s24-s921-sm-s921bzadeue-536859640.jpg"},
            {"iPhone 15 Pro",        9900000, "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-pro-finish-select-202309-6-1inch-bluetitanium?wid=150&hei=120&fmt=jpeg"},
            {"Xiaomi 14",            7800000, "https://i01.appmifile.com/webfile/globalimg/products/pc/xiaomi14/specs_img.png"},
            {"OPPO Find X7",         8200000, "https://image.oppo.com/content/dam/oppo/common/mkt/en-of/find-x7/find-x7-kv.jpg.thumb.webp"},
            {"Vivo X100",            7500000, "https://www.vivo.com/content/dam/vivo/pk/products/x-series/x100/img/x100-img.png"},
            {"Google Pixel 8",       8900000, "https://lh3.googleusercontent.com/Sj8m9QbAMHijPTzSVo5eaFh0f3sNMJw3QU0HhH4u9qyaXJSnblnRomJPrdFHlHI5W8g=w150-h120"},
            {"OnePlus 12",           7900000, "https://oasis.opstatics.com/content/dam/oasis/en/phones/12/key-visual/12-floa.png"},
            {"Realme GT 5",          5500000, "https://image.realme.com/content/dam/realme/ph/phones/realme-gt5/specs.png"},
            {"Nokia G60",            3200000, "https://www.nokia.com/sites/default/files/styles/scale_720_no_upscale/public/2022-09/nokia-g60-5g-polar-night-front.png?itok=oJf3vhNf"},
            {"Sony Xperia 1 V",      9800000, "https://www.sony.com/en/articles/xperia-1-v/img/xperia1v_overview_kv_pc.jpg"},
        };

        for (int i = 0; i < phones.length; i++) {
            Product p = new Product();
            p.setId(i + 1);
            p.setName((String) phones[i][0]);
            p.setPrice((int) phones[i][1]);
            p.setImage((String) phones[i][2]);
            p.setCategory(dienthoai);
            listProduct.add(p);
        }

        // 10 laptop – giá trong khoảng 1 – 9,999,999
        Object[][] laptops = {
            {"Dell XPS 15",                 9500000, "https://i.dell.com/is/image/DellContent/content/dam/ss2/product-images/dell-client-products/notebooks/xps-notebooks/xps-15-9530/pdp/notebook-xps-9530-blue-pdp-504x350.png"},
            {"MacBook Pro 14",              9900000, "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/mbp14-spacegray-select-202301?wid=150&hei=120&fmt=jpeg"},
            {"Asus ROG Zephyrus G14",       9200000, "https://dlcdnwebimgs.asus.com/gain/9dcd66c5-5fac-4c8e-b5a7-e8c96bc30c4e/w150/h120"},
            {"HP Spectre x360",             8800000, "https://ssl-product-images.www8-hp.com/digmedialib/prodimg/knivel/c08000000/c08095000/c08095600/c08095695/subpc.png"},
            {"Lenovo ThinkPad X1 Carbon",   9000000, "https://p3-ofp.static.pub/fes/cms/2022/09/09/pnbp3qszuclk5r3mfggaqphhf2y2z4850384.png"},
            {"Acer Swift X",                7200000, "https://static.acer.com/up/Resource/Acer/Laptops/Swift_X/Images/20210830/SFX14-41G-NX.AU5SI.001-1.png"},
            {"MSI Raider GE78",             9700000, "https://asset.msi.com/resize/image/global/product/product_1693389025f1a45d0d42b17ee9e9e3de1440c975f9.png62405b38c58fe0f07fcef2367d8a9ba1/1024.png"},
            {"LG Gram 16",                  8500000, "https://www.lg.com/levant/images/laptops/md07514831/gallery/D_1.jpg"},
            {"Surface Laptop 5",            8200000, "https://img-prod-cms-rt-microsoft-com.akamaized.net/cms/api/am/imageFileData/RE58ibg?ver=4ecb&q=90&m=6&h=120&w=150"},
            {"Gigabyte Aero 16",            9300000, "https://static.gigabyte.com/StaticFile/Image/Global/5ee52b9e2b2c0f5da6fbbaa0e3ff4a35/Product/27700/Png/FullHD"},
        };

        for (int i = 0; i < laptops.length; i++) {
            Product p = new Product();
            p.setId(11 + i);
            p.setName((String) laptops[i][0]);
            p.setPrice((int) laptops[i][1]);
            p.setImage((String) laptops[i][2]);
            p.setCategory(laptop);
            listProduct.add(p);
        }
    }

    public List<Product> getAll() {
        return listProduct;
    }

    public void add(Product newProduct) {
        int maxId = listProduct.stream().mapToInt(Product::getId).max().orElse(0);
        newProduct.setId(maxId + 1);
        listProduct.add(newProduct);
    }

    public Product get(int id) {
        return listProduct.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void update(Product editProduct) {
        Product find = get(editProduct.getId());
        if (find != null) {
            find.setPrice(editProduct.getPrice());
            find.setName(editProduct.getName());
            find.setCategory(editProduct.getCategory());
            if (editProduct.getImage() != null) {
                find.setImage(editProduct.getImage());
            }
        }
    }

    public void delete(int id) {
        listProduct.removeIf(p -> p.getId() == id);
    }

    public List<Product> search(String keyword, Integer categoryId) {
        return listProduct.stream()
                .filter(p -> (keyword == null || keyword.isBlank()
                        || p.getName().toLowerCase().contains(keyword.toLowerCase())))
                .filter(p -> (categoryId == null || categoryId == 0
                        || (p.getCategory() != null && p.getCategory().getId() == categoryId)))
                .collect(java.util.stream.Collectors.toList());
    }

    public void updateImage(Product newProduct, MultipartFile imageProduct) {
        String contentType = imageProduct.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return;
        }
        if (!imageProduct.isEmpty()) {
            try {
                Path dirImages = Paths.get("static/images");
                if (!Files.exists(dirImages)) {
                    Files.createDirectories(dirImages);
                }
                String newFilename = UUID.randomUUID() + "_" + imageProduct.getOriginalFilename();
                Path pathFileUpload = dirImages.resolve(newFilename);
                Files.copy(imageProduct.getInputStream(), pathFileUpload, StandardCopyOption.REPLACE_EXISTING);
                newProduct.setImage(newFilename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
