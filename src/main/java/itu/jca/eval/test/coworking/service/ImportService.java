package itu.jca.eval.test.coworking.service;

public interface ImportService<T> {
    abstract public T loadFromLine(String line,String separator);
    default T loadFromLine(String line) {
        return loadFromLine(line, ",");
    }
}
