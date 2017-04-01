package br.com.juliomakita.repository;

public interface IGenericDAO<T> {
    public T get(Class<T> cl, Integer id);
    public T save(T object);
    public void update(T object);
    public void delete(T object);

}