package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] result = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks, 0, result, 0, schoolBooks.length);
        result[result.length - 1] = book;
        schoolBooks = result;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int i = 0;
        SchoolBook[] result = new SchoolBook[schoolBooks.length];

        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                result[i] = schoolBook;
                i++;
            }
        }
        System.arraycopy(result, 0, result = new SchoolBook[i], 0, i);
        return result;
    }

    @Override
    public boolean removeByName(String name) {
        int i = 0;
        SchoolBook[] result = new SchoolBook[schoolBooks.length];

        if (findByName(name).length != 0) {
            for (SchoolBook value : schoolBooks) {
                if (!value.getName().equals(name)) {
                    result[i] = value;
                    i++;
                }
            }
            System.arraycopy(result, 0, schoolBooks = new SchoolBook[i], 0, i);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}