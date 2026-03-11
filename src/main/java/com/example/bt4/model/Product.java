package com.example.bt4.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private int id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @Min(value = 1, message = "Giá sản phẩm không được để trống")
    @Max(value = 9999999, message = "Giá sản phẩm không được lớn hơn 9999999")
    private int price;

    @Size(max = 200, message = "Tên hình ảnh không được quá 200 ký tự")
    private String image;

    private Category category;
}
