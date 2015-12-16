package com.guigarage.util;

import com.canoo.dolphin.client.javafx.BidirectionalConverter;
import com.canoo.dolphin.collections.ListChangeEvent;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class FXWrapper2 {

    public static <T, U> ObservableList<U> wrapList(com.canoo.dolphin.collections.ObservableList<T> dolphinList, BidirectionalConverter<T, U> converter) {
        final ObservableList<U> list = FXCollections.observableArrayList();

        dolphinList.forEach(i -> list.add(converter.convert(i)));

        list.addListener((ListChangeListener<U>) c -> {
            if (listenToFx) {
                listenToDolphin = false;
                while (c.next()) {
                    if (c.wasAdded() || c.wasRemoved() || c.wasReplaced()) {
                        for (U removed : c.getRemoved()) {
                            dolphinList.remove(converter.convertBack(removed));
                        }
                        for (U added : c.getAddedSubList()) {
                            dolphinList.add(list.indexOf(added), converter.convertBack(added));
                        }
                    }
                }
                listenToDolphin = true;
            }
        });

        dolphinList.onChanged(e -> {
            if (listenToDolphin) {
                listenToFx = false;
                for (ListChangeEvent.Change<? extends T> c : e.getChanges()) {
                    if (c.isAdded()) {
                        for (int i = c.getFrom(); i < c.getTo(); i++) {
                            list.add(i, converter.convert(dolphinList.get(i)));
                        }
                    } else if (c.isRemoved()) {
                        final int index = c.getFrom();
                        list.remove(index, index + c.getRemovedElements().size());
                    }
                }
                listenToFx = true;
            }
        });

        return list;
    }


    //TODO: HACK
    private static boolean listenToFx = true;

    //TODO: HACK
    private static boolean listenToDolphin = true;
}
