package proiect.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "Max size is 50")
    @NotNull
    @Column(name = "title")
    private String title;

    @Size(max = 255, message = "Max size is 50")
    @Column(name = "description")
    private String description;

    @Size(max = 100, message = "Max size is 100")
    @Column(name = "brand")
    private String brand;

    @Size(max = 20, message = "Max size is 20")
    @Column(name = "volume")
    private String volume;

    @NotNull(message = "Price is required")
    @Column(name = "price")
    private Float price;

    @Column(name = "year")
    private Integer year;

    @Size(max = 255, message = "Max size is 255")
    @Column(name = "fragrance_top_notes")
    private String fragranceTopNotes;

    @Size(max = 255, message = "Max size is 255")
    @Column(name = "image_url")
    private String imageURL;

    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "products",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<PerfumeOrder> orders;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductView productView;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getFragranceTopNotes() {
        return fragranceTopNotes;
    }

    public void setFragranceTopNotes(String fragranceTopNotes) {
        this.fragranceTopNotes = fragranceTopNotes;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<PerfumeOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<PerfumeOrder> orders) {
        this.orders = orders;
    }

    public ProductView getProductView() {
        return productView;
    }

    public void setProductView(ProductView productView) {
        this.productView = productView;
    }
}
