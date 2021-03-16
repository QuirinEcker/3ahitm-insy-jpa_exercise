package at.htl.repository;

import at.htl.enity.DishTranslation;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DishTranslationRepository extends Repository<
        DishTranslation,
        DishTranslation.DishTranslationId
> {

}
