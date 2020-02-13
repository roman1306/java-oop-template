package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] result = new Author[authors.length + 1];
            System.arraycopy(authors, 0, result, 0, authors.length);
            result[result.length - 1] = author;
            authors = result;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        Author result = null;

        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                result = author;
            }
        }
        return result;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            Author[] result = new Author[authors.length - 1];
            int i = 0;
            for (Author value : authors) {
                if (!value.equals(author)) {
                    result[i] = value;
                    i++;
                }
            }
            authors = result;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}
