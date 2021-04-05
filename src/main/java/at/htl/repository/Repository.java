package at.htl.repository;

import at.htl.enity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.persistence.Id;
import javax.transaction.Transactional;

public class Repository<Entity, T> implements PanacheRepositoryBase<Entity, T> {

    @Transactional
    public Entity save(Entity entity) {
        return getEntityManager().merge(entity);
    }

    @Transactional
    public boolean removeById(T id) {
        return deleteById(id);
    }

    @Transactional
    public boolean create(T id, Entity entity) {
        if (findById(id) != null) {
            System.out.println("is not persistent");
            save(entity);
            return true;
        } else return false;
    }
}
