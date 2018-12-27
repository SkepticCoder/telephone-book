package ru.test.tbook.impl;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.test.tbook.AddressBook;
import ru.test.tbook.domain.Contact;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(RandomBeansExtension.class)
class AddressBookImplTest {

    private static final int SIZE = 15;
    private AddressBook addressBook;

    @Random(size = SIZE, type = Contact.class)
    private List<Contact> contacts;

    @BeforeEach
    void setUp() {
        addressBook = new AddressBookImpl();
        contacts.forEach(addressBook::add);
    }

    @Test
    void lookupByFullName() {
        Contact contact = getRandomContanct();
        List<Contact> result = addressBook.lookup(contact.getName());

        List<Contact> expectedOrder = new ArrayList<>(result);
        assertTrue(isSortedByName(result));
    }


    private Contact getRandomContanct() {
        int pos = new java.util.Random().nextInt(SIZE);
        return contacts.get(pos);
    }

    private boolean isSortedByName(List<Contact> result) {
        return isSorted(result, Comparator.comparing(Contact::getName, Comparator.naturalOrder()));
    }
    public static <T> boolean isSorted(List<T> listOfT, Comparator<T> comparator) {
        T previous = null;
        for (T t : listOfT) {
            if (previous != null && comparator.compare(t, previous) < 0)
                return false;
            previous = t;
        }
        return true;
    }


}