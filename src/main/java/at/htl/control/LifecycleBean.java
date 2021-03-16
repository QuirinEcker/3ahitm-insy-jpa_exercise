package at.htl.control;

import at.htl.enity.*;
import at.htl.repository.*;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.time.LocalDate;

public class LifecycleBean {

//    String name
//    String address
//    String phoneNumber
//    LocalDate openingDate
//    OpeningHours openingHours
//
//    Long id,
//    String firstname,
//    String lastname,
//    String address,
//    LocalDate birthDate,
//    int points

    @Inject
    RestaurantRepository restaurantRepository;

    @Inject
    CustomerRepository customerRepository;

    @Inject
    DishRepository dishRepository;

    @Inject
    DishTranslationRepository dishTranslationRepository;

    @Inject
    LanguageRepository languageRepository;

    public void onStart(@Observes StartupEvent ev) {
        OpeningHours openingHours = new OpeningHours(
                "asd",
                "asdj",
                "asd",
                "sad",
                "asd",
                "apsjdf",
                "asjd"
        );

        Restaurant restaurant = new Restaurant(
                "Implotastische Küche",
                "Nania",
                "404",
                LocalDate.now(),
                openingHours
        );

        restaurant = restaurantRepository.save(restaurant);

        Customer customer = new Customer(
                "Quirin",
                "Ecker",
                "Mühlkreisbahnstraße 5",
                LocalDate.now(),
                100
        );

        customer = customerRepository.save(customer);

        restaurant.getCustomers().add(customer);
        restaurant = restaurantRepository.save(restaurant);

        Language english = new Language(
               "english",
               "en"
        );

        Language german = new Language(
                "german",
                "de"
        );

        english = languageRepository.save(english);
        german = languageRepository.save(german);


        Dish dish1 = new Dish(15.60);
        Dish dish2 = new Dish(2.80);
        Dish dish3 = new Dish(9.99);

        dish1 = dishRepository.save(dish1);
        dish2 = dishRepository.save(dish2);
        dish3 = dishRepository.save(dish3);

        restaurant.getOfferedDishes().add(dish1);
        restaurant.getOfferedDishes().add(dish2);
        restaurant.getOfferedDishes().add(dish3);

        restaurant = restaurantRepository.save(restaurant);

        DishTranslation dishTranslation1_en = new DishTranslation(
                new DishTranslation.DishTranslationId(english, dish1),
                "Meat",
                "It is Meat"
        );

        DishTranslation dishTranslation1_de = new DishTranslation(
                new DishTranslation.DishTranslationId(german, dish1),
                "Fleisch",
                "Es ist Fleisch"
        );

        DishTranslation dishTranslation2_en = new DishTranslation(
                new DishTranslation.DishTranslationId(english, dish2),
                "Salad",
                "It is Salad"
        );

        DishTranslation dishTranslation2_de = new DishTranslation(
                new DishTranslation.DishTranslationId(german, dish2),
                "Salat",
                "Es ist Salat"
        );

        DishTranslation dishTranslation3_en = new DishTranslation(
                new DishTranslation.DishTranslationId(english, dish3),
                "Pizza",
                "It is pizza"
        );

        DishTranslation dishTranslation3_de = new DishTranslation(
                new DishTranslation.DishTranslationId(german, dish3),
                "Pizza",
                "Es ist Pizza"
        );

        dishTranslation1_en = dishTranslationRepository.save(dishTranslation1_en);
        dishTranslation1_de = dishTranslationRepository.save(dishTranslation1_de);
        dishTranslation2_en = dishTranslationRepository.save(dishTranslation2_en);
        dishTranslation2_de = dishTranslationRepository.save(dishTranslation2_de);
        dishTranslation3_en = dishTranslationRepository.save(dishTranslation3_en);
        dishTranslation3_de = dishTranslationRepository.save(dishTranslation3_de);
    }
}
