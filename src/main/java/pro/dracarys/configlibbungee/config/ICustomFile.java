package pro.dracarys.configlibbungee.config;

public interface ICustomFile<T> {

    T init();

    String getName();

}