package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Article data transfer object
 * Class to access and control article
 */
@Setter
@Getter
public class ArticleDto implements Comparable<ArticleDto>{

    private Long id;

    @NotEmpty(message = "Title cant be empty")
    private String title;

    @NotNull(message = "Price cant be empty")
    @Min(value = 0,message = "Price cant be Negative")
    private Float price;

    @NotNull(message = "Quantity cant be empty")
    @Min(value = 0, message = "Quantity cant be Negative")
    private Integer quantity;

    private Boolean isActive = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleDto)) return false;
        ArticleDto that = (ArticleDto) o;
        return getTitle().equals(that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    @Override
    public int compareTo(ArticleDto o) {
        return o.getPrice().compareTo(this.getPrice());
    }
}
