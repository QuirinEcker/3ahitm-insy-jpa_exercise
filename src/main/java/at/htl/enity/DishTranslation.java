package at.htl.enity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class DishTranslation {

    @EmbeddedId
    private DishTranslationId id;

    private String name;
    private String description;

    @Embeddable
    public static class DishTranslationId implements Serializable {

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "language_id")
        private Language language;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "dish_id")
        private Dish dish;

        public DishTranslationId(Language language, Dish dish) {
            this.language = language;
            this.dish = dish;
        }

        public DishTranslationId() {}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DishTranslationId that = (DishTranslationId) o;
            return Objects.equals(language, that.language) && Objects.equals(dish, that.dish);
        }

        @Override
        public int hashCode() {
            return Objects.hash(language, dish);
        }
    }

    public DishTranslation(DishTranslationId id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public DishTranslation() {
    }

    public DishTranslationId getId() {
        return id;
    }

    public void setId(DishTranslationId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
