package ru.test.tbook.impl;

import ru.test.tbook.AddressBook;
import ru.test.tbook.domain.Contact;

import java.util.*;

/**
 * A addressBook
 * @author Dmitrii Shakshin <d.shakshin@gmail.com>
 */
public class AddressBookImpl implements AddressBook {

    private SortedMap<String, Contact> namePrefixTree;

    public AddressBookImpl() {
        this.namePrefixTree = new TreeMap<String, Contact>(Comparator.naturalOrder());
    }

    @Override
    public List<Contact> lookup(String namePrefix) {
        return new ArrayList<>(namePrefixTree.subMap(namePrefix, namePrefix + Character.MAX_VALUE).values());
    }

    @Override
    public void add(Contact contact) {
        Objects.requireNonNull(contact, "Contanct should be not null");
        Objects.requireNonNull(contact.getName(), "Contact name should be not null");

        namePrefixTree.put(contact.getName(), contact);
    }
}
