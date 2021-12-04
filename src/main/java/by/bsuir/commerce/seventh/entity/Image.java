package by.bsuir.commerce.seventh.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_images")
public class Image{
    private static final String BASE64_PREFIX = "data:image/png;base64,";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    @Lob
    private String base64;

    public Image(){
    }

    public Image(String base64){
        this.base64 = base64;
    }

    public void setBase64(String base64){
        this.base64 = base64;
    }

    public String getBase64(){
        return base64;
    }

    @Override
    public String toString(){
        return BASE64_PREFIX + base64;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        return base64 != null ? base64.equals(image.base64) : image.base64 == null;
    }

    @Override
    public int hashCode() {
        return base64 != null ? base64.hashCode() : 0;
    }
}
